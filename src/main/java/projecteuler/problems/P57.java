package projecteuler.problems;

import java.math.BigInteger;

public class P57 implements Problem {
    public P57() {
        super();
    }
    
    @Override
    public String solve() {
        BigInteger previous_denominator = BigInteger.ZERO, current_denominator = BigInteger.ONE,
            current_numerator = BigInteger.ONE;
        
        final BigInteger TWO = new BigInteger("2");
        
        int counter = 0;
        
        for (int i = 1; i < 1001; i++) {
            BigInteger temp = current_denominator;
            current_denominator = current_denominator.multiply(TWO).add(previous_denominator);
            previous_denominator = temp;
            current_numerator = current_numerator.add(previous_denominator.multiply(TWO));
            if (current_numerator.toString().length() > current_denominator.toString().length()) counter++;
        }
        
        
        return String.valueOf(counter);
    }
}