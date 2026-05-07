from dataclasses import dataclass
from typing import List


@dataclass
class ReviewResult:
    """
    Final result of PR review.
    """
    llm_output: str
    blocked: bool
    policy_violations: List[str]