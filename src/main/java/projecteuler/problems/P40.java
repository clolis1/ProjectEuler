package projecteuler.problems;

public class P40 implements Problem {
    public P40() {
        super();
    }
    
    @Override
    public String solve() {
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
                product *= Character.getNumericValue(String.valueOf(i).charAt(String.valueOf(i).length() - 1 - offset));
            }
        }
        
        return String.valueOf(product);
    }
}
