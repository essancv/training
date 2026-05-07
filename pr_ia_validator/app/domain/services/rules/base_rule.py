from abc import ABC, abstractmethod
from typing import List
from domain.models.policy_models import PolicyViolation


class BaseRule(ABC):
    """
    Base class for all policy rules.
    """

    @abstractmethod
    def evaluate(self, diff_context) -> List[PolicyViolation]:
        pass