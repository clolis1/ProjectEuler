package projecteuler.problems;

import java.math.BigInteger;

import projecteuler.util.ComplexMath;

public class P20 implements Problem{
    public P20() {
        super();
    }
    
    public void execute() {
        System.out.println("n! means n × (n ? 1) × ... × 3 × 2 × 1");
        System.out.println("For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,");
        System.out.println("and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.");
        System.out.println("Find the sum of the digits in the number 100!");
        
        final int FACT_MAX = 100;
        
        BigInteger num = ComplexMath.bigFactorial(FACT_MAX); // Get the value of FACT_MAX!
        
        int sum = 0;
        
        while (num.compareTo(BigInteger.TEN) >= 0) { // num is greater than or equal to 10
            sum += num.mod(BigInteger.TEN).intValue();
            num = num.divide(BigInteger.TEN);
        }
        
        sum += num.intValue();
        
        System.out.println("\nAnswer: " + sum);
    }
}
