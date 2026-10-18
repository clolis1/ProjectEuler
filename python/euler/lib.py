"""
Helpers shared across problems. Add something here only when it's clearly useful beyond one problem;
for primes, factors, divisors and totients, use sympy (isprime, primerange, factorint, divisors, totient).
"""

from collections.abc import Iterator
from math import gcd, isqrt


def pythagorean_triples(max_a: int, max_b: int, primitive_only: bool = False) -> Iterator[tuple[int, int, int]]:
    """
    Every Pythagorean triple (a, b, c) with a < b, a <= max_a and b <= max_b, in no particular order.
    Includes multiples like (6, 8, 10) unless primitive_only is set.

    Uses Euclid's formula: for m > n > 0, coprime and not both odd, (m² - n², 2mn, m² + n²) is a primitive
    triple, and every primitive triple comes from exactly one such (m, n). Every other triple is a multiple
    k * (a, b, c) of exactly one primitive triple.
    """
    largest_c = isqrt(max_a * max_a + max_b * max_b)  # no wanted triple has a longer hypotenuse
    for m in range(2, isqrt(largest_c) + 1):          # c = m² + n² > m², so m² must stay below largest_c
        for n in range(1, m):
            if (m - n) % 2 == 1 and gcd(m, n) == 1:
                a, b = sorted((m * m - n * n, 2 * m * n))
                c = m * m + n * n
                k = 1
                while k * a <= max_a and k * b <= max_b:
                    yield k * a, k * b, k * c
                    if primitive_only:
                        break
                    k += 1
