package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.ComplexMath;

public class P27 implements Problem {
    public P27() {
        super();
    }

    @Override
    public void execute() {
        System.out.println("Euler discovered the remarkable quadratic formula:\n" + 
        "n^2 + n + 41\n" + 
        "\n" + 
        "It turns out that the formula will produce 40 primes for the consecutive integer values 0 <= n <= 39. " +
        "However, when n = 40, 40^2 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, " +
        "and certainly when n = 41, 41^2 + 41 + 41 is clearly divisible by 41.\n" + 
        "\n" + 
        "The incredible formula n^2 - 79n + 1601 was discovered, which produces 80 primes for the consecutive values 0 <= n <= 79. " + 
        "The product of the coefficients, ?79 and 1601, is ?126479.\n" + 
        "\n" + 
        "Considering quadratics of the form:\n" + 
        "\n" + 
        "n^2 + an + b, where |a| <= 1000 and |b| <= 1000\n" + 
        "\n" + 
        "where |n| is the modulus/absolute value of n\n" + 
        "e.g. |11| = 11 and |-4| = 4\n" + 
        "Find the product of the coefficients, a and b, " +
        "for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.");
        
        // We want to iterate through every combination of a and b. However, certain values of a and b can be ignored.
        // b must be prime because the formula will fail to produce a prime number for n = 0 otherwise.
        // Since b must be prime, and all primes are odd except for 2, a must be an odd number.
        // If a was even, the formula would give an even answer when n is also odd.
        
        int largest_product = 0;
        int max_primes = 0;
        ArrayList<Integer> primes = ComplexMath.getPrimesUpUntil(1000);
        
        // Check all values for a and all positive values for b
        for (int a = 999; a >= -1000; a -= 2) { // Check all odd numbers
            for (int b = 0; b < primes.size(); b++) { // Check all primes
                int n = 0;
                int prime_counter = 0;
                while (ComplexMath.isPrime((Math.pow(n, 2.0)) + a * n + primes.get(b))) {
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
                    while (ComplexMath.isPrime((Math.pow(n, 2.0)) + a * n - primes.get(b))) {
                    prime_counter++;
                    n++;
                }
                if (prime_counter > max_primes) {
                    max_primes = prime_counter;
                    largest_product = a * primes.get(b);
                }
            }
        }
        
        System.out.println("\nAnswer:" + largest_product);
    }
}
