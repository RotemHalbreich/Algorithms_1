package LIS;

import java.util.Vector;

public class GreedyLIS {
    /**
     * The greedy algorithm of LIS - doesn't work for any array
     * Complexity: O(n)
     */
    public static Vector<Integer> greedyLIS(int[] arr) {
        return greedyLIS(arr, 0);
    }

    private static Vector<Integer> greedyLIS(int[] arr, int start) {
        Vector<Integer> ans = new Vector<Integer>();
        ans.add(arr[start]);
        int max = arr[start];
        for (int i = start + 1; i < arr.length; i++) {
            if (arr[i] > max) {
                ans.add(arr[i]);
                max = arr[i];
            }
        }
        return ans;
    }

    /**
     * The improved greedy algorithm of LIS - check from any index - doesn't work for any array
     * Complexity: O(n^2)
     */
    public static Vector<Integer> greedyLISImproved(int[] arr) {
        Vector<Integer> ans = new Vector<Integer>();
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            Vector<Integer> temp = greedyLIS(arr, i);
            if (temp.size() > maxLen) {
                maxLen = temp.size();
                ans = temp;
            }
        }
        return ans;
    }
}