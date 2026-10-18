package projecteuler.problems;

import projecteuler.util.Divisors;

public class P12 implements Problem{
    public P12() {
        super();
    }
    
    public String solve() {
        final int NUM_DIVISORS = 500;
        long triangle_number = 0;
        boolean complete = false;
        
        for (int i = 1; complete == false; i++) {
            triangle_number += i;
            int total_divisors; 
            if (i % 2 == 0) {
                total_divisors = Divisors.getNumDivisors(i/2) * Divisors.getNumDivisors(i+1);
            }
            else {
                total_divisors = Divisors.getNumDivisors(i) * Divisors.getNumDivisors((i + 1) / 2);
            }
            if (total_divisors > NUM_DIVISORS) {
                complete = true;
            }
        }
        
        return String.valueOf(triangle_number);
    }
}