package projecteuler.util;

public class BinaryTree<E> {
    
    private Node root;
    
    class Node {
        E value;
        Node parent;
        Node left;
        Node right;
        
        // Constructor for node with no associated nodes.
        public Node(E e) {
            value = e;
            parent = null;
            left = null;
            right = null;
        }
        
        public void addLeft(E e) {
            this.left = new Node(e);
            this.left.parent = this;
        }
        
        public void addRight(E e) {
            this.right = new Node(e);
            this.right.parent = this;
        }
    }
    
    // e is value of root node. Thi is the only constructor.
    public BinaryTree(E e) {
        root = new Node(e);
    }
    
    public Node getRoot() {
        return root;
    }
}
