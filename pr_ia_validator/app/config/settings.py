import os


class Settings:
    """
    Centralized configuration (DRY principle).

    Reads from environment variables with sensible defaults.
    """

    # ==============================
    # GITHUB CONFIG
    # ==============================
#    GITHUB_TOKEN: str = os.getenv("GITHUB_TOKEN", "")
#    GITHUB_REPO: str = os.getenv("GITHUB_REPO", "")  # owner/repo

    GITHUB_TOKEN: str =  os.getenv("GITHUB_TOKEN")

    GITHUB_REPO: str = "essancv/training"
    GITHUB_BASE_URL: str = os.getenv(
        "GITHUB_BASE_URL",
        "https://api.github.com"
    )

    # ==============================
    # DIFF CONFIG (Bloque 3)
    # ==============================
    DIFF_THRESHOLD: int = int(os.getenv("DIFF_THRESHOLD", "2000"))

    # ==============================
    # TEST MAPPING CONFIG
    # ==============================
    TEST_PATTERN: str = os.getenv(
        "TEST_PATTERN",
        r"(.*)\.py"
    )

    TEST_PREFIX: str = os.getenv(
        "TEST_PREFIX",
        "test_"
    )

    TEST_SUFFIX: str = os.getenv(
        "TEST_SUFFIX",
        ".py"
    )

    # ==============================
    # APP CONFIG
    # ==============================
    DEFAULT_PR_ID: int = int(os.getenv("PR_ID", "1"))


    # ==========================================================
    # POLICY ENGINE CONFIG
    # ==========================================================

    # Enable/disable mandatory tests validation
    ENFORCE_TESTS: bool = os.getenv(
        "ENFORCE_TESTS",
        "true"
    ).lower() == "false"

    # Naming convention for test files
    TEST_NAMING_PREFIX: str = os.getenv(
        "TEST_NAMING_PREFIX",
        "test_"
    )

    # ==========================================================
    # PROMPT ENGINE CONFIG
    # ==========================================================

    # Technology used by the project
    # Examples:
    # - python
    # - java
    PROJECT_TECHNOLOGY: str = os.getenv(
        "PROJECT_TECHNOLOGY",
        "python"
    )

    # Team-specific prompt customization
    # Examples:
    # - default
    # - team_a
    # - payments_team
    PROJECT_TEAM: str = os.getenv(
        "PROJECT_TEAM",
        "default"
    )


    # ==========================================================
    # OLLAMA CONFIG
    # ==========================================================

    OLLAMA_MODEL: str = os.getenv(
        "OLLAMA_MODEL",
        "llama3.1"
    )

    OLLAMA_BASE_URL: str = os.getenv(
        "OLLAMA_BASE_URL",
        "http://localhost:11434"
    )

    # ==========================================================
    # Dynamic resolution of team and technologie
    # ==========================================================

    REPOSITORY_CONFIG_FILE = (
    "app/infrastructure/configuration/repositories.yaml"
)