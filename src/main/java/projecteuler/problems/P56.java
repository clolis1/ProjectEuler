package projecteuler.problems;

import java.math.BigInteger;

public class P56 implements Problem {
    public P56() {
        super();
    }
    
    @Override
    public String solve() {
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
        
        return String.valueOf(max_sum);
    }
}