package projecteuler.problems;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import projecteuler.util.ComplexMath;
import projecteuler.util.Lexography;

public class P61 implements Problem {
    public P61() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Triangle, square, pentagonal, hexagonal, heptagonal, and octagonal numbers\n" +
        "are all figurate (polygonal) numbers and are generated by the following formulae:\n" + 
        "\n" + 
        "Triangle	 	P3,n=n(n+1)/2	 	1, 3, 6, 10, 15, ...\n" + 
        "Square	 	        P4,n=n2	 	        1, 4, 9, 16, 25, ...\n" + 
        "Pentagonal	 	P5,n=n(3n?1)/2	 	1, 5, 12, 22, 35, ...\n" + 
        "Hexagonal	 	P6,n=n(2n?1)	 	1, 6, 15, 28, 45, ...\n" + 
        "Heptagonal	 	P7,n=n(5n?3)/2	 	1, 7, 18, 34, 55, ...\n" + 
        "Octagonal	 	P8,n=n(3n?2)	 	1, 8, 21, 40, 65, ...\n" + 
        "The ordered set of three 4-digit numbers: 8128, 2882, 8281, has three interesting properties.\n" + 
        "\n" + 
        "The set is cyclic, in that the last two digits of each number is the first two digits of the next number\n" +
        "(including the last number with the first).\n" + 
        "Each polygonal type: triangle (P3,127=8128), square (P4,91=8281), and pentagonal (P5,44=2882),\n" +
        "is represented by a different number in the set.\n" + 
        "This is the only set of 4-digit numbers with this property.\n" + 
        "Find the sum of the only ordered set of six cyclic 4-digit numbers for which each polygonal type:\n" +
        "triangle, square, pentagonal, hexagonal, heptagonal, and octagonal, is represented by a different number in the set.");
        
        boolean[][] nums = new boolean[6][100000];
        int sum = 0;
        ArrayList<String> orders = Lexography.sortedPermutations("12345");
        
        for (int i = 1; i * (i + 1) / 2 < 10001; i++) {
            nums[0][i * (i + 1) / 2] = true;
            nums[1][(int) Math.pow(i, 2)] = true;
            nums[2][i * (3 * i - 1) / 2] = true;
            nums[3][i * (2 * i - 1)] = true;
            nums[4][i * (5 * i - 3) / 2] = true;
            nums[5][i * (3 * i - 2)] = true;
        }
        
         
        for (int i = 1010; i < 10000; i++) { // triangular
            if (!nums[0][i] || i % 100 < 10) continue;
            for (String str : orders) {
                for (int j = i % 100 * 100 + 10; j < i % 100 * 100 + 100; j++) {
                    if (!nums[Character.getNumericValue(str.charAt(0))][j]) continue;
                    for (int k = j % 100 * 100 + 10; k < j % 100 * 100 + 100; k++) {
                        if (!nums[Character.getNumericValue(str.charAt(1))][k]) continue;
                        for (int m = k % 100 * 100 + 10; m < k % 100 * 100 + 100; m++) {
                            if (!nums[Character.getNumericValue(str.charAt(2))][m]) continue;
                            for (int n = m % 100 * 100 + 10; n < m % 100 * 100 + 100; n++) {
                                if (!nums[Character.getNumericValue(str.charAt(3))][n]) continue;
                                for (int p = n % 100 * 100 + 10; p < n % 100 * 100 + 100; p++) {
                                    if (nums[Character.getNumericValue(str.charAt(4))][p] && p % 100 == i / 100) sum = i + j + k + m + n + p;
                                    if (sum != 0) break;
                                }
                                if (sum != 0) break;
                            }
                            if (sum != 0) break;
                        }
                        if (sum != 0) break;
                    }
                    if (sum != 0) break;
                }
                if (sum != 0) break;
            }
            if (sum != 0) break;
        }
        
        
        System.out.println("\nAnswer: " + sum);
    }
}