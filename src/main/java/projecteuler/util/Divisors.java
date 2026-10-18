package projecteuler.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/** Divisors, coprimality and least common multiples. */
public final class Divisors {
    private Divisors() {}

    public static int findLCM (List<Integer> list) {
        HashMap<Integer, Integer> final_list = new HashMap<Integer, Integer>();
        
        for (int i : list) {
            HashMap<Integer, Integer> list_i = Primes.getPrimeFactors(i);
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

    // Returns the amount of divisors in the number
    public static int getNumDivisors(double d) {
        int divisors = 0;
        double square_root = Math.floor(Math.sqrt(d));
        for (int i = 1; i <= square_root; i++) {
            if (d % i == 0) {
                divisors += 2;
            }
        }
        if (Arithmetic.isSquare(d) && d != 0) {
            divisors--;
        }
        return divisors;
    }

    // Returns true if the two given numbers are co-prime
    public static boolean isRelativelyPrime(int x, int y) {
        HashMap<Integer, Integer> xFactors = Primes.getPrimeFactors(x);
        HashMap<Integer, Integer> yFactors = Primes.getPrimeFactors(y);
        boolean relPrime = true;
        Iterator<Integer> it = xFactors.keySet().iterator();
        while (it.hasNext()) {
            if (yFactors.containsKey(it.next())) {relPrime = false; break;}
        }                                                       
        return relPrime;
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
        if (Arithmetic.isSquare(d) && d != 0) {
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
        if (Arithmetic.isSquare(d) && d != 0) {
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
        if (Arithmetic.isSquare(d) && d != 0) {
            sum -= (int) Math.sqrt(d);
        }
        sum -= n; // Subtract the number itself as it is not a proper divisor.
        return sum;
    }
}
