package projecteuler.problems;

public class P40 implements Problem {
    public P40() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("An irrational decimal fraction is created by concatenating the positive integers:\n" + 
        "\n" + 
        "0.123456789101112131415161718192021...\n" + 
        "\n" + 
        "It can be seen that the 12th digit of the fractional part is 1.\n" + 
        "\n" + 
        "If dn represents the nth digit of the fractional part, find the value of the following expression.\n" + 
        "\n" + 
        "d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000");
        
        int product = 1, index = 0, counter = 0;
        
        for (int i = 1; i <= 1000000; i++) {
            index += String.valueOf(i).length();  
            if (index >= Math.pow(10, counter)) {
                int temp = index, offset = 0;
                while (temp > Math.pow(10, counter)) {
                    temp--;
                    offset++;
                }
                counter++;
                System.out.println(Character.getNumericValue(String.valueOf(i).charAt(String.valueOf(i).length() - 1 - offset)));
                product *= Character.getNumericValue(String.valueOf(i).charAt(String.valueOf(i).length() - 1 - offset));
            }
        }
        
        System.out.println("\nAnswer: " + product);
    }
}
