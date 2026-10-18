package projecteuler.util;

import java.math.BigInteger;

/** Small general-purpose helpers: squares, factorials, series. */
public final class Arithmetic {
    private Arithmetic() {}

    // Returns true if the given number is a square number
    public static boolean isSquare (double x) {
        if (Math.sqrt(x) % 1 == 0) {
            return true;
        }
        return false;
    }

    public static int factorial(int n) {
        if (n > 1) return n * factorial(n - 1);
        else return 1;
    }

    public static BigInteger bigFactorial(int n) {
        if (n > 1) return (new BigInteger(Integer.toString(n))).multiply(bigFactorial(n-1));
        else return new BigInteger("1");
    }

    // Method for finding the sum of a series of consecutive numbers
    public static int sumOfConsecutiveNumbers(int first, int last) {
        double x = first, y = last, n = first - last;
        
        return (int) (n / 2 * (x + y));
    }
}
