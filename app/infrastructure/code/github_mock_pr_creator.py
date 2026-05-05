# infrastructure/code/github_mock_pr_creator.py

"""
Mock PR Creator

Crea automáticamente una PR en GitHub para pruebas.
"""

import requests
import base64
import time


class GitHubMockPRCreator:

    def __init__(self, token: str, repo: str):
        self.token = token
        self.repo = repo
        self.base_url = "https://api.github.com"

    def create_test_pr(self) -> int:
        """
        Flujo completo:
        - crear rama
        - crear commit
        - crear PR
        """

        branch_name = f"ai-test-{int(time.time())}"

        base_sha = self._get_main_branch_sha()

        print(f"🌿 Creando rama: {branch_name}")
        self._create_branch(branch_name, base_sha)

        print("📝 Creando commit...")
        self._create_file(branch_name)

        print("🔀 Creando PR...")
        pr_number = self._create_pr(branch_name)

        return pr_number

    # --------------------------------------------------

    def _headers(self):
        return {
            "Authorization": f"Bearer {self.token}",
            "Accept": "application/vnd.github+json"
        }

    # --------------------------------------------------

    def _get_main_branch_sha(self):

        url = f"{self.base_url}/repos/{self.repo}/git/ref/heads/main"

        res = requests.get(url, headers=self._headers())

        if res.status_code != 200:
            raise Exception(f"Error obteniendo SHA: {res.text}")

        return res.json()["object"]["sha"]

    # --------------------------------------------------

    def _create_branch(self, branch_name, sha):

        url = f"{self.base_url}/repos/{self.repo}/git/refs"

        payload = {
            "ref": f"refs/heads/{branch_name}",
            "sha": sha
        }

        res = requests.post(url, headers=self._headers(), json=payload)

        if res.status_code not in [200, 201]:
            raise Exception(f"Error creando rama: {res.text}")

    # --------------------------------------------------

    def _create_file(self, branch_name):

        url = f"{self.base_url}/repos/{self.repo}/contents/ai_test_file.txt"

        content = f"Test generado por IA {time.time()}"

        encoded = base64.b64encode(content.encode()).decode()

        payload = {
            "message": "AI test commit",
            "content": encoded,
            "branch": branch_name
        }

        res = requests.put(url, headers=self._headers(), json=payload)

        if res.status_code not in [200, 201]:
            raise Exception(f"Error creando archivo: {res.text}")

    # --------------------------------------------------

    def _create_pr(self, branch_name) -> int:

        url = f"{self.base_url}/repos/{self.repo}/pulls"

        payload = {
            "title": "AI Test PR",
            "head": branch_name,
            "base": "main",
            "body": "PR creada automáticamente para testing IA"
        }

        res = requests.post(url, headers=self._headers(), json=payload)

        if res.status_code not in [200, 201]:
            raise Exception(f"Error creando PR: {res.text}")

        return res.json()["number"]