package projecteuler.problems;

public class P17 implements Problem{
    public P17() {
        super();
    }
    
    public void execute() {
        System.out.println("If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.\n" + 
        "\n" + 
        "If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?\n" + 
        "\n" + 
        "\n" + 
        "NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters." +
        "The use of \"and\" when writing out numbers is in compliance with British usage.");
        
        int sum = 0;
        final int NUMBER_MAX = 1000;
        
        for (int i = 1; i < NUMBER_MAX; i++) {
            sum += this.lettersInNum(i);
        }
        sum += 11; // Add "one thousand"
        
        System.out.println("\nAnswer: " + sum);
    }
    
    // Returns the amount of letters in a given number.
    public int lettersInNum (int x) {
        int sum = 0;
        
        // Split number into a 1-20, 21-99, and 100+ section
        int tens_and_ones = x % 100; // Return last two digits
        int hundreds = x / 100; // Only returns value above 99
        
        switch (tens_and_ones) {
            case 0: break;
            case 1: sum += 3; break;
            case 2: sum += 3; break;
            case 3: sum += 5; break;
            case 4: sum += 4; break;
            case 5: sum += 4; break;
            case 6: sum += 3; break;
            case 7: sum += 5; break;
            case 8: sum += 5; break;
            case 9: sum += 4; break;
            case 10: sum += 3; break;
            case 11: sum += 6; break;
            case 12: sum += 6; break;
            case 13: sum += 8; break;
            case 14: sum += 8; break;
            case 15: sum += 7; break;
            case 16: sum += 7; break;
            case 17: sum += 9; break;
            case 18: sum += 8; break;
            case 19: sum += 8; break;
            default: break;
        }
        
        int tens = tens_and_ones / 10;
        int ones = tens_and_ones % 10;
        
        if (tens_and_ones > 19) {
            switch (tens) {
                case 2: sum += 6; break;
                case 3: sum += 6; break;
                case 4: sum += 5; break;
                case 5: sum += 5; break;
                case 6: sum += 5; break;
                case 7: sum += 7; break;
                case 8: sum += 6; break;
                case 9: sum += 6; break;
                default: break;
            }
            switch (ones) {
                case 1: sum += 3; break;
                case 2: sum += 3; break;
                case 3: sum += 5; break;
                case 4: sum += 4; break;
                case 5: sum += 4; break;
                case 6: sum += 3; break;
                case 7: sum += 5; break;
                case 8: sum += 5; break;
                case 9: sum += 4; break;
            }
        }
        
        if (hundreds > 0) {
            switch (hundreds) {
                case 1: sum += 10; break;
                case 2: sum += 10; break;
                case 3: sum += 12; break;
                case 4: sum += 11; break;
                case 5: sum += 11; break;
                case 6: sum += 10; break;
                case 7: sum += 12; break;
                case 8: sum += 12; break;
                case 9: sum += 11; break;
            }
        }
        
        if (hundreds > 0 && tens_and_ones > 0) {
            sum += 3;
        }
        
        return sum;
    }
}