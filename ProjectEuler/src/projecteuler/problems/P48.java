package projecteuler.problems;

import java.util.*;

import projecteuler.util.ComplexMath;

public class P48 implements Problem {
    public P48() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("The series, 11 + 22 + 33 + ... + 1010 = 10405071317.\n" + 
        "\n" + 
        "Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.");
        
        long lastTen = 0L;
        
        for (int i = 1; i < 1001; i++) {
            long l = 1L;
            for (int j = 0; j < i; j++) {
                l = (l * i) % 10000000000L;
            }
            lastTen = (lastTen + l) % 10000000000L;
        }
        
        System.out.println("Answers: " + lastTen);
    }
}