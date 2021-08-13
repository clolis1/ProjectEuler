package projecteuler.problems;

public class P28 implements Problem {
    public P28() {
        super();
    }

    @Override
    public void execute() {
        System.out.println("Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:\n" +
        "\n" +
        "\t21 22 23 24 25\n" +
        "\t20  7  8  9 10\n" + 
        "\t19  6  1  2 11\n" +
        "\t18  5  4  3 12\n" +
        "\t17 16 15 14 13\n" +
        "\n" +
        "It can be verified that the sum of the numbers on the diagonals is 101.\n" +
        "\n" +
        "What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?");
        
        final int DL = 500;
        long sum = (8 * (long) Math.pow(DL, 3) + 15 * (long) Math.pow(DL, 2) + 13 * DL) * 2 / 3 + 1;
        
        System.out.println("\nAnswer:" + sum);
    }
}
