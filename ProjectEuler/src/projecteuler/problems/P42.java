package projecteuler.problems;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.TreeSet;

public class P42 implements Problem {
    public P42() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The nth term of the sequence of triangle numbers is given by, " + 
                           "tn = ½n(n+1); so the first ten triangle numbers are:\n" + 
        "\n" + 
        "1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...\n" + 
        "\n" + 
        "By converting each letter in a word to a number corresponding to its alphabetical position " +
        "and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. " + 
        "If the word value is a triangle number then we shall call the word a triangle word.\n" + 
        "\n" + 
        "Using words.txt (right click and 'Save Link/Target As...'), " + 
        "a 16K text file containing nearly two-thousand common English words, how many are triangle words?");
        
        File file = new File("src/projecteuler/util/p042_words.txt"); // Read input file.
        Scanner sc;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException f) {
            System.out.println("Error reading file.");
            return;
        }
        
        ArrayList<String> words = new ArrayList<String>(); // Create an ArrayList of all Strings
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            words.add(sc.next().replace("\"", ""));
        }
        sc.close();
        
        int counter = 0, tcounter = 1;
        TreeSet<Integer> triangles = new TreeSet<Integer>();
        
        for (String s : words) {
            int value = 0;
            for (char c : s.toCharArray()) value += c - 64; // Unicode calue for 'A' is 65.
            while (!triangles.contains(value) && triangles.higher(value) == null) {
                triangles.add((int) (0.5 * tcounter * (tcounter + 1)));
                tcounter++;
            }
            if (triangles.contains(value)) counter++;
        }
        
        System.out.println("\nAnswer: " + counter);
    }
}