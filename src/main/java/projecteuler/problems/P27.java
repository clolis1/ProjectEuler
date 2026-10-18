package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.Primes;

public class P27 implements Problem {
    public P27() {
        super();
    }

    @Override
    public String solve() {
        // We want to iterate through every combination of a and b. However, certain values of a and b can be ignored.
        // b must be prime because the formula will fail to produce a prime number for n = 0 otherwise.
        // Since b must be prime, and all primes are odd except for 2, a must be an odd number.
        // If a was even, the formula would give an even answer when n is also odd.
        
        int largest_product = 0;
        int max_primes = 0;
        ArrayList<Integer> primes = Primes.getPrimesUpUntil(1000);
        
        // Check all values for a and all positive values for b
        for (int a = 999; a >= -1000; a -= 2) { // Check all odd numbers
            for (int b = 0; b < primes.size(); b++) { // Check all primes
                int n = 0;
                int prime_counter = 0;
                while (Primes.isPrime((Math.pow(n, 2.0)) + a * n + primes.get(b))) {
                    prime_counter++;
                    n++;
                }
                if (prime_counter > max_primes) {
                    max_primes = prime_counter;
                    largest_product = a * primes.get(b);
                }
            }
        }
        
        // Check all values for a and all negative values for b
        for (int a = 999; a >= -1000; a -= 2) { // Check all odd numbers
            for (int b = 0; b < primes.size(); b++) { // Check all primes
                int n = 0;
                int prime_counter = 0;
                    while (Primes.isPrime((Math.pow(n, 2.0)) + a * n - primes.get(b))) {
                    prime_counter++;
                    n++;
                }
                if (prime_counter > max_primes) {
                    max_primes = prime_counter;
                    largest_product = a * primes.get(b);
                }
            }
        }
        
        return String.valueOf(largest_product);
    }
}
