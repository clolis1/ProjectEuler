package projecteuler.problems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import projecteuler.util.*;


public class P82 implements Problem {
    public P82() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The minimal path sum in the 5 by 5 matrix below,\n" +
        "by starting in any cell in the left column and finishing in any cell in the right column,\n" +
        "and only moving up, down, and right, is indicated in red and bold; the sum is equal to 994.\n" + 
        "\n" +
        "image.txt\n" + 
        " \n" + 
        "Find the minimal path sum from the left column to the right column in matrix.txt (right click and \"Save Link/Target As...\"),\n" +
        "a 31K text file containing an 80 by 80 matrix.");
        
        int[][] grid = new int[80][80];
        
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/projecteuler/util/p082_matrix.txt"));
        }
        catch (FileNotFoundException f) {
            System.out.println("Error reading file.");
            return;
        }
        
        for(int i = 0; i < 80; i++) {
            String[] line = new String[80];
            try {
                line = br.readLine().split(",");
            }
            catch (IOException e) {
                System.out.println("Error parsing line.");
                return;
            }
            for (int j = 0; j < 80; j++) grid[i][j] = Integer.valueOf(line[j]);
        }
        
        int[][] costs = new int[80][80];
        
        for (int j = 0; j < 80; j++) {
            for (int i = 0; i < 80; i++) {
                if (j == 0) costs[i][j] = grid[i][j];
                else if (i == 0) costs[i][j] = costs[i][j - 1] + grid[i][j];
                else costs[i][j] = Math.min(costs[i - 1][j] + grid[i][j], costs[i][j - 1] + grid[i][j]);
            }
            if (j == 0) continue;
            for (int i = 78; i >= 0; i--) costs[i][j] = Math.min(costs[i + 1][j] + grid[i][j], costs[i][j]);
        }
        
        try {
            br.close();
        }
        catch (IOException e) {
            System.out.println("Couldn't close stream.");
            return;
        }
        
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 80; j++) {
                System.out.print(costs[i][j] + " ");
            }
            System.out.print("\n");
        }
        
        int smallest = Integer.MAX_VALUE;
        
        for (int i = 0; i < 80; i++) {
            smallest = Math.min(smallest, costs[i][79]);
        }
        
        System.out.println("\nAnswer: " + smallest);
    }
}