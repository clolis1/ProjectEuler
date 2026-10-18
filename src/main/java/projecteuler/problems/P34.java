package projecteuler.problems;

import projecteuler.util.Arithmetic;

public class P34 implements Problem {
    public P34() {
        super();
    }

    static int total_sum = 0;

    @Override
    public String solve() {
        digitfactorial(0, "", 7);
        
        return String.valueOf((total_sum));
    }
    
    public void digitfactorial(int sum, String number, int digits_remaining) {
        if (digits_remaining != 0) {
            if (number.length() == 0) digitfactorial(sum, number, digits_remaining - 1);
            for (int i = 0; i < 10; i++) {
                digitfactorial(sum + Arithmetic.factorial(i), number + "" + i, digits_remaining - 1);
            }
        }
        else if (number.length() > 1 && number.equals("" + sum)) total_sum += sum;
    }
}