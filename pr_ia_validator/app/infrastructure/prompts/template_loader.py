import os


class TemplateLoader:
    """
    Loads prompt templates by technology and team.
    """

    def __init__(self, tech: str, team: str):
        self.tech = tech
        self.team = team

    def load(self) -> str:

        base_path = f"app/infrastructure/prompts/{self.tech}"

        team_path = f"{base_path}/{self.team}.txt"
        default_path = f"{base_path}/default.txt"

        if os.path.exists(team_path):
            path = team_path
        else:
            path = default_path

        with open(path, "r", encoding="utf-8") as f:
            return f.read()