package projecteuler.problems;

import java.util.Iterator;
import java.util.TreeSet;

import projecteuler.util.Digits;

public class P38 implements Problem {
    public P38() {
        super();
    }
    
    @Override
    public String solve() {
        TreeSet<Integer> concatenated_products = new TreeSet<Integer>();
        concatenated_products.add(192384576);
        
        for (int i = 25; i * 3 < 100; i++) {
            String str = "" + i + i * 2 + i * 3 + i * 4;
            if (Digits.is1To9Pandigital(str)) concatenated_products.add(Integer.valueOf(str));
        }
        
        for (int i = 100; i * 3 < 1000; i++) {
            String str = "" + i + i * 2 + i * 3;
            if (Digits.is1To9Pandigital(str)) concatenated_products.add(Integer.valueOf(str));
        }
        
        for (int i = 5000; i < 10000; i++) {
            String str = "" + i + i * 2;
            if (Digits.is1To9Pandigital(str)) concatenated_products.add(Integer.valueOf(str));
        }
        
        return String.valueOf(concatenated_products.last());
    }
}