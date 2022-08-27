package projecteuler.problems;

import projecteuler.util.ComplexMath;



public class P72 implements Problem {
    public P72() {
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
        "It can be seen that there are 21 elements in this set.\n" + 
        "\n" + 
        "How many elements would be contained in the set of reduced proper fractions for d ? 1,000,000?");
        
        long n = 0L;
        
        for (int i = 2; i <= 1000000; i++) n += i - (i - ComplexMath.eulersTotient(i));
        
        System.out.println("\nAnswer: " + n);
    }
}