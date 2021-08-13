package projecteuler.problems;

import java.util.HashMap;

import projecteuler.util.ComplexMath;

public class P33 implements Problem {
    public P33() {
        super();
    }

    static int denominator_product = 1;

    @Override
    public void execute() {
        System.out.println("The fraction 49/98 is a curious fraction,\n" + 
        "as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8,\n" + 
        "which is correct, is obtained by cancelling the 9s.\n" + 
        "\n" + 
        "We shall consider fractions like, 30/50 = 3/5, to be trivial examples.\n" + 
        "\n" + 
        "There are exactly four non-trivial examples of this type of fraction, less than one in value,\n" + 
        "and containing two digits in the numerator and denominator.\n" + 
        "\n" + 
        "If the product of these four fractions is given in its lowest common terms, find the value of the denominator.\n");
        
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
        
        HashMap<Integer, Integer> n_factors = ComplexMath.getPrimeFactors(n_product);
        HashMap<Integer, Integer> d_factors = ComplexMath.getPrimeFactors(d_product);
        
        n_factors.forEach((key, val) -> {
            if (d_factors.computeIfPresent(key, (k, v) -> v - val) != null && d_factors.get(key) <= 0) d_factors.remove(key);
        });
        
        d_factors.forEach((key, val) -> { 
            denominator_product *= (int) Math.pow(key, val);
        });
        
        System.out.println("\nAnswer:" + denominator_product);
    }
}