package projecteuler.problems;

import java.util.*;

import projecteuler.util.ComplexMath;

public class P49 implements Problem {
    public P49() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330,\n" +
        "is unusual in two ways: (i) each of the three terms are prime, and,\n" +
        "(ii) each of the 4-digit numbers are permutations of one another.\n" + 
        "\n" + 
        "There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property,\n" +
        "but there is one other 4-digit increasing sequence.\n" + 
        "\n" + 
        "What 12-digit number do you form by concatenating the three terms in this sequence?");
        
        TreeSet<Integer> primes = new TreeSet<Integer>();
        primes.addAll(ComplexMath.getSetPrimesUpUntil(10000).tailSet(999));
        
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
        
        System.out.println("Answer: " + result);
    }
}