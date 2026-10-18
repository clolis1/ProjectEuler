package projecteuler.problems;

import java.util.Iterator;
import java.util.TreeSet;

public class P37 implements Problem {
    public P37() {
        super();
    }
    
    @Override
    public String solve() {
        int sum = 0;
        int truncatable_primes = 0;
        
        TreeSet<Integer> primes = new TreeSet<Integer>();
        primes.add(2);
        primes.add(3);
        
        int counter = 5; // We'll start at 5
        while (truncatable_primes < 11) {
            boolean prime = true;
            int sqrt_max = (int) Math.sqrt((double) counter); // Check for divisibility up to the square root
            Iterator<Integer> it = primes.iterator();
            int x = 2;
            while (it.hasNext() && x <= sqrt_max) {
                x = it.next();
                if (counter % x == 0) { prime = false; break; }
            }
            
            if (prime) primes.add(counter);
            else { counter += 2; continue; }
            
            // Check for truncatable primality
            // Right-side
            boolean truncatable = true;
            int y = counter, z = counter;
            int z_length = 0;
            while (y / 10 > 0) {
                z_length++;
                y = y / 10;
                if (!primes.contains(y)) { truncatable = false; break; }
            }
            if (!primes.contains(y)) {
                truncatable = false;
                counter += 2;
                continue;
            }
            // Check for truncatable primality
            // Left-side
            while (z_length > 0) {
                z = z % (int) Math.pow(10, z_length);
                z_length--;
                if (!primes.contains(z)) { truncatable = false; break; }
            }
            if (truncatable && counter / 10 > 0) {
                sum += counter;
                truncatable_primes++;
            } 
            counter += 2; // Check next number
        }
        
        return String.valueOf(sum);
    }
}