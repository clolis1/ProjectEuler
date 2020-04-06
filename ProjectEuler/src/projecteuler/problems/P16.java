package projecteuler.problems;

import java.math.BigInteger;

public class P16 implements Problem{
    public P16() {
        super();
    }
    
    public void execute() {
        System.out.println("2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.\n" + 
        "\n" + 
        "What is the sum of the digits of the number 2^1000?");
        
        BigInteger num = new BigInteger("2");
        final int EXPONENT = 1000;
        
        String str = num.pow(EXPONENT).toString();
        int sum = 0;
        
        for (int i = 0; i < str.length(); i++) {
            sum += Character.getNumericValue(str.charAt(i));
        }
        
        System.out.println("\nAnswer: " + sum);
    }
}