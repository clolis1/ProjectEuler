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
    public String solve() {
        int[][] grid = new int[80][80];
        
        BufferedReader br = null;
        br = Resources.reader("p082_matrix.txt");
        
        for(int i = 0; i < 80; i++) {
            String[] line = new String[80];
            try {
                line = br.readLine().split(",");
            }
            catch (IOException e) {
            throw new java.io.UncheckedIOException(e);
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
            throw new java.io.UncheckedIOException(e);
        }
        
        int smallest = Integer.MAX_VALUE;
        
        for (int i = 0; i < 80; i++) {
            smallest = Math.min(smallest, costs[i][79]);
        }
        
        return String.valueOf(smallest);
    }
}