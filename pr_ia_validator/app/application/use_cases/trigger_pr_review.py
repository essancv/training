class TriggerPRReview:
    """
    Entry use case triggered by external systems (GitHub webhook).

    RESPONSIBILITY:
    - Validate event
    - Extract PR id
    - Trigger full review pipeline

    CLEAN:
    - No HTTP logic
    - No GitHub logic
    """

    def __init__(self, review_use_case, publisher_use_case):
        self.review_use_case = review_use_case
        self.publisher_use_case = publisher_use_case

    def execute(self, pr_id: int):
        """
        Executes full PR analysis pipeline.
        """

        # ==========================================
        # 1. RUN REVIEW (LLM + POLICY)
        # ==========================================

        result = self.review_use_case.execute(pr_id)

        # ==========================================
        # 2. PUBLISH RESULT
        # ==========================================

        self.publisher_use_case.execute(
            pr_id=pr_id,
            raw_llm_output=result.llm_output
        )

        return result