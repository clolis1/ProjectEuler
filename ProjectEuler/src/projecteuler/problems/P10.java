package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.ComplexMath;

public class P10 implements Problem{
    public P10() {
        super();
    }
    
    public void execute() {
        System.out.println("The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.");
        System.out.println("\nFind the sum of all the primes below two million.");
        
        final int VALUE_MAX = 2000000; // Two million
        
        ArrayList<Integer> primes = ComplexMath.getPrimesUpUntil(VALUE_MAX);

        long sum = 0;
        for (int i = 0; i < primes.size(); i++) {
            sum += primes.get(i).longValue();
        }
        
        System.out.println("\nAnswer: " + sum);
    }
}