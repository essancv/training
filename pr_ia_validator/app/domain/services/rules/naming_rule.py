from typing import List
from domain.services.rules.base_rule import BaseRule
from domain.models.policy_models import PolicyViolation


class TestNamingRule(BaseRule):
    """
    Ensures test naming convention.
    """

    def __init__(self, prefix: str):
        self.prefix = prefix

    def evaluate(self, diff_context) -> List[PolicyViolation]:

        violations = []

        for group in diff_context.groups:

            if group.test_file and not group.test_file.startswith(self.prefix):

                violations.append(
                    PolicyViolation(
                        rule_name="TestNamingRule",
                        message=f"{group.test_file} does not follow naming convention",
                        severity="WARNING"
                    )
                )

        return violations