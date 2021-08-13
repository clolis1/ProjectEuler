package projecteuler.problems;

import java.util.ArrayList;
import java.io.File; 
import java.io.FileNotFoundException;

import java.util.Collections;
import java.util.Scanner;

public class P22 implements Problem {
    public P22() {
        super();
    }

    public void execute() {
        System.out.println("Using names.txt (right click and 'Save Link/Target As...'), ");
        System.out.println("a 46K text file containing over five-thousand first names, ");
        System.out.println("begin by sorting it into alphabetical order. ");
        System.out.println("Then working out the alphabetical value for each name,");
        System.out.println("multiply this value by its alphabetical position in the list to obtain a name score."); 
        System.out.println("For example, when the list is sorted into alphabetical order, ");
        System.out.println("COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. ");
        System.out.println("So, COLIN would obtain a score of 938 × 53 = 49714."); 
        System.out.println("What is the total of all the name scores in the file?");
        
        File file = new File("src/projecteuler/util/p022_names.txt"); // Read input file.
        Scanner sc;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException f) {
            System.out.println("Error reading file.");
            return;
        }
        
        ArrayList<String> names = new ArrayList<String>(); // Create an ArrayList of all Strings
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            names.add(sc.next().replace("\"", ""));
        }
        sc.close();
        
        Collections.sort(names); // Sort the ArrayList with TimSort
        
        Long sum = 0L;
        for (int i = 0; i < names.size(); i++) {
            long name_sum = 0;
            for (char c : names.get(i).toCharArray()) { // Determine the sum of the letters in the name.
                name_sum += (int) c - 64;
            }
            sum += name_sum * (i + 1); // Multiply by the position in the Array and add to total sum.
        }
        
        System.out.println("\nAnswer: " + sum);
    }
}
