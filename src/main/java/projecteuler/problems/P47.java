package projecteuler.problems;

import java.util.*;

import projecteuler.util.Primes;

public class P47 implements Problem {
    public P47() {
        super();
    }
    
    @Override
    public String solve() {
        long i = 2L;
        int counter = 0;
        while (counter < 4) {
            if (Primes.getPrimeFactors(i).size() != 4) counter = 0;
            else counter++;
            i++;
        }
        
        return String.valueOf((i - 4L));
    }
}