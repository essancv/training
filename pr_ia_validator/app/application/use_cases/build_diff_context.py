from typing import List
from domain.interfaces.scm_interface import SCMInterface
from domain.models.diff_models import DiffContext, DiffGroup
from domain.services.diff_policy import DiffPolicy


class BuildDiffContext:

    def __init__(self, scm, policy, test_mapper):
        self.scm = scm
        self.policy = policy
        self.test_mapper = test_mapper

    def execute(self, pr_id):

        files = self.scm.get_pr_files(pr_id)

        py_files = [f for f in files if f.filename.endswith(".py")]

        total_lines = sum(len(f.patch.splitlines()) for f in py_files)

        mode = self.policy.decide_mode(total_lines)

        # 🔹 Indexar ficheros por nombre (clave para eficiencia)
        file_index = {f.filename: f for f in py_files}

        groups = []

        for f in py_files:

            # evitar tratar tests como source
            if f.filename.startswith("test_"):
                continue

            expected_test = self.test_mapper.map(f.filename)

            test_file = file_index.get(expected_test)

            groups.append(
                DiffGroup(
                    file=f,
                    expected_test_file=expected_test,
                    test_file=test_file
                )
            )

        return DiffContext(
            groups=groups,
            total_lines=total_lines,
            mode=mode
        )

class BuildDiffContext_v0:

    def __init__(self, scm: SCMInterface, policy: DiffPolicy, test_mapper):
        self.scm = scm
        self.policy = policy
        self.test_mapper = test_mapper

    def execute(self, pr_id: int) -> DiffContext:

        files = self.scm.get_pr_files(pr_id)

        relevant_files = self._filter_py(files)

        total_lines = self._calculate_lines(relevant_files)

        mode = self.policy.decide_mode(total_lines)

        groups = []

        for f in relevant_files:

            test_file = self.test_mapper.map(f.filename)

            groups.append(
                DiffGroup(
                    file=f,
                    test_file=test_file
                )
            )

        return DiffContext(
            groups=groups,
            total_lines=total_lines,
            mode=mode
        )

    def _filter_py(self, files):
        return [f for f in files if f.filename.endswith(".py")]

    def _calculate_lines(self, files):
        return sum(len(f.patch.splitlines()) for f in files)