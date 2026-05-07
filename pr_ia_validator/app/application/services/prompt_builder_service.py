from typing import List
from domain.interfaces.prompt_builder_interface import PromptBuilderInterface
from domain.models.prompt_models import PromptInput, Prompt


class PromptBuilderService(PromptBuilderInterface):
    """
    Builds LLM prompts using templates and structured inputs.
    """

    def __init__(self, template_loader):
        self.template_loader = template_loader

    def build(self, inputs: List[PromptInput]) -> Prompt:
        """
        Build final prompt.

        - Supports batch and per-file modes
        - Injects code + test context
        """

        template = self.template_loader.load()

        sections = []

        for i, item in enumerate(inputs):

            section = self._build_section(item, index=i + 1)
            sections.append(section)

        content = template.format(
            review_sections="\n\n".join(sections)
        )

        return Prompt(content=content)

    def _build_section(self, item: PromptInput, index: int) -> str:
        """
        Builds a structured section for one file.
        """

        test_block = (
            item.test_diff if item.has_test
            else "NO TEST PROVIDED"
        )

        return f"""
### FILE {index}: {item.file_name}

#### CODE DIFF
{item.code_diff}

#### TEST DIFF
{test_block}
"""