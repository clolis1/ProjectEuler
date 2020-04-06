package projecteuler.problems;

import projecteuler.util.ComplexMath;

public class P4 implements Problem{
    public P4() {
        super();
    }
    
    public void execute() {
        System.out.println("A palindromic number reads the same both ways.\nThe largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.");
        System.out.println("\nFind the largest palindrome made from the product of two 3-digit numbers.");
        
        int largest_palindrome = 0;
        
        final int VALUE_MAX = 999;
        // Test all combinations of 3-digit factor pairs
        for (int i = VALUE_MAX; i*VALUE_MAX > largest_palindrome; i--) { // As long as some i * 999 is greater than the largest known palindrome, keep testing
            for (int j = VALUE_MAX; i*j > largest_palindrome; j--) { // As long as some i * j is greater than the largest known palindrome, keep testing
                if (ComplexMath.isPalindrome(i*j)) { // Test for palindrome
                    largest_palindrome = i*j; // New largest_palindrome
                    break;
                }
            }
        }
        
        System.out.println("\nAnswer: " + largest_palindrome);
    }
}