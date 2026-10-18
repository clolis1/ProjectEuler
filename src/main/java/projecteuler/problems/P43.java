package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.Lexography;

public class P43 implements Problem {
    public P43() {
        super();
    }
    
    @Override
    public String solve() {
        ArrayList<String> perms = Lexography.sortedPermutations("0123456789");
        
        long sum = 0L;
        
        for (int i = perms.size() / 10; i < perms.size(); i++) {
            if (Character.getNumericValue(perms.get(i).charAt(3)) % 2 == 0 &&
                Character.getNumericValue(perms.get(i).charAt(5)) % 5 == 0 &&
                Integer.parseInt(perms.get(i).substring(2, 5)) % 3 == 0 &&
                Integer.parseInt(perms.get(i).substring(4, 7)) % 7 == 0 &&
                Integer.parseInt(perms.get(i).substring(5, 8)) % 11 == 0 &&
                Integer.parseInt(perms.get(i).substring(6, 9)) % 13 == 0 &&
                Integer.parseInt(perms.get(i).substring(7, 10)) % 17 == 0) sum += Long.parseLong(perms.get(i));
        }
        
        return String.valueOf(sum);
    }
}