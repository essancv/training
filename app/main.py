# app/main.py

from domain.models import ReviewRequest
from application.review_service import ReviewService

from infrastructure.parser.response_parser import ResponseParser
from infrastructure.validator.validator import validate_ai_json

from infrastructure.llm.gemini_client import GeminiClient
from infrastructure.llm.mock_client import MockLLMClient

from infrastructure.code.github_client import GitHubClient
from infrastructure.llm.llm_factory import create_llm_client

from config.settings import (
    USE_MOCK,
    PROJECT_TYPE,
    GITHUB_TOKEN,
    GITHUB_REPO,
    GITHUB_PR_NUMBER,
    LLM_PROVIDER,
    OLLAMA_MODEL,
    OLLAMA_HOST
)


from infrastructure.code.github_mock_pr_creator import GitHubMockPRCreator
from infrastructure.code.github_pre_creator import GitHubPreCreator

USE_TEST_PR = False

from infrastructure.code.github_pr_publisher import GitHubPRPublisher
from infrastructure.code.pr_comment_formatter import format_comment


def main():

    print("🚀 AI Code Review (GitHub POC)")

    if USE_TEST_PR:
        print("🧪 Creando PR de test...")
        creator = GitHubMockPRCreator(GITHUB_TOKEN, GITHUB_REPO)
        pr_number = creator.create_test_pr()
    else:
        creator = GitHubPreCreator(
            token=GITHUB_TOKEN,
            repo=GITHUB_REPO,
            base_branch="pr_ia_validator"
        )
        pr_number = creator.create_pr()
    # --------------------------------------------------
    # 1. Obtener diff real desde GitHub
    # --------------------------------------------------
    github = GitHubClient(GITHUB_TOKEN)

    print("📥 Obteniendo diff de GitHub...")
    diff = github.get_pr_diff(GITHUB_REPO, pr_number)

    print(f"📊 Tamaño diff: {len(diff)} caracteres")

    # --------------------------------------------------
    # 2. Selección LLM
    # --------------------------------------------------
    if USE_MOCK:
        llm = MockLLMClient()
    elif LLM_PROVIDER == "ollama":
        llm = create_llm_client(
            LLM_PROVIDER,
            {
                "model": OLLAMA_MODEL,
                "host": OLLAMA_HOST
            }
        )       
    elif LLM_PROVIDER == "gemini":
        llm = create_llm_client(
            LLM_PROVIDER,
            {
                "model": GEMINI_MODEL,
                "host": GEMINI_HOST
            }
        )
    else:
        raise Exception(f"Unknown LLM provider: {LLM_PROVIDER}")
    # --------------------------------------------------
    # 3. Dependencias
    # --------------------------------------------------
    parser = ResponseParser()

    class ValidatorWrapper:
        def validate_ai_json(self, data):
            return validate_ai_json(data)

    validator = ValidatorWrapper()

    # --------------------------------------------------
    # 4. Servicio
    # --------------------------------------------------
    service = ReviewService(llm, parser, validator)

    request = ReviewRequest(
        diff=diff,
        project_type=PROJECT_TYPE
    )

    # --------------------------------------------------
    # 5. Ejecutar análisis
    # --------------------------------------------------
    result = service.execute(request)

    print("\n✅ RESULTADO RAW:")
    print(result.raw_text)

    print("\n📦 JSON PARSEADO:")
    print(result.parsed_json)

    # --------------------------------------------------
    # 6. Publicar comentario en PR
    # --------------------------------------------------

    publisher = GitHubPRPublisher(GITHUB_TOKEN)

    comment = format_comment(result.parsed_json)

    print("\n📢 Publicando comentario en PR...")
    publisher.publish_comment(
        GITHUB_REPO,
        pr_number,
        comment
    )

if __name__ == "__main__":
    main()