package projecteuler.problems;

public class P30 implements Problem {
    public P30() {
        super();
    }
    
    static int sum_of_fifth_powers = 0;
    
    @Override
    public String solve() {
        for (int i = 2; i < 7; i++) add_digits(0, "", i);

        return String.valueOf(sum_of_fifth_powers);
    }
    
    // Adds one of every fifth power, 0-9 to a sum.
    // @sum the sum of the previous digit slot, carried over
    // @number, a String of the numbers added so far.
    // @depth, the number of layers left to go to construct the number
    public void add_digits(int sum, String number, int depth) {
        if (depth == 0) {
            if (number.equals("" + sum)) sum_of_fifth_powers += sum;
            return;
        }
        for (int i = 0; i < 10; i++) {
            add_digits(sum + i * i * i * i * i, number + i, depth - 1);             
        }
    }
}
