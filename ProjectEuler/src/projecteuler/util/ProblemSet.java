package projecteuler.util;
import projecteuler.problems.*;

import java.util.ArrayList;

public class ProblemSet {
    
    //Instance Variables
    public ArrayList<Problem> probs;
    
    public ProblemSet() {
        probs = new ArrayList<Problem>();
        int i = 1;
        boolean adding = true;
        do {
            try {
                probs.add((Problem) Class.forName("projecteuler.problems.P" + i).getDeclaredConstructor().newInstance());
            } catch (ClassNotFoundException e) {
                adding = false;
            } catch (Exception e) {
                adding = false;
                e.printStackTrace();
            }
            i++;
        } while (adding);
    }
    
    // Returns the associated Problem from the array
    public Problem get(int x) {
        try {
            return probs.get(x-1);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("That number problem hasn't been solved yet! Please enter another number.");
            return null;
        }
    }
}
