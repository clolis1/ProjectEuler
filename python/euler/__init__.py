"""Project Euler solutions in Python (problems 86 onward; 1-85 are in the Java project)."""

import multiprocessing
import time
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
EXPLANATIONS = ROOT / "explanations"
ANSWERS = ROOT / "answers.csv"
DATA = ROOT / "src" / "main" / "resources" / "data"  # problem input files, shared with the Java solutions


def load_answers() -> dict[int, str]:
    """Reads the shared answers.csv (problem,answer per line; # starts a comment)."""
    answers = {}
    for line in ANSWERS.read_text(encoding="utf-8").splitlines():
        line = line.strip()
        if not line or line.startswith("#"):
            continue
        n, answer = line.split(",", 1)
        answers[int(n)] = answer.strip()
    return answers


def timed_solve(n: int) -> tuple[str, float]:
    """Solves problem n in this process and returns (answer, seconds)."""
    from euler import problems

    start = time.perf_counter()
    answer = problems.solve(n)
    return answer, time.perf_counter() - start


def _worker(n: int, results) -> None:
    try:
        results.put(("ok", *timed_solve(n)))
    except Exception as e:  # report the failure to the parent instead of dying silently
        results.put(("error", repr(e), 0.0))


def solve_with_timeout(n: int, seconds: float) -> tuple[str | None, float]:
    """
    Solves problem n in a separate process so a slow one can be abandoned.
    Returns (answer, seconds taken), or (None, seconds) if it ran past the limit.
    """
    results = multiprocessing.Queue()
    process = multiprocessing.Process(target=_worker, args=(n, results), daemon=True)
    process.start()
    process.join(seconds)
    if process.is_alive():
        process.terminate()
        process.join()
        return None, seconds
    status, value, elapsed = results.get(timeout=5)
    if status == "error":
        raise RuntimeError(f"Problem {n} raised {value}")
    return value, elapsed
