package projecteuler.problems;

import projecteuler.util.ComplexMath;

public class P36 implements Problem {
    public P36() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The decimal number, 585 = 1001001001v2 (binary), is palindromic in both bases.\n" + 
        "\n" + 
        "Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.\n" + 
        "\n" + 
        "(Please note that the palindromic number, in either base, may not include leading zeros.)");
        
        int sum = 0;
        
        for (int i = 0; i < 1000000; i++) {
            if (ComplexMath.isPalindrome(Integer.toString(i)) &&
                ComplexMath.isPalindrome(Integer.toBinaryString(i)))
            sum += i;
        }
        
        System.out.println("\nAnswer:" + sum);
    }
}