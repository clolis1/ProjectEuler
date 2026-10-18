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


import projecteuler.util.Lexography;

public class P61 implements Problem {
    public P61() {
        super();
    }
    
    @Override
    public String solve() {
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
        
        
        return String.valueOf(sum);
    }
}