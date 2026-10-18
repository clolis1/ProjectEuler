"""Checks the shared helpers in euler.lib against brute force."""

from math import gcd, isqrt

import pytest

from euler.lib import pythagorean_triples


def brute_force_triples(max_a, max_b):
    return {
        (a, b, isqrt(a * a + b * b))
        for a in range(1, max_a + 1)
        for b in range(a + 1, max_b + 1)
        if isqrt(a * a + b * b) ** 2 == a * a + b * b
    }


@pytest.mark.parametrize("max_a, max_b", [(1, 1), (5, 5), (30, 50), (60, 30), (100, 200)])
def test_triples_match_brute_force(max_a, max_b):
    triples = list(pythagorean_triples(max_a, max_b))
    assert len(triples) == len(set(triples)), "no triple should be produced twice"
    assert set(triples) == brute_force_triples(max_a, max_b)


def test_primitive_only():
    primitive = set(pythagorean_triples(100, 100, primitive_only=True))
    assert primitive == {t for t in brute_force_triples(100, 100) if gcd(t[0], t[1]) == 1}
    assert (3, 4, 5) in primitive and (6, 8, 10) not in primitive
