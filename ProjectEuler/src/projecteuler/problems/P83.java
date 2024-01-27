package projecteuler.problems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;

import java.util.concurrent.ArrayBlockingQueue;

import projecteuler.util.*;


public class P83 implements Problem {
    public P83() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right,\n" +
        "by moving left, right, up, and down, is indicated in bold red and is equal to 2297.\n" + 
        "\n" +
        "image.txt\n" + 
        " \n" + 
        "Find the minimal path sum from the top left to the bottom right\n" +
        "by moving left, right, up, and down in matrix.txt (right click and \"Save Link/Target As...\"),\n" +
        "a 31K text file containing an 80 by 80 matrix.");
        
        long[][] grid = new long[80][80];
        
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/projecteuler/util/p083_matrix.txt"));
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
        
        long[][] costs = new long[81][81]; // One larger than actual size for buffer
        
        // Set initial guesses at minimum costs
        for (int i = 0; i < 81; i++) {
            for (int j = 0; j < 81; j++) {
                costs[i][j] = Integer.MAX_VALUE;
            }
        }

        boolean changesMade = true;
        
        while (changesMade){
            changesMade = false;
            for (int i = 0; i < 80; i++) {
                for (int j = 0; j < 80; j++) {
                    if (i == 0 && j == 0) {
                        if (costs[i][j] != grid[i][j]) {
                            costs[i][j] = grid[i][j];
                            changesMade = true;
                        }
                    }
                    else if (i == 0) {
                        long temp_min = Math.min(costs[i][j], Math.min(Math.min(grid[i][j] + costs[i][j - 1], grid[i][j] + costs[i][j + 1]), grid[i][j] + costs[i + 1][j]));
                        if (costs[i][j] != temp_min) {
                            costs[i][j] = temp_min;
                            changesMade = true;
                        }
                    }
                    else if (j == 0) {
                        long temp_min = Math.min(costs[i][j], Math.min(Math.min(grid[i][j] + costs[i - 1][j], grid[i][j] + costs[i][j + 1]), grid[i][j] + costs[i + 1][j]));
                        if (costs[i][j] != temp_min) {
                            costs[i][j] = temp_min;
                            changesMade = true;
                        }
                    }
                    else {
                        long temp_min = Math.min(costs[i][j], Math.min(Math.min(grid[i][j] + costs[i - 1][j], grid[i][j] + costs[i][j - 1]), Math.min(grid[i][j] + costs[i + 1][j], grid[i][j] + costs[i][j + 1])));
                        if (costs[i][j] != temp_min) {
                            costs[i][j] = temp_min;
                            changesMade = true;
                        }
                    }
                }
            }
        }
        
        try {
            br.close();
        }
        catch (IOException e) {
            System.out.println("Couldn't close stream.");
            return;
        }
        
        System.out.println("\nAnswer: " + costs[79][79]);
    }
}