package projecteuler.problems;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;

public class P65 implements Problem {
    public P65() {
        super();
    }
    
    @Override
    public String solve() {
        int sum = 0;
        
        BigInteger prev_n = new BigInteger("2"), n = new BigInteger("3");
        int factor = 0;
        
        for (int i = 2; i < 100; i++) {
            if (i % 3 == 2) factor += 2;
            BigInteger temp = new BigInteger(n.toString());
            if (i % 3 == 2) n = n.multiply(new BigInteger(Integer.toString(factor))).add(prev_n);
            else n = n.add(prev_n);
            prev_n = temp;
        }
        
        for (char c : n.toString().toCharArray()) sum += Character.getNumericValue(c);
        
        return String.valueOf(sum);
    }
}