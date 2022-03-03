package projecteuler.problems;

import projecteuler.util.ComplexMath;

public class P58 implements Problem {
    public P58() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Starting with 1 and spiralling anticlockwise in the following way,\n" +
        "a square spiral with side length 7 is formed.\n" + 
        "\n" + 
        "37 36 35 34 33 32 31\n" + 
        "38 17 16 15 14 13 30\n" + 
        "39 18  5  4  3 12 29\n" + 
        "40 19  6  1  2 11 28\n" + 
        "41 20  7  8  9 10 27\n" + 
        "42 21 22 23 24 25 26\n" + 
        "43 44 45 46 47 48 49\n" + 
        "\n" + 
        "It is interesting to note that the odd squares lie along the bottom right diagonal,\n" +
        "but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime;\n" +
        "that is, a ratio of 8/13 ? 62%.\n" + 
        "\n" + 
        "If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed.\n" +
        "If this process is continued, what is the side length of the square spiral for which\n" +
        "the ratio of primes along both diagonals first falls below 10%?");
        
        int ne = 3, nw = 5, sw = 7, addon = 10, side_length = 3;
        double primes = 3, nums = 5;
        
        while (primes / nums >= 0.1) {
            ne += addon; addon += 2;
            nw += addon; addon += 2;
            sw += addon; addon += 4;
            
            if (ComplexMath.isPrime(ne)) primes++;
            if (ComplexMath.isPrime(nw)) primes++;
            if (ComplexMath.isPrime(sw)) primes++;
            
            nums += 4;
            side_length += 2;
        }
        
        System.out.println("\nAnswer: " + side_length);
    }
}