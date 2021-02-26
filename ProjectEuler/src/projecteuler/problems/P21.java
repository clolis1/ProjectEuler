package projecteuler.problems;

import java.util.ArrayList;
import java.util.Hashtable;

import java.util.concurrent.atomic.AtomicInteger;

import projecteuler.util.ComplexMath;

public class P21 implements Problem{
    Hashtable<Integer, Integer> nums_and_sums;
    
    public P21() {
        super();
        nums_and_sums = new Hashtable<Integer, Integer>();
    }
    
    public void execute() {
        System.out.println("Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).");
        System.out.println("If d(a) = b and d(b) = a, where a =/= b, then a and b are an amicable pair and each of a and b are called amicable numbers.");
        System.out.println("\nFor example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55, and 110;");
        System.out.println("therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71, 142; so d(284) = 220.");
        System.out.println("\nEvaluate the sum of all amicable numbers under 10000.");
        
        // The hashtable will keep track of what numbers key to which sums.
        for (int i = 1; i < 10000; i++) {
            AtomicInteger sum = new AtomicInteger(0);
            ArrayList<Integer> divisors = ComplexMath.getDivisors(i); // Create an ArrayList of Divisors
            divisors.forEach((n) -> sum.addAndGet(n)); // Calculate sum of divisors
            sum.addAndGet(i * -1); // Remove the number itself as it is not a proper divisor
            nums_and_sums.put(i, sum.intValue()); // Add num and sum to hashtable
        }
        
        AtomicInteger sum_of_amicable_numbers = new AtomicInteger(0);
        nums_and_sums.forEach((key, val) -> {
            if (nums_and_sums.containsKey(val) &&
                key.intValue() == nums_and_sums.get(val).intValue() &&
                key.intValue() != val.intValue()) {
                sum_of_amicable_numbers.addAndGet(key);
            }});
        
        System.out.println("\nAnswer: " + sum_of_amicable_numbers.intValue());
    }
}