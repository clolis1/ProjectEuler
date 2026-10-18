package projecteuler.problems;

import java.util.Iterator;
import java.util.TreeSet;

public class P32 implements Problem {
    public P32() {
        super();
    }

    @Override
    public String solve() {
        // Check x * xxxx = xxxx and xx * xxx = xxxx
        
        TreeSet<Integer> products = new TreeSet<Integer>();
        int total_sum = 0;
        
        for (int i = 1; i < 100; i++) {
            for (int j = 100; j < 10000; j++) {
                if (i * j >= 10000) break;
                else if (i * j < 1000) continue;
                String eq = "" + i + j + i * j;
                if (eq.contains("1") &&
                    eq.contains("2") &&
                    eq.contains("3") &&
                    eq.contains("4") &&
                    eq.contains("5") &&
                    eq.contains("6") &&
                    eq.contains("7") &&
                    eq.contains("8") &&
                    eq.contains("9")    ) products.add(i * j);
            }
        }
        
        Iterator<Integer> it = products.iterator();
        while (it.hasNext()) total_sum += it.next();
        
        return String.valueOf(total_sum);
    }
}
