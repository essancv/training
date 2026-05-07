from domain.models.repository_config import (
    RepositoryConfig
)


class RepositoryResolver:
    """
    Resolves repository configuration.

    STRATEGY:
    1. Explicit repository config
    2. Fallback detection by file extensions
    """

    EXTENSION_MAP = {
        ".py": "python",
        ".java": "java",
        ".ts": "typescript"
    }

    def __init__(self, repository_repository):
        self.repository_repository = repository_repository

    def resolve(
        self,
        repository: str,
        changed_files: list[str]
    ) -> RepositoryConfig:
        """
        Resolves repository configuration.
        """

        configured = (
            self.repository_repository
            .find_by_repository(repository)
        )

        if configured:
            return configured

        technology = self._detect_technology(
            changed_files
        )

        return RepositoryConfig(
            repository=repository,
            team="default",
            technology=technology
        )

    def _detect_technology(
        self,
        files: list[str]
    ) -> str:
        """
        Detects dominant technology from extensions.
        """

        for file in files:

            for extension, technology in (
                self.EXTENSION_MAP.items()
            ):
                if file.endswith(extension):
                    return technology

        return "generic"