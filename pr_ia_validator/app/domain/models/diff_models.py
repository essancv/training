from dataclasses import dataclass
from typing import Optional, List


@dataclass
class FileDiff:
    filename: str
    patch: str
    status: str


@dataclass
class DiffGroup:
    file: FileDiff
    expected_test_file: Optional[str]
    test_file: Optional[FileDiff]  



@dataclass
class DiffContext:
    groups: List[DiffGroup]
    total_lines: int
    mode: str  # batch | per_file