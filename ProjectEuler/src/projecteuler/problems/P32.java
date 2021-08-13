package projecteuler.problems;

import java.util.Iterator;
import java.util.TreeSet;

public class P32 implements Problem {
    public P32() {
        super();
    }

    @Override
    public void execute() {
        System.out.println("We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once;\n" + 
        "for example, the 5-digit number, 15234, is 1 through 5 pandigital.\n" + 
        "\n" + 
        "The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier,\n" + 
        "and product is 1 through 9 pandigital.\n" + 
        "\n" + 
        "Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.\n" + 
        "\n" + 
        "HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.");
        
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
        
        System.out.println("\nAnswer:" + total_sum);
    }
}
