package projecteuler.problems;

import java.io.File;

import java.math.BigInteger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import java.util.TreeSet;

import projecteuler.util.ComplexMath;
import projecteuler.util.Lexography;


public class P69 implements Problem {
    public P69() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Euler's Totient function, ?(n) [sometimes called the phi function], is used to determine the number of numbers less than n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, ?(9)=6.\n" + 
        "\n" + 
        "n	Relatively Prime	?(n)	n/?(n)\n" + 
        "2	1	1	2\n" + 
        "3	1,2	2	1.5\n" + 
        "4	1,3	2	2\n" + 
        "5	1,2,3,4	4	1.25\n" + 
        "6	1,5	2	3\n" + 
        "7	1,2,3,4,5,6	6	1.1666...\n" + 
        "8	1,3,5,7	4	2\n" + 
        "9	1,2,4,5,7,8	6	1.5\n" + 
        "10	1,3,7,9	4	2.5\n" + 
        "It can be seen that n=6 produces a maximum n/?(n) for n ? 10.\n" + 
        "\n" + 
        "Find the value of n ? 1,000,000 for which n/?(n) is a maximum." + 
        "\n");
        
        int result = 1;
        TreeSet<Integer> primes = ComplexMath.getSetPrimesUpUntil(10000);
        for (Integer i : primes) {
            if (result * i < 1000000) result *= i;
            else break;
        }
        
        System.out.println("\nAnswer: " + result);
    }
}