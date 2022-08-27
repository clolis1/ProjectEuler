package projecteuler.problems;

import java.io.File;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import java.util.TreeSet;

import projecteuler.util.ComplexMath;
import projecteuler.util.Lexography;


public class P71 implements Problem {
    public P71() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Consider the fraction, n/d, where n and d are positive integers.\n" +
        "If n<d and HCF(n,d)=1, it is called a reduced proper fraction.\n" + 
        "\n" + 
        "If we list the set of reduced proper fractions for d ? 8 in ascending order of size, we get:\n" + 
        "\n" + 
        "1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8\n" + 
        "\n" + 
        "It can be seen that 2/5 is the fraction immediately to the left of 3/7.\n" + 
        "\n" + 
        "By listing the set of reduced proper fractions for d ? 1,000,000 in ascending order of size,\n" +
        "find the numerator of the fraction immediately to the left of 3/7.");
        
        final double THREE_SEVENTHS = 3.0 / 7.0;
        int closest_numerator = 0;
        double closest_decimal = 0.0;
        
        for (int i = 1; i <= 1000000; i++) {
            int numerator = i * 3 / 7;
            
            while ((double) numerator / i < THREE_SEVENTHS) {
                if ((double) numerator / i > closest_decimal && ComplexMath.isRelativelyPrime(numerator, i)) {
                    closest_numerator = numerator;
                    closest_decimal = (double) numerator / i;
                }
                numerator++;
            }
        }
        
        System.out.println("\nAnswer: " + closest_numerator);
    }
}