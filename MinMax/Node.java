package MinMax;

import java.util.Stack;

/**
 * Node with data and stack
 */
public class Node {
    private int data;
    private Stack<Integer> st;

    public Node(int data) {
        this.data = data;
        st = new Stack<Integer>();
    }

    public int getData() {
        return data;
    }

    public Stack<Integer> getStack() {
        return st;
    }
}
