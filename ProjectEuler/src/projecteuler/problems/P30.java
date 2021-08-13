package projecteuler.problems;

public class P30 implements Problem {
    public P30() {
        super();
    }
    
    static int sum_of_fifth_powers = 0;
    
    @Override
    public void execute() {
        System.out.println("Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:\n" + 
        "\n" + 
        "1634 = 1^4 + 6^4 + 3^4 + 4^4\n" + 
        "8208 = 8^4 + 2^4 + 0^4 + 8^4\n" + 
        "9474 = 9^4 + 4^4 + 7^4 + 4^4\n" + 
        "As 1 = 1^4 is not a sum it is not included.\n" + 
        "\n" + 
        "The sum of these numbers is 1634 + 8208 + 9474 = 19316.\n" + 
        "\n" + 
        "Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.");
        
        for (int i = 2; i < 7; i++) add_digits(0, "", i);

        System.out.println("\nAnswer:" + sum_of_fifth_powers);
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
