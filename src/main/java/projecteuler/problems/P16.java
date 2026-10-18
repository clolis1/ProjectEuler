package projecteuler.problems;

import java.math.BigInteger;

public class P16 implements Problem{
    public P16() {
        super();
    }
    
    public String solve() {
        BigInteger num = new BigInteger("2");
        final int EXPONENT = 1000;
        
        String str = num.pow(EXPONENT).toString();
        int sum = 0;
        
        for (int i = 0; i < str.length(); i++) {
            sum += Character.getNumericValue(str.charAt(i));
        }
        
        return String.valueOf(sum);
    }
}