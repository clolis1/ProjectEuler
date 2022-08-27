package projecteuler.problems;

import java.util.List;

import projecteuler.util.ComplexMath;

public class P77 implements Problem {
    public P77() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("It is possible to write ten as the sum of primes in exactly five different ways:\n" + 
        "\n" + 
        "7 + 3\n" + 
        "5 + 5\n" + 
        "5 + 3 + 2\n" + 
        "3 + 3 + 2 + 2\n" + 
        "2 + 2 + 2 + 2 + 2\n" + 
        "\n" + 
        "What is the first value which can be written as the sum of primes in over five thousand different ways?");
        
        int target = 2;
        
        List<Integer> primes = ComplexMath.getPrimesUpUntil(1000);
        
        while (true) {
            int[] summation = new int[target + 1];
            summation[0] = 1;
            
            for (int i = 0; i < primes.size(); i++) {
                for (int j = primes.get(i); j <= target; j++) {
                    summation[j] += summation[j - primes.get(i)];
                }
            }
            
            if (summation[target] > 5000) break;
            target++;
        }
        System.out.println("\nAnswer: " + target);
    }
}