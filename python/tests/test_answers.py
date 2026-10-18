"""Runs every Python solution and checks it against the shared answers.csv."""

import pytest

from euler import explanation_path, load_answers, solve_with_timeout
from euler.problems import solved

TIME_LIMIT_SECONDS = 60
ANSWERS = load_answers()


def test_answers_file_parses():
    assert ANSWERS, "answers.csv should list at least one answer"


@pytest.mark.parametrize("n", solved())
def test_gives_the_known_answer(n):
    if n not in ANSWERS:
        pytest.skip(f"No known answer for problem {n} in answers.csv")
    answer, _ = solve_with_timeout(n, TIME_LIMIT_SECONDS)
    assert answer is not None, f"Problem {n} took longer than {TIME_LIMIT_SECONDS} s"
    assert answer == ANSWERS[n]


@pytest.mark.parametrize("n", solved())
def test_has_an_explanation(n):
    assert explanation_path(n).exists(), f"{explanation_path(n)} is missing"
