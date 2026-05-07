from typing import List

from domain.interfaces.llm_interface import LLMInterface
from domain.models.review_models import ReviewResult
from application.use_cases.build_diff_context import BuildDiffContext
from application.use_cases.evaluate_policy import EvaluatePolicy
from application.services.prompt_builder_service import PromptBuilderService
from domain.models.prompt_models import PromptInput


class ReviewPullRequest:
    """
    Main orchestration use case.

    Responsibilities:
    - Coordinate all blocks (3, 4, 5, 6)
    - Decide execution flow (batch vs per_file)
    - Keep orchestration logic OUT of domain

    SOLID:
    - SRP: only orchestration
    - DIP: depends on abstractions
    """

    def __init__(
        self,
        diff_builder: BuildDiffContext,
        policy_evaluator: EvaluatePolicy,
        prompt_builder: PromptBuilderService,
        llm: LLMInterface
    ):
        self.diff_builder = diff_builder
        self.policy_evaluator = policy_evaluator
        self.prompt_builder = prompt_builder
        self.llm = llm

    def execute(self, pr_id: int) -> ReviewResult:
        """
        Full PR review pipeline.
        """

        # ==============================
        # 1. BUILD DIFF CONTEXT
        # ==============================
        diff_context = self.diff_builder.execute(pr_id)

        # ==============================
        # 2. APPLY POLICY ENGINE
        # ==============================
        policy_result = self.policy_evaluator.execute(diff_context)

        # Si hay bloqueantes → no llamamos al LLM
        if policy_result["blocked"]:
            return ReviewResult(
                llm_output="Policy violations detected. LLM analysis skipped.",
                blocked=True,
                policy_violations=[
                    v.message for v in policy_result["violations"]
                ]
            )

        # ==============================
        # 3. BUILD PROMPT INPUTS
        # ==============================
        inputs = self._build_inputs(diff_context)

        # ==============================
        # 4. EXECUTE LLM (batch / per_file)
        # ==============================
        if diff_context.mode == "batch":
            llm_output = self._execute_batch(inputs)
        else:
            llm_output = self._execute_per_file(inputs)

        # ==============================
        # 5. RETURN RESULT
        # ==============================
        return ReviewResult(
            llm_output=llm_output,
            blocked=False,
            policy_violations=[]
        )

    # ==========================================================
    # PRIVATE METHODS (SRP + DRY)
    # ==========================================================

    def _build_inputs(self, diff_context) -> List[PromptInput]:
        """
        Converts DiffContext → PromptInput list.
        """

        inputs = []

        for group in diff_context.groups:

            inputs.append(
                PromptInput(
                    file_name=group.file.filename,
                    code_diff=group.file.patch,
                    test_diff=group.test_file.patch if group.test_file else "",
                    has_test=group.test_file is not None,
                    mode=diff_context.mode
                )
            )

        return inputs

    def _execute_batch(self, inputs: List[PromptInput]) -> str:
        """
        Single LLM call for all files.
        """

        prompt = self.prompt_builder.build(inputs)

        return self.llm.generate(prompt.content)

    def _execute_per_file(self, inputs: List[PromptInput]) -> str:
        """
        Multiple LLM calls (one per file).
        Aggregates results.

        DRY:
        - Reuses prompt builder
        """

        outputs = []

        for item in inputs:

            prompt = self.prompt_builder.build([item])

            response = self.llm.generate(prompt.content)

            outputs.append(
                f"### FILE: {item.file_name}\n{response}"
            )

        return "\n\n".join(outputs)