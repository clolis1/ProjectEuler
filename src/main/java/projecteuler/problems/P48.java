package projecteuler.problems;

import java.util.*;



public class P48 implements Problem {
    public P48() {
        super();
    }
    
    @Override
    public String solve() {
        long lastTen = 0L;
        
        for (int i = 1; i < 1001; i++) {
            long l = 1L;
            for (int j = 0; j < i; j++) {
                l = (l * i) % 10000000000L;
            }
            lastTen = (lastTen + l) % 10000000000L;
        }
        
        return String.valueOf(lastTen);
    }
}