"""
Problem 84: Monopoly Odds
https://projecteuler.net/problem=84

The game is a Markov chain over (square, streak) states, where streak is how many doubles in a row you've
just rolled: 0, 1 or 2. M[from_square, from_streak, to_square, to_streak] is the probability that one roll
takes you from one state to the other. The long-run share of turns spent in each state is the eigenvector
of M with eigenvalue 1.
"""

from itertools import product

import numpy as np

SQUARES = [
    "GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3",
    "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3",
    "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3",
    "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2",
]
at = SQUARES.index
N, STREAKS, JAIL = len(SQUARES), 3, at("JAIL")


def solve() -> str:
    return modal_string(4)


def example() -> str:
    """The statement's worked example: with two 6-sided dice this should be "102400"."""
    return modal_string(6)


def modal_string(dice_sides: int) -> str:
    """The three most visited squares, most visited first, as a six-digit string."""
    M = dice_matrix(dice_sides)
    for rule in (go_to_jail, chance, community_chest):  # chance first: "back 3" from CH3 lands on CC3
        rule(M)
    assert np.allclose(M.sum(axis=(2, 3)), 1), "a rule created or lost probability"

    values, vectors = np.linalg.eig(M.reshape(N * STREAKS, -1).T)  # pi * M = pi, so transpose
    pi = np.real(vectors[:, np.argmin(abs(values - 1))])
    squares = (pi / pi.sum()).reshape(N, STREAKS).sum(axis=1)  # add up each square's three streaks
    return "".join(f"{square:02d}" for square in np.argsort(squares)[::-1][:3])


def dice_matrix(dice_sides: int) -> np.ndarray:
    """Moving by the dice alone. A double raises your streak; a third double sends you to jail instead."""
    M = np.zeros((N, STREAKS, N, STREAKS))
    for square, streak in product(range(N), range(STREAKS)):
        for d1, d2 in product(range(1, dice_sides + 1), repeat=2):
            to = (square + d1 + d2) % N
            if d1 != d2:
                M[square, streak, to, 0] += 1
            elif streak < STREAKS - 1:
                M[square, streak, to, streak + 1] += 1
            else:
                M[square, streak, JAIL, 0] += 1
    return M / dice_sides**2


# ---- The rules. Each moves the probability that lands on its squares somewhere else. ----

def go_to_jail(M):
    redirect(M, at("G2J"), [(1, JAIL)])


def chance(M):
    for square in (at("CH1"), at("CH2"), at("CH3")):
        def next_one(kind):  # the next railway or utility, clockwise
            return next(s % N for s in range(square + 1, square + N) if SQUARES[s % N].startswith(kind))
        cards = ["GO", "JAIL", "C1", "E3", "H2", "R1"]
        destinations = [at(c) for c in cards] + [next_one("R"), next_one("R"), next_one("U"), (square - 3) % N]
        redirect(M, square, [(1 / 16, d) for d in destinations])


def community_chest(M):
    for square in (at("CC1"), at("CC2"), at("CC3")):
        redirect(M, square, [(1 / 16, at("GO")), (1 / 16, JAIL)])


def redirect(M, square, moves):
    """Send each fraction of whatever lands on `square` to its destination. Your streak carries over, except into jail."""
    landed = M[:, :, square, :].copy()
    M[:, :, square, :] -= landed * sum(fraction for fraction, _ in moves)
    for fraction, destination in moves:
        if destination == JAIL:
            M[:, :, JAIL, 0] += fraction * landed.sum(axis=-1)
        else:
            M[:, :, destination, :] += fraction * landed
