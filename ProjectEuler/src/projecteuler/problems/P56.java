package projecteuler.problems;

import java.math.BigInteger;

public class P56 implements Problem {
    public P56() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("A googol (10100) is a massive number: one followed by one-hundred zeros;\n" +
        "100^100 is almost unimaginably large: one followed by two-hundred zeros.\n" +
        "Despite their size, the sum of the digits in each number is only 1.\n" + 
        "\n" + 
        "Considering natural numbers of the form, ab, where a, b < 100, what is the maximum digital sum?");
        
        int max_sum = 0;
        
        for (int i = 99; i > 0; i--) {
            BigInteger bi = new BigInteger(Integer.toString(i));
            for (int j = 99; j > 0; j--) {
                BigInteger pow = bi.pow(j);
                int sum = 0;
                String s = pow.toString();
                for (int k = 0; k < s.length(); k++) sum += Character.getNumericValue(s.charAt(k));
                max_sum = Math.max(max_sum, sum);
            }
        }
        
        System.out.println("\nAnswer: " + max_sum);
    }
}