package projecteuler.problems;

public class P2 implements Problem{
    public P2() {
        super();
    }
    
    public String solve() {
        long sum = 0;
        final long UPPER_LIMIT = 4000000; // 4 million
        long x, y, z;
        x = 1;
        y = 1;
        z = 2;
        
        while (z < UPPER_LIMIT) {
            if (z % 2 == 0) {
                sum += z;
            }
            long temp = y + z;
            x = y;
            y = z;
            z = temp;
        }
        
        return String.valueOf(sum);
    }
}
