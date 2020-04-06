package projecteuler.problems;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.concurrent.atomic.AtomicLong;

import projecteuler.util.ComplexMath;

public class P15 implements Problem{
    public P15() {
        super();
    }
    
    public void execute() {
        System.out.println("Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.\n" + 
        "How many such routes are there through a 20×20 grid?");
        
        final int GRID_LENGTH = 20;
        final int GRID_WIDTH = 20;
        AtomicLong num_paths = new AtomicLong(1L);
        
        // Because calculating very large factorials will take too long, it is faster to generate the prime factors of all the numbers in the factorials.
        // Then, remove factors common to the numerator and the denominator and multiply out what remains of the denominator.
        
//        ArrayList<Double> numerator_prime_factors = new ArrayList<Double>();
//        ArrayList<Double> denominator_prime_factors = new ArrayList<Double>();
        
        HashMap<Integer, Integer> numerator_prime_factors = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> denominator_prime_factors = new HashMap<Integer, Integer>();
        
        // Populate arrays with prime factors from the factorials
        for (int i = 1; i <= GRID_LENGTH + GRID_WIDTH; i++) {
            HashMap<Integer, Integer> temp = ComplexMath.getPrimeFactors(i);
            
            // add temp to numerator_prime_factors
            temp.forEach((key, val) -> {
                numerator_prime_factors.computeIfPresent(key, (k, v) -> numerator_prime_factors.replace(key, v + val));
                numerator_prime_factors.computeIfAbsent(key, k -> numerator_prime_factors.put(key, val));
            });
            
            if (i <= GRID_LENGTH) {
                temp.forEach((key, val) -> {
                    denominator_prime_factors.computeIfPresent(key, (k, v) -> numerator_prime_factors.replace(key, v + val));
                    denominator_prime_factors.computeIfAbsent(key, k -> numerator_prime_factors.put(key, val));
                });
            }
            if (i <= GRID_WIDTH) {
                temp.forEach((key, val) -> {
                    denominator_prime_factors.computeIfPresent(key, (k, v) -> numerator_prime_factors.replace(key, v + val));
                    denominator_prime_factors.computeIfAbsent(key, k -> numerator_prime_factors.put(key, val));
                });
            }
        }
        
        // "Simplify" the equation by removing like factors.
        denominator_prime_factors.forEach((key, val) -> {
                numerator_prime_factors.compute(key, (k, v) -> numerator_prime_factors.replace(key, v - val));
        });
        
        // Multiply out the numerator
        
        numerator_prime_factors.forEach((key, val) -> {
                num_paths.set(num_paths.get() * (long) Math.pow(key, val));
        });
        
        System.out.println("\nAnswer: " + num_paths);
    }
}