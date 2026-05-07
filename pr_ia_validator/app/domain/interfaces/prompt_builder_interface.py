from abc import ABC, abstractmethod
from typing import List
from domain.models.prompt_models import PromptInput, Prompt


class PromptBuilderInterface(ABC):
    """
    Contract for building prompts.
    """

    @abstractmethod
    def build(self, inputs: List[PromptInput]) -> Prompt:
        pass