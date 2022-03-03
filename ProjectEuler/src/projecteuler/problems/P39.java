package projecteuler.problems;

public class P39 implements Problem {
    public P39() {
        super();
    }
    
    @Override
    public void execute() {
        System.out.println("If p is the perimeter of a right angle triangle with integral length sides, " +
                           "{a,b,c}, there are exactly three solutions for p = 120.\n" + 
        "\n" + 
        "{20,48,52}, {24,45,51}, {30,40,50}\n" + 
        "\n" + 
        "For which value of p <= 1000, is the number of solutions maximised?");
        
        int[] perimeters = new int[1001];
        
        for (int i = 12; i < perimeters.length; i++) {
            for (int x = 1; x <= i / 3; x++) {
                for (int y = x; y <= (i - x) / 2; y++) {
                    int z = i - x - y;
                    if (Math.pow(x, 2) + Math.pow(y, 2) == Math.pow(z, 2)) perimeters[i]++;
                }
            }
        }
        
        int index = 0, max = 0;
        for (int i = 1; i < perimeters.length; i++) {
            if (perimeters[i] > max) {
                index = i;
                max = perimeters[i];
            }
        }
        
        System.out.println("\nAnswer: " + index);
    }
}
