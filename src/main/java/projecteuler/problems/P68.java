package projecteuler.problems;

import java.io.File;

import java.util.Scanner;

import projecteuler.util.Lexography;


public class P68 implements Problem {
    public P68() {
        super();
    }
    
    @Override
    public String solve() {
        int[] perm = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String start = "" + perm[0] + perm[1] + perm[2] + perm[3] + perm[2] + perm[4] + perm[5] + perm[4] + perm[6] +
                        perm[7] + perm[6] + perm[8] + perm[9] + perm[8] + perm[1];
        String current = start;
        String largest = current;
        
        do {
            Lexography.nextPermutation(perm);
            current = "" + perm[0] + perm[1] + perm[2] + perm[3] + perm[2] + perm[4] + perm[5] + perm[4] + perm[6] +
                        perm[7] + perm[6] + perm[8] + perm[9] + perm[8] + perm[1];
            if (perm[1] == 10 || perm[2] == 10 || perm[4] == 10 || perm[6] == 10 || perm[8] == 10) continue;
            if (perm[0] > perm[3] || perm[0] > perm[5] || perm[0] > perm[7] || perm[0] > perm[9] ) continue;
            
            if (perm[0] + perm[1] + perm[2] != perm[3] + perm[2] + perm[4]) continue;
            if (perm[0] + perm[1] + perm[2] != perm[5] + perm[4] + perm[6]) continue;
            if (perm[0] + perm[1] + perm[2] != perm[7] + perm[6] + perm[8]) continue;
            if (perm[0] + perm[1] + perm[2] != perm[9] + perm[8] + perm[1]) continue;
            
            if (current.compareTo(largest) > 0) largest = current;
            
        } while (!current.equals(start));
        
        return String.valueOf(largest);
    }
}