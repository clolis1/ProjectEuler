package projecteuler.problems;

public class P25 implements Problem {
    public P25() {
        super();
    }

    public void execute() {
        System.out.println("The Fibonacci sequence is defined by the recurrence relation:\n" + 
        "\n" + 
        "Fn = Fn?1 + Fn?2, where F1 = 1 and F2 = 1.\n" + 
        "Hence the first 12 terms will be:\n" + 
        "\n" + 
        "F1 = 1\n" + 
        "F2 = 1\n" + 
        "F3 = 2\n" + 
        "F4 = 3\n" + 
        "F5 = 5\n" + 
        "F6 = 8\n" + 
        "F7 = 13\n" + 
        "F8 = 21\n" + 
        "F9 = 34\n" + 
        "F10 = 55\n" + 
        "F11 = 89\n" + 
        "F12 = 144\n" + 
        "The 12th term, F12, is the first term to contain three digits.\n" + 
        "\n" + 
        "What is the index of the first term in the Fibonacci sequence to contain 1000 digits?");
        
        // This problem can be trivially solved with the use of Binet's Formula.
        // Most of the difficulty of this problem is solved with pen and paer to ascertain the right formula to use.
        
        int n = 1000; // Number of digits you want in your fibonacci number
        int fib_number = (int) Math.round((n + Math.log10(5) / 2 - 1) / Math.log10((1 + Math.pow(5, 0.5)) / 2));
        
        System.out.println("Answer: " + fib_number);
    }
}
