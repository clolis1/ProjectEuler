package projecteuler.problems;

public class P1 implements Problem{
    public P1() {
        super();
    }
    
    public String solve() {
        int sum = 0;
        
        for (int i = 0; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        
        return String.valueOf(sum);
    }
}
