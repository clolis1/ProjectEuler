package projecteuler.problems;

import java.util.HashMap;

import projecteuler.util.Primes;

public class P33 implements Problem {
    public P33() {
        super();
    }

    static int denominator_product = 1;

    @Override
    public String solve() {
        int d_product = 1, n_product = 1;
        
        for (int i = 11; i < 99; i++) {
            if (i % 10 == 0) i++;
            for (int j = i + 1; j < 100; j++) {
                if (j % 10 == 0) j++;
                if (i % 10 == j % 10 && (double) i / (double) j == (double) (i / 10) / (double) (j / 10)) {n_product *= i; d_product *= j;}
                else if (i % 10 == j / 10 && (double) i / (double) j == (double) (i / 10) / (double) (j % 10)) {n_product *= i; d_product *= j;}
                else if (i / 10 == j % 10 && (double) i / (double) j == (double) (i % 10) / (double) (j / 10)) {n_product *= i; d_product *= j;}
                else if (i / 10 == j / 10 && (double) i / (double) j == (double) (i % 10) / (double) (j % 10)) {n_product *= i; d_product *= j;}
            }
        }
        
        HashMap<Integer, Integer> n_factors = Primes.getPrimeFactors(n_product);
        HashMap<Integer, Integer> d_factors = Primes.getPrimeFactors(d_product);
        
        n_factors.forEach((key, val) -> {
            if (d_factors.computeIfPresent(key, (k, v) -> v - val) != null && d_factors.get(key) <= 0) d_factors.remove(key);
        });
        
        d_factors.forEach((key, val) -> { 
            denominator_product *= (int) Math.pow(key, val);
        });
        
        return String.valueOf(denominator_product);
    }
}