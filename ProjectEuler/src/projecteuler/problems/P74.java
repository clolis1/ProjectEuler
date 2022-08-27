package projecteuler.problems;

import java.util.ArrayList;
import java.util.HashMap;

public class P74 implements Problem {
    public P74() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The number 145 is well known for the property\n" +
        "that the sum of the factorial of its digits is equal to 145:\n" + 
        "\n" + 
        "1! + 4! + 5! = 1 + 24 + 120 = 145\n" + 
        "\n" + 
        "Perhaps less well known is 169, in that it produces the longest chain of numbers that link back to 169;\n" +
        "it turns out that there are only three such loops that exist:\n" + 
        "\n" + 
        "169 ? 363601 ? 1454 ? 169\n" + 
        "871 ? 45361 ? 871\n" + 
        "872 ? 45362 ? 872\n" + 
        "\n" + 
        "It is not difficult to prove that EVERY starting number will eventually get stuck in a loop. For example,\n" + 
        "\n" + 
        "69 ? 363600 ? 1454 ? 169 ? 363601 (? 1454)\n" + 
        "78 ? 45360 ? 871 ? 45361 (? 871)\n" + 
        "540 ? 145 (? 145)\n" + 
        "\n" + 
        "Starting with 69 produces a chain of five non-repeating terms,\n" +
        "but the longest non-repeating chain with a starting number below one million is sixty terms.\n" + 
        "\n" + 
        "How many chains, with a starting number below one million, contain exactly sixty non-repeating terms?");
        
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
            // System.out.println(i);
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
        
        for (int i = 1; i < 1000000; i++) System.out.println(i + " " + terms.get(i));
        
        System.out.println("\nAnswer: " + length_sixty_terms);
    }
}