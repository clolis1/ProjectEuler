package projecteuler.problems;

import java.util.Iterator;
import java.util.TreeSet;

import projecteuler.util.Primes;

public class P35 implements Problem {
    public P35() {
        super();
    }
    
    @Override
    public String solve() {
        TreeSet<Integer> primes = new TreeSet<Integer>(Primes.getPrimesUpUntil(1000000));
        Iterator<Integer> it = primes.iterator();
        
        int cp_counter = 0;
        int num = 0;
        
        outer:
        while (it.hasNext()) {
            num = it.next();
            boolean circular = true;
            for (int i = 0; i < num / 10; i++) {
                num += (int) Math.pow(10, ("" + num).length()) * (num % 10);
                num /= 10;
                if (!primes.contains(num)) {circular = false; break;}
            }
            if (circular) cp_counter++;
        }
        
        return String.valueOf(cp_counter);
    }
}