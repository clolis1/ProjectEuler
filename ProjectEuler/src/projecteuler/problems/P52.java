package projecteuler.problems;

import java.math.BigInteger;

import java.util.Arrays;

public class P52 implements Problem {
    public P52() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("It can be seen that the number, 125874, and its double, 251748,\n" +
        "contain exactly the same digits, but in a different order.\n" + 
        "\n" + 
        "Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.");
        
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; result.compareTo(BigInteger.ZERO) == 0; i++) {
            for (BigInteger bi = BigInteger.TEN.pow(i); bi.compareTo(BigInteger.TEN.pow(i + 1).divide(new BigInteger("6"))) < 0; bi = bi.add(BigInteger.ONE)) {
                char[] s1 = bi.toString().toCharArray(), s2 = bi.multiply(new BigInteger("2")).toString().toCharArray();
                Arrays.sort(s1);
                Arrays.sort(s2);
                if (!String.valueOf(s1).equals(String.valueOf(s2))) continue;
                
                char[] s3 = bi.multiply(new BigInteger("3")).toString().toCharArray();
                Arrays.sort(s3);
                if (!String.valueOf(s1).equals(String.valueOf(s3))) continue;
                
                char[] s4 = bi.multiply(new BigInteger("4")).toString().toCharArray();
                Arrays.sort(s4);
                if (!String.valueOf(s1).equals(String.valueOf(s4))) continue;
                
                char[] s5 = bi.multiply(new BigInteger("5")).toString().toCharArray();
                Arrays.sort(s5);
                if (!String.valueOf(s1).equals(String.valueOf(s5))) continue;
                
                char[] s6 = bi.multiply(new BigInteger("6")).toString().toCharArray();
                Arrays.sort(s6);
                if (!String.valueOf(s1).equals(String.valueOf(s6))) continue;
                
                result = bi;
            }
        }
        
        System.out.println("\nAnswer: " + result.toString());
    }
}