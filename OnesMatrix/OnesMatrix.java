package OnesMatrix;

import java.util.Vector;

public class OnesMatrix {

    /**
     * returns the biggest square contains ones
     * Complexity: O(n*m)
     */
    public static int getBiggestSubMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] help = new int[n][m];
        int max = 0, imax = 0, jmax = 0, counter = 0;
        for (int i = 0; i < n; i++) {
            help[i][0] = mat[i][0];
        }
        for (int i = 0; i < m; i++) {
            help[0][i] = mat[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mat[i][j] == 1) {
                    help[i][j] = min(help[i][j - 1], help[i - 1][j], help[i - 1][j - 1]) + 1;
                    if (help[i][j] > max) {
                        max = help[i][j];
                        jmax = j - max + 1;
                        imax = i - max + 1;
                        counter = 0;
                    }
                    if(max == help[i][j]) counter++;
                }
            }
        }
        if (max != 0) System.out.println("Max square length is: " + max + ", start at: (" + imax + "," + jmax + ")" + " Number of max squares: " + counter);
        return max;
    }

    private static int min(int i, int j, int k) {
        if (i <= j && i <= k) return i;
        if (j <= i && j <= k) return j;
        if (k <= i && k <= j) return k;
        else return -1;
    }

    /**
     * returns all biggest squares contains ones
     * Complexity: O(n*m)
     */
    public static Vector<String> getAllMaxSubSquares(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] help = new int[n][m];
        int max = 0;
        for (int i = 0; i < n; i++) {
            help[i][0] = mat[i][0];
        }
        for (int i = 0; i < m; i++) {
            help[0][i] = mat[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mat[i][j] == 1) {
                    help[i][j] = min(help[i][j - 1], help[i - 1][j], help[i - 1][j - 1]) + 1;
                    if (help[i][j] > max) {
                        max = help[i][j];
                    }
                }
            }
        }
        Vector<String> allSquares = new Vector<String>();
        if (max == 0) return allSquares;
        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[0].length; j++) {
                if (help[i][j] == max) {
                    allSquares.add("(" + (i - max + 1) + "," + (j - max + 1) + "): " + max);
                }
            }
        }
        return allSquares;
    }

    public static void main(String[] args) {
        int mat [][] = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        System.out.println(getBiggestSubMatrix(mat));
        System.out.println(getAllMaxSubSquares(mat));
    }
}