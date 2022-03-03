package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.ComplexMath;
import projecteuler.util.Lexography;

public class P41 implements Problem {
    public P41() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("We shall say that an n-digit number is pandigital if " + 
                           "it makes use of all the digits 1 to n exactly once. " + 
                           "For example, 2143 is a 4-digit pandigital and is also prime.\n" + 
        "\n" + 
        "What is the largest n-digit pandigital prime that exists?");
        
        ArrayList<String> arr = new ArrayList<String>();
        String str = "";
        for (int i = 1; i < 10; i++) {
            str += i;
            arr.addAll(Lexography.sortedPermutations(str));
        }
        
        int answer = 0;
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (ComplexMath.isPrime(Integer.parseInt(arr.get(i)))) {
                answer = Integer.parseInt(arr.get(i));
                break;
            }
        }
        
        System.out.println("\nAnswer: " + answer);
    }
}