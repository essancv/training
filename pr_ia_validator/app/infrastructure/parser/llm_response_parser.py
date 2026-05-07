import re

from domain.interfaces.parser_interface import ParserInterface
from domain.models.review_issue_models import (
    ParsedReview,
    ReviewIssue
)


class LLMResponseParser(ParserInterface):
    """
    Parses raw LLM responses into structured issues.

    IMPORTANT:
    This parser assumes the prompt forces a deterministic format.
    """

    ISSUE_PATTERN = re.compile(
        r"SEVERITY:\s*(.*?)\n"
        r"FILE:\s*(.*?)\n"
        r"EXPLANATION:\s*(.*?)\n"
        r"RECOMMENDATION:\s*(.*?)(?:\n---|\Z)",
        re.DOTALL
    )

    def parse(self, raw_response: str) -> ParsedReview:

        issues = []

        matches = self.ISSUE_PATTERN.findall(raw_response)

        for match in matches:

            severity, file, explanation, recommendation = match

            issues.append(
                ReviewIssue(
                    severity=severity.strip(),
                    file=file.strip(),
                    explanation=explanation.strip(),
                    recommendation=recommendation.strip()
                )
            )

        return ParsedReview(issues=issues)