package projecteuler.problems;

import projecteuler.util.Resources;

import java.util.ArrayList;
import java.io.File; 
import java.io.FileNotFoundException;

import java.util.Collections;
import java.util.Scanner;

public class P22 implements Problem {
    public P22() {
        super();
    }

    public String solve() {
        Scanner sc = Resources.scanner("p022_names.txt");
        
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
        
        return String.valueOf(sum);
    }
}
