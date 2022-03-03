package projecteuler.problems;

import java.util.*;

import projecteuler.util.ComplexMath;

public class P47 implements Problem {
    public P47() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The first two consecutive numbers to have two distinct prime factors are:\n" + 
        "\n" + 
        "14 = 2 × 7\n" + 
        "15 = 3 × 5\n" + 
        "\n" + 
        "The first three consecutive numbers to have three distinct prime factors are:\n" + 
        "\n" + 
        "644 = 2² × 7 × 23\n" + 
        "645 = 3 × 5 × 43\n" + 
        "646 = 2 × 17 × 19.\n" + 
        "\n" + 
        "Find the first four consecutive integers to have four distinct prime factors each.\n" +
        "What is the first of these numbers?");
        
        long i = 2L;
        int counter = 0;
        while (counter < 4) {
            if (ComplexMath.getPrimeFactors(i).size() != 4) counter = 0;
            else counter++;
            i++;
        }
        
        System.out.println("Answers: " + (i - 4L));
    }
}