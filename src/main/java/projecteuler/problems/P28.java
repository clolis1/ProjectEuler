package projecteuler.problems;

public class P28 implements Problem {
    public P28() {
        super();
    }

    @Override
    public String solve() {
        final int DL = 500;
        long sum = (8 * (long) Math.pow(DL, 3) + 15 * (long) Math.pow(DL, 2) + 13 * DL) * 2 / 3 + 1;
        
        return String.valueOf(sum);
    }
}
