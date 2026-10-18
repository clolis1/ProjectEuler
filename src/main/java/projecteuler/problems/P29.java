package projecteuler.problems;

import java.math.BigInteger;
import java.util.TreeSet;


public class P29 implements Problem {
    public P29() {
        super();
    }

    @Override
    public String solve() {
        TreeSet<BigInteger> nums = new TreeSet<BigInteger>();
        
        for (int i = 2; i <= 100; i++) {
            for (int j = 2; j <= 100; j++) {
                nums.add(BigInteger.valueOf(i).pow(j));
            }
        }
        
        return String.valueOf(nums.size());
    }
}
