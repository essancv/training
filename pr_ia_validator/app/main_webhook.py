import sys
import os

# ==========================================================
# PYTHON PATH CONFIGURATION
# ==========================================================
# Permite ejecutar el proyecto desde raíz sin instalación
# del paquete como librería.
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

from application.use_cases.build_diff_context import BuildDiffContext
from infrastructure.scm.github_scm_client import GitHubSCMClient
from domain.services.diff_policy import DiffPolicy
from domain.services.test_mapper import TestMapper

# ==========================================================
# BLOCK 4 - POLICY ENGINE
# ==========================================================

from application.use_cases.evaluate_policy import EvaluatePolicy
from domain.services.policy_engine import PolicyEngine
from domain.services.rules.naming_rule import TestNamingRule

# ==========================================================
# BLOCK 5 - PROMPT ENGINE
# ==========================================================

from application.services.prompt_builder_service import PromptBuilderService
from infrastructure.prompts.template_loader import TemplateLoader

# ==========================================================
# BLOCK 6 - LLM (OLLAMA)
# ==========================================================

from application.use_cases.review_pull_request import ReviewPullRequest
from infrastructure.llm.ollama_client import OllamaClient

# ==========================================================
# BLOCK 7 - PARSER + FORMATTER + PUBLISHER
# ==========================================================

from application.use_cases.publish_review_result import PublishReviewResult
from application.services.review_parser_service import ReviewParserService
from application.services.review_formatter_service import ReviewFormatterService
from infrastructure.parser.json_review_parser import JSONReviewParser
from infrastructure.github.github_pr_publisher import GitHubPRPublisher

# ==========================================================
# BLOCK 8 - WEBHOOK / HTTP SERVER
# ==========================================================

from application.use_cases.trigger_pr_review import TriggerPRReview
from infrastructure.web.fastapi_app import create_app

import uvicorn


# ==========================================================
# MAIN BOOTSTRAP
# ==========================================================

def main():
    """
    ENTRYPOINT DEL SISTEMA

    RESPONSABILIDAD:
    - Construir dependencias (DI manual)
    - Inicializar use cases
    - Levantar servidor FastAPI (webhook GitHub)

    CLEAN ARCHITECTURE:
    - main = composition root
    - no lógica de negocio aquí
    """

    # ======================================================
    # VALIDACIÓN CONFIG
    # ======================================================

    if not Settings.GITHUB_TOKEN:
        raise ValueError("Missing GITHUB_TOKEN")

    if not Settings.GITHUB_REPO:
        raise ValueError("Missing GITHUB_REPO")

    # ======================================================
    # BLOCK 3 - SCM + DIFF ENGINE
    # ======================================================

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

    diff_builder = BuildDiffContext(
        scm=scm_client,
        policy=diff_policy,
        test_mapper=test_mapper
    )

    # ======================================================
    # BLOCK 4 - POLICY ENGINE
    # ======================================================

    rules = [
        TestNamingRule(prefix=Settings.TEST_NAMING_PREFIX)
    ]

    policy_engine = PolicyEngine(rules)

    policy_evaluator = EvaluatePolicy(policy_engine)

    # ======================================================
    # BLOCK 5 - PROMPT BUILDER
    # ======================================================

    template_loader = TemplateLoader(
        tech=Settings.PROJECT_TECHNOLOGY,
        team=Settings.PROJECT_TEAM
    )

    prompt_builder = PromptBuilderService(template_loader)

    # ======================================================
    # BLOCK 6 - LLM (OLLAMA)
    # ======================================================

    llm_client = OllamaClient(
        model=Settings.OLLAMA_MODEL
    )

    review_use_case = ReviewPullRequest(
        diff_builder=diff_builder,
        policy_evaluator=policy_evaluator,
        prompt_builder=prompt_builder,
        llm=llm_client
    )

    # ======================================================
    # BLOCK 7 - PARSER + FORMATTER + PUBLISHER
    # ======================================================

    parser = JSONReviewParser()

    parser_service = ReviewParserService(parser)

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
    # BLOCK 8 - WEBHOOK TRIGGER USE CASE
    # ======================================================

    trigger_use_case = TriggerPRReview(
        review_use_case=review_use_case,
        publisher_use_case=publish_review
    )

    # ======================================================
    # FASTAPI APP CREATION
    # ======================================================

    app = create_app(trigger_use_case)

    # ======================================================
    # START SERVER
    # ======================================================

    print("\n======================================")
    print("PR AI VALIDATOR - WEBHOOK SERVER")
    print("======================================")
    print(f"Repo: {Settings.GITHUB_REPO}")
    print("Listening on: http://0.0.0.0:8000/webhook/github")
    print("======================================\n")

    uvicorn.run(
        app,
        host="0.0.0.0",
        port=8000
    )


# ==========================================================
# ENTRYPOINT
# ==========================================================

if __name__ == "__main__":
    main()