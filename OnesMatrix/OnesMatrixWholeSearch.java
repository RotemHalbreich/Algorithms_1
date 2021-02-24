package OnesMatrix;

import java.util.Vector;

public class OnesMatrixWholeSearch {
    /**
     * returns the biggest square contains ones
     * Complexity: O((n*m)^2*min(n,m))
     */
    public static int getBiggestSubMatrix(int[][] mat) {
        Vector<int[][]> allMatrix = getAllSubMatrix(mat);
        int maxSize = 0;
        for (int[][] m : allMatrix) {
            boolean isOnes = true;
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    if (m[i][j] == 0) isOnes = false;
                }
            }
            if (isOnes) {
                if (m.length > maxSize) maxSize = m.length;
            }
        }
        return maxSize;
    }

    private static Vector<int[][]> getAllSubMatrix(int[][] mat) {
        Vector<int[][]> ans = new Vector<int[][]>();
        for (int i = 1; i < Math.min(mat.length, mat[0].length) + 1; i++) {
            for (int j = 0; j < mat.length - i + 1; j++) {
                for (int k = 0; k < mat[0].length - i + 1; k++) {
                    int[][] temp = new int[i][i];
                    for (int i1 = j; i1 < j + i; i1++) {
                        for (int j1 = k; j1 < k + i; j1++) {
                            temp[i1 - j][j1 - k] = mat[i1][j1];
                        }
                    }
                    ans.add(temp);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}};
        System.out.println(getBiggestSubMatrix(mat));
    }
}