package projecteuler.problems;

import java.util.ArrayList;
import java.util.Hashtable;

import java.util.concurrent.atomic.AtomicInteger;

import projecteuler.util.Divisors;

public class P21 implements Problem{
    Hashtable<Integer, Integer> nums_and_sums;
    
    public P21() {
        super();
        nums_and_sums = new Hashtable<Integer, Integer>();
    }
    
    public String solve() {
        // The hashtable will keep track of what numbers key to which sums.
        for (int i = 1; i < 10000; i++) {
            AtomicInteger sum = new AtomicInteger(0);
            ArrayList<Integer> divisors = Divisors.getDivisors(i); // Create an ArrayList of Divisors
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
        
        return String.valueOf(sum_of_amicable_numbers.intValue());
    }
}