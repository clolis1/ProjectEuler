"""
Problem 87: Prime Power Triples
https://projecteuler.net/problem=87

Add up every prime square, cube and fourth power whose total stays below the limit, and count each total
once, since some numbers can be reached in more than one way.

Speed comes from working in bulk with numpy. There are about 1.1 million (square, cube, fourth power)
combinations, but only 73 x 23 = 1,679 (cube, fourth power) pairs. So Python loops over just those pairs,
and for each one numpy adds it to *every* prime square at once in compiled code. Plain Python looping over
all 1.1 million combinations took about 0.5 s; this takes about 0.1 s.
"""

from math import isqrt

import numpy as np
from sympy import primerange

LIMIT = 50_000_000  # count numbers below this


def solve() -> int:
    return count_expressible(LIMIT)


def example() -> str:
    """The statement's example: exactly four numbers below fifty (28, 33, 47, 49)."""
    return f"below 50: {count_expressible(50)} (want 4)"


def count_expressible(limit: int) -> int:
    """How many numbers below limit can be written as (prime)² + (prime)³ + (prime)⁴."""
    primes = list(primerange(2, isqrt(limit) + 1))  # a square below the limit needs a prime below √limit

    # Work out each power once, up front, keeping only those below the limit.
    squares = np.array([p**2 for p in primes if p**2 < limit], dtype=np.int64)  # 908 of them for 50 million
    cubes = [p**3 for p in primes if p**3 < limit]                              # 73
    fourths = [p**4 for p in primes if p**4 < limit]                            # 23

    # One yes/no slot per number below the limit. Marking a slot twice is harmless, so a number reached in
    # several ways is still counted once (this replaces the set of totals, and needs no hashing).
    seen = np.zeros(limit, dtype=bool)

    for fourth in fourths:
        for cube in cubes:
            # Bulk step: add this cube and fourth power to all ~900 squares in one numpy operation,
            # instead of a Python loop doing ~900 separate additions.
            totals = squares + (fourth + cube)
            # Bulk step: keep the totals below the limit and mark all of them in one operation.
            seen[totals[totals < limit]] = True

    return int(seen.sum())  # bulk count of the marked slots
