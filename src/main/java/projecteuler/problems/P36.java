package projecteuler.problems;

import projecteuler.util.Digits;

public class P36 implements Problem {
    public P36() {
        super();
    }
    
    @Override
    public String solve() {
        int sum = 0;
        
        for (int i = 0; i < 1000000; i++) {
            if (Digits.isPalindrome(Integer.toString(i)) &&
                Digits.isPalindrome(Integer.toBinaryString(i)))
            sum += i;
        }
        
        return String.valueOf(sum);
    }
}