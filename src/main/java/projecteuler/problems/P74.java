package projecteuler.problems;

import java.util.ArrayList;
import java.util.HashMap;

public class P74 implements Problem {
    public P74() {
        super();
    }
    
    @Override
    public String solve() {
        HashMap<Integer, Integer> terms = new HashMap<Integer, Integer>();
        terms.put(169, 3);
        terms.put(871, 2);
        terms.put(872, 2);
        terms.put(1454, 3);
        terms.put(45361, 2);
        terms.put(45362, 2);
        terms.put(363601, 3);
        
        int length_sixty_terms = 0;
        
        for (int i = 1; i < 1000000; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            
            int num = i;
            
            while (true) {
                if (terms.containsKey(num) && temp.size() == 0) break;
                if (terms.containsKey(num)) {
                    for (int j = temp.size() - 1; j >= 0; j--) {
                        terms.put(temp.get(j), terms.get(num) + temp.size() - j);
                        if (terms.get(temp.get(j)) == 60 && temp.get(j) < 1000001) length_sixty_terms++;
                    }
                    break;
                }
                
                int value = 0;
                for (char c : Integer.toString(num).toCharArray()) {
                    switch (c) {
                    case '0': 
                    case '1': value++;
                        break;
                    case '2': value += 2;
                        break;
                    case '3': value += 6;
                        break;
                    case '4': value += 24;
                        break;
                    case '5': value += 120;
                        break;
                    case '6': value += 720;
                        break;
                    case '7': value += 5040;
                        break;
                    case '8': value += 40320;
                        break;
                    case '9': value += 362880;
                    }
                }
                if (num == value) terms.put(num, 1);
                else temp.add(num);
                num = value;
            }
        }
        
        
        return String.valueOf(length_sixty_terms);
    }
}