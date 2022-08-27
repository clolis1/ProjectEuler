package projecteuler.problems;

import projecteuler.util.ComplexMath;

public class P73 implements Problem {
    public P73() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Consider the fraction, n/d, where n and d are positive integers.\n" +
        "If n<d and HCF(n,d)=1, it is called a reduced proper fraction.\n" + 
        "\n" + 
        "If we list the set of reduced proper fractions for d ? 8 in ascending order of size, we get:\n" + 
        "\n" + 
        "1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8\n" + 
        "\n" + 
        "It can be seen that there are 3 fractions between 1/3 and 1/2.\n" + 
        "\n" + 
        "How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for d ? 12,000?");
        
        final double ONE_THIRD = 1.0 / 3.0, ONE_HALF = 1.0 / 2.0;
        long fractions = 0L;
        
        for (int i = 1; i <= 12000; i++) {
            int numerator = i / 3;
            
            while ((double) numerator / i < ONE_HALF) {
                if ((double) numerator / i > ONE_THIRD && ComplexMath.isRelativelyPrime(numerator, i)) fractions++;
                numerator++;
            }
        }
        
        System.out.println("\nAnswer: " + fractions);
    }
}