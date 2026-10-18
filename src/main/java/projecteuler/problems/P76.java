package projecteuler.problems;

public class P76 implements Problem {
    public P76() {
        super();
    }
    
    @Override
    public String solve() {
        int[] summations = new int[101];
        summations[0] = 1;
        
        for (int i = 1; i < 100; i++) {
            for (int j = i; j <= 100; j++) {
                summations[j] += summations[j - i];
            }
        }
        
        
        return String.valueOf(summations[100]);
    }
}