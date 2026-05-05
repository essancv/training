# infrastructure/code/github_client.py

"""
GitHub Code Provider

Obtiene el diff real de una Pull Request desde GitHub.
"""

import requests


class GitHubClient:
    """
    Cliente para obtener diffs de PR desde GitHub.
    """

    def __init__(self, token: str):
        self.token = token
        self.base_url = "https://api.github.com"

    def get_pr_diff(self, repo: str, pr_number: int) -> str:
        """
        Obtiene el diff completo de una PR.

        :param repo: "owner/repo"
        :param pr_number: número de PR
        """

        url = f"{self.base_url}/repos/{repo}/pulls/{pr_number}"

        headers = {
            "Authorization": f"Bearer {self.token}",
            "Accept": "application/vnd.github.v3.diff"
        }

        response = requests.get(url, headers=headers)

        if response.status_code == 404:
            raise Exception("PR no encontrada")

        if response.status_code == 401:
            raise Exception("Token inválido o sin permisos")

        if response.status_code != 200:
            raise Exception(f"Error GitHub: {response.text}")

        diff = response.text

        if not diff.strip():
            raise Exception("Diff vacío")

        # 🔒 Protección básica: limitar tamaño
        max_chars = 15000
        if len(diff) > max_chars:
            print("⚠️ Diff truncado por tamaño")
            diff = diff[:max_chars]

        return diff