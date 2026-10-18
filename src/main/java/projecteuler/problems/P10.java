package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.Primes;

public class P10 implements Problem{
    public P10() {
        super();
    }
    
    public String solve() {
        final int VALUE_MAX = 2000000; // Two million
        
        ArrayList<Integer> primes = Primes.getPrimesUpUntil(VALUE_MAX);

        long sum = 0;
        for (int i = 0; i < primes.size(); i++) {
            sum += primes.get(i).longValue();
        }
        
        return String.valueOf(sum);
    }
}