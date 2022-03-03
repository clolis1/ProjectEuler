package projecteuler.problems;

import java.math.BigInteger;

public class P53 implements Problem {
    public P53() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("There are exactly ten ways of selecting three from five, 12345:\n" + 
        "\n" + 
        "123, 124, 125, 134, 135, 145, 234, 235, 245, and 345\n" + 
        "\n" + 
        "In combinatorics, we use the notation, \n" + 
        ".\n" + 
        "\n" + 
        "In general, \n" + 
        " \n" + 
        ", where , , and .\n" + 
        "\n" + 
        "It is not until , that a value exceeds one-million: \n" + 
        ".\n" + 
        "\n" + 
        "How many, not necessarily distinct, values of \n" + 
        " for , are greater than one-million?");
        
        long counter = 0;
        BigInteger bnFactorial = BigInteger.ONE;
        for (BigInteger bi = BigInteger.ONE; bi.compareTo(new BigInteger("100")) < 1; bi = bi.add(BigInteger.ONE)) {
            bnFactorial = bnFactorial.multiply(bi);
            BigInteger brFactorial = BigInteger.ONE;
            BigInteger bnrFactorial = bnFactorial;
            for (BigInteger bj = BigInteger.ONE; bj.compareTo(bi) < 0; bj = bj.add(BigInteger.ONE)) {
                brFactorial = brFactorial.multiply(bj);
                bnrFactorial = bnrFactorial.divide(bi.subtract(bj).add(BigInteger.ONE));
                if (bnFactorial.divide(brFactorial.multiply(bnrFactorial)).compareTo(new BigInteger("1000000")) > 0) counter++;
            }
        }
        
        System.out.println("\nAnswer: " + counter);
    }
}