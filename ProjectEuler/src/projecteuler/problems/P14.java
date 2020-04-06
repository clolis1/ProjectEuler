package projecteuler.problems;

import java.util.Hashtable;

public class P14 implements Problem{
    // First Integer is start, second Integer is number of links including 1.
    Hashtable<Integer, Integer> chains;
    public P14() {
        super();
        chains = new Hashtable<Integer, Integer>();
    }
    
    public void execute() {
        System.out.println("The following iterative sequence is defined for the set of positive integers:\n" + 
        "\n" + 
        "n ? n/2 (n is even)\n" + 
        "n ? 3n + 1 (n is odd)\n" + 
        "\n" + 
        "Using the rule above and starting with 13, we generate the following sequence:\n" + 
        "\n" + 
        "13 ? 40 ? 20 ? 10 ? 5 ? 16 ? 8 ? 4 ? 2 ? 1\n" + 
        "It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.\n" + 
        "\n" + 
        "Which starting number, under one million, produces the longest chain?\n" + 
        "\n" + 
        "NOTE: Once the chain starts the terms are allowed to go above one million.");
        
        final int MAX_STARTING_LINK = 1000000;
        int max_links = 0;
        int max_links_num = 0;
        
        for (int i = 1; i < MAX_STARTING_LINK; i += 2) {
            int temp = this.collatzNum((long) i);
            if (temp > max_links) {
                max_links = temp;
                max_links_num = i;
            }
        }
        
        System.out.println("\nAnswer: " + max_links_num);
    }
    
    // Returns the number of links of the inputted number 
    public int collatzNum(long x) {
        int num_links = 0;
        while (x != 1) {
            if (chains.containsKey(x)) {
                return num_links + chains.get(x);
            }
            else if (x % 2 == 0) {
                num_links++;
                x /= 2;
            }
            else if (x == 1) {
                num_links++;
                break;
            }
            else {
                num_links++;
                x = (3 * x) + 1;
            }
        }
        return num_links;
    }
}