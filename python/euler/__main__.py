"""
Command-line runner for the Python solutions.

    python -m euler 86      solve problem 86 and show the time taken
    python -m euler 86e     show the explanation for problem 86 (works for the Java problems too)
    python -m euler all     solve every Python problem and print a timing table
    python -m euler         interactive mode (same commands, Q to quit)
"""

import re
import sys

from euler import EXPLANATIONS, problems, solve_with_timeout, timed_solve

TIME_LIMIT_SECONDS = 60


def solve(n: int) -> None:
    if n not in problems.solved():
        print(f"Problem {n} hasn't been solved in Python yet.")
        return
    answer, seconds = timed_solve(n)
    print(f"Problem {n}: {answer}  ({seconds:.3f} s)")


def explain(n: int) -> None:
    path = EXPLANATIONS / f"{n}.md"
    if not path.exists():
        print(f"No explanation for problem {n} yet.")
        return
    print(path.read_text(encoding="utf-8"))


def run_all() -> None:
    print(f"{'#':>5}  {'Answer':<20} {'Seconds':>9}")
    total = 0.0
    for n in problems.solved():
        answer, seconds = solve_with_timeout(n, TIME_LIMIT_SECONDS)
        if answer is None:
            answer = f"(gave up after {TIME_LIMIT_SECONDS} s)"
        total += seconds
        print(f"{n:>5}  {answer:<20} {seconds:>9.3f}")
    print(f"{'':>5}  {'Total':<20} {total:>9.3f}")


def handle(command: str) -> None:
    if command.lower() == "all":
        run_all()
        return
    match = re.fullmatch(r"(\d+)([eE]?)", command)
    if not match:
        print("Enter a problem number, a number followed by 'e', 'all', or Q.")
        return
    n = int(match.group(1))
    if match.group(2):
        explain(n)
    else:
        solve(n)


def main() -> None:
    sys.stdout.reconfigure(encoding="utf-8")  # explanations use Unicode math (², √, ≤)
    if len(sys.argv) > 1:
        handle("".join(sys.argv[1:]).strip())
        return

    print(f"Project Euler (Python) — {len(problems.solved())} problems solved.")
    print("Enter a number to solve it, a number followed by 'e' (e.g. 86e) for its explanation,")
    print("'all' to run everything, or Q to quit.")
    while True:
        try:
            line = input("\n> ").strip()
        except EOFError:
            break
        if line.lower() == "q":
            break
        if line:
            handle(line)
    print("Bye!")


if __name__ == "__main__":
    main()
