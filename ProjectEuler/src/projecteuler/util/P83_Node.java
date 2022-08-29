package projecteuler.util;

public class P83_Node {
    private int id, cost;
    
    public P83_Node() {
        super();
    }
    
    public P83_Node(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }
    
    public int getID() {
        return this.id;
    }
    
    public int getCost() {
        return this.cost;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }
}
