package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.ComplexMath;

public class P26 implements Problem {
    public P26() {
        super();
    }

    @Override
    public void execute() {
        System.out.println("A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:\n" + 
        "\n" + 
        "1/2	= 	0.5\n" + 
        "1/3	= 	0.(3)\n" + 
        "1/4	= 	0.25\n" + 
        "1/5	= 	0.2\n" + 
        "1/6	= 	0.1(6)\n" + 
        "1/7	= 	0.(142857)\n" + 
        "1/8	= 	0.125\n" + 
        "1/9	= 	0.(1)\n" + 
        "1/10	= 	0.1\n" + 
        "Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.\n" + 
        "\n" + 
        "Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.");
        
        // By using code to find full reptend primes, we can easily find the number below any limit with the longest recurring cycle.
        
        final int n = 1000;
        ArrayList<Integer> primes = ComplexMath.getPrimesUpUntil(n);
        
        int largest_reptend = -1;
        int largest_period = 0;
        
        for (int primes_index = primes.size() - 1; primes_index >= 0; primes_index--) {
            int prime = primes.get(primes_index).intValue();
            int r = 1, period = 0;
            for (int i = 1; i < prime; i++) r = (10 * r) % prime;
            int rr = r;
            do {
                r = (10 * r) % prime;
                ++period;
            } while (r != rr);
            if (period > largest_period) {
                largest_period = period;
                largest_reptend = prime;
            }
            if (period == prime - 1) break;
        }
        System.out.println("Answer:" + largest_reptend);
    }
}
