package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.Primes;

public class P26 implements Problem {
    public P26() {
        super();
    }

    @Override
    public String solve() {
        // By using code to find full reptend primes, we can easily find the number below any limit with the longest recurring cycle.
        
        final int n = 1000;
        ArrayList<Integer> primes = Primes.getPrimesUpUntil(n);
        
        int largest_reptend = -1;
        int largest_period = 0;
        
        for (int primes_index = primes.size() - 1; primes_index >= 0; primes_index--) {
            int prime = primes.get(primes_index).intValue();
            int r = 1, period = 0;
            for (int i = 1; i < prime; i++) r = (10 * r) % prime;
            int rr = r;
            do {
                r = (10 * r) % prime;
                ++period;
            } while (r != rr);
            if (period > largest_period) {
                largest_period = period;
                largest_reptend = prime;
            }
            if (period == prime - 1) break;
        }
        return String.valueOf(largest_reptend);
    }
}
