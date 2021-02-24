package LCS;

/**
 * LCS - Longest Common Substring
 * returns the LCS of X and Y
 */
public class GreedyLCS {
    /**
     * The greedy algorithm of LCS - doesn't work for any strings
     * Complexity: O(n*m) - |X| = n , |Y| = m
     */
    public static String greedyLCS(String X, String Y) {
        String ans = "";
        int start = 0;
        for (int i = 0; i < X.length(); i++) {
            int index = Y.indexOf(X.charAt(i), start);
            if (index != -1) {
                ans = ans + X.charAt(i);
                start = index + 1;
            }
        }
        return ans;
    }

    /**
     * The greedy algorithm of LCS - doesn't work for any strings
     * Complexity: O(n+m) - |X| = n , |Y| = m
     */
    public static String greedyLCSImproved(String X, String Y) {
        int freq[] = new int[26];
        for (int i = 0; i < X.length(); i++) {
            int place = (X.charAt(i) - 'a');
            freq[place]++;
        }
        String ans = "";
        int start = 0;
        for (int i = 0; i < Y.length(); i++) {
            int place = (Y.charAt(i) - 'a');
            if (freq[place] > 0) {
                int index = X.indexOf(Y.charAt(i), start);
                if (index != -1) {
                    ans = ans + Y.charAt(i);
                    start = index + 1;
                    freq[place]--;
                }
            }
        }
        return ans;
    }
}