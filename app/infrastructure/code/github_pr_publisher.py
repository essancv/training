# infrastructure/code/github_pr_publisher.py

"""
Publica comentarios en Pull Requests de GitHub
"""

import requests


class GitHubPRPublisher:

    def __init__(self, token: str):
        self.token = token
        self.base_url = "https://api.github.com"

    def publish_comment(self, repo: str, pr_number: int, comment: str):
        """
        Publica comentario en PR
        """

        url = f"{self.base_url}/repos/{repo}/issues/{pr_number}/comments"

        headers = {
            "Authorization": f"Bearer {self.token}",
            "Accept": "application/vnd.github+json"
        }

        payload = {
            "body": comment
        }

        response = requests.post(url, headers=headers, json=payload)

        if response.status_code not in [200, 201]:
            raise Exception(f"Error publicando comentario: {response.text}")

        print("💬 Comentario publicado en PR")