package projecteuler.problems;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class P66 implements Problem {
    public P66() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Consider quadratic Diophantine equations of the form:\n" + 
        "\n" + 
        "x2 – Dy2 = 1\n" + 
        "\n" + 
        "For example, when D=13, the minimal solution in x is 6492 – 13×1802 = 1.\n" + 
        "\n" + 
        "It can be assumed that there are no solutions in positive integers when D is square.\n" + 
        "\n" + 
        "By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:\n" + 
        "\n" + 
        "32 – 2×22 = 1\n" + 
        "22 – 3×12 = 1\n" + 
        "92 – 5×42 = 1\n" + 
        "52 – 6×22 = 1\n" + 
        "82 – 7×32 = 1\n" + 
        "\n" + 
        "Hence, by considering minimal solutions in x for D ? 7, the largest x is obtained when D=5.\n" + 
        "\n" + 
        "Find the value of D ? 1000 in minimal solutions of x for which the largest value of x is obtained.");
        
        BigInteger max_n = BigInteger.ZERO;
        int result = 0;
        
        for (int i = 1; i < 1001; i++) {
            if (Math.sqrt(i) == (int) Math.sqrt(i)) continue;
            
            BigInteger limit = new BigInteger(Integer.toString((int) Math.sqrt(i)));
            
            BigInteger m = BigInteger.ZERO;
            BigInteger d = BigInteger.ONE;
            BigInteger a = limit;
            
            BigInteger numerator_prev = BigInteger.ONE;
            BigInteger numerator = a;
             
            BigInteger denominator_prev = BigInteger.ZERO;
            BigInteger denominator = BigInteger.ONE;
            
            while (numerator.multiply(numerator).subtract(denominator.multiply(denominator).multiply(new BigInteger(Integer.toString(i)))).compareTo(BigInteger.ONE) != 0) {
                m = d.multiply(a).subtract(m);
                d = (new BigInteger(Integer.toString(i))).subtract(m.pow(2)).divide(d);
                a = (limit.add(m)).divide(d);
                
                BigInteger temp = numerator_prev;
                numerator_prev = numerator;
                numerator = a.multiply(numerator_prev).add(temp);
                
                temp = denominator_prev;
                denominator_prev = denominator;
                denominator = a.multiply(denominator_prev).add(temp);
            }
            if (numerator.compareTo(max_n) > 0) {
                max_n = numerator;
                result = i;
            }
        }
        
        System.out.println("\nAnswer: " + result);
    }
}