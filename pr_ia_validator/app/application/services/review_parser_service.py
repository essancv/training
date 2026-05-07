from domain.interfaces.parser_interface import ParserInterface
from domain.models.review_issue_models import ParsedReview


class ReviewParserService:
    """
    Coordinates parsing process.

    Keeps orchestration outside infrastructure.
    """

    def __init__(self, parser: ParserInterface):
        self.parser = parser

    def execute(self, raw_response: str) -> ParsedReview:
        return self.parser.parse(raw_response)