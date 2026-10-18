package projecteuler.problems;

import java.math.BigInteger;

import projecteuler.util.Arithmetic;

public class P24 implements Problem {
    public P24() {
        super();
    }


    public String solve() {
        int num_permutations = Arithmetic.bigFactorial(10).intValue();
        
        String millionth_permutation = "";
        String nums = "0123456789";
        int marker = 1000000;
        
        for (int i = nums.length(); i > 0; i--) {
            int subset_size = num_permutations / i;
            int counter = 0;
            for (int j = 1; subset_size * j < marker; j++) counter++;
            millionth_permutation = millionth_permutation.concat(nums.substring(counter, counter + 1));
            nums = nums.substring(0, counter) + nums.substring(counter + 1);
            num_permutations /= i;
            marker -= subset_size * counter;
        }
        
        return String.valueOf(millionth_permutation);
    }
}
