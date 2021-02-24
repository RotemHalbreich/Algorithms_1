package Parking;

public class LinkedList {
    private Node head;

    public LinkedList() {
        head = null;
    }

    public void add(int data) {
        if(head == null) head = new Node(data);
        else {
            head = new Node(data,head);
        }
    }

    public Integer remove(int data) {
        if(head == null) return null;
        Node n = head,p = head;
        while(n != null && n.getData() != data) {
            p = n;
            n = n.getNext();
        }
        if(n == null) return null;
        int ans = n.getData();
        p.setNext(n.getNext());
        return ans;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * create circle in the list -> start with element with index i, if i > listSize it creates a circle list
     */
    public void createCircle(int i) {
        if(head == null) return;
        Node n = head,p = head;
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
        Node n = head;
        while(n != null && n.getNext()!= null) {
            ans += n.getData() + ", ";
            n = n.getNext();
        }
        if(n == null) return ans + "]";
        return ans + n.getData() + "]";
    }
}
