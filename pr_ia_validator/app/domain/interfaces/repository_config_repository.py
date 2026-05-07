from abc import ABC, abstractmethod
from typing import Optional

from domain.models.repository_config import (
    RepositoryConfig
)


class RepositoryConfigRepository(ABC):
    """
    Repository configuration provider abstraction.
    """

    @abstractmethod
    def find_by_repository(
        self,
        repository: str
    ) -> Optional[RepositoryConfig]:
        """
        Returns repository configuration.
        """