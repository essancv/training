import re


class TestMapper:
    """
    Maps source files to test files using configurable rules.
    """

    def __init__(self, pattern: str, prefix: str, suffix: str):
        self.pattern = pattern
        self.prefix = prefix
        self.suffix = suffix

    def map(self, filename: str):

        match = re.match(self.pattern, filename)

        if not match:
            return None

        base = match.group(1)

        return f"{self.prefix}{base}{self.suffix}"