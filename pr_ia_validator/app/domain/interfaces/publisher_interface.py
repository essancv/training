from abc import ABC, abstractmethod


class PublisherInterface(ABC):
    """
    Abstraction for publishing review results.
    """

    @abstractmethod
    def publish(self, pr_id: int, content: str):
        pass