package projecteuler.util;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
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
                if (counter % primes.get(i) == 0) { // number is not prime
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
    
    // Returns a TreeSet<Integer> containing prime numbers less than the given value
    public static TreeSet<Integer> getSetPrimesUpUntil (int n) {
        TreeSet<Integer> primes = new TreeSet<Integer>();
        primes.add(2);
        primes.add(3);
        
        int counter = 5; // We'll start at 5
        while (counter < n) {
            boolean prime = true;
            int sqrt_max = (int) Math.sqrt((double) counter); // Check for divisibility up to the square root
            Iterator<Integer> it = primes.iterator();
            int x = 0;
            while (it.hasNext() && x <= sqrt_max) {
                x = it.next();
                if (counter % x == 0) {
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
    
    // Checks if a number is prime
    // Code from https://www.geeksforgeeks.org/java-program-to-check-if-a-number-is-prime-or-not/
    public static boolean isPrime(int n)
    {
        // Corner cases
        if (n <= 1) return false;
        if (n <= 3) return true;
  
        // This is checked so that we can skip middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) return false;
  
        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        
        return true;
    }
    
    public static boolean isPrime(long n)
    {
        // Corner cases
        if (n <= 1) return false;
        if (n <= 3) return true;
    
        // This is checked so that we can skip middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) return false;
    
        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        
        return true;
    }
    
    // Checks if a number is prime (takes a double)
    // Code from https://www.geeksforgeeks.org/java-program-to-check-if-a-number-is-prime-or-not/
    public static boolean isPrime(double n)
    {
        // Corner cases
        if (n <= 1) return false;
        if (n <= 3) return true;
    
        // This is checked so that we can skip middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) return false;
    
        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        
        return true;
    }
    
    // Checks if a number is prime (takes a BigInteger)
    // Code from https://www.geeksforgeeks.org/java-program-to-check-if-a-number-is-prime-or-not/
    public static boolean isPrime(BigInteger b)
    {
        // Corner cases
        if (b.compareTo(BigInteger.ONE) <= 0) return false;
        if (b.compareTo(new BigInteger("3")) <= 0) return true;
    
        // This is checked so that we can skip middle five numbers in below loop
        if (b.mod(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0 || 
            b.mod(new BigInteger("3")).compareTo(BigInteger.ZERO) == 0) return false;
    
        for (BigInteger i = new BigInteger("5"); i.pow(2).compareTo(b) <= 0; i = i.add(new BigInteger("6"))) {
            if (b.mod(i).compareTo(BigInteger.ZERO) == 0 ||
                b.mod(i.add(new BigInteger("2"))).compareTo(BigInteger.ZERO) == 0) return false;
        }
        
        return true;
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
            pFactors.compute(2, (key, val) -> val + 1); // Increase multiplicity by one.
        }
        
        if (n % 3 == 0) pFactors.put(3, 0);
        while (n % 3 == 0) {
            n /= 3;
            pFactors.compute(3, (key, val) -> val + 1); // Increase multiplicity by one.
        }
        
        double square_root = Math.floor(Math.sqrt(n));
        double temp = n;
        for (int i = 5; i <= square_root; i += 2) {
            if (temp % i == 0) pFactors.put(i, 0); // add i to list of prime factors
            while (temp % i == 0) {
                pFactors.compute(i, (key, val) -> val + 1); // Increase multiplicity by one
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
            pFactors.replace(2L, pFactors.get(2L) + 1); // Increase multiplicity by one.
        }
        
        if (n % 3 == 0) pFactors.put(3L, 0L);
        while (n % 3 == 0) {
            n /= 3;
            pFactors.replace(3L, pFactors.get(3L) + 1); // Increase multiplicity by one.
        }
        
        double square_root = Math.floor(Math.sqrt(n));
        double temp = n;
        
        for (long i = 5; i <= square_root; i++) {
            if (temp % i == 0) pFactors.put(i, 0L); // add i to list of prime factors
            while (temp % i == 0) {
                pFactors.compute(i, (key, val) -> val + 1); // Increase multiplicity by one
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
        boolean relPrime = true;
        Iterator<Integer> it = xFactors.keySet().iterator();
        while (it.hasNext()) {
            if (yFactors.containsKey(it.next())) {relPrime = false; break;}
        }                                                       
        return relPrime;
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
    
    // Returns true if the given String is a palindrome
    public static boolean isPalindrome (String str) {
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
    
    // Returns true if the given number is pandigital
    public static boolean is1To9Pandigital(int x) {
        String str = "" + x;
        if (str.contains("1") &&
            str.contains("2") &&
            str.contains("3") &&
            str.contains("4") &&
            str.contains("5") &&
            str.contains("6") &&
            str.contains("7") &&
            str.contains("8") &&
            str.contains("9")    ) return true;
        return false;
    }
    
    // Returns true if the given number is pandigital
    public static boolean is1To9Pandigital(String str) {
        if (str.contains("1") &&
            str.contains("2") &&
            str.contains("3") &&
            str.contains("4") &&
            str.contains("5") &&
            str.contains("6") &&
            str.contains("7") &&
            str.contains("8") &&
            str.contains("9")    ) return true;
        return false;
    }
    
    // Returns true if the given number x is pandigital up to n
    public static boolean isNPandigital(int x, int n) {
        String str = "" + x;
        for (int i = 1; i <= n; i++) {
            if (!str.contains(Integer.toString(i))) return false;
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
        return divisors;
    }
    
    public static ArrayList<Integer> getProperDivisors(int n) {
        if (n == 0 || n == 1) return new ArrayList<Integer>();
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
        divisors.remove(1); // remove the number itself as it is not a proper divisor.
        return divisors;
    }
    
    public static int getSumOfProperDivisors(int n) {
        if (n == 0 || n == 1) return 0;
        double d = n;
        int sum = 0;
        double square_root = Math.floor(Math.sqrt(d));
        for (int i = 1; i <= square_root; i++) {
            if (d % i == 0) {
                sum += i;
                sum += (int) d / i;
            }
        }
        if (ComplexMath.isSquare(d) && d != 0) {
            sum -= (int) Math.sqrt(d);
        }
        sum -= n; // Subtract the number itself as it is not a proper divisor.
        return sum;
    }
    
    public static int factorial(int n) {
        if (n > 1) return n * factorial(n - 1);
        else return 1;
    }
    
    public static BigInteger bigFactorial(int n) {
        if (n > 1) return (new BigInteger(Integer.toString(n))).multiply(bigFactorial(n-1));
        else return new BigInteger("1");
    }
}
