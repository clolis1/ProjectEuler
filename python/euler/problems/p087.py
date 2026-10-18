"""
Problem 87: Prime Power Triples
https://projecteuler.net/problem=87

Add up every prime square, cube and fourth power whose total stays below the limit, keeping the totals in a
set so a number reachable in more than one way is only counted once.
"""

from math import isqrt

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
    totals = set()
    for p in primes:
        if p**2 >= limit:
            break
        for q in primes:
            if p**2 + q**3 >= limit:
                break
            for r in primes:
                total = p**2 + q**3 + r**4
                if total >= limit:
                    break
                totals.add(total)
    return len(totals)
