import yaml

from domain.models.repository_config import (
    RepositoryConfig
)


class YAMLRepositoryConfigRepository:
    """
    YAML implementation for repository config storage.
    """

    def __init__(self, config_file: str):
        self.config_file = config_file

    def find_by_repository(
        self,
        repository: str
    ):
        """
        Finds repository configuration.
        """

        with open(
            self.config_file,
            "r",
            encoding="utf-8"
        ) as file:

            data = yaml.safe_load(file)

        repositories = data.get(
            "repositories",
            {}
        )

        config = repositories.get(repository)

        if not config:
            return None

        return RepositoryConfig(
            repository=repository,
            team=config["team"],
            technology=config["technology"]
        )