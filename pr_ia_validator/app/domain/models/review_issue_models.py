from dataclasses import dataclass
from typing import List


@dataclass
class ReviewIssue:
    """
    Represents one issue detected during review.
    """

    severity: str
    file: str
    explanation: str
    recommendation: str


@dataclass
class ParsedReview:
    """
    Structured review returned by parser.
    """

    issues: List[ReviewIssue]