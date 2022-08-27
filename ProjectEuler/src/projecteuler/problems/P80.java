package projecteuler.problems;

import projecteuler.util.*;


public class P80 implements Problem {
    public P80() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("It is well known that if the square root of a natural number is not an integer,\n" +
        "then it is irrational.\n" +
        "The decimal expansion of such square root is infinite without any repeating pattern at all.\n" +
        "\n" + 
        "The square root of two is 1.41421356237309504880...,\n" + 
        "and the digital sum of the first one hundred decimal digits is 475.\n" + 
        "\n" + 
        "For the first one hundred natural numbers,\n" + 
        "find the total of the digital sums of the first one hundred decimal digits for all the irrational square roots.\n");
        
        int sum = 0;
        
        for (int i = 0; i <= 100; i++) {
            if (ComplexMath.isSquare(i)) continue;
            String s = ComplexMath.decimalRepOfSquareRoot(i, 100);
            
            for (int j = 0; j < s.length(); j++) sum += Character.getNumericValue(s.charAt(j));
        }
        
        System.out.println("\nAnswer: " + sum);
    }
}