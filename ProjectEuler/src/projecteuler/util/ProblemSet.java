package projecteuler.util;
import projecteuler.problems.*;

import java.util.ArrayList;

public class ProblemSet {
    
    //Instance Variables
    ArrayList<Problem> probs;
    
    public ProblemSet() {
        probs = new ArrayList<Problem>();
        
        probs.add(new P1());
        probs.add(new P2());
        probs.add(new P3());
        probs.add(new P4());
        probs.add(new P5());
        probs.add(new P6());
        probs.add(new P7());
        probs.add(new P8());
        probs.add(new P9());
        probs.add(new P10());
        probs.add(new P11());
        probs.add(new P12());
        probs.add(new P13());
        probs.add(new P14());
        probs.add(new P15());
        probs.add(new P16());
        probs.add(new P17());
        probs.add(new P18());
        probs.add(new P19());
        probs.add(new P20());
        probs.add(new P21());
        probs.add(new P22());
        probs.add(new P23());
        probs.add(new P24());
        probs.add(new P25());
        probs.add(new P26());
        probs.add(new P27());
        probs.add(new P28());
        probs.add(new P29());
        probs.add(new P30());
        probs.add(new P31());
        probs.add(new P32());
        probs.add(new P33());
        probs.add(new P34());
        probs.add(new P35());
        probs.add(new P36());
        probs.add(new P37());
        probs.add(new P38());
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
