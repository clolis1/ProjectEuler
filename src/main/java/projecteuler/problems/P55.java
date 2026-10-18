package projecteuler.problems;

import java.io.File;
import java.io.FileNotFoundException;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.TreeSet;

import projecteuler.util.Digits;

public class P55 implements Problem {
    public P55() {
        super();
    }
    
    @Override
    public String solve() {
        int lychrels = 0;
        
        for (int i = 1; i < 10000; i++) {
            int counter = 0;
            BigInteger bi = new BigInteger(Integer.toString(i));
            while (counter <= 50) {
                String s = "", t = bi.toString();
                for (int j = t.length() - 1; j >= 0; j--) s += t.charAt(j);
                bi = bi.add(new BigInteger(s));
                if (Digits.isPalindrome(bi.toString())) break;
                counter++;
            }
            if (counter > 50) lychrels++;
        }
        
        return String.valueOf(lychrels);
    }
}