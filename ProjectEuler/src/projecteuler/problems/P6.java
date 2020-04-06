package projecteuler.problems;

public class P6 implements Problem{
    public P6() {
        super();
    }
    
    public void execute() {
        System.out.println("The sum of the squares of the first ten natural numbers is,");
        System.out.println("\n1^2 + 2^2 + ... + 10^2 = 385\n\nThe square of the sum of the first ten natural numbers is,");
        System.out.println("\n(1 + 2 + ... + 10)^2 = 55^2 = 3025\n");
        System.out.println("Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 ? 385 = 2640.");
        System.out.println("\nFind the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.");
        
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
        
        System.out.println("\nAnswer: " + (int) (square_of_sums - sum_of_squares));
    }
}