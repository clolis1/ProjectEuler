package projecteuler.problems;

import java.util.HashMap;

import java.util.Map;

import projecteuler.util.Divisors;

public class P75 implements Problem {
    public P75() {
        super();
    }
    
    @Override
    public String solve() {
        int m = 2, n = 1, a = m * m - n * n, b = 2 * m * n, c = m * m + n * n;
        
        Map<Integer, Integer> perimeters = new HashMap<Integer, Integer>();
        
        while (a + b + c <= 1500000) {
            m = n + 1;
            a = m * m - n * n;
            b = 2 * m * n;
            c = m * m + n * n;
            
            while (a + b + c <= 1500000) {
                if (Divisors.isRelativelyPrime(m, n)) {
                    if (perimeters.containsKey(a + b + c)) perimeters.replace(a + b + c, perimeters.get(a + b + c) + 1);
                    else perimeters.put(a + b + c, 1);
                }
                m += 2;
                 
                a = m * m - n * n;
                b = 2 * m * n;
                c = m * m + n * n;
            }
            n++;
            m = n + 1;
                
            a = m * m - n * n;
            b = 2 * m * n;
            c = m * m + n * n;
        }
        
        int[] result = new int[1];
        
        Map<Integer, Integer> updated_perimeters = new HashMap<Integer, Integer>();
        
        perimeters.forEach((k, v) -> { 
            int temp = k;
            while (temp <= 1500000) {
                if (updated_perimeters.containsKey(temp)) updated_perimeters.replace(temp, updated_perimeters.get(temp) + 1);
                else if (perimeters.containsKey(temp)) updated_perimeters.put(temp, perimeters.get(temp));
                else updated_perimeters.put(temp, 1);
                temp += k;
            }
        });
        
        updated_perimeters.forEach((k, v) -> {if (v == 1) result[0]++;});
        
        return String.valueOf(result[0]);
    }
}