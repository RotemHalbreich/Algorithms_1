package LCS;

import java.util.Arrays;
import java.util.Vector;

/**
 * LCS - Longest Common Substring
 * returns the LCS of s1 and s2
 */
public class LCSDynamic {
    private int[][] mat;

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "eafddbac";
        System.out.println(lcs(s1, s2));

    }

    /**
     * S1 = "abcde", S2 = "eafddbac"
     * <p>
     * Marathon:
     * Complexity: O(n*m) - creating matrix, O(n+m) - return the string backwards
     */

    public static void printM(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
    }

    public static String lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            f[i][0] = 0;
        }
        for (int j = 0; j < m + 1; j++) {
            f[0][j] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) f[i][j] = 1 + f[i - 1][j - 1];
                else f[i][j] = Math.max(f[i][j - 1], f[i - 1][j]);
            }
        }
// build ans
        printM(f);
        int i = n, j = m;
        String ans = "";
        while (f[i][j] != 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                ans = s1.charAt(i - 1) + ans;
                i--;
                j--;
            } else {
                if (f[i][j - 1] > f[i - 1][j]) j--;
                else i--;
            }
        }
        return ans;
    }

    /**
     * Returns the longest substring between s1,s2,s3 as String
     * Complexity: O(n*m*k)
     *
     */
    public static String TripleLCS(String s1, String s2, String s3) {

        int[][][] LCS = new int[s1.length()+1][s2.length()+1][s3.length()+1];

        for(int i = 1; i < s1.length()+1; i++) {
            for(int j = 1 ; j < s2.length()+1; j++) {
                for(int k = 1; k < s3.length()+1; k++) {

                    if(s1.charAt(i-1) == s2.charAt(j-1) && s1.charAt(i-1) == s3.charAt(k-1)) {
                        LCS[i][j][k] = LCS[i-1][j-1][k-1] + 1;
                    } else {
                        LCS[i][j][k] = max(LCS[i-1][j][k],LCS[i][j-1][k],LCS[i][j][k-1]);
                    }
                }
            }
        }
        String ans = "";
        int i = s1.length(), j = s2.length(), k = s3.length();

        while (i > 0 && j > 0 && k > 0) {

            if(LCS[i][j][k] == LCS[i-1][j-1][k-1] + 1) {
                ans = s1.charAt(i-1) + ans;
                i--;
                j--;
                k--;
            }
            else if(LCS[i][j][k] == LCS[i-1][j][k] ) {
                i--;
            }
            else if(LCS[i][j][k] == LCS[i][j-1][k]) {
                j--;
            }
            else {
                k--;
            }
        }
        return ans;
    }

    public static int max(int x, int y, int z) {
        int max = x;
        if(max < y) {
            max = y;
        }
        if(max < z) {
            max = z;
        }
        return max;
    }

    /**
     * Dynamic programming of LCS - length
     * Complexity: O(m*n) - |s1| = n , |s2| = m
     */
    public int LCS_length(String s1, String s2) {
        int n = s1.length() + 1;
        int m = s2.length() + 1;
        mat = new int[n][m];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                } else {
                    mat[i][j] = Math.max(mat[i - 1][j], mat[i][j - 1]);
                }
            }
        }
        return mat[n - 1][m - 1];
    }

    /**
     * Dynamic programming of LCS - string
     * Complexity: O(m*n) - build a matrix[|s1|=n,|s2|=m] . O(m+n) - get the string
     */
    public String LCS_string(String s1, String s2) {
        int len = LCS_length(s1, s2);
        int i = s1.length();
        int j = s2.length();
        String ans = "";
        while (len > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                ans = s1.charAt(i - 1) + ans;
                i--;
                j--;
                len--;
            } else {
                if (mat[i - 1][j] > mat[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return ans;
    }

    /**
     * Dynamic programming of LCS - string - Recursion
     * Complexity: O(m*n) - build a matrix|X| = n , |Y| = m . O(m+n) - get the string
     */
    public String LCS_string_Recursion(String X, String Y) {
        int len = LCS_length(X, Y);
        return LCS_string_Recursion(X, Y, X.length(), Y.length(), len);
    }

    private String LCS_string_Recursion(String X, String Y, int i, int j, int len) {
        if (len == 0) return "";
        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            return LCS_string_Recursion(X, Y, i - 1, j - 1, len - 1) + X.charAt(i - 1);
        } else {
            if (mat[i - 1][j] > mat[i][j - 1]) {
                return LCS_string_Recursion(X, Y, i - 1, j, len);
            } else {
                return LCS_string_Recursion(X, Y, i, j - 1, len);
            }
        }
    }

    /**
     * Dynamic programming of LCS - all substrings - Recursion
     * Complexity: O(m*n) - build a matrix|X| = n , |Y| = m . O(2^(m+n)) - get the strings
     */
    public Vector<String> LCS_Allstrings(String X, String Y) {
        int len = LCS_length(X, Y);
        Vector<String> ans = new Vector<String>();
        LCS_Allstrings(ans, X, Y, X.length(), Y.length(), len, "");
        return ans;
    }

    private void LCS_Allstrings(Vector<String> ans, String X, String Y, int i, int j, int len, String temp) {
        if (len == 0) {
            if (!ans.contains(temp)) ans.add(temp);
            return;
        }
        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            LCS_Allstrings(ans, X, Y, i - 1, j - 1, len - 1, X.charAt(i - 1) + temp);
        } else {
            if (mat[i - 1][j] > mat[i][j - 1]) {
                LCS_Allstrings(ans, X, Y, i - 1, j, len, temp);
            } else if (mat[i - 1][j] < mat[i][j - 1]) {
                LCS_Allstrings(ans, X, Y, i, j - 1, len, temp);
            } else {

                LCS_Allstrings(ans, X, Y, i - 1, j, len, temp);
                LCS_Allstrings(ans, X, Y, i, j - 1, len, temp);
            }
        }
    }
}