package projecteuler.problems;

import java.util.HashMap;

import java.util.Map;

import projecteuler.util.ComplexMath;

public class P75 implements Problem {
    public P75() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("It turns out that 12 cm is the smallest length of wire that can be bent to form\n" +
        "an integer sided right angle triangle in exactly one way, but there are many more examples.\n" + 
        "\n" + 
        "12 cm: (3,4,5)\n" + 
        "24 cm: (6,8,10)\n" + 
        "30 cm: (5,12,13)\n" + 
        "36 cm: (9,12,15)\n" + 
        "40 cm: (8,15,17)\n" + 
        "48 cm: (12,16,20)\n" + 
        "\n" + 
        "In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer sided right angle triangle,\n" +
        "and other lengths allow more than one solution to be found;\n" +
        "for example, using 120 cm it is possible to form exactly three different integer sided right angle triangles.\n" + 
        "\n" + 
        "120 cm: (30,40,50), (20,48,52), (24,45,51)\n" + 
        "\n" + 
        "Given that L is the length of the wire,\n" +
        "for how many values of L ? 1,500,000 can exactly one integer sided right angle triangle be formed?");
        
        int m = 2, n = 1, a = m * m - n * n, b = 2 * m * n, c = m * m + n * n;
        
        Map<Integer, Integer> perimeters = new HashMap<Integer, Integer>();
        
        while (a + b + c <= 1500000) {
            m = n + 1;
            a = m * m - n * n;
            b = 2 * m * n;
            c = m * m + n * n;
            
            while (a + b + c <= 1500000) {
                if (ComplexMath.isRelativelyPrime(m, n)) {
                    if (perimeters.containsKey(a + b + c)) perimeters.replace(a + b + c, perimeters.get(a + b + c) + 1);
                    else perimeters.put(a + b + c, 1);
                }
                m += 2;
                 
                a = m * m - n * n;
                b = 2 * m * n;
                c = m * m + n * n;
            }
            n++;
            m = n + 1;
                
            a = m * m - n * n;
            b = 2 * m * n;
            c = m * m + n * n;
        }
        
        int[] result = new int[1];
        
        Map<Integer, Integer> updated_perimeters = new HashMap<Integer, Integer>();
        
        perimeters.forEach((k, v) -> { 
            int temp = k;
            while (temp <= 1500000) {
                if (updated_perimeters.containsKey(temp)) updated_perimeters.replace(temp, updated_perimeters.get(temp) + 1);
                else if (perimeters.containsKey(temp)) updated_perimeters.put(temp, perimeters.get(temp));
                else updated_perimeters.put(temp, 1);
                temp += k;
            }
        });
        
        updated_perimeters.forEach((k, v) -> {if (v == 1) result[0]++;});
        
        System.out.println("\nAnswer: " + result[0]);
    }
}