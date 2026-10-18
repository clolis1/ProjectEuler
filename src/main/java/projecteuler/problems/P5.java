package projecteuler.problems;

import java.util.HashMap;

import java.util.concurrent.atomic.AtomicLong;

import projecteuler.util.Primes;

public class P5 implements Problem{
    public P5() {
        super();
    }
    
    public String solve() {
        HashMap<Integer, Integer> final_list = new HashMap<Integer, Integer>();
        
        final int VALUE_MAX = 20;
        
        for (int i = 2; i < VALUE_MAX + 1; i++) { // Iterate through each number of 1 through VALUE_MAX
            HashMap<Integer, Integer> list_i = Primes.getPrimeFactors(i); 
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
        
        return String.valueOf(smallest_dividend.get());
    }
}