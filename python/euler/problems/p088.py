"""
Problem 88: Product-sum Numbers
https://projecteuler.net/problem=88

Every way of writing N as a product of factors >= 2 gives a product-sum set: pad the factors with
(N - their sum) ones, so the sum and the product are both N. That set has
    k = (number of factors) + (N - sum of factors)
numbers in it. Going through N = 4, 5, 6, ... in order, the first N to reach a given k is that k's
minimal product-sum number, so each slot is filled once and never replaced.
"""

from math import isqrt

K_MAX = 12_000  # sum the minimal product-sum numbers for 2 <= k <= K_MAX


def solve() -> int:
    return sum_of_minimal(K_MAX)


def example() -> str:
    """The statement's figures: 4, 6, 8, 8, 12 for k = 2..6; sum 30 up to k = 6 and 61 up to k = 12."""
    first = [minimal_product_sum(k) for k in range(2, 7)]
    return (f"k=2..6: {first} (want [4, 6, 8, 8, 12]), "
            f"sum to 6: {sum_of_minimal(6)} (want 30), sum to 12: {sum_of_minimal(12)} (want 61)")


def sum_of_minimal(k_max: int) -> int:
    """The sum of the *distinct* minimal product-sum numbers for 2 <= k <= k_max (each value counted once)."""
    return sum(set(minimal_product_sums(k_max)[2:]))


def minimal_product_sum(k: int) -> int:
    """The smallest N that is both the sum and the product of some set of k natural numbers."""
    return minimal_product_sums(k)[k]


def minimal_product_sums(k_max: int) -> list[int]:
    """best[k] = the minimal product-sum number for each 2 <= k <= k_max (slots 0 and 1 unused)."""
    best = [0] * (k_max + 1)
    unfilled = k_max - 1
    n = 3
    while unfilled:
        n += 1
        for k in set_sizes(n, k_max):
            if best[k] == 0:  # n is increasing, so the first n to reach k is the smallest
                best[k] = n
                unfilled -= 1
    return best


def set_sizes(n: int, k_max: int):
    """The k (up to k_max) of every product-sum set for n: one per way of writing n as a product of 2+ factors."""

    def split(remaining, smallest, count, total):
        # `count` factors adding up to `total` are already chosen; now split `remaining` as f x rest,
        # with factors in non-decreasing order so each factorization is produced exactly once.
        for f in range(smallest, isqrt(remaining) + 1):
            if remaining % f:
                continue
            rest = remaining // f
            k = (count + 2) + n - (total + f + rest)  # factors, plus the 1s needed to bring the sum up to n
            if k > k_max:
                break  # a bigger f gives a smaller factor sum, so k only grows from here
            yield k
            yield from split(rest, f, count + 1, total + f)  # split `rest` further (k grows again)

    yield from split(n, 2, 0, 0)
