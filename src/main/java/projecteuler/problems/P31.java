package projecteuler.problems;

public class P31 implements Problem {
    public P31() {
        super();
    }

    static int total_combinations = 0;
    static int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};

    @Override
    public String solve() {
        addcoins(0, 7);
        
        return String.valueOf(total_combinations);
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
