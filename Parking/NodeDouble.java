package Parking;

public class NodeDouble {
    private int data;
    private NodeDouble next,prev;

    public NodeDouble(int data) {
        this.data = data;
        next = prev = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public NodeDouble getNext() {
        return next;
    }

    public void setNext(NodeDouble next) {
        this.next = next;
    }

    public NodeDouble getPrev() {
        return prev;
    }

    public void setPrev(NodeDouble prev) {
        this.prev = prev;
    }
}
