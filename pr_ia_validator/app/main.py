import os
import sys

# ==========================================================
# PYTHON PATH CONFIGURATION
# ==========================================================
# Allows execution from project root:
#
#   python app/main.py
#
# ==========================================================

sys.path.append(
    os.path.dirname(os.path.abspath(__file__))
)

# ==========================================================
# CONFIGURATION
# ==========================================================

from config.settings import Settings

# ==========================================================
# BLOCK 3 - DIFF ENGINE
# ==========================================================

from application.use_cases.build_diff_context import (
    BuildDiffContext
)

from domain.services.diff_policy import DiffPolicy
from domain.services.test_mapper import TestMapper

from infrastructure.scm.github_scm_client import (
    GitHubSCMClient
)

# ==========================================================
# BLOCK 4 - POLICY ENGINE
# ==========================================================

from application.use_cases.evaluate_policy import (
    EvaluatePolicy
)

from domain.services.policy_engine import PolicyEngine

from domain.services.rules.test_rule import (
    TestPresenceRule
)

from domain.services.rules.naming_rule import (
    TestNamingRule
)

# ==========================================================
# BLOCK 5 - PROMPT ENGINE
# ==========================================================

from application.services.prompt_builder_service import (
    PromptBuilderService
)

from infrastructure.prompts.template_loader import (
    TemplateLoader
)

# ==========================================================
# BLOCK 6 - LLM ORCHESTRATION
# ==========================================================

from application.use_cases.review_pull_request import (
    ReviewPullRequest
)

from infrastructure.llm.ollama_client import (
    OllamaClient
)

# ==========================================================
# BLOCK 7 - OUTPUT PARSER + PUBLISHER
# ==========================================================

from application.use_cases.publish_review_result import (
    PublishReviewResult
)

from application.services.review_parser_service import (
    ReviewParserService
)

from application.services.review_formatter_service import (
    ReviewFormatterService
)

from infrastructure.parser.json_review_parser import (
    JSONReviewParser
)

from infrastructure.github.github_pr_publisher import (
    GitHubPRPublisher
)


def main():
    """
    Main entry point for the AI PR Validator.

    =========================================================
    SYSTEM FLOW
    =========================================================

    1. Retrieve PR diffs from GitHub
    2. Build diff context
    3. Detect related test files
    4. Apply deterministic policies
    5. Build prompts
    6. Execute LLM review
    7. Parse structured JSON output
    8. Format markdown review
    9. Publish comment into GitHub PR

    =========================================================
    ARCHITECTURE
    =========================================================

    - Clean Architecture
    - SOLID principles
    - DRY principles
    - LLM provider interchangeable
    - SCM provider interchangeable
    """

    # ======================================================
    # VALIDATE CONFIGURATION
    # ======================================================

    if not Settings.GITHUB_TOKEN:
        raise ValueError(
            "GITHUB_TOKEN is required"
        )

    if not Settings.GITHUB_REPO:
        raise ValueError(
            "GITHUB_REPO is required"
        )

    # ======================================================
    # BLOCK 3 - SCM + DIFF ENGINE
    # ======================================================

    print("\n======================================")
    print("INITIALIZING DIFF ENGINE")
    print("======================================")

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

    # ======================================================
    # BLOCK 4 - POLICY ENGINE
    # ======================================================

    print("\n======================================")
    print("INITIALIZING POLICY ENGINE")
    print("======================================")

    rules = []

    # ------------------------------------------------------
    # Mandatory unit tests rule
    # ------------------------------------------------------

    if Settings.ENFORCE_TESTS:

        rules.append(
            TestPresenceRule()
        )

    # ------------------------------------------------------
    # Test naming validation
    # ------------------------------------------------------

    rules.append(
        TestNamingRule(
            prefix=Settings.TEST_NAMING_PREFIX
        )
    )

    policy_engine = PolicyEngine(rules)

    evaluate_policy = EvaluatePolicy(
        engine=policy_engine
    )

    # ======================================================
    # BLOCK 5 - PROMPT ENGINE
    # ======================================================

    print("\n======================================")
    print("INITIALIZING PROMPT ENGINE")
    print("======================================")

    template_loader = TemplateLoader(
        tech=Settings.PROJECT_TECHNOLOGY,
        team=Settings.PROJECT_TEAM
    )

    prompt_builder = PromptBuilderService(
        template_loader=template_loader
    )

    # ======================================================
    # BLOCK 6 - LLM CLIENT
    # ======================================================

    print("\n======================================")
    print("INITIALIZING LLM CLIENT")
    print("======================================")

    llm_client = OllamaClient(
        model=Settings.OLLAMA_MODEL
    )

    review_use_case = ReviewPullRequest(
        diff_builder=build_diff_context,
        policy_evaluator=evaluate_policy,
        prompt_builder=prompt_builder,
        llm=llm_client
    )

    # ======================================================
    # BLOCK 7 - PARSER + FORMATTER + PUBLISHER
    # ======================================================

    print("\n======================================")
    print("INITIALIZING REVIEW PUBLISHER")
    print("======================================")

    parser = JSONReviewParser()

    parser_service = ReviewParserService(
        parser=parser
    )

    formatter_service = ReviewFormatterService()

    publisher = GitHubPRPublisher(
        token=Settings.GITHUB_TOKEN,
        repo=Settings.GITHUB_REPO
    )

    publish_review = PublishReviewResult(
        parser_service=parser_service,
        formatter_service=formatter_service,
        publisher=publisher
    )

    # ======================================================
    # EXECUTION
    # ======================================================

    print("\n======================================")
    print("STARTING PR REVIEW")
    print("======================================")

    print(f"\nRepository : {Settings.GITHUB_REPO}")
    print(f"PR ID      : {Settings.DEFAULT_PR_ID}")

    # ======================================================
    # EXECUTE REVIEW
    # ======================================================

    result = review_use_case.execute(
        Settings.DEFAULT_PR_ID
    )

    # ======================================================
    # PRINT RESULT
    # ======================================================

    print("\n======================================")
    print("REVIEW RESULT")
    print("======================================")

    print(f"\nBLOCKED: {result.blocked}")

    # ------------------------------------------------------
    # POLICY VIOLATIONS
    # ------------------------------------------------------

    if result.policy_violations:

        print("\nPolicy violations detected:\n")

        for violation in result.policy_violations:
            print(f"- {violation}")

    # ------------------------------------------------------
    # LLM OUTPUT
    # ------------------------------------------------------

    print("\n======================================")
    print("RAW LLM OUTPUT")
    print("======================================\n")

    print(result.llm_output)

    # ======================================================
    # PUBLISH REVIEW
    # ======================================================

    print("\n======================================")
    print("PUBLISHING REVIEW")
    print("======================================")

    try:

        publish_review.execute(
            pr_id=Settings.DEFAULT_PR_ID,
            raw_llm_output=result.llm_output
        )

        print("\n✅ Review successfully published")

    except Exception as exc:

        print("\n❌ Error publishing review")
        print(str(exc))

    # ======================================================
    # END
    # ======================================================

    print("\n======================================")
    print("END")
    print("======================================\n")


if __name__ == "__main__":
    main()