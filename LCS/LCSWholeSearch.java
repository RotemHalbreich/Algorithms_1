package LCS;

import java.util.Vector;

/**
 * LCS - Longest Common Substring
 * returns the LCS of X and Y
 */
public class LCSWholeSearch {
    /**
     * The whole search of LCS
     * Complexity: O(2^(m+n)*min(m,n)) - |X| = n , |Y| = m
     */
    public static String wholeSearch(String X, String Y) {
        Vector<String> sx = getAllSubStrings(X);
        Vector<String> sy = getAllSubStrings(Y);
        String lcs = "";
        for (String s1 : sx) {
            for (String s2 : sy) {
                if (s1.equals(s2)) {
                    if (s1.length() > lcs.length()) {
                        lcs = s1;
                    }
                }
            }
        }
        return lcs;
    }

    private static Vector<String> getAllSubStrings(String str) {
        Vector<String> ans = new Vector<String>();
        getAllSubStrings(str, ans, 0, "");
        return ans;
    }


    private static void getAllSubStrings(String str, Vector<String> ans, int i, String temp) {
        if (i == str.length()) {
            ans.add(temp);
            return;
        }
        getAllSubStrings(str, ans, i + 1, temp);
        getAllSubStrings(str, ans, i + 1, temp + str.charAt(i));
    }
}