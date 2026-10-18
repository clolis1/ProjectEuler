"""
Problem 84: Monopoly Odds
https://projecteuler.net/problem=84

The game is a Markov chain. A state is (square, streak), where streak is how many doubles in a row
you've just rolled (0, 1 or 2), so there are 40 x 3 = 120 states. M[a][b] is the probability that a
turn starting in state a ends in state b. Each state gets a single index: square * STREAKS + streak.
"""

from fractions import Fraction

import numpy as np

# The 40 squares, numbered 00-39 clockwise from GO (index = square number).
SQUARES = [
    "GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3",
    "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3",
    "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3",
    "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2",
]
JAIL = SQUARES.index("JAIL")

# Each pile has 16 cards. These are the ones that move you, as listed in the statement;
# the rest leave you where you are.
CARDS_PER_PILE = 16
COMMUNITY_CHEST_MOVES = ["GO", "JAIL"]
CHANCE_MOVES = ["GO", "JAIL", "C1", "E3", "H2", "R1", "next R", "next R", "next U", "back 3"]

STREAKS = 3  # doubles in a row so far: 0, 1 or 2 (a third double sends you to jail instead of moving)
STATES = len(SQUARES) * STREAKS

Matrix = list[list[Fraction]]


def state(square: int, streak: int) -> int:
    """The single index of the (square, streak) pair, e.g. (GO, 0) -> 0, (GO, 1) -> 1, (A1, 0) -> 3."""
    return square * STREAKS + streak


def landing(square: int, streak: int) -> int:
    """The state you end up in when a rule moves you to `square`: going to jail resets your streak."""
    return state(square, 0 if square == JAIL else streak)


def dice_matrix(dice_sides: int) -> Matrix:
    """
    The 120x120 matrix for moving by the dice alone, before any square's rule:
    - a double on streak 0 or 1 moves you and adds one to the streak,
    - a double on streak 2 (the third in a row) sends you straight to (JAIL, 0) without moving,
    - anything else moves you and resets the streak to 0.
    """
    matrix = [[Fraction(0)] * STATES for _ in range(STATES)]
    each_roll = Fraction(1, dice_sides * dice_sides)
    for square in range(len(SQUARES)):
        for streak in range(STREAKS):
            row = matrix[state(square, streak)]
            for first in range(1, dice_sides + 1):
                for second in range(1, dice_sides + 1):
                    to_square = (square + first + second) % len(SQUARES)  # wraps past H2 back around to GO
                    if first != second:
                        row[state(to_square, 0)] += each_roll
                    elif streak < STREAKS - 1:
                        row[state(to_square, streak + 1)] += each_roll
                    else:
                        row[state(JAIL, 0)] += each_roll
    return matrix


def apply_go_to_jail(matrix: Matrix) -> None:
    """Landing on G2J sends you to (JAIL, 0): move each row's G2J probability over, for every streak (in place)."""
    g2j = SQUARES.index("G2J")
    for streak in range(STREAKS):
        for row in matrix:
            row[landing(JAIL, streak)] += row[state(g2j, streak)]
            row[state(g2j, streak)] = Fraction(0)


def chance_destination(card: str, chance_square: int) -> int:
    """Where a chance card sends you from the given CH square."""
    n = len(SQUARES)
    if card == "back 3":
        return (chance_square - 3) % n
    if card.startswith("next "):
        kind = card[len("next "):]  # "R" or "U"
        square = (chance_square + 1) % n
        while not SQUARES[square].startswith(kind):  # walk clockwise, wrapping past GO
            square = (square + 1) % n
        return square
    return SQUARES.index(card)


def apply_chance(matrix: Matrix) -> None:
    """
    Landing on a CH square draws a card: each movement card sends 1/16 of that row's CH probability
    to its destination (keeping your streak, unless it's JAIL), and the rest (6/16) stays put (in place).
    Run this before apply_community_chest: "back 3" from CH3 lands on CC3, which then draws a CC card.
    """
    for ch in (i for i, name in enumerate(SQUARES) if name.startswith("CH")):
        for streak in range(STREAKS):
            for row in matrix:
                landed = row[state(ch, streak)]
                for card in CHANCE_MOVES:
                    row[landing(chance_destination(card, ch), streak)] += landed / CARDS_PER_PILE
                row[state(ch, streak)] = landed * (CARDS_PER_PILE - len(CHANCE_MOVES)) / CARDS_PER_PILE


def apply_community_chest(matrix: Matrix) -> None:
    """
    Landing on a CC square draws a card: each movement card sends 1/16 of that row's CC probability
    to its destination (keeping your streak, unless it's JAIL), and the rest (14/16) stays put (in place).
    """
    for cc in (i for i, name in enumerate(SQUARES) if name.startswith("CC")):
        for streak in range(STREAKS):
            for row in matrix:
                landed = row[state(cc, streak)]
                for destination in COMMUNITY_CHEST_MOVES:
                    row[landing(SQUARES.index(destination), streak)] += landed / CARDS_PER_PILE
                row[state(cc, streak)] = landed * (CARDS_PER_PILE - len(COMMUNITY_CHEST_MOVES)) / CARDS_PER_PILE


def build_matrix(dice_sides: int) -> Matrix:
    """The full 120x120 transition matrix. Order matters: chance before community chest."""
    matrix = dice_matrix(dice_sides)
    apply_go_to_jail(matrix)
    apply_chance(matrix)
    apply_community_chest(matrix)
    assert all(sum(row) == 1 for row in matrix), "a rule created or lost probability"
    return matrix


def write_matrix_csv(matrix: Matrix, path: str, by_square: bool = False) -> None:
    """
    Writes the matrix to a CSV file for viewing in a spreadsheet: rows are "from" states labelled like
    "GO,0" (square, streak). Columns are "to" states the same way, or with by_square=True one column per
    board square (its three streaks added together). Values are decimals; blank cells are zero.
    """
    import csv

    labels = [f"{SQUARES[s // STREAKS]},{s % STREAKS}" for s in range(STATES)]
    if by_square:
        columns = SQUARES
        rows = [[sum(row[state(sq, st)] for st in range(STREAKS)) for sq in range(len(SQUARES))] for row in matrix]
    else:
        columns, rows = labels, matrix
    with open(path, "w", newline="", encoding="utf-8") as f:
        writer = csv.writer(f)
        writer.writerow(["from \\ to", *columns])
        for label, row in zip(labels, rows):
            writer.writerow([label, *(f"{float(x):.6f}" if x else "" for x in row)])


def square_probabilities(dice_sides: int) -> np.ndarray:
    """
    The long-run probability of ending a turn on each of the 40 squares: the eigenvector of the
    transition matrix for eigenvalue 1 (pi * M = pi), with each square's three streaks added together.
    """
    matrix = np.array(build_matrix(dice_sides), dtype=float)
    # eig finds M x = lambda x (vector on the right); pi * M = pi has the vector on the left, so transpose.
    values, vectors = np.linalg.eig(matrix.T)
    k = np.argmin(abs(values - 1))           # the eigenvalue that's 1
    pi = np.real(vectors[:, k])
    pi /= pi.sum()                           # scale so the probabilities add up to 1
    return pi.reshape(len(SQUARES), STREAKS).sum(axis=1)  # states are ordered square * STREAKS + streak


def modal_string(dice_sides: int) -> str:
    """The six-digit string of the three most visited squares, most visited first, using two dice_sides-sided dice."""
    probabilities = square_probabilities(dice_sides)
    top_three = np.argsort(probabilities)[::-1][:3]
    return "".join(f"{square:02d}" for square in top_three)


def example() -> str:
    """The statement's worked example: with two 6-sided dice this should be "102400"."""
    return modal_string(6)


def solve() -> str:
    return modal_string(4)
