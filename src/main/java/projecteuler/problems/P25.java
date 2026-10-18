package projecteuler.problems;

public class P25 implements Problem {
    public P25() {
        super();
    }

    public String solve() {
        // This problem can be trivially solved with the use of Binet's Formula.
        // Most of the difficulty of this problem is solved with pen and paer to ascertain the right formula to use.
        
        int n = 1000; // Number of digits you want in your fibonacci number
        int fib_number = (int) Math.round((n + Math.log10(5) / 2 - 1) / Math.log10((1 + Math.pow(5, 0.5)) / 2));
        
        return String.valueOf(fib_number);
    }
}
