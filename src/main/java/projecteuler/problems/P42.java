package projecteuler.problems;

import projecteuler.util.Resources;

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
    public String solve() {
        Scanner sc = Resources.scanner("p042_words.txt");
        
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
        
        return String.valueOf(counter);
    }
}