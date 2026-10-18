package projecteuler.problems;

import projecteuler.util.Resources;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;



public class P59 implements Problem {
    public P59() {
        super();
    }
    
    @Override
    public String solve() {
        Scanner sc = Resources.scanner("p059_cipher.txt");
        
        ArrayList<Integer> chars = new ArrayList<Integer>(); // Create an ArrayList of all Strings
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            chars.add(Integer.parseInt(sc.next().replace("\"", "")));
        }
        sc.close();
        
        int sum = 0;
        int best_score = -1;

        // Try every three-lowercase-letter key. The right one turns the text into English,
        // so score each key by how many decrypted characters are letters or spaces.
        for (int i = 97; i < 123; i++) {
            for (int j = 97; j < 123; j++) {
                for (int k = 97; k < 123; k++) {
                    int[] key = {i, j, k};
                    int score = 0;
                    int counter = 0;
                    for (int m = 0; m < chars.size(); m++) {
                        int c = key[m % 3] ^ chars.get(m);
                        counter += c;
                        if (c == ' ' || Character.isLetter(c)) score++;
                    }

                    // 101 120 112 ("exp") is the password
                    if (score > best_score) {
                        best_score = score;
                        sum = counter;
                    }
                }
            }
        }

        return String.valueOf(sum);
    }
}