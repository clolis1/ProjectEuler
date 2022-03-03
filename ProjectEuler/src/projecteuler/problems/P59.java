package projecteuler.problems;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import projecteuler.util.ComplexMath;

public class P59 implements Problem {
    public P59() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Each character on a computer is assigned a unique code and the preferred standard is\n" +
        "ASCII (American Standard Code for Information Interchange).\n" +
        "For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.\n" + 
        "\n" + 
        "A modern encryption method is to take a text file, convert the bytes to ASCII,\n" +
        "then XOR each byte with a given value, taken from a secret key.\n" +
        "The advantage with the XOR function is that using the same encryption key on the cipher text,\n" +
        "restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.\n" + 
        "\n" + 
        "For unbreakable encryption, the key is the same length as the plain text message,\n" +
        "and the key is made up of random bytes.\n" +
        "The user would keep the encrypted message and the encryption key in different locations,\n" +
        "and without both \"halves\", it is impossible to decrypt the message.\n" + 
        "\n" + 
        "Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key.\n" +
        "If the password is shorter than the message, which is likely, the key is repeated cyclically throughout the message.\n" +
        "The balance for this method is using a sufficiently long password key for security, but short enough to be memorable.\n" + 
        "\n" + 
        "Your task has been made easy, as the encryption key consists of three lower case characters.\n" +
        "Using p059_cipher.txt (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes,\n" +
        "and the knowledge that the plain text must contain common English words,\n" +
        "decrypt the message and find the sum of the ASCII values in the original text.");
        
        File file = new File("src/projecteuler/util/p059_cipher.txt"); // Read input file.
        Scanner sc;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException f) {
            System.out.println("Error reading file.");
            return;
        }
        
        ArrayList<Integer> chars = new ArrayList<Integer>(); // Create an ArrayList of all Strings
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            chars.add(Integer.parseInt(sc.next().replace("\"", "")));
        }
        sc.close();
        
        int sum = 0;
        
        for (int i = 97; i < 123; i++) {
            for (int j = 97; j < 123; j++) {
                for (int k = 97; k < 123; k++) {
                    int counter = 0;
                    for (int m = 0; m < chars.size(); m++) {
                        counter += chars.get(m);
                        int c = 0;
                        switch (m % 3) {
                            case 0: c = (i ^ chars.get(m));
                                    break;
                            case 1: c = (j ^ chars.get(m));
                                    break;
                            case 2: c = (k ^ chars.get(m));
                        }
                        
                        counter += c;
                        
                        if (c > 31 && c < 127 && !(c > 34 && c < 39) && c != 74 && c != 75 && c != 76) continue;
                        else break;
                    }
                    
                    // 101 120 112 is the password
                    sum = Math.max(sum, counter);
                }
            }
        } 
        
        System.out.println("\nAnswer: " + sum);
    }
}