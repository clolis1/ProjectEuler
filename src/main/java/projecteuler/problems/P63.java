package projecteuler.problems;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;

public class P63 implements Problem {
    public P63() {
        super();
    }
    
    @Override
    public String solve() {
        int nums = 0;
        
        for (BigInteger i = BigInteger.ONE; i.compareTo(BigInteger.TEN) < 0; i = i.add(BigInteger.ONE)) {
            int counter = i.toString().length() - 1;
            for (BigInteger j = i; j.toString().length() >= counter; j = j.multiply(i)) {
                counter++;
                if (j.toString().length() == counter) nums++;
            }
        }
        
        return String.valueOf(nums);
    }
}