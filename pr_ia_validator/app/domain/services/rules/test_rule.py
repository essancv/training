from typing import List
from domain.services.rules.base_rule import BaseRule
from domain.models.policy_models import PolicyViolation


class TestPresenceRule(BaseRule):
    """
    Every modified or new file must have a test.
    """

    def evaluate(self, diff_context) -> List[PolicyViolation]:

        violations = []

        for group in diff_context.groups:

            if group.file.status in ["added", "modified"]:

                if not group.test_file:
                    violations.append(
                        PolicyViolation(
                            rule_name="TestPresenceRule",
                            message=f"{group.file.filename} has no test file",
                            severity="BLOCKER"
                        )
                    )

        return violations