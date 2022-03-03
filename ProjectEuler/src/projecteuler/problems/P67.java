package projecteuler.problems;

import java.io.File;
import java.io.FileNotFoundException;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class P67 implements Problem {
    public P67() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("By starting at the top of the triangle below and moving to adjacent numbers on the row below,\n" +
        "the maximum total from top to bottom is 23.\n" + 
        "\n" + 
        "3\n" + 
        "7 4\n" + 
        "2 4 6\n" + 
        "8 5 9 3\n" + 
        "\n" + 
        "That is, 3 + 7 + 4 + 9 = 23.\n" + 
        "\n" + 
        "Find the maximum total from top to bottom in triangle.txt (right click and 'Save Link/Target As...'),\n" +
        "a 15K text file containing a triangle with one-hundred rows.\n" + 
        "\n" + 
        "NOTE: This is a much more difficult version of Problem 18.\n" +
        "It is not possible to try every route to solve this problem, as there are 299 altogether!\n" +
        "If you could check one trillion (1012) routes every second\n" +
        "it would take over twenty billion years to check them all.\n" +
        "There is an efficient algorithm to solve it. ;o)");
        
        int grid[][] = new int[100][100];
        int x_counter = 0;
        
        File file = new File("src/projecteuler/util/p067_triangle.txt"); // Read input file.
        Scanner sc;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException f) {
            System.out.println("Error reading file.");
            return;
        }
        
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            Scanner scanner = new Scanner(str);
            scanner.useDelimiter(" ");
            int y_counter = 0;
            while (scanner.hasNext()) {
                grid[x_counter][y_counter] = Integer.valueOf(scanner.next());
                y_counter++;
            }
            x_counter++;
            scanner.close();
        }
        sc.close();
        
        int[] max_sums = new int[grid[grid.length - 1].length]; // int array with same number of spots as bottom layer of triangle.
        
        max_sums[0] = grid[0][0];
        
        // Iterate through each row of the triangle. Keep track of the maximum value that can be reached at each node in a tier.
        for (int i = 1; i < grid.length; i++) {
            
            max_sums[i] = max_sums[i-1]; // Rightmost node is node one level higher + right child.
            
            // Determine highest value path per node for non-edge nodes.
            for (int j = grid[i].length - 2; j > 0; j--) {
                max_sums[j] = grid[i][j] + ((max_sums[j] > max_sums[j-1]) ? max_sums[j] : max_sums[j-1]);
            }
            
            // Calculate highest value for left-only and right-only paths.
            max_sums[0] += grid[i][0];
            max_sums[i] += grid[i][grid[i].length - 1];
        }
        
        // Determine greatest vale path.
        int max = 0;
        for (int i : max_sums) {
            if (i > max) max = i;
        }
        
        System.out.println("\nAnswer: " + max);
    }
}