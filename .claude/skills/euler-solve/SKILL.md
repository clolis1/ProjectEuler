---
name: euler-solve
description: Solve a new Project Euler problem in this repo, in Python (python/euler/problems/pNNN.py), check it, and write its explanation. Use when the user asks to solve, do, or work on a Project Euler problem number, or to redo/speed up an existing solution.
---

# Solving a Project Euler problem

New problems are solved in **Python**. Problems 1–85 are in Java (`src/main/java/projecteuler/problems/`)
and stay there unless the user asks to port one.

## Steps

0. If `.venv/` is missing, set it up from the repo root:
   `python -m venv .venv` then `.venv/Scripts/python -m pip install -e "python[dev]"`.
1. **Get the statement** from `https://projecteuler.net/problem=N` (or from the user). Input files go in
   `src/main/resources/data/` (shared with Java) and are read via `euler.DATA / "pNNN_name.txt"`.
2. **Write `python/euler/problems/pNNN.py`** (zero-padded: `p086.py`) with a module docstring naming the
   problem and a `solve()` that **returns** the answer and prints nothing. Keep it readable; the user reads
   these to learn from them.
   - Prefer the standard library (`math.isqrt`, `math.gcd`, `math.comb`, `itertools`, `functools.cache`,
     `fractions.Fraction`) and `sympy` (`isprime`, `primerange`, `factorint`, `totient`, `divisors`) over
     hand-rolled helpers.
   - Aim for well under a minute. If plain Python is too slow, improve the algorithm first (sieves,
     memoization, math shortcuts) before reaching for numpy.
3. **Sanity-check against the statement's own small example** (e.g. "for n ≤ 10 the answer is 17") — run the
   same logic on the small case and confirm it matches before trusting the real answer.
4. **Run it** from the repo root: `.venv/Scripts/python -m euler N`.
5. **Record the answer — carefully.** `answers.csv` holds only answers known to be correct. A new answer is
   *not* known-correct until the user has submitted it to Project Euler. So: tell the user the answer and ask
   them to submit it; add `N,answer` to `answers.csv` once they confirm. Then run
   `.venv/Scripts/python -m pytest python`.
6. **Write `explanations/N.md`** with the `euler-explanation` skill.

## Publishing

Project Euler asks that solutions to problems above 100 not be shared publicly. Everything here is internal;
never post solutions or explanations for problems above 100 anywhere public.
