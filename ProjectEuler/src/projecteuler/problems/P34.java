package projecteuler.problems;

import projecteuler.util.ComplexMath;

public class P34 implements Problem {
    public P34() {
        super();
    }

    static int total_sum = 0;

    @Override
    public void execute() {
        System.out.println("145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.\n" + 
        "\n" + 
        "Find the sum of all numbers which are equal to the sum of the factorial of their digits.\n" + 
        "\n" + 
        "Note: As 1! = 1 and 2! = 2 are not sums they are not included.");
        
        digitfactorial(0, "", 7);
        
        System.out.println("\nAnswer:" + (total_sum));
    }
    
    public void digitfactorial(int sum, String number, int digits_remaining) {
        if (digits_remaining != 0) {
            if (number.length() == 0) digitfactorial(sum, number, digits_remaining - 1);
            for (int i = 0; i < 10; i++) {
                digitfactorial(sum + ComplexMath.factorial(i), number + "" + i, digits_remaining - 1);
            }
        }
        else if (number.length() > 1 && number.equals("" + sum)) total_sum += sum;
    }
}