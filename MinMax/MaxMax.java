package MinMax;

import java.util.ArrayList;
import java.util.Stack;

public class MaxMax {
    /**
     * Find the minimum and the maximum elements in array
     * Complexity: O(n) - 3/2(n-1) comparisons
     *
     * @return number of comparisons
     */
    public static int maxMaxM1(int[] arr) {
        if (arr.length == 1) {
            System.out.println("Max1 = " + arr[0] + " , Max2 = " + arr[0] + " , Comparisons = " + 0);
            return 0;
        }
        int comparisons = 0;
        int max1 = arr[0];
        int max2 = arr[1];
        comparisons++;
        if (arr[0] < arr[1]) {
            max1 = arr[1];
            max2 = arr[0];
        }
        for (int i = 2; i < arr.length - 1; i += 2) {
            comparisons++;
            if (arr[i] > arr[i + 1]) {
                comparisons += 2;
                if (arr[i] > max1) {
                    if (arr[i + 1] > max1) {
                        max1 = arr[i];
                        max2 = arr[i + 1];
                    } else {
                        max2 = max1;
                        max1 = arr[i];
                    }
                } else if (arr[i] > max2) {
                    max2 = arr[i];
                }
            } else {
                comparisons += 2;
                if (arr[i + 1] > max1) {
                    if (arr[i] > max1) {
                        max1 = arr[i + 1];
                        max2 = arr[i];
                    } else {
                        max2 = max1;
                        max1 = arr[i + 1];
                    }
                } else if (arr[i + 1] > max2) {
                    max2 = arr[i + 1];
                }
            }
        }
        comparisons++;
        if (arr[arr.length - 1] > max1) {
            max2 = max1;
            max1 = arr[arr.length - 1];
        } else {
            comparisons++;
            if (arr[arr.length - 1] > max2) {
                max2 = arr[arr.length - 1];
            }
        }
        System.out.println("Max1 = " + max1 + " , Max2 = " + max2 + " , Comparisons = " + comparisons);
        return comparisons;
    }

    /**
     * Find the 2 maximum elements in array - Recursive
     * Complexity: O(n) - (n + log n) comparisons
     *
     * @return number of comparisons
     */
    public static int maxMaxM2Recursive(int[] arr) {
        int[] comparisons = new int[1];
        comparisons[0] = 0;
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i]);
        }
        Node max1 = maxMaxM2Recursive(nodes, 0, nodes.length - 1, comparisons);
        int max2 = max1.getStack().pop();
        while (!max1.getStack().isEmpty()) {
            int x = max1.getStack().pop();
            comparisons[0]++;
            if (max2 < x) max2 = x;
        }
        System.out.println("Max1 = " + max1.getData() + " , Max2 = " + max2 + " , Comparisons = " + comparisons[0]);
        return comparisons[0];
    }

    private static Node maxMaxM2Recursive(Node[] nodes, int low, int high, int[] comparisons) {
        if (high == low) return nodes[low];
        int mid = (low + high) / 2;
        Node maxL = maxMaxM2Recursive(nodes, low, mid, comparisons);
        Node maxR = maxMaxM2Recursive(nodes, mid + 1, high, comparisons);
        comparisons[0]++;
        if (maxL.getData() > maxR.getData()) {
            maxL.getStack().push(maxR.getData());
            return maxL;
        } else {
            maxR.getStack().push(maxL.getData());
            return maxR;
        }
    }

    /**
     * Find the 2 maximum elements in array - Inductive
     * Complexity: O(n) - (n + log n) comparisons
     *
     * @return number of comparisons
     */
    public static int maxMaxM2Induction(int[] arr) {
        int comparisons = 0;
        ArrayList<Node> list = new ArrayList<Node>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new Node(arr[i]));
        }
        int i = 0;
        while (list.size() > 1) {
            Node x = list.get(i);
            Node y = list.get(i + 1);
            comparisons++;
            if (x.getData() > y.getData()) {
                x.getStack().add(y.getData());
                list.remove(i + 1);
            } else {
                y.getStack().add(x.getData());
                list.remove(i);
            }
            i++;
            if (i == list.size() || i == list.size() - 1) i = 0;
        }
        int max1 = list.get(0).getData();
        Stack<Integer> st = list.get(0).getStack();
        int max2 = st.pop();
        while (!st.isEmpty()) {
            int x = st.pop();
            comparisons++;
            if (x > max2) {
                max2 = x;
            }
        }
        System.out.println("Max1 = " + max1 + " , Max2 = " + max2 + " , Comparisons = " + comparisons);
        return comparisons;
    }

    /**
     * @param method
     * @param checks
     * @param max_size Prints the average number of comparisons of given method
     */
    public static void getAverageOfMethod(int method, int checks, int max_size) {
        int[] arr;
        double sum = 0;
        for (int i = 0; i < checks; i++) {
            arr = new int[(int) (Math.random() * max_size + 10)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (int) (Math.random() * max_size * 10 + 1);
            }
            if (method == 1) sum += (double) maxMaxM1(arr) / arr.length;
            if (method == 2) sum += (double) maxMaxM2Recursive(arr) / arr.length;
            if (method == 3) sum += (double) maxMaxM2Induction(arr) / arr.length;
        }
        System.out.println("avarage: " + sum / checks);
    }

    /**
     * @param method
     * @param checks
     * @param max_size Prints the average time of given method
     */
    public static void getAverageTimeOfMethod(int method, int checks, int max_size) {
        int[] arr;
        long sum = 0;
        for (int i = 0; i < checks; i++) {
            arr = new int[max_size];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (int) (Math.random() * max_size * 10 + 1);
            }
            long start = System.currentTimeMillis();
            if (method == 1) maxMaxM1(arr);
            if (method == 2) maxMaxM2Recursive(arr);
            if (method == 3) maxMaxM2Induction(arr);
            long end = System.currentTimeMillis();
            sum += (end - start);
        }
        System.out.println("average: " + (double) sum / checks + " ms");
    }
}
