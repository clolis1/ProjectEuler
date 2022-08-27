package projecteuler.problems;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;


public class P79 implements Problem {
    public P79() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("A common security method used for online banking" +
        "is to ask the user for three random characters from a passcode." +
        "For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters;" +
        "the expected reply would be: 317.\n" + 
        "\n" + 
        "The text file, keylog.txt, contains fifty successful login attempts.\n" + 
        "\n" + 
        "Given that the three characters are always asked for in order," +
        "analyse the file so as to determine the shortest possible secret passcode of unknown length.");
        
        HashSet<String> possible_passcodes = new HashSet<String>();
        
        File file = new File("src/projecteuler/util/keylog.txt"); // Read input file.
        Scanner sc;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException f) {
            System.out.println("Error reading file.");
            return;
        }
        
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String x = str.substring(0, 1), y = str.substring(1, 2), z = str.substring(2, 3);
            HashSet<String> new_codes = new HashSet<String>();
            int smallest_passcode = Integer.MAX_VALUE;
            
            if (possible_passcodes.size() == 0) {
                possible_passcodes.add(x + y + z);
                continue;
            }
            
            for (String s : possible_passcodes) {
                // Check for all 3 in order
                int counter = 0;
                for (int i = 0; i < s.length() && counter != 3; i++) {
                    if (s.charAt(i) == str.charAt(counter)) counter++;
                }
                if (counter == 3) {
                    smallest_passcode = Math.min(smallest_passcode, s.length());
                    new_codes.add(s);
                }
                
                if (smallest_passcode < s.length() + 1) continue;
                // Check for at least 2 in order
                // Check for x and y in order
                counter = 0;
                for (int i = 0; i < s.length() && counter != 2; i++) {
                    if (s.charAt(i) == str.charAt(counter)) counter++;
                }
                if (counter == 2) {
                    for (int i = s.indexOf(y, s.indexOf(x) + 1); i <= s.length(); i++) {
                        new_codes.add(s.substring(0, i) + z + s.substring(i, s.length()));
                    }
                    smallest_passcode = Math.min(smallest_passcode, s.length() + 1);
                }
                // Check for x and z in order
                counter = 0;
                for (int i = 0; i < s.length() && counter != 4; i++) {
                    if (s.charAt(i) == str.charAt(counter)) counter += 2;
                }
                if (counter == 4) {
                    for (int i = s.indexOf(x); i < s.indexOf(z, s.indexOf(x) + 1); i++) {
                        new_codes.add(s.substring(0, i) + y + s.substring(i, s.length()));
                    }
                    smallest_passcode = Math.min(smallest_passcode, s.length() + 1);
                }
                //Check y and z in order
                counter = 1;
                for (int i = 0; i < s.length() && counter != 3; i++) {
                    if (s.charAt(i) == str.charAt(counter)) counter++;
                }
                if (counter == 3) {
                    for (int i = 0; i < s.indexOf(y); i++) {
                        new_codes.add(s.substring(0, i) + x + s.substring(i, s.length()));
                    }
                    smallest_passcode = Math.min(smallest_passcode, s.length() + 1);
                }
                
                if (smallest_passcode < s.length() + 2) continue;
                // Check for at least 1 match
                // Check x match
                counter = 0;
                for (int i = 0; i < s.length() && counter == 0; i++) {
                    if (s.charAt(i) == str.charAt(counter)) counter++;
                }
                if (counter == 1) {
                    for (int i = s.indexOf(x); i <= s.length(); i++) {
                        for (int j = i; j <= s.length(); j++) {
                            new_codes.add(s.substring(0, i) + y + s.substring(i, j) + z + s.substring(j, s.length()));
                        }
                    }
                    smallest_passcode = Math.min(smallest_passcode, s.length() + 2);
                }
                // Check y match
                counter = 1;
                for (int i = 0; i < s.length() && counter == 1; i++) {
                    if (s.charAt(i) == str.charAt(counter)) counter++;
                }
                if (counter == 2) {
                    for (int i = 0; i < s.indexOf(y); i++) {
                        for (int j = s.indexOf(y); j <= s.length(); j++) {
                            new_codes.add(s.substring(0, i) + x + s.substring(i, s.indexOf(y)) + s.substring(s.indexOf(y), j) + z + s.substring(j, s.length()));
                        }
                    }
                    smallest_passcode = Math.min(smallest_passcode, s.length() + 2);
                }
                // Check z match
                counter = 2;
                for (int i = 0; i < s.length() && counter == 2; i++) {
                    if (s.charAt(i) == str.charAt(counter)) counter++;
                }
                if (counter == 3) {
                    for (int i = 0; i < s.indexOf(z); i++) {
                        for (int j = i; j < s.indexOf(z); j++) {
                            new_codes.add(s.substring(0, i) + x + s.substring(i, j) + y + s.substring(j, s.length()));
                        }
                    }
                    smallest_passcode = Math.min(smallest_passcode, s.length() + 2);
                }
                if (smallest_passcode < s.length() + 3) continue;
                // No matches
                for (int i = 0; i <= s.length(); i++) {
                    for (int j = i; j <= s.length(); j++) {
                        for (int k = j; k <= s.length(); k++) {
                            new_codes.add(s.substring(0, i) + x + s.substring(i, j) + y + s.substring(j, k) + z + s.substring(k, s.length()));
                        }
                    }
                }
                smallest_passcode = s.length() + 3;
            }
            
            possible_passcodes = new HashSet<String>();
            for (String s : new_codes) if (s.length() == smallest_passcode) possible_passcodes.add(s);
        }
        sc.close();
        
        String answer = "";
        
        for (String s : possible_passcodes) answer = s;
        
        System.out.println("\nAnswer: " + answer);
    }
}
