package projecteuler.util;

import java.math.BigInteger;

/** Properties of a number's decimal digits: palindromes, pandigitals, decimal expansions. */
public final class Digits {
    private Digits() {}

    // Returns true if the given number is a palindrome
    public static boolean isPalindrome (int x) {
        String str = Integer.toString(x);
        
        int forwards = 0;
        int backwards = str.length() - 1;
        
        while (forwards < str.length()) {
            if (str.charAt(forwards) != str.charAt(backwards)) {
                return false;
            }
            else {
                forwards++;
                backwards--;
            }
        }
        return true;
    }

    // Returns true if the given String is a palindrome
    public static boolean isPalindrome (String str) {
        int forwards = 0;
        int backwards = str.length() - 1;
        
        while (forwards < str.length()) {
            if (str.charAt(forwards) != str.charAt(backwards)) {
                return false;
            }
            else {
                forwards++;
                backwards--;
            }
        }
        return true;
    }

    // Returns true if the given number is pandigital
    public static boolean is1To9Pandigital(int x) {
        String str = "" + x;
        if (str.contains("1") &&
            str.contains("2") &&
            str.contains("3") &&
            str.contains("4") &&
            str.contains("5") &&
            str.contains("6") &&
            str.contains("7") &&
            str.contains("8") &&
            str.contains("9")    ) return true;
        return false;
    }

    // Returns true if the given number is pandigital
    public static boolean is1To9Pandigital(String str) {
        if (str.contains("1") &&
            str.contains("2") &&
            str.contains("3") &&
            str.contains("4") &&
            str.contains("5") &&
            str.contains("6") &&
            str.contains("7") &&
            str.contains("8") &&
            str.contains("9")    ) return true;
        return false;
    }

    // Returns true if the given number x is pandigital up to n
    public static boolean isNPandigital(int x, int n) {
        String str = "" + x;
        for (int i = 1; i <= n; i++) {
            if (!str.contains(Integer.toString(i))) return false;
        }
        return true;
    }

    // Gives the decimal represenation of the given integer's square root to y decimal places. Trailing zeroes are removed sometimes.
    // Does not round
    public static String decimalRepOfSquareRoot(int x_int, int y) {
        BigInteger i = BigInteger.ONE, r, x = new BigInteger(Integer.toString(x_int)), h = BigInteger.TEN.multiply(BigInteger.TEN);
        String decimal = "";
        
        while ((i.add(BigInteger.ONE)).multiply(i.add(BigInteger.ONE)).compareTo(x) <= 0) i = i.add(BigInteger.ONE);
        r = x.subtract(i.multiply(i));
        decimal = i.toString();
        i = i.add(i);
            
        for (int j = 0; j < y - 1; j++) {
            BigInteger n = i.multiply(BigInteger.TEN), m = BigInteger.ZERO;
            while (n.add(m).add(BigInteger.ONE).multiply(m.add(BigInteger.ONE)).compareTo(r.multiply(h)) < 0) m = m.add(BigInteger.ONE);
            decimal += m.toString();
            r = r.multiply(h).subtract((n.add(m).multiply(m)));
            i = n.add(m.add(m));
        }
        
        // For rounding
/**        
        if (Character.getNumericValue(decimal.charAt(decimal.length() - 1)) >= 5) {
            decimal = decimal.substring(0, decimal.length() - 1);
            while (decimal.charAt(decimal.length() - 1) == 9) decimal = decimal.substring(0, decimal.length() - 1);
            decimal = decimal.substring(0, decimal.length() - 1) + (Character.getNumericValue(decimal.charAt(decimal.length() - 1)) + 1);
        }
        else decimal = decimal.substring(0, decimal.length() - 1);
*/        
        return decimal;
    }
}
