---
name: euler-explanation
description: Write or rework the explanation file for a solved Project Euler problem in this repo (explanations/N.md), following the house format — Problem, In Plain Terms, In Theory, In Code, Complexity. Use when the user asks to explain a problem, add/redo an explanation, or after a new solution (pNNN.py or PN.java) is written.
---

# Project Euler explanation

Every solved problem gets an `explanations/N.md`. The `.md` file is the single home for everything
*about* the problem — its statement and its explanation. The code only computes the answer.

Solutions live in one of two places:

- **Python (problem 86 onward — the default for new work):** `python/euler/problems/pNNN.py`
  (zero-padded, e.g. `p086.py`) with a `solve()` that returns the answer and prints nothing.
- **Java (problems 1–85):** `src/main/java/projecteuler/problems/PN.java` with `String solve()`.

Explanations are internal notes (shown by `python -m euler Ne` or `gradlew run --args="Ne"`), written so
someone who has never seen the problem can follow the idea, and someone who has can check the reasoning.

## Steps

1. **Read the solution** and any helpers it uses — for Java, `src/main/java/projecteuler/util/`
   (`Primes`, `Divisors`, `Digits`, `Arithmetic`, `Lexography`, `BinaryTree`, `Resources`); for Python,
   `python/euler/lib.py`, the standard library, numpy and `sympy`. Explain what the code *actually* does, not an idealized version.
2. **Verify the answer** (commands run from the repo root):
   - Python: `.venv/Scripts/python -m euler N` prints the answer and how long `solve()` took;
     `.venv/Scripts/python -m pytest python` checks it.
   - Java: `gradlew run --args="N"`; `gradlew test` checks it.
   - Both test suites read the shared `answers.csv` at the repo root. Only put answers there that are
     known to be correct; if the code disagrees with a known-correct answer, say so in the explanation and
     tell the user rather than recording the wrong value.
3. **Write `explanations/N.md`** using the template below. If the file already exists, keep anything
   correct and fix anything wrong.

## Finishing a problem we worked on together

When the explanation follows a session where the user reasoned their way to the method (see `euler-start`):

- **Tell their story, not a textbook's.** In Plain Terms and In Theory should follow the steps the user
  actually took and use their framing and analogies where they had good ones. They're the author; write
  in first person ("I noticed…").
- **Keep the good corrections.** Mistakes they made and fixed along the way (an off-by-one, a missed case,
  a wrong first guess) often make the best one-line warnings. Put them in In Theory where they explain a
  rule, or in Further Thoughts.
- **Replace every open question** from the `euler-start` draft. None should survive.
- **Credit earlier versions.** If the solution was rewritten (e.g. a faster method), say what changed and
  the before/after runtimes in Further Thoughts.
- **The answer:** fill in the Answer line once the code's answer matches the statement's example. But add it
  to `answers.csv` only after the user confirms projecteuler.net accepted it, and say in chat which it is.
- Remove any `Status: in progress` line from the problem file's docstring.

## Template

```markdown
# Problem N: <Official title>

https://projecteuler.net/problem=N

**Answer:** <answer> · **Solution:** `pNNN.py` (or `PN.java`) · **Runtime:** <~x s>

## Problem

<The full problem statement. Put grids, triangles and tables in fenced code blocks.
For very large inputs (more than ~25 lines), describe them and say where the data lives
instead of pasting it: "the 100 numbers are in P13.java" / "data/p067_triangle.txt".
Replace references to images ("shown in red") with a sentence that works without them.>

## In Plain Terms

<3–6 sentences for a non-programmer. What is being asked, and the one idea that cracks it.
Use an everyday analogy or a tiny example. No formulas beyond basic arithmetic, no jargon
without a one-phrase definition.>

## In Theory

<The math / CS reasoning: the key insight, why it's correct, and a small worked example.
Name the concept when it has a name (e.g. "inclusion–exclusion", "dynamic programming").
Short derivations are good; proofs only when short.>

## In Code

<How the solution implements it: the main loop/recursion, data structures, helpers used
(e.g. `Divisors.getNumDivisors`), and any shortcuts or bounds it relies on.
A numbered list of steps works well. Refer to methods by name, not line numbers.>

## Complexity

<Time and space in big-O with one line of justification each, e.g. "O(n log log n) — sieve up to n.">

## Further Thoughts

<Optional — include only when there's something worth saying: a faster or closed-form approach,
a bug or fragile assumption in the current code, or a link to a related problem.>
```

## Style

- Plain text that reads well **both** in a terminal (the runner prints the raw file) and in Markdown preview.
  So: no LaTeX. Write math inline with Unicode — `n(n+1)/2`, `≤`, `√n`, `×`, `Σ`, `C(40, 20)`, `a² + b² = c²`.
  Put multi-line math or tables in fenced code blocks.
- "In Plain Terms" must genuinely be for a lay reader — this is the user's strength, so make it clear and
  friendly, never condescending.
- First person is fine when describing the solution ("My solution recurses…"), matching the user's
  original explanations.
- Be concise: a simple problem might be 30 lines total; a hard one rarely needs more than ~100.
- Runtime is the time `solve()` takes (as printed by the runner), rounded: `< 0.01 s`, `~0.04 s`, `~2 s`.
- Problems numbered above 100 are internal-only — Project Euler asks that those solutions not be published.
