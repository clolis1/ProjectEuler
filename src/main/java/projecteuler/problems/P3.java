package projecteuler.problems;

import projecteuler.util.Primes;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class P3 implements Problem{
    public P3() {
        super();
    }
    
    public String solve() {
        HashMap<Long, Long> pFactors = new HashMap<Long, Long>();
        
        long target = 600851475143L;
        AtomicLong greatest_factor = new AtomicLong(0L);
        
        pFactors = Primes.getPrimeFactors(target); // Returns prime factors of number in ascending order.
        pFactors.forEach((key, val) -> {if (key > greatest_factor.get()) greatest_factor.set(key);}); // Determine the largest key
        
        return String.valueOf(greatest_factor);
    }
}
