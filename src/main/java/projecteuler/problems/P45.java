package projecteuler.problems;

import java.util.*;

public class P45 implements Problem {
    public P45() {
        super();
    }
    
    @Override
    public String solve() {
        long result = 0L;
        
        for (int i = 144; result == 0L; i++) {
            long hexag = (long) (2 * Math.pow(i, 2) - i);
            if ((1 + Math.sqrt(1 + 24 * hexag)) % 6 == 0) result = hexag;
        }
        
        return String.valueOf(result);
    }
}