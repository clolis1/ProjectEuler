package projecteuler;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import projecteuler.problems.Problem;

/** Finds every solved problem by looking for classes named projecteuler.problems.P1, P2, ... */
public final class Problems {
    /** Highest problem number to look for. Gaps (unsolved problems) are skipped. */
    private static final int MAX_PROBLEM = 1000;

    private static SortedMap<Integer, Class<? extends Problem>> solved;

    private Problems() {}

    public static synchronized SortedMap<Integer, Class<? extends Problem>> solved() {
        if (solved == null) {
            SortedMap<Integer, Class<? extends Problem>> found = new TreeMap<>();
            for (int n = 1; n <= MAX_PROBLEM; n++) {
                try {
                    found.put(n, Class.forName("projecteuler.problems.P" + n).asSubclass(Problem.class));
                } catch (ClassNotFoundException e) {
                    // Not solved yet.
                }
            }
            solved = Collections.unmodifiableSortedMap(found);
        }
        return solved;
    }

    /** Returns a fresh instance of problem n, or null if it hasn't been solved. */
    public static Problem create(int n) {
        Class<? extends Problem> c = solved().get(n);
        if (c == null) return null;
        try {
            return c.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Could not create P" + n, e);
        }
    }
}
