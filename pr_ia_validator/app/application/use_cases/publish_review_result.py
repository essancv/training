from application.services.review_parser_service import (
    ReviewParserService
)

from application.services.review_formatter_service import (
    ReviewFormatterService
)


class PublishReviewResult:
    """
    Coordinates:

    - parsing LLM output
    - formatting markdown
    - publishing PR comment

    CLEAN ARCHITECTURE:
    - orchestration belongs to use_cases
    """

    def __init__(
        self,
        parser_service: ReviewParserService,
        formatter_service: ReviewFormatterService,
        publisher,
        validator=None
    ):
        self.parser_service = parser_service
        self.formatter_service = formatter_service
        self.publisher = publisher
        self.validator = validator

    def execute(
        self,
        pr_id: int,
        raw_llm_output: str
    ):
        """
        Full publishing flow.
        """

        # ==========================================
        # 1. PARSE
        # ==========================================

        parsed_review = self.parser_service.execute(
            raw_llm_output
        )

        # ==========================================
        # 2. VALIDATE
        # ==========================================

        if self.validator:
            self.validator.validate(parsed_review)

        # ==========================================
        # 3. FORMAT
        # ==========================================

        markdown = self.formatter_service.format(
            parsed_review
        )

        # ==========================================
        # 4. PUBLISH
        # ==========================================

        self.publisher.publish(
            pr_id=pr_id,
            content=markdown
        )