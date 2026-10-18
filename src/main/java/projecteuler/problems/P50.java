package projecteuler.problems;

import java.util.*;

import projecteuler.util.Primes;

public class P50 implements Problem {
    public P50() {
        super();
    }
    
    @Override
    public String solve() {
        TreeSet<Integer> primes = Primes.getSetPrimesUpUntil(1000000);
        
        int most_consecutive = 0;
        int cSum = 0;
        for (int i : primes) {
            int sum = 0;
            int counter = 0;
            for (int j : primes.tailSet(i)) {
                sum += j;
                counter++;
                if (primes.contains(sum) && counter > most_consecutive) {
                    most_consecutive = counter;
                    cSum = sum;
                }
                if (sum > 1000000) break;
            }
        }
        
        return String.valueOf(cSum);
    }
}