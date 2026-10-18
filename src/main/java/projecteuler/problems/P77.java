package projecteuler.problems;

import java.util.List;

import projecteuler.util.Primes;

public class P77 implements Problem {
    public P77() {
        super();
    }
    
    @Override
    public String solve() {
        int target = 2;
        
        List<Integer> primes = Primes.getPrimesUpUntil(1000);
        
        while (true) {
            int[] summation = new int[target + 1];
            summation[0] = 1;
            
            for (int i = 0; i < primes.size(); i++) {
                for (int j = primes.get(i); j <= target; j++) {
                    summation[j] += summation[j - primes.get(i)];
                }
            }
            
            if (summation[target] > 5000) break;
            target++;
        }
        return String.valueOf(target);
    }
}