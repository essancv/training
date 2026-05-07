class DiffPolicy:

    def __init__(self, threshold: int):
        self.threshold = threshold

    def decide_mode(self, total_lines: int) -> str:
        return "batch" if total_lines < self.threshold else "per_file"