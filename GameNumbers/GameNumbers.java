package GameNumbers;

import java.util.Scanner;

public class GameNumbers {
    public static final int ODD = 1, EVEN = 0;
    private int[] arr;
    private int _o_sum, _e_sum; // save the sum of even and odd
    private Node[] gameTree;

    public GameNumbers(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            this.arr = arr;
        }
    }

    /**
     * The algorithm of the game - compare between sum of elements at even index
     * and elements at odd index - once
     * assumption - size is even
     * Complexity: O(n)
     */
    public int gameAlgo1() {
        int e_sum = 0, o_sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) e_sum += arr[i];
            else o_sum += arr[i];
        }
        _o_sum = o_sum;
        _e_sum = e_sum;
        if (e_sum > o_sum) return EVEN;
        else if (e_sum < o_sum) return ODD;
        else {
            if (arr[0] < arr[arr.length - 1]) return EVEN;
            else return ODD;
        }
    }

    /**
     * The algorithm of the game - each step
     * assumption - size is even
     * Complexity: O(n)
     */
    public int gameAlgo2(int left, int right) {
        int e_sum = 0, o_sum = 0;
        for (int i = left; i <= right; i++) {
            if (i % 2 == 0) e_sum += arr[i];
            else o_sum += arr[i];
        }
        if (e_sum > o_sum) return EVEN;
        else if (e_sum < o_sum) return ODD;
        else {
            if (arr[0] < arr[arr.length - 1]) return EVEN;
            else return ODD;
        }
    }

    /**
     * The game process
     *
     * @param algo -
     *             0 - check the sums once. Complexity: O(n)
     *             1 - check the sums each step. Complexity: O(n^2)
     *             2 - check the sums once and update it each step. Complexity: O(n)
     */
    public void run(int algo) {
        int left = 0, right = arr.length - 1;
        Scanner sc = new Scanner(System.in);
        int strategy = gameAlgo1();
        int sum1 = 0, sum2 = 0;
        while (left <= right) {
            showArray(left, right);
            // computer turn
            /////////////////////////
            if (algo == 1) strategy = gameAlgo2(left, right); // check each step
            if (algo == 2) {
                if (_o_sum < _e_sum) strategy = EVEN;
                else strategy = ODD;
            }
            /////////////////////////
            System.out.println(_o_sum + " " + _e_sum);
            if (left % 2 == EVEN) {
                if (strategy == EVEN) {
                    if (algo == 2) _e_sum -= arr[left];
                    sum1 += arr[left++];
                } else {
                    if (algo == 2) _o_sum -= arr[right];
                    sum1 += arr[right--];
                }
            } else {
                if (strategy == EVEN) {
                    if (algo == 2) _e_sum -= arr[right];
                    sum1 += arr[right--];
                } else {
                    if (algo == 2) _o_sum -= arr[left];
                    sum1 += arr[left++];
                }
            }
            showArray(left, right);
            System.out.println(_o_sum + " " + _e_sum);

            // your turn
            int side = sc.nextInt();
            if (side == 0) {
                /////////////////////////
                if (algo == 2) {
                    if (left % 2 == 0) _e_sum -= arr[left];
                    else _o_sum -= arr[left];
                }
                //////////////////////////
                sum2 += arr[left++];
            } else {
                /////////////////////////
                if (algo == 2) {
                    if (left % 2 == 0) _e_sum -= arr[right];
                    else _o_sum -= arr[right];
                }
                //////////////////////////
                sum2 += arr[right--];
            }
        }
        System.out.println("sum1 = " + sum1 + " sum2 = " + sum2);
        sc.close();
    }

    /**
     * @return the maximum difference for the first player using the game tree
     * Complexity: O(2^n)
     */
    public int fullSearch() {
        return fullSearch(0, arr.length - 1, true);
    }

    private int fullSearch(int s, int e, boolean turn) {
        if (s == e) {
            if (turn) return arr[s];
            else return -arr[s];
        }
        int left;
        int right;
        if (turn) {
            left = arr[s] + fullSearch(s + 1, e, !turn);
            right = arr[e] + fullSearch(s, e - 1, !turn);
        } else {
            left = -arr[s] + fullSearch(s + 1, e, !turn);
            right = -arr[e] + fullSearch(s, e - 1, !turn);
        }
        if (left > right) return left;
        return right;
    }

    /**
     * @return string represents the optimal path for both sides and the different of optimal game.
     * Complexity: O(2^n)
     */
    public String pathOptimal() {
        String[] ans = new String[1];
        int diff = pathOptimal(0, arr.length - 1, ans);
        return ans[0] + " , difference: " + diff;
    }

    private int pathOptimal(int s, int e, String[] path) {
        if (s == e) {
            path[0] = s + "(" + arr[s] + ")";
            return arr[s];
        }
        String[] t1 = new String[1];
        String[] t2 = new String[1];
        int l = pathOptimal(s + 1, e, t1);
        int r = pathOptimal(s, e - 1, t2);
        if (arr[s] - l > arr[e] - r) {
            path[0] = s + "(" + arr[s] + ")" + "->" + t1[0];
            return arr[s] - l;
        } else {
            path[0] = e + "(" + arr[e] + ")" + "->" + t2[0];
            return arr[e] - r;
        }
    }

    public void showArray(int s, int e) {
        for (int i = s; i <= e; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    /**
     * Build the game tree
     * Complexity: O(2^n)
     */
    public void buildGameTree() {
        int treeLen = (int) Math.pow(2, arr.length) - 1;
        gameTree = new Node[treeLen];
        gameTree[0] = new Node(0, arr.length - 1);
        for (int i = 0; i < treeLen; i++) {
            if (left(i) < treeLen) gameTree[left(i)] = new Node(gameTree[i].left + 1, gameTree[i].right);
            if (right(i) < treeLen) gameTree[right(i)] = new Node(gameTree[i].left, gameTree[i].right - 1);
        }
        for (int i = treeLen - 1; i >= 0; i--) {
            if (gameTree[i].left == gameTree[i].right) {
                gameTree[i].diff = arr[gameTree[i].left];
                gameTree[i].path = "" + arr[gameTree[i].left];
            } else {
                int diffLeft = arr[gameTree[i].left] - gameTree[left(i)].diff;
                int diffRight = arr[gameTree[i].right] - gameTree[right(i)].diff;
                if (diffLeft > diffRight) {
                    gameTree[i].diff = diffLeft;
                    gameTree[i].path = arr[gameTree[i].left] + "->" + gameTree[left(i)].path;
                } else {
                    gameTree[i].diff = diffRight;
                    gameTree[i].path = arr[gameTree[i].right] + "->" + gameTree[right(i)].path;
                }
            }
        }
        printTree();
    }

    private void printTree() {
        int level = 1;
        int counter = 0;
        for (int i = 0; i < gameTree.length; i++) {
            counter++;
            System.out.print(gameTree[i]);
            if (counter == level) {
                System.out.println();
                level *= 2;
                counter = 0;
            }
        }
    }

    private int right(int i) {
        return i * 2 + 2;
    }

    private int left(int i) {
        return i * 2 + 1;
    }
}
