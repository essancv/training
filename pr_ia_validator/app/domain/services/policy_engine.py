from typing import List
from domain.models.policy_models import PolicyResult


class PolicyEngine:
    """
    Executes all rules.
    """

    def __init__(self, rules):
        self.rules = rules

    def evaluate(self, diff_context) -> PolicyResult:

        all_violations = []

        for rule in self.rules:
            result = rule.evaluate(diff_context)
            all_violations.extend(result)

        return PolicyResult(violations=all_violations)