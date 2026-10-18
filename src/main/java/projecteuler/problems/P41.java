package projecteuler.problems;

import java.util.ArrayList;

import projecteuler.util.Primes;
import projecteuler.util.Lexography;

public class P41 implements Problem {
    public P41() {
        super();
    }
    
    @Override
    public String solve() {
        ArrayList<String> arr = new ArrayList<String>();
        String str = "";
        for (int i = 1; i < 10; i++) {
            str += i;
            arr.addAll(Lexography.sortedPermutations(str));
        }
        
        int answer = 0;
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (Primes.isPrime(Integer.parseInt(arr.get(i)))) {
                answer = Integer.parseInt(arr.get(i));
                break;
            }
        }
        
        return String.valueOf(answer);
    }
}