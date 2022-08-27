package projecteuler.problems;

import java.io.File;

import java.util.Scanner;

import projecteuler.util.Lexography;


public class P68 implements Problem {
    public P68() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Consider the following \"magic\" 3-gon ring, filled with the numbers 1 to 6,\n" +
        "and each line adding to nine.\n" + 
        "\n" + 
        "\n" + 
        "Working clockwise, and starting from the group of three with the numerically lowest external node (4,3,2 in this example),\n" +
        "each solution can be described uniquely. For example, the above solution can be described by the set: 4,3,2; 6,2,1; 5,1,3.\n" + 
        "\n" + 
        "It is possible to complete the ring with four different totals: 9, 10, 11, and 12. There are eight solutions in total.\n" + 
        "\n" + 
        "Total	Solution Set\n" + 
        "9	4,2,3; 5,3,1; 6,1,2\n" + 
        "9	4,3,2; 6,2,1; 5,1,3\n" + 
        "10	2,3,5; 4,5,1; 6,1,3\n" + 
        "10	2,5,3; 6,3,1; 4,1,5\n" + 
        "11	1,4,6; 3,6,2; 5,2,4\n" + 
        "11	1,6,4; 5,4,2; 3,2,6\n" + 
        "12	1,5,6; 2,6,4; 3,4,5\n" + 
        "12	1,6,5; 3,5,4; 2,4,6\n" + 
        "By concatenating each group it is possible to form 9-digit strings; the maximum string for a 3-gon ring is 432621513.\n" + 
        "\n" + 
        "Using the numbers 1 to 10, and depending on arrangements, it is possible to form 16- and 17-digit strings.\n" +
        "What is the maximum 16-digit string for a \"magic\" 5-gon ring?\n" + 
        "\n");
        
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
        
        System.out.println("\nAnswer: " + largest);
    }
}