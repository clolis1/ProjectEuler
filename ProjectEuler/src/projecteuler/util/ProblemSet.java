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
        probs.add(new P39());
        probs.add(new P40());
        probs.add(new P41());
        probs.add(new P42());
        probs.add(new P43());
        probs.add(new P44());
        probs.add(new P45());
        probs.add(new P46());
        probs.add(new P47());
        probs.add(new P48());
        probs.add(new P49());
        probs.add(new P50());
        probs.add(new P51());
        probs.add(new P52());
        probs.add(new P53());
        probs.add(new P54());
        probs.add(new P55());
        probs.add(new P56());
        probs.add(new P57());
        probs.add(new P58());
        probs.add(new P59());
        probs.add(new P60());
        probs.add(new P61());
        probs.add(new P62());
        probs.add(new P63());
        probs.add(new P64());
        probs.add(new P65());
        probs.add(new P66());
        probs.add(new P67());
        probs.add(new P68());
        probs.add(new P69());
        probs.add(new P70());
        probs.add(new P71());
        probs.add(new P72());
        probs.add(new P73());
        probs.add(new P74());
        probs.add(new P75());
        probs.add(new P76());
        probs.add(new P77());
        probs.add(new P78());
        probs.add(new P79());
        probs.add(new P80());
        probs.add(new P80()); // Placeholder
        probs.add(new P80()); // Placeholder
        probs.add(new P80()); // Placeholder
        probs.add(new P80()); // Placeholder
        probs.add(new P85());
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
