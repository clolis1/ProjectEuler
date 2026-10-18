package projecteuler.problems;

import projecteuler.util.Divisors;

public class P9 implements Problem{
    public P9() {
        super();
    }
    
    public String solve() {
        // Use u and v as iterative counters to clarify equations
        // 3 and 1 form the smallest relatively prime pair of odd numbers.
        int u = 3;
        int v = 1;
        int a = u*v;
        int b = ((int) Math.pow((double) u, 2.0) - (int) Math.pow((double) v, 2.0)) / 2;
        int c = ((int) Math.pow((double) u, 2.0) + (int) Math.pow((double) v, 2.0)) / 2;
        
        u = 1;
        
        while ((a + b + c) != 1000) { // Every run-through iterates through a primitive triple and its multiples
            if ((a + b + c) < 1000) { // Set the new primtive triple
                u += 2;
                while (!Divisors.isRelativelyPrime(u, v)) { // Continuously increase u until there is coparity
                    u += 2;
                }
                a = u*v;
                b = ((int) Math.pow((double) u, 2.0) - (int) Math.pow((double) v, 2.0)) / 2;
                c = ((int) Math.pow((double) u, 2.0) + (int) Math.pow((double) v, 2.0)) / 2;
                
                if  ((a + b + c > 1000)) { // If the Pythagorean triple is too large, we need a different Pythagorean triple
                    v += 2;
                    u = v + 2;
                    while (!Divisors.isRelativelyPrime(u, v)) {
                        u += 2;
                    }
                    a = u*v;
                    b = ((int) Math.pow((double) u, 2.0) - (int) Math.pow((double) v, 2.0)) / 2;
                    c = ((int) Math.pow((double) u, 2.0) + (int) Math.pow((double) v, 2.0)) / 2;
                }
            }
            // Test multiples of current triple
            int x = a;
            int y = b;
            int z = c;
            while ((a + b + c) < 1000) {
                a += x;
                b += y;
                c += z;
            }
            if ((a + b + c) == 1000) {
                break;
            }
            else {
                a = x;
                b = y;
                c = z;
            }
        }
        
        int product = a * b * c;
        
        return String.valueOf(product);
    }
}