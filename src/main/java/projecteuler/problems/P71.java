package projecteuler.problems;

import java.io.File;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import java.util.TreeSet;

import projecteuler.util.Divisors;
import projecteuler.util.Lexography;


public class P71 implements Problem {
    public P71() {
        super();
    }
    
    @Override
    public String solve() {
        final double THREE_SEVENTHS = 3.0 / 7.0;
        int closest_numerator = 0;
        double closest_decimal = 0.0;
        
        for (int i = 1; i <= 1000000; i++) {
            int numerator = i * 3 / 7;
            
            while ((double) numerator / i < THREE_SEVENTHS) {
                if ((double) numerator / i > closest_decimal && Divisors.isRelativelyPrime(numerator, i)) {
                    closest_numerator = numerator;
                    closest_decimal = (double) numerator / i;
                }
                numerator++;
            }
        }
        
        return String.valueOf(closest_numerator);
    }
}