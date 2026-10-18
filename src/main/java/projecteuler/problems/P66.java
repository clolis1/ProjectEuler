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
    public String solve() {
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
        
        return String.valueOf(result);
    }
}