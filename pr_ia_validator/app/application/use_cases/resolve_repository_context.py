class ResolveRepositoryContext:
    """
    Resolves repository runtime configuration.
    """

    def __init__(self, resolver):
        self.resolver = resolver

    def execute(
        self,
        repository: str,
        changed_files: list[str]
    ):
        """
        Executes repository resolution.
        """

        return self.resolver.resolve(
            repository=repository,
            changed_files=changed_files
        )