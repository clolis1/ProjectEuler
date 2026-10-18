---
name: euler-start
description: Start a new Project Euler problem for the user to solve — create a Python stub (python/euler/problems/pNNN.py) and an explanation draft (explanations/N.md) with the full statement and open questions, but no solution. Use when the user wants to start, set up, open or work on a problem; then coach them through it.
---

# Starting a Project Euler problem

**The user works out the method. Claude scaffolds, coaches, and then writes the code.** Never propose the
approach, name the key insight, or put the answer anywhere unless the user explicitly asks for it. Once the
user has reasoned their way to a method, Claude implements *that method* in Python (pointing out any flaws in
it first) — the user doesn't want to write much code themselves.

New problems are done in **Python**. Problems 1–83 and 85 are solved in Java
(`src/main/java/projecteuler/problems/`).

## 1. Scaffold

0. If `.venv/` is missing, set it up from the repo root:
   `python -m venv .venv` then `.venv/Scripts/python -m pip install -e "python[dev]"`.
1. **Get the statement** from `https://projecteuler.net/problem=N`. Input files go in
   `src/main/resources/data/` (shared with Java) and are read via `euler.DATA / "pNNN_name.txt"`.
2. **Create `python/euler/problems/pNNN.py`** (zero-padded, e.g. `p084.py`):
   - A module docstring with the problem's number, title and link.
   - **Mechanical setup only**: transcribed data from the statement (lists, tables, constants), and the
     parameters the question asks about. Nothing that encodes the approach.
   - Function signatures with `raise NotImplementedError` bodies, and `solve()` calling them with the
     question's actual parameters. `solve()` must **return** the answer and print nothing.
   - If the statement gives a smaller worked example (e.g. "with 6-sided dice the string is 102400"), add an
     `example()` the user can run to check their logic against it.
3. **Create `explanations/N.md`** from the `euler-explanation` template:
   - Fill in the title, link and the full **Problem** section.
   - Set `**Answer:** —` and `**Runtime:** —`.
   - Under each other heading, write 2–4 **open questions** that help the user think it through, in the
     section's spirit (In Plain Terms: "How would you describe one turn to a friend?"; In Theory: "What
     determines where you land next?"). Questions, not hints — don't steer toward a specific technique.
4. Open both files for the user in VS Code: `code -r <stub> <explanation>`.

The runner (`python -m euler N`) reports "still in progress" while `solve()` raises `NotImplementedError`,
and the tests skip problems with no answer in `answers.csv`, so a stub never breaks anything.

## 2. Coach

- Answer questions directly when they're about Python, the libraries, or reading the statement.
- On the problem itself, ask before telling. Offer hints in increasing strength only when asked:
  a guiding question → a nudge toward the relevant idea → the idea itself → a sketch. Say which level you're
  giving.
- Check the user's reasoning honestly as they think out loud: test their claims on small cases, show
  counterexamples, and point out off-by-ones and missed cases before any code is written.
- When the method is settled, implement it in `pNNN.py`, run `Nx` against the statement's example first,
  then `N` for the real answer, and walk the user through the code if they want.
- **Before writing any helper, read `python/euler/lib.py`** and use what's there (e.g.
  `pythagorean_triples`). If a new helper would clearly be useful in other problems, put a general
  version in `lib.py` with a brute-force check in `python/tests/test_lib.py`, rather than inside the
  problem file. Keep problem-specific logic in `pNNN.py`, and don't build helpers ahead of need.
- Useful tools to suggest when relevant: `math.isqrt`, `math.gcd`, `math.comb`, `itertools`,
  `functools.cache`, `fractions.Fraction`, and `sympy` (`isprime`, `primerange`, `factorint`, `totient`).

## 3. Finish

1. When the user has an answer, they submit it on projecteuler.net. Add `N,answer` to `answers.csv` only
   after they confirm it was accepted, then run `.venv/Scripts/python -m pytest python`.
2. Help them finish `explanations/N.md` with the `euler-explanation` skill — in their words where possible;
   replace the open questions with the real sections.

## Publishing

Project Euler asks that solutions to problems above 100 not be shared publicly. Never post solutions or
explanations for problems above 100 anywhere public.
