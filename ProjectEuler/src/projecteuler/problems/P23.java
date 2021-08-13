package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.ComplexMath;

public class P23 implements Problem {
    public P23() {
        super();
    }

    public void execute() {
        System.out.println("A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.\n" +
              "For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.\n" + 
        "\n" + 
        "A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.\n" + 
        "\n" + 
        "As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16,\n" +
              "the smallest number that can be written as the sum of two abundant numbers is 24.\n" +
              "By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers.\n" +
              "However, this upper limit cannot be reduced any further by analysis even though\n" +
              "it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.\n" + 
        "\n" + 
        "Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.");
        
        // We can start by creating a list of all abundant numbers less than 28123.
        final int LIMIT = 28123;
        int sum_of_non_abundants = 0;
        ArrayList<Integer> abundants = new ArrayList<Integer>();
        for (int i = 0; i < LIMIT; i++) {
            if (ComplexMath.getSumOfProperDivisors(i) > i) abundants.add(i); // Compile list of abundant numbers.
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
        
        System.out.println("\nAnswer: " + total);
    }
}
