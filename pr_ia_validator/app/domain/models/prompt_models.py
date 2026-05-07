from dataclasses import dataclass


@dataclass
class PromptInput:
    """
    Input required to build a prompt for a single analysis unit.
    """
    file_name: str
    code_diff: str
    test_diff: str
    has_test: bool
    mode: str  # batch | per_file


@dataclass
class Prompt:
    """
    Final prompt sent to LLM.
    """
    content: str