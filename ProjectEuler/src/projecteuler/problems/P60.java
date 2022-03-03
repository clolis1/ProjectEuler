package projecteuler.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import java.util.Set;
import java.util.TreeSet;

import projecteuler.util.ComplexMath;

public class P60 implements Problem {
    public P60() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The primes 3, 7, 109, and 673, are quite remarkable.\n" +
        "By taking any two primes and concatenating them in any order the result will always be prime.\n" +
        "For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes, 792,\n" +
        "represents the lowest sum for a set of four primes with this property.\n" + 
        "\n" + 
        "Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.");
        
        final int SET_SIZE = 5;
        
        Set<Long> primes = new TreeSet<Long>();
        HashMap<Long, Set<Set<Long>>> sets = new HashMap<Long, Set<Set<Long>>>();
        for (long i = 1; i <= SET_SIZE; i++) sets.put(i, new HashSet<Set<Long>>());
        long[] lowest_sums = new long[SET_SIZE + 1];
        lowest_sums[2] = 10;
        for (int i = 3; i < SET_SIZE + 1; i++) lowest_sums[i] = Integer.MAX_VALUE * (long) i;
        
        primes.add(2L);
        primes.add(3L);
        
        long counter = 3; // We'll start at 3
        
        Iterator<Long> foo = new TreeSet<Long>().iterator();
        
        while (counter + lowest_sums[SET_SIZE - 1] < lowest_sums[SET_SIZE]) {
            boolean prime = true;
            long sqrt_max = (int) Math.sqrt((double) counter); // Check for divisibility up to the square root
            Iterator<Long> it = primes.iterator();
            long x = 0;
            if (primes.contains(counter)) it = foo;
            while (it.hasNext() && x <= sqrt_max) {
                x = it.next();
                if (counter % x == 0) {
                    prime = false;
                    break;
                }
            }
            
            if (prime) { // number is prime
                if (counter < Math.sqrt(lowest_sums[SET_SIZE])) primes.add(counter);
                
                Set<Long> prime_pairs = new HashSet<Long>();
                
                for (long i : new TreeSet<Long>(primes)) {
                    if (i > counter || i + counter + lowest_sums[SET_SIZE - 2] > lowest_sums[SET_SIZE]) break;
                    long m = Long.parseLong(Long.toString(i) + Long.toString(counter));
                    long n = Long.parseLong(Long.toString(counter) + Long.toString(i));
                    boolean mb = false, nb = false;
                    if (ComplexMath.isPrime(m)) {
                        if (m < Math.sqrt(lowest_sums[SET_SIZE])) primes.add(m);
                        mb = true;
                    }
                    if (ComplexMath.isPrime(n)) {
                        if (n < Math.sqrt(lowest_sums[SET_SIZE])) primes.add(n);
                        nb = true;
                    }
                    if (mb && nb) {
                        prime_pairs.add(i);
                        
                        HashSet<Long> temp = new HashSet<Long>();
                        temp.add(i);
                        temp.add(counter);
                        
                        sets.get(2L).add(temp);
                    }
                }
                
                for (int i = SET_SIZE - 1; i > 1; i--) {
                    if ((counter * (SET_SIZE - i)) + lowest_sums[i] > lowest_sums[SET_SIZE]) {
                        sets.get((long) i).clear();
                        break;
                    }
                    for (Set<Long> s : sets.get((long) i)) {
                        Set<Long> temp = new HashSet<Long>(s);
                        temp.retainAll(prime_pairs);
                        if (temp.size() == i) {
                            temp.add(counter);
                            sets.get(i + 1L).add(temp);
                            
                            int temp_sum = 0;
                            for (long j : temp) temp_sum += j;
                            lowest_sums[i + 1] = Math.min(temp_sum, lowest_sums[i + 1]);
                        }
                    }
                }
            }
            counter += 2; // Check next number
        }
        
        System.out.println("\nAnswer: " + lowest_sums[SET_SIZE]);
    }
}