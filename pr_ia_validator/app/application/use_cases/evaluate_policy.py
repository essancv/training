from domain.services.policy_engine import PolicyEngine


class EvaluatePolicy:

    def __init__(self, engine: PolicyEngine):
        self.engine = engine

    def execute(self, diff_context):

        result = self.engine.evaluate(diff_context)

        return {
            "violations": result.violations,
            "blocked": result.has_blockers()
        }