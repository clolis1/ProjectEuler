package projecteuler.problems;

import java.io.File;

import java.math.BigInteger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import java.util.TreeSet;

import projecteuler.util.Primes;
import projecteuler.util.Lexography;


public class P70 implements Problem {
    public P70() {
        super();
    }
    
    @Override
    public String solve() {
        int result = Integer.MAX_VALUE;
        int result_totient = 1;
        
        for (int i = 2; i < 10000000; i++) {
            int totient = Primes.eulersTotient(i);
            if ((double) result / result_totient < (double) i / totient) continue;
            char[] str_i = Integer.toString(i).toCharArray(), str_t = Integer.toString(totient).toCharArray();
            if (str_i.length == str_t.length) {
                Arrays.sort(str_i);
                Arrays.sort(str_t);
                boolean permutation = true;
                for (int j = 0; j < str_i.length; j++) {
                    if (str_i[j] != str_t[j]) {
                        permutation = false;
                        break;
                    }
                }
                if (permutation) {
                    result = i;
                    result_totient = totient;
                }
            }
        }
        
        return String.valueOf(result);
    }
}