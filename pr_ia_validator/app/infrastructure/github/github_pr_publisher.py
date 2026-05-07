import requests

from domain.interfaces.publisher_interface import (
    PublisherInterface
)


class GitHubPRPublisher(PublisherInterface):
    """
    Publishes markdown review comments into GitHub PR.
    """

    def __init__(self, token: str, repo: str):

        self.token = token
        self.repo = repo

    def publish(self, pr_id: int, content: str):

        url = (
            f"https://api.github.com/repos/"
            f"{self.repo}/issues/{pr_id}/comments"
        )

        response = requests.post(
            url,
            headers={
                "Authorization": f"Bearer {self.token}",
                "Accept": "application/vnd.github+json"
            },
            json={
                "body": content
            }
        )

        if response.status_code >= 300:
            raise Exception(
                f"GitHub publish error: {response.text}"
            )