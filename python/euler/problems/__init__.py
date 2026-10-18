"""
One module per solved problem: pNNN.py (zero-padded, e.g. p086.py) with a solve() function that
returns the answer and prints nothing.
"""

import importlib
import pkgutil
import re


def solved() -> list[int]:
    """Numbers of every problem that has a module here."""
    numbers = []
    for module in pkgutil.iter_modules(__path__):
        match = re.fullmatch(r"p(\d+)", module.name)
        if match:
            numbers.append(int(match.group(1)))
    return sorted(numbers)


def module(n: int):
    """The module for problem n."""
    return importlib.import_module(f"{__name__}.p{n:03d}")


def solve(n: int) -> str:
    """Runs problem n's solve() and returns the answer as a string."""
    return str(module(n).solve())
