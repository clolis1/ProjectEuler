package projecteuler.problems;

public class P2 implements Problem{
    public P2() {
        super();
    }
    
    public void execute() {
        System.out.println("Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:");
        System.out.println("\n\t\t\t\t1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...");
        System.out.println("\nBy considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.");
        
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
        
        System.out.println("\nAnswer: " + sum);
    }
}
