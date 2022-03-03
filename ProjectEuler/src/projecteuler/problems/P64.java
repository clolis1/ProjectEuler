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
    public void execute() {
        System.out.println("All square roots are periodic when written as continued fractions and can be written in the form:\n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "For example, let us consider \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "If we continue we would get the following expansion:\n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "The process can be summarised as follows:\n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        "\n" + 
        "It can be seen that the sequence is repeating. For conciseness, we use the notation ,\n" +
        "to indicate that the block (1,3,1,8) repeats indefinitely.\n" + 
        "\n" + 
        "The first ten continued fraction representations of (irrational) square roots are:\n" + 
        "\n" + 
        ", period=\n" + 
        ", period=\n" + 
        ", period=\n" + 
        ", period=\n" + 
        ", period=\n" + 
        ", period=\n" + 
        ", period=\n" + 
        ", period=\n" + 
        ", period=\n" + 
        ", period=\n" + 
        "\n" + 
        "Exactly four continued fractions, for , have an odd period.\n" + 
        "\n" + 
        "How many continued fractions for  have an odd period?");
        
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
        
        System.out.println("\nAnswer: " + odd_periods);
    }
}