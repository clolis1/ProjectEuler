package projecteuler.problems;

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
    public void execute() {
        System.out.println("In the card game poker, a hand consists of five cards and are ranked,\n" +
        "from lowest to highest, in the following way:\n" + 
        "\n" + 
        "High Card: Highest value card.\n" + 
        "One Pair: Two cards of the same value.\n" + 
        "Two Pairs: Two different pairs.\n" + 
        "Three of a Kind: Three cards of the same value.\n" + 
        "Straight: All cards are consecutive values.\n" + 
        "Flush: All cards of the same suit.\n" + 
        "Full House: Three of a kind and a pair.\n" + 
        "Four of a Kind: Four cards of the same value.\n" + 
        "Straight Flush: All cards are consecutive values of same suit.\n" + 
        "Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.\n" + 
        "The cards are valued in the order:\n" + 
        "2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.\n" + 
        "\n" + 
        "If two players have the same ranked hands then the rank made up of the highest value wins;\n" +
        "for example, a pair of eights beats a pair of fives (see example 1 below).\n" +
        "But if two ranks tie, for example, both players have a pair of queens,\n" +
        "then highest cards in each hand are compared (see example 4 below);\n" +
        "if the highest cards tie then the next highest cards are compared, and so on.\n" + 
        "\n" + 
        "Consider the following five hands dealt to two players:\n" + 
        "\n" + 
        "Hand	 	Player 1	 	Player 2	 	Winner\n" + 
        "1	 	5H 5C 6S 7S KD\n" + 
        "Pair of Fives\n" + 
        " 	2C 3S 8S 8D TD\n" + 
        "Pair of Eights\n" + 
        " 	Player 2\n" + 
        "2	 	5D 8C 9S JS AC\n" + 
        "Highest card Ace\n" + 
        " 	2C 5C 7D 8S QH\n" + 
        "Highest card Queen\n" + 
        " 	Player 1\n" + 
        "3	 	2D 9C AS AH AC\n" + 
        "Three Aces\n" + 
        " 	3D 6D 7D TD QD\n" + 
        "Flush with Diamonds\n" + 
        " 	Player 2\n" + 
        "4	 	4D 6S 9H QH QC\n" + 
        "Pair of Queens\n" + 
        "Highest card Nine\n" + 
        " 	3D 6D 7H QD QS\n" + 
        "Pair of Queens\n" + 
        "Highest card Seven\n" + 
        " 	Player 1\n" + 
        "5	 	2H 2D 4C 4D 4S\n" + 
        "Full House\n" + 
        "With Three Fours\n" + 
        " 	3C 3D 3S 9S 9D\n" + 
        "Full House\n" + 
        "with Three Threes\n" + 
        " 	Player 1\n" + 
        "The file, poker.txt, contains one-thousand random hands dealt to two players.\n" + 
        "Each line of the file contains ten cards (separated by a single space):\n" +
        "the first five are Player 1's cards and the last five are Player 2's cards.\n" +
        "You can assume that all hands are valid (no invalid characters or repeated cards),\n" +
        "each player's hand is in no specific order, and in each hand there is a clear winner.\n" + 
        "\n" + 
        "How many hands does Player 1 win?");
        
        File file = new File("src/projecteuler/util/p054_poker.txt"); // Read input file.
        Scanner sc;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException f) {
            System.out.println("Error reading file.");
            return;
        }
        
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
        
        System.out.println("\nAnswer: " + counter);
    }
}