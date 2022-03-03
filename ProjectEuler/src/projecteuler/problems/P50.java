package projecteuler.problems;

import java.util.*;

import projecteuler.util.ComplexMath;

public class P50 implements Problem {
    public P50() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The prime 41, can be written as the sum of six consecutive primes:\n" + 
        "\n" + 
        "41 = 2 + 3 + 5 + 7 + 11 + 13\n" + 
        "This is the longest sum of consecutive primes that adds to a prime below one-hundred.\n" + 
        "\n" + 
        "The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.\n" + 
        "\n" + 
        "Which prime, below one-million, can be written as the sum of the most consecutive primes?");
        
        TreeSet<Integer> primes = ComplexMath.getSetPrimesUpUntil(1000000);
        
        int most_consecutive = 0;
        int cSum = 0;
        for (int i : primes) {
            int sum = 0;
            int counter = 0;
            for (int j : primes.tailSet(i)) {
                sum += j;
                counter++;
                if (primes.contains(sum) && counter > most_consecutive) {
                    most_consecutive = counter;
                    cSum = sum;
                }
                if (sum > 1000000) break;
            }
        }
        
        System.out.println("Answer: " + cSum);
    }
}