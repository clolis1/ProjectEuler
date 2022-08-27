package projecteuler.problems;

import java.io.File;

import java.math.BigInteger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import java.util.TreeSet;

import projecteuler.util.ComplexMath;
import projecteuler.util.Lexography;


public class P70 implements Problem {
    public P70() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Euler's Totient function, ?(n) [sometimes called the phi function],\n" +
        "is used to determine the number of positive numbers less than or equal to n which are relatively prime to n.\n" +
        "For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, ?(9)=6.\n" + 
        "The number 1 is considered to be relatively prime to every positive number, so ?(1)=1.\n" + 
        "\n" + 
        "Interestingly, ?(87109)=79180, and it can be seen that 87109 is a permutation of 79180.\n" + 
        "\n" + 
        "Find the value of n, 1 < n < 107, for which ?(n) is a permutation of n and the ratio n/?(n) produces a minimum.");
        
        int result = Integer.MAX_VALUE;
        int result_totient = 1;
        
        for (int i = 2; i < 10000000; i++) {
            int totient = ComplexMath.eulersTotient(i);
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
        
        System.out.println("\nAnswer: " + result);
    }
}