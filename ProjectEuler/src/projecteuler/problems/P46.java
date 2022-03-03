package projecteuler.problems;

import java.util.*;

public class P46 implements Problem {
    public P46() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("It was proposed by Christian Goldbach that every odd composite number\n"  +
        "can be written as the sum of a prime and twice a square.\n" + 
        "\n" + 
        "9 = 7 + 2×12\n" + 
        "15 = 7 + 2×22\n" + 
        "21 = 3 + 2×32\n" + 
        "25 = 7 + 2×32\n" + 
        "27 = 19 + 2×22\n" + 
        "33 = 31 + 2×12\n" + 
        "\n" + 
        "It turns out that the conjecture was false.\n" + 
        "\n" + 
        "What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?");
        
        long composite = -1L;
        
        ArrayList<Long> primes = new ArrayList<Long>();
        ArrayList<Long> squares = new ArrayList<Long>();
        squares.add(1L);
        primes.add(2L);
        primes.add(3L);
        
        for (long i = 4; composite == -1L; i++) {
            double sqrt_max = Math.sqrt((double) i); // Check for divisibility up to the square root
            if (sqrt_max == (int) sqrt_max) squares.add(i);
            if (i % 2 == 0) continue;
            boolean prime = true;
            for (int j = 0; primes.get(j) <= sqrt_max; j++) { 
                if (i % primes.get(j) == 0) {// number is not prime
                    prime = false;
                    break;
                }
            }
            if (prime) { // number is prime
                primes.add(i);
            }
            else {
                boolean conjTrue = false;
                for (Long l : primes) {
                    ListIterator<Long> it = squares.listIterator(squares.size() - 1);
                    while (it.hasPrevious()) {
                        long m = it.previous();
                        if (l + 2L * m == i) {
                            conjTrue = true;
                            break;
                        }
                        else if (l + 2L * m < i) break;
                    }
                    if (conjTrue) break;
                }
                if (!conjTrue) composite = i;
            }
        }
        
        System.out.println("\nAnswer: " + composite);
    }
}