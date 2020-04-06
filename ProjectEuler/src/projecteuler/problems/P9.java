package projecteuler.problems;

import projecteuler.util.ComplexMath;

public class P9 implements Problem{
    public P9() {
        super();
    }
    
    public void execute() {
        System.out.println("A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,");
        System.out.println("\na^2 + b^2 = c^2");
        System.out.println("\nFor example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.");
        System.out.println("\nThere exists exactly one Pythagorean triplet for which a + b + c = 1000.\nFind the product abc.");
        
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
                while (!ComplexMath.isRelativelyPrime(u, v)) { // Continuously increase u until there is coparity
                    u += 2;
                }
                a = u*v;
                b = ((int) Math.pow((double) u, 2.0) - (int) Math.pow((double) v, 2.0)) / 2;
                c = ((int) Math.pow((double) u, 2.0) + (int) Math.pow((double) v, 2.0)) / 2;
                
                if  ((a + b + c > 1000)) { // If the Pythagorean triple is too large, we need a different Pythagorean triple
                    v += 2;
                    u = v + 2;
                    while (!ComplexMath.isRelativelyPrime(u, v)) {
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
//                System.out.println(a + " + " + b + " + " + c + " = " + (a + b + c));
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
        
        System.out.println("\nAnswer: " + product);
    }
}