package Parking;

public class DoubleLinkedList {
    private NodeDouble head,tail;

    public DoubleLinkedList() {
        head = tail = null;
    }

    public void add(int data) {
        NodeDouble n = new NodeDouble(data);
        if(head == null) {
            head = tail = n;
        }
        else {
            n.setPrev(tail);
            tail.setNext(n);
            tail = n;
        }
    }

    public NodeDouble getHead() {
        return head;
    }

    /**
     * create circle in the list -> start with element with index i, if i > listSize it creates a circle list
     */
    public void createCircle(int i) {
        if(head == null) return;
        NodeDouble n = head,p = head;
        while(p.getNext() != null) p = p.getNext();
        for (int j = 0; n != null && j < i; j++) {
            n = n.getNext();
        }
        if(n != null) p.setNext(n);
        else p.setNext(head);
    }

    @Override
    public String toString() {
        String ans = "[";
        NodeDouble n = head;
        while(n!= null && n != tail) {
            ans += n.getData() + ", ";
            n = n.getNext();
        }
        if(n == null) return ans + "]";
        return ans + tail.getData() + "]";
    }
}
