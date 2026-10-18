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
    public String solve() {
        int[][] grid = new int[80][80];
        
        BufferedReader br = null;
        br = Resources.reader("p081_matrix.txt");
        
        for(int i = 0; i < 80; i++) {
            String[] line = new String[80];
            try {
                line = br.readLine().split(",");
            }
            catch (IOException e) {
            throw new java.io.UncheckedIOException(e);
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
            throw new java.io.UncheckedIOException(e);
        }
        
        return String.valueOf(grid[79][79]);
    }
}