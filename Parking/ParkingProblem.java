package Parking;

public class ParkingProblem {
    public static final int x = 0, v = 1;

    /**
     * parking problem with array (cycle array)
     * Complexity: O(n)
     */
    public static int parkingProblem(int[] arr) {
        int count = 0;
        int start = (int) (Math.random() * arr.length);
        arr[start] = v;
        boolean finnish = false;
        while (!finnish) {
            count++;
            if (arr[(start + count) % arr.length] == v) {
                arr[(start + count) % arr.length] = x;
                if (arr[start % arr.length] == x) finnish = true;
            }
        }
        return count;
    }

    /**
     * parking problem with double cycle linked list
     * Complexity: O(n^2)
     */
    public static int parkingProblem(DoubleCycleLinkedList list) {
        if (list.getHead() == null) return 0;
        int count = 0;
        NodeDouble n = list.getHead();
        n.setData(v);
        n = n.getNext();
        boolean finnish = false;
        while (!finnish) {
            count = 1;
            while (n.getData() != v) {
                count++;
                n = n.getNext();
            }
            n.setData(x);
            int steps = count;
            while (steps > 0) {
                steps--;
                n = n.getPrev();
            }
            if (n.getData() == x) finnish = true;
            n = n.getNext();
        }
        return count;
    }

    /**
     * parking problem with linear part and cycle part - double linked list
     * Complexity: O(n^2)
     */
    public static int parkingProblemWithLinearPart(DoubleLinkedList list) {
        if (list == null || list.getHead() == null) return 0;
        if (list.getHead().getNext() == null) return 1;
        NodeDouble n = list.getHead(), m = list.getHead();
        int steps = 0;
        while (m == n) { // check weather there is a cycle and count the linear part
            steps++;
            for (int i = 0; i < steps; i++)
                if (m != null) m = m.getNext();
            for (int i = steps; i > 0; i--)
                if (m != null) m = m.getPrev();
        }
        if (m == null) return steps;
        while (m != n) n = n.getNext();
        while (m != n) { // count the length of cycle
            m = m.getNext();
            steps++;
        }
        return steps;
    }

    /**
     * parking problem with linear part and cycle part - not double list
     * Complexity: O(n)
     */
    public static int parkingProblemWithLinearPart(LinkedList list) {
        if (list == null || list.getHead() == null) return 0;
        if (list.getHead().getNext() == null) return 1;
        Node n = list.getHead().getNext(), m = list.getHead().getNext().getNext();
        while (m != null && m != n) { // check weather there is a cycle && meeting point
            m = m.getNext();
            if (m != null) m = m.getNext();
            n = n.getNext();
        }
        if (m == null) return sizeOfList(list);
        int count = 0;
        m = list.getHead();
        while (m != n) { // count the length of linear part
            m = m.getNext();
            n = n.getNext();
            count++;
        }
        m = m.getNext();
        count++;
        while (m != n) { // count the length of cycle
            m = m.getNext();
            count++;
        }
        return count;
    }

    private static int sizeOfList(LinkedList list) {
        int count = 0;
        Node n = list.getHead();
        while (n != null) {
            n = n.getNext();
            count++;
        }
        return count;
    }
}
