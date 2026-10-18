package projecteuler.problems;

import java.util.*;

import projecteuler.util.Primes;
import projecteuler.util.Lexography;

public class P51 implements Problem {
    public P51() {
        super();
    }
    
    @Override
    public String solve() {
        long smallest_prime = 0L;
        
        for (int i = 2; smallest_prime == 0L; i++) { // Length of number
            TreeSet<String> number_combinations = new TreeSet<String>();
            String wildcards = "";
            for (int j = 1; j < i && smallest_prime == 0L; j++) { // Number of wildcards
                wildcards += '*';
                number_combinations = new TreeSet<String>();
                digitMultisets("", '0', i - j, number_combinations); // Every choice of fixed digits, e.g. "233"
                for (String s : number_combinations) {
                    s += wildcards;
                    for (String t : Lexography.sortedPermutations(s)) {
                        if (t.charAt(t.length() - 1) == '*' ||
                            t.charAt(t.length() - 1) == '5' ||
                            Character.getNumericValue(t.charAt(t.length() - 1)) % 2 == 0 ||
                            t.charAt(0) == '0') continue;
                        int primes = 0;
                        long first = Long.MAX_VALUE;
                        for (int l = 0; l < 10; l++) {
                            if (l == 0 && t.charAt(0) == '*') continue;
                            long m = Long.parseLong(t.replaceAll("\\*", Integer.toString(l)));
                            if (Primes.isPrime(m)) {
                                primes++;
                                first = Math.min(first, m);
                            }
                        }
                        if (primes > 7) {
                            smallest_prime = first;
                            break;
                        }           
                    }
                    if (smallest_prime != 0L) break;
                }
            }
        }
        
        return String.valueOf(smallest_prime);
    }

    // Adds every non-decreasing string of `remaining` more digits (starting no lower than `from`) to `out`.
    // Each string stands for one multiset of fixed digits; sortedPermutations then places them.
    private void digitMultisets(String prefix, char from, int remaining, Set<String> out) {
        if (remaining == 0) {
            out.add(prefix);
            return;
        }
        for (char c = from; c <= '9'; c++) digitMultisets(prefix + c, c, remaining - 1, out);
    }
}