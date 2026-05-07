from dataclasses import dataclass


@dataclass(frozen=True)
class RepositoryConfig:
    """
    Repository configuration resolved dynamically.

    RESPONSIBILITY:
    - Represent runtime configuration
    - Immutable domain model
    """

    repository: str
    team: str
    technology: str