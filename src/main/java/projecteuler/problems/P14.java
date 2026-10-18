package projecteuler.problems;

import java.util.Hashtable;

public class P14 implements Problem{
    // First Integer is start, second Integer is number of links including 1.
    Hashtable<Integer, Integer> chains;
    public P14() {
        super();
        chains = new Hashtable<Integer, Integer>();
    }
    
    public String solve() {
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
        
        return String.valueOf(max_links_num);
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