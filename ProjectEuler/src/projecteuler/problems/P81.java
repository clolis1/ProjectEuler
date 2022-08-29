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


public class P81 implements Problem {
    public P81() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right,\n" +
        "by only moving to the right and down, is indicated in bold red and is equal to 2427.\n" + 
        "\n" +
        "image.txt\n" + 
        " \n" + 
        "Find the minimal path sum from the top left to the bottom right by only moving right and down in matrix.txt (right click and \"Save Link/Target As...\"), a 31K text file containing an 80 by 80 matrix.");
        
        int[][] grid = new int[80][80];
        
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/projecteuler/util/p081_matrix.txt"));
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
            for (int j = 0; j < 80; j++) {
                if (i == 0 && j == 0) grid[i][j] = Integer.valueOf(line[j]);
                else if (i == 0 && j > 0) grid[i][j] = grid[i][j - 1] + Integer.valueOf(line[j]);
                else if (i > 0 && j == 0) grid[i][j] = grid[i - 1][j] + Integer.valueOf(line[j]);
                else grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + Integer.valueOf(line[j]);
            }
        }
        
        try {
            br.close();
        }
        catch (IOException e) {
            System.out.println("Couldn't close stream.");
            return;
        }
        
        System.out.println("\nAnswer: " + grid[79][79]);
    }
}