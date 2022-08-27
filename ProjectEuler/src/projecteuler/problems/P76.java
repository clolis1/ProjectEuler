package projecteuler.problems;

public class P76 implements Problem {
    public P76() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("It is possible to write five as a sum in exactly six different ways:\n" + 
        "\n" + 
        "4 + 1\n" + 
        "3 + 2\n" + 
        "3 + 1 + 1\n" + 
        "2 + 2 + 1\n" + 
        "2 + 1 + 1 + 1\n" + 
        "1 + 1 + 1 + 1 + 1\n" + 
        "\n" + 
        "How many different ways can one hundred be written as a sum of at least two positive integers?");
        
        int[] summations = new int[101];
        summations[0] = 1;
        
        for (int i = 1; i < 100; i++) {
            for (int j = i; j <= 100; j++) {
                summations[j] += summations[j - i];
            }
        }
        
        for (int i = 0; i < 11; i++) System.out.println(i + " " + summations[i]);
        
        System.out.println("\nAnswer: " + summations[100]);
    }
}