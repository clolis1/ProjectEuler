package projecteuler.problems;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;

public class P65 implements Problem {
    public P65() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The square root of 2 can be written as an infinite continued fraction.\n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        "The infinite continued fraction can be written, ,  indicates that 2 repeats ad infinitum. In a similar way, .\n" + 
        "\n" + 
        "It turns out that the sequence of partial values of continued fractions for square roots\n" +
        "provide the best rational approximations. Let us consider the convergents for .\n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        "Hence the sequence of the first ten convergents for  are:\n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        "What is most surprising is that the important mathematical constant,\n" + 
        ".\n" + 
        "\n" + 
        "The first ten terms in the sequence of convergents for e are:\n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        "The sum of digits in the numerator of the 10th convergent is .\n" + 
        "\n" + 
        "Find the sum of digits in the numerator of the 100th convergent of the continued fraction for .");
        
        int sum = 0;
        
        BigInteger prev_n = new BigInteger("2"), n = new BigInteger("3");
        int factor = 0;
        
        for (int i = 2; i < 100; i++) {
            if (i % 3 == 2) factor += 2;
            BigInteger temp = new BigInteger(n.toString());
            if (i % 3 == 2) n = n.multiply(new BigInteger(Integer.toString(factor))).add(prev_n);
            else n = n.add(prev_n);
            prev_n = temp;
        }
        
        for (char c : n.toString().toCharArray()) sum += Character.getNumericValue(c);
        
        System.out.println("\nAnswer: " + sum);
    }
}