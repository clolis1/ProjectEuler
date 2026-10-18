package projecteuler.problems;

import projecteuler.util.Divisors;

public class P73 implements Problem {
    public P73() {
        super();
    }
    
    @Override
    public String solve() {
        final double ONE_THIRD = 1.0 / 3.0, ONE_HALF = 1.0 / 2.0;
        long fractions = 0L;
        
        for (int i = 1; i <= 12000; i++) {
            int numerator = i / 3;
            
            while ((double) numerator / i < ONE_HALF) {
                if ((double) numerator / i > ONE_THIRD && Divisors.isRelativelyPrime(numerator, i)) fractions++;
                numerator++;
            }
        }
        
        return String.valueOf(fractions);
    }
}