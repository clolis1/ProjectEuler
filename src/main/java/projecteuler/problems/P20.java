package projecteuler.problems;

import java.math.BigInteger;

import projecteuler.util.Arithmetic;

public class P20 implements Problem{
    public P20() {
        super();
    }
    
    public String solve() {
        final int FACT_MAX = 100;
        
        BigInteger num = Arithmetic.bigFactorial(FACT_MAX); // Get the value of FACT_MAX!
        
        int sum = 0;
        
        while (num.compareTo(BigInteger.TEN) >= 0) { // num is greater than or equal to 10
            sum += num.mod(BigInteger.TEN).intValue();
            num = num.divide(BigInteger.TEN);
        }
        
        sum += num.intValue();
        
        return String.valueOf(sum);
    }
}
