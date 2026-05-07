import json
import re


from domain.interfaces.parser_interface import ParserInterface

from domain.models.review_issue_models import (
    ParsedReview,
    ReviewIssue
)


class JSONReviewParser(ParserInterface):
    """
    Parses strict JSON responses returned by the LLM.

    Advantages over regex:
    - deterministic
    - schema-friendly
    - safer
    - easier to validate
    """

    def parse(self, raw_response: str) -> ParsedReview:

        try:
            cleaned_response = self._clean_response(
                raw_response
            )

            data = json.loads(cleaned_response)

        except json.JSONDecodeError as exc:
            raise Exception(
                f"Invalid JSON returned by LLM: {exc}"
            )

        if "issues" not in data:
            raise Exception(
                "LLM response missing 'issues'"
            )

        issues = []

        for item in data["issues"]:

            issues.append(
                ReviewIssue(
                    severity=item.get("severity", "WARNING"),
                    file=item.get("file", "unknown"),
                    explanation=item.get("explanation", ""),
                    recommendation=item.get("recommendation", "")
                )
            )

        return ParsedReview(issues=issues)

    def _clean_response(self, raw_response: str) -> str:
        """
        Removes markdown wrappers frequently added by LLMs.

        Example:
        ```json
        {...}
        ```
        """

        cleaned = raw_response.strip()

        # Remove opening markdown fence
        cleaned = re.sub(
            r"^```json\s*",
            "",
            cleaned,
            flags=re.IGNORECASE
        )

        # Remove generic opening fence
        cleaned = re.sub(
            r"^```\s*",
            "",
            cleaned
        )

        # Remove closing fence
        cleaned = re.sub(
            r"\s*```$",
            "",
            cleaned
        )

        print ("\n###############  CLEANED LLM REVIEW ####################")
        print (f"{cleaned.strip ()}")
        print ("\n###############  END CLEANED LLM REVIEW ####################")
        return cleaned.strip()
        