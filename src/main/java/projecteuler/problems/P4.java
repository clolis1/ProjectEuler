package projecteuler.problems;

import projecteuler.util.Digits;

public class P4 implements Problem{
    public P4() {
        super();
    }
    
    public String solve() {
        int largest_palindrome = 0;
        
        final int VALUE_MAX = 999;
        // Test all combinations of 3-digit factor pairs
        for (int i = VALUE_MAX; i*VALUE_MAX > largest_palindrome; i--) { // As long as some i * 999 is greater than the largest known palindrome, keep testing
            for (int j = VALUE_MAX; i*j > largest_palindrome; j--) { // As long as some i * j is greater than the largest known palindrome, keep testing
                if (Digits.isPalindrome(i*j)) { // Test for palindrome
                    largest_palindrome = i*j; // New largest_palindrome
                    break;
                }
            }
        }
        
        return String.valueOf(largest_palindrome);
    }
}