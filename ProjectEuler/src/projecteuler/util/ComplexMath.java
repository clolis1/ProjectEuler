package projecteuler.util;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class ComplexMath {
    public ComplexMath() {
        super();
    }
    
    public static int findLCM (List<Integer> list) {
        HashMap<Integer, Integer> final_list = new HashMap<Integer, Integer>();
        
        for (int i : list) {
            HashMap<Integer, Integer> list_i = ComplexMath.getPrimeFactors(i);
            list_i.forEach((key, val) -> {
                if (final_list.containsKey(key) && val > final_list.get(key)) {
                    final_list.replace(key, val);
                }
                else if (!final_list.containsKey(key)) {
                    final_list.put(key, val);
                }
            });
        }
        
        AtomicInteger lcm = new AtomicInteger(1);
        final_list.forEach((key, val) -> lcm.set(lcm.get() * (int) Math.pow(key, val)));
        
        return lcm.get();
    }
    
    // Returns an ArrayList<Integer> containing prime numbers less than the given value
    public static ArrayList<Integer> getPrimesUpUntil (int n) {
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        primes.add(3);
        
        int counter = 5; // We'll start at 5
        while (counter < n) {
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
            counter += 2; // Check next number
        }
        return primes;
    }
    
    // Returns the amount of prime factors in d
    public static int numPrimeFactors (double d) {
        int num_prime_factors = 0;
        double saved = d;
        while (d % 2 == 0) {
            double half = d / 2.0;
            d = half;
            num_prime_factors++;
        }
        while (d % 3 == 0) {
            double third = d / 3.0;
            d = third;
            num_prime_factors++;
        }
        
        if (d == 1) {return num_prime_factors;}
        
        double square_root = Math.floor(Math.sqrt(d));
        double temp = d;
        for (int i = 5; i <= square_root; i++) {
            if (temp % i == 0) {
                num_prime_factors++; // Add one to counter
                temp = temp / i; // temp is divided by one of its prime factors.
                square_root = Math.floor(Math.sqrt(temp)); // New square root value to save time.
                i--; // There may be multiple instances of the same factor.
            }
        }
        num_prime_factors++; // The remainder of temp is prime, so add 1 to the counter
        return num_prime_factors;
    }
    
    /**
     * Returns an HashMap<Integer, Integer> of prime factors; includes prime factors and their multiplicity.
     *
     * @param n The target number to determine the prime factors of.
     * @return A HashMap of Integer values. The key is the prime factor, the value is its multiplicity as a component of n's prime factorization.
     */
    @SuppressWarnings("oracle.jdeveloper.java.comment-html-is-unterminated")
    public static HashMap<Integer, Integer> getPrimeFactors (int n) {
        
        HashMap<Integer, Integer> pFactors = new HashMap<Integer, Integer>();
        
        if (n == 1 || n == 0) {
            return pFactors;
        }
        
        if (n % 2 == 0) pFactors.put(2, 0);
        while (n % 2 == 0) {
            n /= 2;
            pFactors.replace(2, pFactors.get(2) + 1); // Increase multiplicity by one.
        }
        
        if (n % 3 == 0) pFactors.put(3, 0);
        while (n % 3 == 0) {
            n /= 3;
            pFactors.replace(3, pFactors.get(3) + 1); // Increase multiplicity by one.
        }
        
        double square_root = Math.floor(Math.sqrt(n));
        double temp = n;
        
        for (int i = 5; i <= square_root; i++) {
            if (temp % i == 0) pFactors.put(i, 0); // add i to list of prime factors
            while (temp % i == 0) {
                pFactors.replace(i, pFactors.get(i) + 1); // Increase multiplicity by one
                temp /= i; // temp is divided by one of its prime factors.
                square_root = Math.floor(Math.sqrt(temp)); // New square root value to save time.
            }
        }
        if (temp != 1.0) pFactors.put((int) temp, 1); // Using this method, there cannot be another factor greater than than this quotient.
        return pFactors;
    }

    /**
     * Returns an HashMap<Long, Long> of prime factors; includes prime factors and their multiplicity.
     *
     * @param n The target number to determine the prime factors of.
     * @return A HashMap of Integer values. The key is the prime factor, the value is its multiplicity as a component of n's prime factorization.
     */
    @SuppressWarnings("oracle.jdeveloper.java.comment-html-is-unterminated")
    public static HashMap<Long, Long> getPrimeFactors (long n) {
        
        HashMap<Long, Long> pFactors = new HashMap<Long, Long>();
        
        if (n == 1 || n == 0) {
            return pFactors;
        }
        
        if (n % 2 == 0) pFactors.put(2L, 0L);
        while (n % 2 == 0) {
            n /= 2;
            pFactors.replace(2L, pFactors.get(2) + 1); // Increase multiplicity by one.
        }
        
        if (n % 3 == 0) pFactors.put(3L, 0L);
        while (n % 3 == 0) {
            n /= 3;
            pFactors.replace(3L, pFactors.get(3) + 1); // Increase multiplicity by one.
        }
        
        double square_root = Math.floor(Math.sqrt(n));
        double temp = n;
        
        for (long i = 5; i <= square_root; i++) {
            if (temp % i == 0) pFactors.put(i, 0L); // add i to list of prime factors
            while (temp % i == 0) {
                pFactors.replace(i, pFactors.get(i) + 1); // Increase multiplicity by one
                temp /= i; // temp is divided by one of its prime factors.
                square_root = Math.floor(Math.sqrt(temp)); // New square root value to save time.
            }
        }
        if (temp != 1.0) pFactors.put((long) temp, 1L); // Using this method, there cannot be another factor greater than than this quotient.
        return pFactors;
    }

    // Returns the amount of divisors in the number
    public static int getNumDivisors(double d) {
        int divisors = 0;
        double square_root = Math.floor(Math.sqrt(d));
        for (int i = 1; i <= square_root; i++) {
            if (d % i == 0) {
                divisors += 2;
            }
        }
        if (ComplexMath.isSquare(d) && d != 0) {
            divisors--;
        }
        return divisors;
    }
    
    // Returns true if the two given numbers are co-prime
    public static boolean isRelativelyPrime(int x, int y) {
        HashMap<Integer, Integer> xFactors = ComplexMath.getPrimeFactors(x);
        HashMap<Integer, Integer> yFactors = ComplexMath.getPrimeFactors(y);
        
        AtomicReference<Boolean> relPrime = new AtomicReference<Boolean>(false);
        xFactors.forEach((key, val) -> {if (yFactors.containsKey(key)) relPrime.set(true);});
 
        return relPrime.get();
    }
    
    // Returns true if the given number is a palindrome
    public static boolean isPalindrome (int x) {
        String str = Integer.toString(x);
        
        int forwards = 0;
        int backwards = str.length() - 1;
        
        while (forwards < str.length()) {
            if (str.charAt(forwards) != str.charAt(backwards)) {
                return false;
            }
            else {
                forwards++;
                backwards--;
            }
        }
        return true;
    }
    
    // Returns true if the given number is a square number
    public static boolean isSquare (double x) {
        if (Math.sqrt(x) % 1 == 0) {
            return true;
        }
        return false;
    }
    
    public static ArrayList<Integer> getDivisors(int n) {
        double d = n;
        ArrayList<Integer> divisors = new ArrayList<Integer>();
        double square_root = Math.floor(Math.sqrt(d));
        for (int i = 1; i <= square_root; i++) {
            if (d % i == 0) {
                divisors.add(i);
                divisors.add((int) d / i);
            }
        }
        if (ComplexMath.isSquare(d) && d != 0) {
            divisors.remove(divisors.size() - 1);
        }

/**
        HashMap<Integer, Integer> temp = ComplexMath.getPrimeFactors(n);
        
        
        
        // Use list of prime factors to determine divisors.
        

       
        for (int i = 1; i < temp.size() / 2; i++) {
            temp.forEach((e) -> ;
        }
*/
        return divisors;
    }
  
    public static BigInteger bigFactorial(int n) {
        if (n > 1) return (new BigInteger(Integer.toString(n))).multiply(bigFactorial(n-1));
        else return new BigInteger("1");
    }
}
