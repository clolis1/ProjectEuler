package projecteuler.problems;

import java.io.File;
import java.io.FileNotFoundException;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.TreeSet;

import projecteuler.util.ComplexMath;

public class P55 implements Problem {
    public P55() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.\n" + 
        "\n" + 
        "Not all numbers produce palindromes so quickly. For example,\n" + 
        "\n" + 
        "349 + 943 = 1292,\n" + 
        "1292 + 2921 = 4213\n" + 
        "4213 + 3124 = 7337\n" + 
        "\n" + 
        "That is, 349 took three iterations to arrive at a palindrome.\n" + 
        "\n" + 
        "Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome.\n" +
        "A number that never forms a palindrome through the reverse and add process is called a Lychrel number.\n" +
        "Due to the theoretical nature of these numbers, and for the purpose of this problem,\n" +
        "we shall assume that a number is Lychrel until proven otherwise.\n" +
        "In addition you are given that for every number below ten-thousand,\n" +
        "it will either (i) become a palindrome in less than fifty iterations, or,\n" +
        "(ii) no one, with all the computing power that exists, has managed so far to map it to a palindrome." +
        "In fact, 10677 is the first number to be shown to require over fifty iterations\n" +
        "before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).\n" + 
        "\n" + 
        "Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.\n" + 
        "\n" + 
        "How many Lychrel numbers are there below ten-thousand?\n" + 
        "\n" + 
        "NOTE: Wording was modified slightly on 24 April 2007 to emphasise the theoretical nature of Lychrel numbers.");
        
        int lychrels = 0;
        
        for (int i = 1; i < 10000; i++) {
            int counter = 0;
            BigInteger bi = new BigInteger(Integer.toString(i));
            while (counter <= 50) {
                String s = "", t = bi.toString();
                for (int j = t.length() - 1; j >= 0; j--) s += t.charAt(j);
                bi = bi.add(new BigInteger(s));
                if (ComplexMath.isPalindrome(bi.toString())) break;
                counter++;
            }
            if (counter > 50) lychrels++;
        }
        
        System.out.println("\nAnswer: " + lychrels);
    }
}