package projecteuler.problems;

import java.util.*;

import projecteuler.util.ComplexMath;
import projecteuler.util.Lexography;

public class P51 implements Problem {
    public P51() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("By replacing the 1st digit of the 2-digit number *3,\n" +
        "it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.\n" + 
        "\n" + 
        "By replacing the 3rd and 4th digits of 56**3 with the same digit,\n" +
        "this 5-digit number is the first example having seven primes among the ten generated numbers,\n" +
        "yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993.\n" +
        "Consequently 56003, being the first member of this family, is the smallest prime with this property.\n" + 
        "\n" + 
        "Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit,\n" +
        "is part of an eight prime value family.");
        
        long smallest_prime = 0L;
        
        for (int i = 2; smallest_prime == 0L; i++) { // Length of number
            TreeSet<String> number_combinations = new TreeSet<String>();
            String wildcards = "";
            for (int j = 1; j < i && smallest_prime == 0L; j++) { // Number of wildcards
                wildcards += '*';
                System.out.println(i + " " + j + " " + (i - j) + " " + wildcards);
                char[] nums = new char[10 * (i - j)];
                int n = 0;
                for (n = 0; n < nums.length; n++) nums[n] = (char) ((n % 10) + '0');
                number_combinations = Lexography.getCombinations(nums, nums.length, i - j);
                for (String s : number_combinations) {
                    s += wildcards;
                    for (String t : Lexography.sortedPermutations(s)) {
                        if (t.equals("*2*3*3")) System.out.println(t);
                        if (t.charAt(t.length() - 1) == '*' ||
                            t.charAt(t.length() - 1) == '5' ||
                            Character.getNumericValue(t.charAt(t.length() - 1)) % 2 == 0 ||
                            t.charAt(0) == '0') continue;
                        int primes = 0;
                        long first = Long.MAX_VALUE;
//                      System.out.println(!ComplexMath.isPrime(Long.parseLong(t.replaceAll("\\*", Integer.toString(3)))));
                        for (int l = 0; l < 10; l++) {
                            if (l == 0 && t.charAt(0) == '*') continue;
                            long m = Long.parseLong(t.replaceAll("\\*", Integer.toString(l)));
                            if (ComplexMath.isPrime(m)) {
                                primes++;
                                first = Math.min(first, m);
                            }
                        }
                        if (primes > 7) {
                            smallest_prime = first;
                            break;
                        }           
                    }
                    if (smallest_prime != 0L) break;
                }
            }
        }
        
        System.out.println("Answer: " + smallest_prime);
    }
}