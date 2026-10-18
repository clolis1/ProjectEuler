package projecteuler.problems;

public class P6 implements Problem{
    public P6() {
        super();
    }
    
    public String solve() {
        double sum_of_squares = 0;
        double square_of_sums = 0;
        
        final int VALUE_MAX = 100;
        
        for (int i = 1; i <= VALUE_MAX; i++) {
            sum_of_squares += Math.pow(i, 2); // Squaring each number
        }
        
        for (int i = 1; i <= VALUE_MAX; i++) {
            square_of_sums += (double) i;
        }
        square_of_sums = Math.pow(square_of_sums, 2);
        
        return String.valueOf((int) (square_of_sums - sum_of_squares));
    }
}