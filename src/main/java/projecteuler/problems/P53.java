package projecteuler.problems;

import java.math.BigInteger;

public class P53 implements Problem {
    public P53() {
        super();
    }
    
    @Override
    public String solve() {
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
        
        return String.valueOf(counter);
    }
}