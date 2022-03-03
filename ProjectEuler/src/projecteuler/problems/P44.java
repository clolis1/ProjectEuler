package projecteuler.problems;

import java.util.*;

public class P44 implements Problem {
    public P44() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Pentagonal numbers are generated by the formula, Pn=n(3n?1)/2.\n" +
                           "The first ten pentagonal numbers are:\n" + 
        "\n" + 
        "1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...\n" + 
        "\n" + 
        "It can be seen that P4 + P7 = 22 + 70 = 92 = P8.\n" +
        "However, their difference, 70 ? 22 = 48, is not pentagonal.\n" + 
        "\n" + 
        "Find the pair of pentagonal numbers, Pj and Pk,\n" +
        "for which their sum and difference are pentagonal and D = |Pk ? Pj| is minimised;\n" +
        "what is the value of D?");
        
        TreeSet<Long> pentagonals = new TreeSet<Long>(); // Pn, n
        
        long difference = Long.MAX_VALUE;
        pentagonals.add(1L);
        pentagonals.add(5L);
        pentagonals.add(12L);
        
        for (long i = 4; difference == Long.MAX_VALUE; i++) {
            long pk = i * (3 * i - 1) / 2;
            
            pentagonals.add(pk);
            Iterator<Long> it = pentagonals.descendingIterator();
            while (it.hasNext()){
                long pj = it.next();
                if (pj < 3 * i - 2) break;
                if (pentagonals.contains(pk - pj)) { // pk - pj is a pentagonal
                    double sqDiscrim = Math.sqrt(1.0 + 24.0 * (pk + pj));
                    if ((1.0 + sqDiscrim) % 6.0 == 0) {
                        difference = Math.min(difference, pk - pj);
                    }
                }
            }
        }
        System.out.println("\nAnswer: " + difference);
    }
}