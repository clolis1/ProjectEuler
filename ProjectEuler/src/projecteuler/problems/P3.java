package projecteuler.problems;

import projecteuler.util.ComplexMath;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class P3 implements Problem{
    public P3() {
        super();
    }
    
    public void execute() {
        System.out.println("The prime factors of 13195 are 5, 7, 13 and 29.");
        System.out.println("\nWhat is the largest prime factor of the number 600851475143 ?");
        
        HashMap<Long, Long> pFactors = new HashMap<Long, Long>();
        
        long target = 600851475143L;
        AtomicLong greatest_factor = new AtomicLong(0L);
        
        pFactors = ComplexMath.getPrimeFactors(target); // Returns prime factors of number in ascending order.
        pFactors.forEach((key, val) -> {if (key > greatest_factor.get()) greatest_factor.set(key);}); // Determine the largest key
        
        System.out.println("\nAnswer: " + greatest_factor);
    }
}
