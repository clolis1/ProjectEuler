package projecteuler.problems;

public class P31 implements Problem {
    public P31() {
        super();
    }

    static int total_combinations = 0;
    static int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};

    @Override
    public void execute() {
        System.out.println("In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general circulation:\n" + 
        "\n" + 
        "1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).\n" + 
        "It is possible to make £2 in the following way:\n" + 
        "\n" + 
        "1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p\n" + 
        "How many different ways can £2 be made using any number of coins?");
        
        addcoins(0, 7);
        
        System.out.println("\nAnswer:" + total_combinations);
    }
    
    public void addcoins(int total, int order) {
        if (order != 0) addcoins(total, order - 1);
        total += coins[order];
        if (total == 200 || (total == 199 && order == 1)) {
            total_combinations++;
            return;
        }
        else if (total < 200) addcoins(total, order);
    }
}
