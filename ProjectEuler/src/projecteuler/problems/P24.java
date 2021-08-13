package projecteuler.problems;

import java.math.BigInteger;

import projecteuler.util.ComplexMath;

public class P24 implements Problem {
    public P24() {
        super();
    }


    public void execute() {
        
        System.out.println("A permutation is an ordered arrangement of objects." +
        "\nFor example, 3124 is one possible permutation of the digits 1, 2, 3 and 4." +
        "\nIf all of the permutations are listed numerically or alphabetically, we call it lexicographic order." +
        "\nThe lexicographic permutations of 0, 1 and 2 are:\n" + 
        "\n" + 
        "012   021   102   120   201   210\n" + 
        "\n" + 
        "What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?");
         
        int num_permutations = ComplexMath.bigFactorial(10).intValue();
        
        String millionth_permutation = "";
        String nums = "0123456789";
        int marker = 1000000;
        
        for (int i = nums.length(); i > 0; i--) {
            int subset_size = num_permutations / i;
            int counter = 0;
            for (int j = 1; subset_size * j < marker; j++) {
                counter++;
            }
            System.out.println(counter * subset_size + " " + marker + " " + num_permutations);
            millionth_permutation = millionth_permutation.concat(nums.substring(counter, counter + 1));
            nums = nums.substring(0, counter) + nums.substring(counter + 1);
            num_permutations /= i;
            marker -= subset_size * counter;
        }
        
        System.out.println("\nAnswer: " + millionth_permutation);
    }
}
