package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.Divisors;

public class P23 implements Problem {
    public P23() {
        super();
    }

    public String solve() {
        // We can start by creating a list of all abundant numbers less than 28123.
        final int LIMIT = 28123;
        int sum_of_non_abundants = 0;
        ArrayList<Integer> abundants = new ArrayList<Integer>();
        for (int i = 0; i < LIMIT; i++) {
            if (Divisors.getSumOfProperDivisors(i) > i) abundants.add(i); // Compile list of abundant numbers.
        }
        
        // Create a boolean array to keep track of which numbers are sums of 2 abundant numbers.
        // All even numbers greater than 46 are such sums. So are the 8 numbers manually added below.
        boolean[] abundant_sums = new boolean[LIMIT + 1];
        abundant_sums[24] = true;
        abundant_sums[30] = true;
        abundant_sums[32] = true;
        abundant_sums[36] = true;
        abundant_sums[38] = true;
        abundant_sums[40] = true;
        abundant_sums[42] = true;
        abundant_sums[44] = true;
        for (int i = 48; i < LIMIT + 1; i += 2) abundant_sums[i] = true;
        
        // Add abundant numbers to discovered odd abundant numbers to create new two-number sums.
        // Only arithmetic with at least one odd abundant number is necessary.
        // Stop comparing against a given odd abundant number if further sums would be greater than the limit of 28123.
        for (Integer e : abundants) {
            if (e % 2 == 0) continue;
            for (Integer f : abundants) {
                if (e.intValue() + f.intValue() > LIMIT) break;
                abundant_sums[e.intValue() + f.intValue()] = true;
            }
        }
        
        int total = 0;
        for (int i = 1; i < LIMIT + 1; i++) {
            if (!abundant_sums[i]) total += i;
        }
        
        return String.valueOf(total);
    }
}
