from abc import ABC, abstractmethod
from domain.models.review_issue_models import ParsedReview


class ParserInterface(ABC):
    """
    Abstraction for LLM response parsers.
    """

    @abstractmethod
    def parse(self, raw_response: str) -> ParsedReview:
        pass