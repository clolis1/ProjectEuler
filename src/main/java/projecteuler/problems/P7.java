package projecteuler.problems;

import java.util.ArrayList;

public class P7 implements Problem{
    public P7() {
        super();
    }
    
    public String solve() {
        final int NUMBER_OF_PRIMES = 10001;
        
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        primes.add(3);
        
        int counter = 4; // We'll start at 4
        while (primes.size() < NUMBER_OF_PRIMES) {
            boolean prime = true;
            int sqrt_max = (int) Math.sqrt((double) counter); // Check for divisibility up to the square root
            for (int i = 0; primes.get(i) <= sqrt_max; i++) { 
                if (counter % primes.get(i) == 0) {// number is not prime
                    prime = false;
                    break;
                }
            }
            
            if (prime) { // number is prime
                primes.add(counter);
            }
            counter++; // Check next number
        }
        
        return String.valueOf(primes.get(10000));
    }
}