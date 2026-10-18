package projecteuler.problems;

import java.util.*;

import projecteuler.util.Primes;

public class P49 implements Problem {
    public P49() {
        super();
    }
    
    @Override
    public String solve() {
        TreeSet<Integer> primes = new TreeSet<Integer>();
        primes.addAll(Primes.getSetPrimesUpUntil(10000).tailSet(999));
        
        String result = "";
        
        for (int i : primes) {
            if (i == 1487) continue;
            char[] temp = Integer.toString(i).toCharArray();
            Arrays.sort(temp);
            String si = new String(temp);
            Iterator<Integer> it = primes.tailSet(i, false).iterator();
            while (it.hasNext()) {
                int j = it.next();
                char[] temp2 = Integer.toString(j).toCharArray();
                Arrays.sort(temp2);
                String sj = new String(temp2);
                if (!si.equals(sj) || !primes.contains(j + j - i)) continue;
                char[] temp3 = Integer.toString(j + j - i).toCharArray();
                Arrays.sort(temp3);
                String sk = new String(temp3);
                if (si.equals(sk)) {
                    result = Integer.toString(i) + Integer.toString(j) + Integer.toString(j + j - i);
                    break;
                }
            }
            if (result.length() != 0) break;
        }
        
        return String.valueOf(result);
    }
}