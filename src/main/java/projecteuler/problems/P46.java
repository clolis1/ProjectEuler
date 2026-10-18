package projecteuler.problems;

import java.util.*;

public class P46 implements Problem {
    public P46() {
        super();
    }
    
    @Override
    public String solve() {
        long composite = -1L;
        
        ArrayList<Long> primes = new ArrayList<Long>();
        ArrayList<Long> squares = new ArrayList<Long>();
        squares.add(1L);
        primes.add(2L);
        primes.add(3L);
        
        for (long i = 4; composite == -1L; i++) {
            double sqrt_max = Math.sqrt((double) i); // Check for divisibility up to the square root
            if (sqrt_max == (int) sqrt_max) squares.add(i);
            if (i % 2 == 0) continue;
            boolean prime = true;
            for (int j = 0; primes.get(j) <= sqrt_max; j++) { 
                if (i % primes.get(j) == 0) {// number is not prime
                    prime = false;
                    break;
                }
            }
            if (prime) { // number is prime
                primes.add(i);
            }
            else {
                boolean conjTrue = false;
                for (Long l : primes) {
                    ListIterator<Long> it = squares.listIterator(squares.size() - 1);
                    while (it.hasPrevious()) {
                        long m = it.previous();
                        if (l + 2L * m == i) {
                            conjTrue = true;
                            break;
                        }
                        else if (l + 2L * m < i) break;
                    }
                    if (conjTrue) break;
                }
                if (!conjTrue) composite = i;
            }
        }
        
        return String.valueOf(composite);
    }
}