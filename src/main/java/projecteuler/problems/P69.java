package projecteuler.problems;

import java.io.File;

import java.math.BigInteger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import java.util.TreeSet;

import projecteuler.util.Primes;
import projecteuler.util.Lexography;


public class P69 implements Problem {
    public P69() {
        super();
    }
    
    @Override
    public String solve() {
        int result = 1;
        TreeSet<Integer> primes = Primes.getSetPrimesUpUntil(10000);
        for (Integer i : primes) {
            if (result * i < 1000000) result *= i;
            else break;
        }
        
        return String.valueOf(result);
    }
}