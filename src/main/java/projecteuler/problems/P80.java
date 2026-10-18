package projecteuler.problems;

import projecteuler.util.*;


public class P80 implements Problem {
    public P80() {
        super();
    }
    
    @Override
    public String solve() {
        int sum = 0;
        
        for (int i = 0; i <= 100; i++) {
            if (Arithmetic.isSquare(i)) continue;
            String s = Digits.decimalRepOfSquareRoot(i, 100);
            
            for (int j = 0; j < s.length(); j++) sum += Character.getNumericValue(s.charAt(j));
        }
        
        return String.valueOf(sum);
    }
}