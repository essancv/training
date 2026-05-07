from abc import ABC, abstractmethod
from typing import List
from domain.models.diff_models import FileDiff


class SCMInterface(ABC):

    @abstractmethod
    def get_pr_files(self, pr_id: int) -> List[FileDiff]:
        pass