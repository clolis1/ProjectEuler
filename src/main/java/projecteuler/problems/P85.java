package projecteuler.problems;

import projecteuler.util.*;


public class P85 implements Problem {
    public P85() {
        super();
    }
    
    @Override
    @SuppressWarnings("org.adfemg.audits.java.system-out-usage")
    public String solve() {
        double closest = 2000000, answer = 0;
        double i = 1;
        for (; i / 2 * (i + 1) < 2000000; i++) {
            double j = 1;
            // Check every combination of i and j below 2,000,000 rectangles
            for (; (i / 2 * (i + 1)) * (j / 2 * (j + 1)) < 2000000; j++) {
                if (Math.abs(2000000 - (i / 2 * (i + 1)) * (j / 2 * (j + 1))) < closest) {
                    closest = Math.abs(2000000 - (i / 2 * (i + 1)) * (j / 2 * (j + 1)));
                    answer = i * j;
                }
            }
            // Check the final combination of i and some j for the first combination after 2,000,000 rectangles
            if (Math.abs(2000000 - (i / 2 * (i + 1)) * (j / 2 * (j + 1))) < closest) {
                closest = Math.abs(2000000 - i * j);
                answer = i * j;
            }
        }
        
        return String.valueOf((long) answer);
    }
}