package projecteuler.problems;

import java.math.BigInteger;

public class P57 implements Problem {
    public P57() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("It is possible to show that the square root of two can be expressed as an infinite continued fraction.\n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        "By expanding this for the first four iterations, we get:\n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        " \n" + 
        "\n" + 
        "\n" + 
        "The next three expansions are \n" + 
        " \n" + 
        ", \n" + 
        " \n" + 
        ", and \n" + 
        " \n" + 
        ", but the eighth expansion, \n" + 
        " \n" + 
        ", is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.\n" + 
        "\n" + 
        "In the first one-thousand expansions, how many fractions contain a numerator with more digits than the denominator?");
        
        BigInteger previous_denominator = BigInteger.ZERO, current_denominator = BigInteger.ONE,
            current_numerator = BigInteger.ONE;
        
        final BigInteger TWO = new BigInteger("2");
        
        int counter = 0;
        
        for (int i = 1; i < 1001; i++) {
            BigInteger temp = current_denominator;
            current_denominator = current_denominator.multiply(TWO).add(previous_denominator);
            previous_denominator = temp;
            current_numerator = current_numerator.add(previous_denominator.multiply(TWO));
            if (current_numerator.toString().length() > current_denominator.toString().length()) counter++;
        }
        
        
        System.out.println("\nAnswer: " + counter);
    }
}