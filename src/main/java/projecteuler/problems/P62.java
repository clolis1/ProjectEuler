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
    public String solve() {
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
        
        return String.valueOf(solution);
    }
}