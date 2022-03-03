package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.Lexography;

public class P43 implements Problem {
    public P43() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The number, 1406357289, is a 0 to 9 pandigital number because " + 
        "it is made up of each of the digits 0 to 9 in some order, " + 
        "but it also has a rather interesting sub-string divisibility property.\n" + 
        "\n" + 
        "Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:\n" + 
        "\n" + 
        "d2d3d4=406 is divisible by 2\n" + 
        "d3d4d5=063 is divisible by 3\n" + 
        "d4d5d6=635 is divisible by 5\n" + 
        "d5d6d7=357 is divisible by 7\n" + 
        "d6d7d8=572 is divisible by 11\n" + 
        "d7d8d9=728 is divisible by 13\n" + 
        "d8d9d10=289 is divisible by 17\n" + 
        "Find the sum of all 0 to 9 pandigital numbers with this property.");
        
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
        
        System.out.println("\nAnswer: " + sum);
    }
}