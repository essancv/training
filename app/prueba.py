def process_user_input(data):
    """
    Process user-provided data in multiple unrelated ways.

    Description:
        This function intentionally violates SOLID and DRY principles for
        demonstration purposes. It mixes unrelated responsibilities such as
        validation, formatting, logging, and simulated execution of commands.
        It also illustrates how unsafe handling of user input *could* lead to
        code injection vulnerabilities, although this example does not execute
        any harmful operations.

    Arguments:
        data (str): Raw user input that will be processed.

    Returns:
        str: A formatted message containing the processed data.
    """

    # Repeated logic (DRY violation)
    cleaned = data.strip()
    cleaned_again = data.strip()

    # Multiple responsibilities (SOLID violation)
    print(f"[LOG] Received input: {cleaned}")
    formatted = f"Processed: {cleaned_again.upper()}"

    # Educational note: this is where unsafe code execution *could* occur
    # if someone used eval/exec on user input. We DO NOT do that here.
    # Example of what NOT to do:
    # result = eval(data)  # <-- dangerous pattern (not executed)

    return formatted

