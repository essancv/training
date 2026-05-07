from dataclasses import dataclass
from typing import List


@dataclass
class PolicyViolation:
    """
    Represents a violation detected by a rule.
    """
    rule_name: str
    message: str
    severity: str  # BLOCKER | WARNING


@dataclass
class PolicyResult:
    """
    Aggregated result of all rules.
    """
    violations: List[PolicyViolation]

    def has_blockers(self) -> bool:
        return any(v.severity == "BLOCKER" for v in self.violations)