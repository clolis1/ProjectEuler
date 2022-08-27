package projecteuler.problems;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.math.MathContext;
import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.List;

import projecteuler.util.ComplexMath;

public class P78 implements Problem {
    public P78() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("Let p(n) represent the number of different ways in which n coins can be separated into piles.\n" +
        "For example, five coins can be separated into piles in exactly seven different ways, so p(5)=7.\n" + 
        "\n" + 
        "OOOOO\n" + 
        "OOOO   O\n" + 
        "OOO   OO\n" + 
        "OOO   O   O\n" + 
        "OO   OO   O\n" + 
        "OO   O   O   O\n" + 
        "O   O   O   O   O\n" + 
        "Find the least value of n for which p(n) is divisible by one million.");
        
        List<Integer> p = new ArrayList<Integer>();
        p.add(1);
        
        int target = 1;            
        while(true){
            int i = 0;
            int penta = 1;
            p.add(0);
         
            while (penta <= target){                    
                int sign = (i % 4 > 1) ? -1 : 1;
                p.set(target, p.get(target) + sign * p.get(target - penta));
                p.set(target, p.get(target) % 1000000);
                i++;
                          
                int j = (i % 2 == 0) ? i / 2 + 1 : -(i / 2 + 1);
                penta = j * (3 * j - 1) / 2;
            } 
                         
            if (p.get(target) == 0) break;
            target++;
        }
        
        System.out.println("\nAnswer: " + target);
    }
}