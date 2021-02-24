package GameNumbers;

/**
 * Game Numbers - dynamic algorithm
 * Complexity: O(n^2)
 */
public class GameNumbersDynamic {

    private int[][] mat;
    private int n;

    public GameNumbersDynamic(int[] a) {
        buildMatrix(a);
    }

    public void buildMatrix(int[] a) {
        n = a.length;
        mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            mat[i][i] = a[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                mat[i][j] = Math.max(a[i] - mat[i + 1][j], a[j] - mat[i][j - 1]);
            }
        }
    }

    /**
     * Returns the difference between the first player and the second player
     * if both playing optimal
     */
    public int getDifference() {
        return mat[0][mat[0].length - 1];
    }

    public String getOptimalPathRec() {
        return getOptimalPath(0, n - 1);
    }

    /**
     * Returns the game's optimal path for both players
     */
    private String getOptimalPath(int i, int j) {
        if (i == j) return "(" + i + ")" + mat[i][i];
        if (mat[i][i] - mat[i + 1][j] > mat[j][j] - mat[i][j - 1]) {
            return "(" + i + ")" + mat[i][i] + "->" + getOptimalPath(i + 1, j);
        } else {
            return "(" + j + ")" + mat[j][j] + "->" + getOptimalPath(i, j - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new GameNumbersDynamic(new int[]{1, 3, 6, 1, 3, 6}).getOptimalPathRec());
    }
}