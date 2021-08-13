package projecteuler.problems;

import java.util.Iterator;
import java.util.TreeSet;

import projecteuler.util.ComplexMath;

public class P38 implements Problem {
    public P38() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Take the number 192 and multiply it by each of 1, 2, and 3:\n" + 
        "\n" + 
        "192 × 1 = 192\n" + 
        "192 × 2 = 384\n" + 
        "192 × 3 = 576\n" + 
        "By concatenating each product we get the 1 to 9 pandigital, 192384576.\n" + 
        "We will call 192384576 the concatenated product of 192 and (1,2,3)\n" + 
        "\n" + 
        "The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5,\n" + 
        "giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).\n" + 
        "\n" + 
        "What is the largest 1 to 9 pandigital 9-digit number that can be formed as\n" + 
        "the concatenated product of an integer with (1,2, ... , n) where n > 1?");
        
        TreeSet<Integer> concatenated_products = new TreeSet<Integer>();
        concatenated_products.add(192384576);
        
        for (int i = 25; i * 3 < 100; i++) {
            String str = "" + i + i * 2 + i * 3 + i * 4;
            if (ComplexMath.is1To9Pandigital(str)) concatenated_products.add(Integer.valueOf(str));
        }
        
        for (int i = 100; i * 3 < 1000; i++) {
            String str = "" + i + i * 2 + i * 3;
            if (ComplexMath.is1To9Pandigital(str)) concatenated_products.add(Integer.valueOf(str));
        }
        
        for (int i = 5000; i < 10000; i++) {
            String str = "" + i + i * 2;
            if (ComplexMath.is1To9Pandigital(str)) concatenated_products.add(Integer.valueOf(str));
        }
        
        System.out.println("\nAnswer:" + concatenated_products.last());
    }
}