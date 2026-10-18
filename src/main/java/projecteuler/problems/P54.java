package projecteuler.problems;

import projecteuler.util.Resources;

import java.io.File;
import java.io.FileNotFoundException;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P54 implements Problem {
    public P54() {
        super();
    }
    
    @Override
    public String solve() {
        Scanner sc = Resources.scanner("p054_poker.txt");
        
        ArrayList<String> cards = new ArrayList<String>(); // Create an ArrayList of all Strings
        while (sc.hasNextLine()) {
            Scanner scs = new Scanner(sc.nextLine());
            scs.useDelimiter(" ");
            while (scs.hasNext()) cards.add(scs.next().replace("\n", ""));
            scs.close();
        }
        sc.close();
        
        int counter = 0;
        
        for (int i = 0; i < cards.size(); i += 10) {
            int[][] hands = new int[2][13];
            boolean[] flushes = new boolean[2];
            
            for (int j = 0; j < 10; j++) {
                char c = cards.get(i + j).charAt(0);
                
                if (Character.isDigit(c)) hands[j / 5][Character.getNumericValue(c) - 1]++;
                else if (c == 'A') hands[j / 5][0]++;
                else if (c == 'T') hands[j / 5][9]++;
                else if (c == 'J') hands[j / 5][10]++;
                else if (c == 'Q') hands[j / 5][11]++;
                else hands[j / 5][12]++;
                
                if (j % 5 == 0) {
                    char d = cards.get(i + j).charAt(1);
                    if (cards.get(i + j + 1).charAt(1) == d && 
                        cards.get(i + j + 2).charAt(1) == d && 
                        cards.get(i + j + 3).charAt(1) == d && 
                        cards.get(i + j + 4).charAt(1) == d) flushes[j / 5] = true;
                }
            }
            
            int[] ranks = new int[2];
            
            boolean[] toaks = new boolean[2], pairs = new boolean[2], straight_checks = new boolean[2];
            
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < 13; j++) {
                    if (hands[k][j] == 4) {
                        ranks[k] = 8;
                        break;
                    }
                    else if (hands[k][j] == 3 && pairs[k]) {
                        ranks[k] = 7;
                        break;
                    }
                    else if (hands[k][j] == 3) {
                        straight_checks[k] = true;
                        toaks[k] = true;
                    }
                    else if (hands[k][j] == 2 && toaks[k]) {
                        ranks[k] = 7;
                        break;
                    }
                    else if (hands[k][j] == 2 && pairs[k]) {
                        ranks[k] = 3;
                        break;
                    }
                    else if (hands[k][j] == 2) {
                        straight_checks[k] = true;
                        pairs[k] = true;
                    }
                    else if (!straight_checks[k] && j < 9 &&
                                hands[k][j] == 1 && hands[k][j + 1] == 1 && hands[k][j + 2] == 1 &&
                                hands[k][j + 3] == 1 && hands[k][j + 4] == 1) {
                        ranks[k] = 5;
                        break;
                    }
                    else if (!straight_checks[k] && j == 9 &&
                                hands[k][j] == 1 && hands[k][j + 1] == 1 && hands[k][j + 2] == 1 &&
                                hands[k][j + 3] == 1 && hands[k][0] == 1) {
                        ranks[k] = 5;
                        break;
                    }
                    else if (hands[k][j] == 1) straight_checks[k] = true;
                }
            
                if (ranks[k] == 5 && flushes[k] == true) ranks[k] = 9;
                if (toaks[k] && ranks[k] != 7) ranks[k] = 4;
                if (pairs[k] && ranks[k] != 3) ranks[k] = 2;
                if (ranks[k] == 0 && flushes[k]) ranks[k] = 6;
                else if (ranks[k] == 0) ranks[k] = 1;
            }
            
            if (ranks[0] > ranks[1]) {
                counter++;
                continue;
            }
            else if (ranks[1] > ranks[0]) continue;
            
            for (int k = 4; k > 0; k--) {
                if (ranks[0] == 9 || ranks[0] == 6 || ranks[0] == 5) k = 1;
                if (hands[0][0] == k && hands[0][0] > hands[1][0]) {
                    counter++;
                    break;
                }
                else if (hands[1][0] == k && hands[1][0] > hands[0][0]) break;
                
                boolean found = false;
                
                for (int j = 12; j > 0; j--) {
                    if (hands[0][j] == k && hands[0][j] > hands[1][j]) {
                        found = true;
                        counter++;
                        break;
                    }
                    else if (hands[1][j] == k && hands[1][j] > hands[0][j]) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
        }
        
        return String.valueOf(counter);
    }
}