package Airplane;

import java.util.ArrayList;
import java.util.Vector;

/**
 * class Node:
 * x,y - the price for get up (y) or right (x)
 * entry - the best price from (0,0) to this node
 * numOfPaths - number of shortest paths until this node
 * entryFromTheEnd - same as entry, but from the end to (0,0)
 */
class Node {
    int x, y, entry, numOfPaths, entryFromTheEnd;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        entry = 0;
        entryFromTheEnd = 0;
        numOfPaths = 1;
    }
}

public class BestPath {
    int cheapestPrice, numOfPaths;
    private Node[][] mat;

    public BestPath(Node[][] mat) {
        this.mat = mat;
        cheapestPrice = 0;
        numOfPaths = 0;
        buildMatrix();
    }

    /**
     * build the matrix contains the price to get each point (from (0,0))
     * and the number of shortest path until each point
     * Complexity: O(n*m)
     */
    private void buildMatrix() {
        int n = mat.length, m = mat[0].length;
        for (int i = 1; i < n; i++) {
            mat[i][0].entry = mat[i - 1][0].y + mat[i - 1][0].entry;
            mat[i][0].numOfPaths = 1;
        }
        for (int j = 1; j < m; j++) {
            mat[0][j].entry = mat[0][j - 1].entry + mat[0][j - 1].x;
            mat[0][j].numOfPaths = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int y = mat[i - 1][j].entry + mat[i - 1][j].y;
                int x = mat[i][j - 1].entry + mat[i][j - 1].x;
                mat[i][j].entry = x <= y ? x : y;
                if (y < x) mat[i][j].numOfPaths = mat[i - 1][j].numOfPaths;
                else if (y > x) mat[i][j].numOfPaths = mat[i][j - 1].numOfPaths;
                else mat[i][j].numOfPaths = mat[i][j - 1].numOfPaths + mat[i - 1][j].numOfPaths;
            }
        }
        numOfPaths = mat[n - 1][m - 1].numOfPaths;
        cheapestPrice = mat[n - 1][m - 1].entry;
    }

    public void printPrices() {
        System.out.println("\nmatrix of prices in right direction\n");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j].entry + " ");
            }
            System.out.println();
        }
    }

    public void printNumberOfPaths() {
        System.out.println("\nthe matrix of numbers of the cheapest paths \n");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j].numOfPaths + " ");
            }
            System.out.println();
        }
    }

    /**
     * returns one of shortest path - recursion
     * Complexity: O(n+m) - but need to build the matrix first in O(n*m)
     */
    public String getOnePathRec() {
        return getOnePathRec(mat.length - 1, mat[0].length - 1);
    }

    private String getOnePathRec(int i, int j) {
        if (i == 0 && j == 0) return "";
        if (i > 0 && j == 0) return getOnePathRec(i - 1, 0) + "1";
        if (i == 0 && j > 0) return getOnePathRec(0, j - 1) + "0";
        if (mat[i][j].entry == mat[i - 1][j].entry + mat[i - 1][j].y) {
            return getOnePathRec(i - 1, j) + "1";
        } else {
            return getOnePathRec(i, j - 1) + "0";
        }
    }

    /**
     * returns one of shortest path - induction
     * Complexity: O(n+m) - but need to build the matrix first in O(n*m)
     */
    public String getOnePath() {
        String ans = "";
        int i = mat.length - 1, j = mat[0].length - 1;
        while (i > 0 && j > 0) {
            int a = mat[i - 1][j].entry + mat[i - 1][j].y;
            int b = mat[i][j - 1].entry + mat[i][j - 1].x;
            if (a < b) {
                ans = "1" + ans;
                i--;
            } else {
                ans = "0" + ans;
                j--;
            }
        }
        while (j > 0) {
            ans = "0" + ans;
            j--;
        }
        while (i > 0) {
            ans = "1" + ans;
            i--;
        }
        return ans;
    }

    /**
     * get all shortest paths
     * complexity: O((n+m)choose(n))
     */
    public Vector<String> getAllPathsRec() {
        Vector<String> ans = new Vector<String>();
        getAllPathsRec("", mat.length - 1, mat[0].length - 1, ans);
        return ans;
    }

    private void getAllPathsRec(String temp, int i, int j, Vector<String> ans) {
        if (i == 0 && j == 0) {
            ans.add(temp);
            return;
        } else if (i > 0 && j == 0) {
            getAllPathsRec("1" + temp, i - 1, 0, ans);
        } else if (i == 0 && j > 0) {
            getAllPathsRec("0" + temp, 0, j - 1, ans);
        } else {
            int a = mat[i - 1][j].entry + mat[i - 1][j].y;
            int b = mat[i][j - 1].entry + mat[i][j - 1].x;
            if (a < b) {
                getAllPathsRec("1" + temp, i - 1, j, ans);
            } else if (b < a) {
                getAllPathsRec("0" + temp, i, j - 1, ans);
            } else {
                getAllPathsRec("1" + temp, i - 1, j, ans);
                getAllPathsRec("0" + temp, i, j - 1, ans);
            }
        }
    }

    /**
     * returns true if the given point is on one of the shortest paths
     * Complexity: O(n*m) but if we call buildMatrixFromTheEnd() first and then call isOnBestPath
     * the answer is in O(1)
     */
    public boolean isOnBestPath(int i, int j) {
        buildMatrixFromTheEnd();
        return (mat[i][j].entry + mat[i][j].entryFromTheEnd) == cheapestPrice;
    }

    /**
     * the same like build matrix but now we build it from the end to (0,0)
     * Complexity: O(n*m)
     */
    private void buildMatrixFromTheEnd() {
        int n = mat.length - 1, m = mat[0].length - 1;
        for (int i = n - 1; i >= 0; i--) {
            mat[i][m].entryFromTheEnd = mat[i][m].y + mat[i + 1][m].entryFromTheEnd;
        }
        for (int j = m - 1; j >= 0; j--) {
            mat[n][j].entryFromTheEnd = mat[n][j + 1].entryFromTheEnd + mat[n][j].x;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int y = mat[i + 1][j].entryFromTheEnd + mat[i][j].y;
                int x = mat[i][j + 1].entryFromTheEnd + mat[i][j].x;
                mat[i][j].entryFromTheEnd = x <= y ? x : y;
            }
        }
    }

    /**
     * return the minimal price from point (p1,q1) to (p2,q2)
     * Complexity: O(n*m)
     */
    private int bestPathFromTo(int p1, int q1, int p2, int q2) {
        // Assuming p2>=p1 and q2>=q1
        Node[][] temp = new Node[q2 - q1 + 1][p2 - p1 + 1];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                temp[i][j] = new Node(0, 0);
            }
        }
        for (int i = 1; i < q2 - q1 + 1; i++) {
            temp[i][0].entry = mat[i - 1 + q1][p1].y + temp[i - 1][0].entry;
        }
        for (int j = 1; j < p2 - p1 + 1; j++) {
            temp[0][j].entry = temp[0][j - 1].entry + mat[q1][j - 1 + p1].x;
        }
        for (int i = 1; i < q2 - q1 + 1; i++) {
            for (int j = 1; j < p2 - p1 + 1; j++) {
                int x = temp[i - 1][j].entry + mat[i - 1 + q1][j + p1].y;
                int y = temp[i][j - 1].entry + mat[i + q1][j - 1 + p1].x;
                temp[i][j].entry = x <= y ? x : y;
            }
        }
        return temp[q2 - q1][p2 - p1].entry;
    }

    /**
     * return true if all the points in p is on the same shortest path
     * sorting the array first (using counting sort: O(k)) by x and then by y
     * Complexity: O(n*m*k) k = |p|
     */
    public boolean isOnBestPath(Node[] p) {
        sort(p);
        int sum = mat[p[0].y][p[0].x].entry;
        for (int i = 1; i < p.length; i++) {
            if (p[i].y < p[i - 1].y) return false;
            sum += bestPathFromTo(p[i - 1].x, p[i - 1].y, p[i].x, p[i].y);
        }
        sum += bestPathFromTo(p[p.length - 1].x, p[p.length - 1].y, mat[0].length - 1, mat.length - 1);
        return sum == cheapestPrice;
    }

    private void sort(Node[] p) {
        @SuppressWarnings("unchecked")
        ArrayList<Node>[] freqy = new ArrayList[mat.length];
        for (int i = 0; i < freqy.length; i++) {
            freqy[i] = new ArrayList<Node>();
        }
        Node[] temp = new Node[p.length];
        for (int i = 0; i < p.length; i++) {
            freqy[p[i].y].add(p[i]);
        }
        int k = 0;
        for (int i = 0; i < freqy.length; i++) {
            for (int j = 0; j < freqy[i].size(); j++) {
                temp[k++] = freqy[i].get(j);
            }
        }
        @SuppressWarnings("unchecked")
        ArrayList<Node>[] freqx = new ArrayList[mat[0].length];
        for (int i = 0; i < freqx.length; i++) {
            freqx[i] = new ArrayList<Node>();
        }
        Node[] temp2 = new Node[p.length];
        for (int i = 0; i < temp.length; i++) {
            freqx[temp[i].x].add(temp[i]);
        }
        k = 0;
        for (int i = 0; i < freqx.length; i++) {
            for (int j = 0; j < freqx[i].size(); j++) {
                temp2[k++] = freqx[i].get(j);
            }
        }
        for (int i = 0; i < temp2.length; i++) {
            p[i] = temp2[i];
        }
    }

    /**
     * returns the shortest path with minimal turnings
     * Complexity: O((n+m)choose(n)*(m+n))
     */
    public String optimalPath() {
        Vector<String> paths = getAllPathsRec();
        String ans = "";
        int min = Integer.MAX_VALUE;
        for (String path : paths) {
            int turning = 0;
            for (int i = 1; i < path.length(); i++) {
                if (path.charAt(i) != path.charAt(i - 1)) turning++;
            }
            if (turning < min) {
                ans = path;
                min = turning;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Node[][] mat = new Node[4][4];
        mat[0][0] = new Node(1, 2);
        mat[0][1] = new Node(1, 1);
        mat[0][2] = new Node(1, 3);
        mat[0][3] = new Node(0, 1);
        mat[1][0] = new Node(2, 3);
        mat[1][1] = new Node(5, 1);
        mat[1][2] = new Node(6, 3);
        mat[1][3] = new Node(0, 1);
        mat[2][0] = new Node(2, 4);
        mat[2][1] = new Node(7, 1);
        mat[2][2] = new Node(2, 3);
        mat[2][3] = new Node(0, 1);
        mat[3][0] = new Node(2, 0);
        mat[3][1] = new Node(1, 0);
        mat[3][2] = new Node(1, 0);
        mat[3][3] = new Node(0, 0);
        BestPath bp = new BestPath(mat);
        System.out.println(bp.cheapestPrice);
        System.out.println(bp.numOfPaths);
        System.out.println(bp.getOnePath());
        System.out.println(bp.getOnePathRec());
        System.out.println(bp.getAllPathsRec());
        System.out.println(bp.isOnBestPath(1, 1));
        System.out.println(bp.isOnBestPath(1, 2));
        System.out.println(bp.isOnBestPath(new Node[]{new Node(1, 1), new Node(1, 2), new Node(1, 3)}));
        System.out.println(bp.isOnBestPath(new Node[]{new Node(1, 1), new Node(3, 1), new Node(3, 3)}));
        System.out.println(bp.optimalPath());
        Node[][] mat2 = new Node[6][6];
        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2.length; j++) {
                mat2[i][j] = new Node(j == 4 ? 0 : 1, i == 4 ? 0 : 1);
            }
        }
        BestPath b = new BestPath(mat2);
        System.out.println(b.getAllPathsRec().size());
    }
}