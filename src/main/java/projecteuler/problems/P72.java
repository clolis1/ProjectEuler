package projecteuler.problems;

import projecteuler.util.Primes;



public class P72 implements Problem {
    public P72() {
        super();
    }
    
    @Override
    public String solve() {
        long n = 0L;
        
        for (int i = 2; i <= 1000000; i++) n += i - (i - Primes.eulersTotient(i));
        
        return String.valueOf(n);
    }
}