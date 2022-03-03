package projecteuler;
import projecteuler.util.ProblemSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;

import projecteuler.problems.Problem;

public class Driver
{
    @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
    public static void main(String[] args) throws IOException
    {
        System.out.println("Hello! This program executes various algorithms to solve certain problems from ProjectEuler.net.");
        System.out.println("Input a number. The following data will outputted to the screen:");
        System.out.println("The associated problem in plaintext, the answer, and the time spent calculating.");
        System.out.println("By inputting a number followed by \"e\", as in \"#e\", a .txt file explaining the algorithm used will be displayed to the console.");
        System.out.println("If a number inputted does not have an associated solution within this file, a message will appear to inform the user.");
        System.out.println("\nType Q to quit.\nPlease input a number:");
        
        boolean running = true;
        
        ProblemSet pSet = new ProblemSet();
        
        while (running) {
            // Receive input from user.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            
            if (input == null) { // Error
                System.out.println("Error: Failed to read from console. Aborting...");
                break;
            }
            
            input = input.trim();
            char[] i_array = input.toCharArray();
            String problem_number = "";
            boolean valid_num = false;
            boolean explain_please = false;
            
            if (i_array.length == 0) { // Check if input is empty
                // Do nothing. Prithee, be careful.
            }
            else if (i_array[0] == 'Q' || i_array[0] == 'q' && i_array.length == 1) { // Check if QUIT is executed
                System.out.println("Exiting program...");
                break;
            }
            else if (!Character.isDigit(i_array[0])) { // Check if input begins with a number
                System.out.println("Error: Please start with a positive number.");
            }
            else { // Determine what number problem, and return an error if a non-numeric character is found
                for (int i = 0; i < i_array.length; i++) {
                    if (Character.isDigit(i_array[i])) {
                        problem_number += i_array[i];
                    }
                    else if (i_array[i] == 'e' || i_array[i] == 'E') { // Check if explanation text is requested
                        if (i != i_array.length - 1) { // Check to see if 'e' is last character
                            System.out.println("Error: There should be no trailing symbols after the 'e'.");
                            break;
                        }
                        explain_please = true;
                    }
                    else {  // Character was not numeric or 'e'
                        System.out.println("Error: A non-numeric and non-'e' character was inputted.");
                        break;
                    }
                    
                    if (i == i_array.length - 1) { // The input is valid
                        valid_num = true;
                    }
                }
            }
            
            if (valid_num && !explain_please) { // Execute the appropriate program, if it exists
                final long startTime = System.currentTimeMillis();
                Problem temp = pSet.get(Integer.parseInt(problem_number));
                if (temp == null) continue;
                temp.execute();
                final long endTime = System.currentTimeMillis();
                
                double totalTime = (double)(endTime - startTime);
                totalTime = totalTime / 1000.000; // Convert to seconds
                if (totalTime == 0.0) { // Less than 1 millisecond of execution time
                    System.out.println("Total execution time: less than .001 seconds");
                }
                else{ // Print execution diration
                    System.out.println("Total execution time: " + totalTime + " seconds");
                    System.out.println("\nPlease input a number: ");
                }
            }
            else if (valid_num && explain_please) { // Display the appropriate explanation, if it exists
                if (Integer.parseInt(problem_number) > 38) {
                    System.out.println("Sorry, no explanations beyond this point!");
                    System.out.println("\nPlease input a number: ");
                    continue;
                }
                String explanation = "explanations/" + problem_number + ".txt";
                br = new BufferedReader(new FileReader(explanation));
                String line = null;
                // Print out explanation text file
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("\nPlease input a number: ");
            }
            else { // input wasn't valid. An appropriate error message *should* have been displayed already
                // Mario, it that really you?
            }
        }
        System.out.println("Bye!");
    }
}
