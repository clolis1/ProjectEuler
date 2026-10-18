package projecteuler.problems;

import java.util.*;

public class P44 implements Problem {
    public P44() {
        super();
    }
    
    @Override
    public String solve() {
        TreeSet<Long> pentagonals = new TreeSet<Long>(); // Pn, n
        
        long difference = Long.MAX_VALUE;
        pentagonals.add(1L);
        pentagonals.add(5L);
        pentagonals.add(12L);
        
        for (long i = 4; difference == Long.MAX_VALUE; i++) {
            long pk = i * (3 * i - 1) / 2;
            
            pentagonals.add(pk);
            Iterator<Long> it = pentagonals.descendingIterator();
            while (it.hasNext()){
                long pj = it.next();
                if (pj < 3 * i - 2) break;
                if (pentagonals.contains(pk - pj)) { // pk - pj is a pentagonal
                    double sqDiscrim = Math.sqrt(1.0 + 24.0 * (pk + pj));
                    if ((1.0 + sqDiscrim) % 6.0 == 0) {
                        difference = Math.min(difference, pk - pj);
                    }
                }
            }
        }
        return String.valueOf(difference);
    }
}