import sys
import os

# ==========================================================
# PYTHON PATH CONFIGURATION
# ==========================================================
# Allows execution from project root:
#
#   python app/main.py
#
# Without requiring package installation.
# ==========================================================

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

# ==========================================================
# CONFIGURATION
# ==========================================================

from config.settings import Settings

# ==========================================================
# BLOCK 3 - DIFF CONTEXT
# ==========================================================

from application.use_cases.build_diff_context import BuildDiffContext

from domain.services.diff_policy import DiffPolicy
from domain.services.test_mapper import TestMapper

from infrastructure.scm.github_scm_client import GitHubSCMClient

# ==========================================================
# BLOCK 4 - POLICY ENGINE
# ==========================================================

from application.use_cases.evaluate_policy import EvaluatePolicy

from domain.services.policy_engine import PolicyEngine

from domain.services.rules.test_rule import TestPresenceRule
from domain.services.rules.naming_rule import TestNamingRule

# ==========================================================
# BLOCK 5 - PROMPT ENGINE
# ==========================================================

from application.services.prompt_builder_service import (
    PromptBuilderService
)

from infrastructure.prompts.template_loader import TemplateLoader

# ==========================================================
# BLOCK 6 - LLM ORCHESTRATION
# ==========================================================

from application.use_cases.review_pull_request import (
    ReviewPullRequest
)

from infrastructure.llm.ollama_client import OllamaClient




def main():
    """
    Main entry point for the PR AI Validator POC.

    Architecture:
    - Clean Architecture
    - SOLID principles
    - DRY principles

    Flow:
    1. Build diff context from GitHub PR
    2. Apply deterministic policies
    3. Build prompt
    4. Execute LLM review
    5. Print result

    Notes:
    - Policy blockers stop the pipeline before LLM execution
    - Supports batch/per_file execution
    - LLM provider is interchangeable
    """

    # ==========================================================
    # VALIDATE REQUIRED CONFIGURATION
    # ==========================================================

    if not Settings.GITHUB_TOKEN:
        raise ValueError("GITHUB_TOKEN is required")

    if not Settings.GITHUB_REPO:
        raise ValueError("GITHUB_REPO is required")

    # ==========================================================
    # BLOCK 3 - SCM + DIFF ENGINE
    # ==========================================================

    scm_client = GitHubSCMClient(
        token=Settings.GITHUB_TOKEN,
        repo=Settings.GITHUB_REPO
    )

    diff_policy = DiffPolicy(
        threshold=Settings.DIFF_THRESHOLD
    )

    test_mapper = TestMapper(
        pattern=Settings.TEST_PATTERN,
        prefix=Settings.TEST_PREFIX,
        suffix=Settings.TEST_SUFFIX
    )

    build_diff_context = BuildDiffContext(
        scm=scm_client,
        policy=diff_policy,
        test_mapper=test_mapper
    )

    # ==========================================================
    # BLOCK 4 - POLICY ENGINE
    # ==========================================================

    rules = []

    # ----------------------------------------------------------
    # Mandatory test validation
    # ----------------------------------------------------------

    if Settings.ENFORCE_TESTS:
        rules.append(TestPresenceRule())

    # ----------------------------------------------------------
    # Test naming convention validation
    # ----------------------------------------------------------

    rules.append(
        TestNamingRule(
            prefix=Settings.TEST_NAMING_PREFIX
        )
    )

    policy_engine = PolicyEngine(rules)

    evaluate_policy = EvaluatePolicy(
        engine=policy_engine
    )

    # ==========================================================
    # BLOCK 5 - PROMPT ENGINE
    # ==========================================================

    template_loader = TemplateLoader(
        tech=Settings.PROJECT_TECHNOLOGY,
        team=Settings.PROJECT_TEAM
    )

    prompt_builder = PromptBuilderService(
        template_loader=template_loader
    )

    # ==========================================================
    # BLOCK 6 - LLM CLIENT
    # ==========================================================

    llm_client = OllamaClient(
        model=Settings.OLLAMA_MODEL
    )

    # ==========================================================
    # MAIN ORCHESTRATION USE CASE
    # ==========================================================

    review_use_case = ReviewPullRequest(
        diff_builder=build_diff_context,
        policy_evaluator=evaluate_policy,
        prompt_builder=prompt_builder,
        llm=llm_client
    )

    # ==========================================================
    # EXECUTION
    # ==========================================================

    print("\n======================================")
    print("PR AI VALIDATOR")
    print("======================================")

    print(f"Repository : {Settings.GITHUB_REPO}")
    print(f"PR ID      : {Settings.DEFAULT_PR_ID}")

    print("\nStarting review...\n")

    result = review_use_case.execute(
        Settings.DEFAULT_PR_ID
    )

    # ==========================================================
    # OUTPUT
    # ==========================================================

    print("\n======================================")
    print("REVIEW RESULT")
    print("======================================")

    print(f"\nBLOCKED: {result.blocked}")

    # ----------------------------------------------------------
    # Policy violations
    # ----------------------------------------------------------

    if result.policy_violations:

        print("\nPolicy violations:\n")

        for violation in result.policy_violations:
            print(f"- {violation}")

    # ----------------------------------------------------------
    # LLM analysis
    # ----------------------------------------------------------

    print("\n======================================")
    print("LLM ANALYSIS")
    print("======================================\n")

    print(result.llm_output)

    print("\n======================================")
    print("END")
    print("======================================\n")


if __name__ == "__main__":
    main()