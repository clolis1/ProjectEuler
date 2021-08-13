package projecteuler.problems;

import java.util.Iterator;
import java.util.TreeSet;

import projecteuler.util.ComplexMath;

public class P35 implements Problem {
    public P35() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.\n" + 
        "\n" + 
        "There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.\n" + 
        "\n" + 
        "How many circular primes are there below one million?");
        
        TreeSet<Integer> primes = new TreeSet<Integer>(ComplexMath.getPrimesUpUntil(1000000));
        Iterator<Integer> it = primes.iterator();
        
        int cp_counter = 0;
        int num = 0;
        
        outer:
        while (it.hasNext()) {
            num = it.next();
            boolean circular = true;
            for (int i = 0; i < num / 10; i++) {
                num += (int) Math.pow(10, ("" + num).length()) * (num % 10);
                num /= 10;
                if (!primes.contains(num)) {circular = false; break;}
            }
            if (circular) cp_counter++;
        }
        
        System.out.println("\nAnswer:" + cp_counter);
    }
}