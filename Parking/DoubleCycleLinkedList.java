package Parking;

public class DoubleCycleLinkedList {
    private NodeDouble head, tail;

    public DoubleCycleLinkedList() {
        head = tail = null;
    }

    public void add(int data) {
        NodeDouble n = new NodeDouble(data);
        if (head == null) {
            head = tail = n;
            head.setNext(head);
            head.setPrev(head);
        } else {
            n.setNext(head);
            n.setPrev(tail);
            tail.setNext(n);
            head.setPrev(n);
            tail = n;
        }
    }

    public NodeDouble getHead() {
        return head;
    }

    @Override
    public String toString() {
        String ans = "[";
        NodeDouble n = head;
        while (n != null && n != tail) {
            ans += n.getData() + ", ";
            n = n.getNext();
        }
        if (n == null) return ans + "]";
        return ans + tail.getData() + "]";
    }
}
