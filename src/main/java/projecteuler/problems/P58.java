package projecteuler.problems;

import projecteuler.util.Primes;

public class P58 implements Problem {
    public P58() {
        super();
    }
    
    @Override
    public String solve() {
        int ne = 3, nw = 5, sw = 7, addon = 10, side_length = 3;
        double primes = 3, nums = 5;
        
        while (primes / nums >= 0.1) {
            ne += addon; addon += 2;
            nw += addon; addon += 2;
            sw += addon; addon += 4;
            
            if (Primes.isPrime(ne)) primes++;
            if (Primes.isPrime(nw)) primes++;
            if (Primes.isPrime(sw)) primes++;
            
            nums += 4;
            side_length += 2;
        }
        
        return String.valueOf(side_length);
    }
}