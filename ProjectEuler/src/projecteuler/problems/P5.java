package projecteuler.problems;

import java.util.HashMap;

import java.util.concurrent.atomic.AtomicLong;

import projecteuler.util.ComplexMath;

public class P5 implements Problem{
    public P5() {
        super();
    }
    
    public void execute() {
        System.out.println("2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.");
        System.out.println("\nWhat is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?");
        
        HashMap<Integer, Integer> final_list = new HashMap<Integer, Integer>();
        
        final int VALUE_MAX = 20;
        
        for (int i = 2; i < VALUE_MAX + 1; i++) { // Iterate through each number of 1 through VALUE_MAX
            HashMap<Integer, Integer> list_i = ComplexMath.getPrimeFactors(i); 
            // any prime_factors found to not be in common with current list will be added
            list_i.forEach((key, val) -> {
                if (final_list.containsKey(key) && val > final_list.get(key)) {
                    final_list.replace(key, val);
                }
                else if (!final_list.containsKey(key)) {
                    final_list.put(key, val);
                }
            });
        }
        
        AtomicLong smallest_dividend = new AtomicLong(1L);
        final_list.forEach((key, val) -> smallest_dividend.set(smallest_dividend.get() * (long) Math.pow(key, val)));
        
        System.out.println("\nAnswer: " + smallest_dividend.get());
    }
}