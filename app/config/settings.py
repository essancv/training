# config/settings.py

import os

# LLM
USE_MOCK = False
PROJECT_TYPE = "COMPLEX"

# GitHub

GITHUB_TOKEN = os.getenv("GITHUB_TOKEN")
GITHUB_PR_NUMBER = 1
GITHUB_REPO = "essancv/training"
# config/settings.py



LLM_PROVIDER = "ollama"  # ollama | gemini | mock

OLLAMA_MODEL = "llama3.1"
OLLAMA_HOST = "http://localhost:11434"
