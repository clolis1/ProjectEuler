package projecteuler.problems;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;

public class P64 implements Problem {
    public P64() {
        super();
    }
    
    @Override
    public String solve() {
        int odd_periods = 0;
        
        for (int i = 1; i < 10000; i++) {
            if (Math.sqrt(i) == (int) Math.sqrt(i)) continue;
            int a = (int) Math.sqrt(i), d = 1, m = 0;
            List<List<Integer>> triplets = new ArrayList<List<Integer>>();
            boolean old_triplet = false;
            while (!old_triplet) {
                
                m = d * a - m;
                d = (i - m * m) / d;
                a = (int) ((Math.sqrt(i) + m) / d);
                
                for (List<Integer> list : triplets) {
                    if (list.get(0) == a && list.get(1) == d && list.get(2) == m) old_triplet = true;
                }
                if (!old_triplet) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(a);
                    temp.add(d);
                    temp.add(m);
                    triplets.add(temp);
                }
            }
            if (triplets.size() % 2 == 1) odd_periods++;
        }
        
        return String.valueOf(odd_periods);
    }
}