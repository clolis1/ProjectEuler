package projecteuler.problems;

import java.util.Hashtable;

import projecteuler.util.ComplexMath;

public class P21 implements Problem{
    Hashtable<Integer, Integer> sum_of_divisors;
    
    public P21() {
        super();
        sum_of_divisors = new Hashtable<Integer, Integer>();
    }
    
    public void execute() {
        System.out.println("Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).");
        System.out.println("If d(a) = b and d(b) = a, where a =/= b, then a and b are an amicable pair and each of a and b are called amicable numbers.");
        System.out.println("\nFor example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55, and 110;");
        System.out.println("therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71, 142; so d(284) = 220.");
        System.out.println("\nEvaluate the sum of all amicable numbers under 10000.");
        
        
        
        System.out.println("\nAnswer: " + "");
    }
}