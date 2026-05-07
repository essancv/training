import requests
from domain.interfaces.scm_interface import SCMInterface
from domain.models.diff_models import FileDiff


class GitHubSCMClient(SCMInterface):

    def __init__(self, token, repo):
        self.token = token
        self.repo = repo

    def get_pr_files(self, pr_id: int):

        url = f"https://api.github.com/repos/{self.repo}/pulls/{pr_id}/files"

        r = requests.get(url, headers={
            "Authorization": f"Bearer {self.token}"
        })

        files = []

        for f in r.json():

            files.append(
                FileDiff(
                    filename=f["filename"],
                    patch=f.get("patch", ""),
                    status=f["status"]
                )
            )

        return files