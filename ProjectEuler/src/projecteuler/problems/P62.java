package projecteuler.problems;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;

public class P62 implements Problem {
    public P62() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The cube, 41063625 (3453), can be permuted to produce two other cubes:\n" +
        "56623104 (3843) and 66430125 (4053).\n" +
        "In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.\n" + 
        "\n" + 
        "Find the smallest cube for which exactly five permutations of its digits are cube.");
        
        BigInteger solution = BigInteger.ZERO;
        HashMap<String, List<BigInteger>> cubes = new HashMap<String, List<BigInteger>>();
        
        for (BigInteger bi = BigInteger.ONE; solution.compareTo(BigInteger.ZERO) == 0; bi = bi.add(BigInteger.ONE)) {
            BigInteger cube = bi.multiply(bi).multiply(bi);
            char[] arr = cube.toString().toCharArray();
            Arrays.sort(arr);
            String str = new String(arr);
            if (cubes.containsKey(str)) {
                cubes.get(str).add(cube);
                if (cubes.get(str).size() == 5) solution = cubes.get(str).get(0);
            }
            else {
                List<BigInteger> nums = new ArrayList<BigInteger>();
                nums.add(cube);
                cubes.put(str, nums);
            }
        }
        
        System.out.println("\nAnswer: " + solution);
    }
}