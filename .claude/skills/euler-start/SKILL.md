---
name: euler-start
description: Start a new Project Euler problem for the user to solve — create a Python stub (python/euler/problems/pNNN.py) and an explanation draft (explanations/N.md) with the full statement and open questions, but no solution. Use when the user wants to start, set up, open or work on a problem; then coach them through it.
---

# Starting a Project Euler problem

**The user solves the problems. Claude scaffolds and coaches.** Never write a working solution, name the
key insight, or put the answer anywhere unless the user explicitly asks for it.

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
- Review the user's code honestly: bugs, edge cases, performance. Point at the issue; let them fix it unless
  they ask you to.
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
