"""
Problem 86: Cuboid Route
https://projecteuler.net/problem=86

For a room a <= b <= c, the shortest route keeps the longest side c alone and unfolds the other two
faces into one rectangle: length sqrt(c^2 + (a + b)^2). So a room counts when c and s = a + b are the
two legs of a Pythagorean triple. Rather than test every (c, s) pair, generate the triples directly
(euler.lib.pythagorean_triples, Euclid's formula), and count the rooms each one makes.
"""

from euler.lib import pythagorean_triples

TARGET = 1_000_000  # find the least M where the number of solutions first exceeds this
START_LIMIT = 50    # largest room size to try first; doubled until the count passes the target


def solve() -> int:
    return least_m_exceeding(TARGET)


def example() -> str:
    """The statement's figures: 1975 cuboids for M = 99, 2060 for M = 100, so 100 is the first M past 2000."""
    return (f"M=99: {count_cuboids(99)} (want 1975), M=100: {count_cuboids(100)} (want 2060), "
            f"first past 2000: {least_m_exceeding(2000)} (want 100)")


def least_m_exceeding(target: int) -> int:
    """The least M for which the number of rooms up to M x M x M first exceeds target."""
    limit = START_LIMIT
    while True:
        total = 0
        for m, rooms in enumerate(rooms_by_longest_side(limit)):
            total += rooms
            if total > target:
                return m
        limit *= 2  # not enough rooms up to this size yet: try again with a bigger limit


def count_cuboids(m: int) -> int:
    """How many distinct rooms (ignoring rotations), with sides up to m x m x m, have an integer shortest route."""
    return sum(rooms_by_longest_side(m))


def rooms_by_longest_side(limit: int) -> list[int]:
    """rooms[c] = how many rooms with longest side exactly c have an integer shortest route, for c up to limit."""
    rooms = [0] * (limit + 1)
    # A triple can only make a room if its shorter leg x is at most limit (as the longest side) and its
    # longer leg y is at most 2 * limit (as the sum of two sides, each at most limit).
    for x, y, _ in pythagorean_triples(limit, 2 * limit):
        if y <= limit:               # y is the longest side; the other two add up to x
            rooms[y] += splits(x, y)
        if y <= 2 * x:               # x is the longest side; the other two add up to y
            rooms[x] += splits(y, x)
    return rooms


def splits(s: int, c: int) -> int:
    """Ways to write s = a + b with 1 <= a <= b <= c."""
    if s <= c:
        return s // 2                 # a = 1 .. s//2
    return s // 2 - (s - c) + 1       # a = s - c .. s//2, so that b = s - a stays <= c
