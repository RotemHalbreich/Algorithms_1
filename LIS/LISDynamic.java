package LIS;

import java.util.Arrays;

public class LISDynamic {


    /**
     * ~~~Dynamic:~~~
     *
     * Only LIS size
     * Complexity: O(n*logn)
     */
    public static int lisSize(int[] arr) { // only size - O(nlog(n))
        int n = arr.length;
        int[] lis = new int[n];
        lis[0] = arr[0];
        int k = 1;
        for (int i = 1; i < n; i++) { //O(n)
            int index = Arrays.binarySearch(lis, 0, k, arr[i]); //O(logn)
            if(index < 0) index = -index - 1; // fix java's results
            if(index == k) k++;
            lis[index] = arr[i];
        }
        System.out.println(Arrays.toString(lis));
        return k;
    }

    /**
     * ~~~Dynamic:~~~
     *
     * Returns the sequence of the LIS
     * Complexity: O(n^2)
     */
    public static int[] lis(int[] arr) { // O(n^2)
        int n = arr.length;
        int[] lis = new int[n];
        int[][] mat = new int[n][n];
        lis[0] = mat[0][0] = arr[0];
        int k = 1;
        for (int i = 1; i < n; i++) {
            int index = Arrays.binarySearch(lis, 0, k, arr[i]);
            if(index < 0) index = -index - 1; // fix java's results
            if(index == k) k++;
            lis[index] = mat[index][index] = arr[i];
            for (int j = 0; j < index; j++) {
                mat[index][j] = mat[index-1][j];
            }
        }
        return Arrays.copyOf(mat[k-1], k);
    }


    /**
     * dynamic programming of LIS - get the sequence
     * Complexity: O(n^2)
     */
    public static int[] LISDynamic(int[] arr) {
        int n = arr.length;
        int[][] mat = new int[n][n];
        mat[0][0] = arr[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            int index = binarySearchBetween(mat, len, arr[i]);
            mat[index][index] = arr[i];
            if (index == len) len++;
            copy(mat, index);
        }
        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = mat[len - 1][i];
        }
        return ans;
    }

    private static void copy(int[][] mat, int index) {
        for (int i = 0; i < index; i++) {
            mat[index][i] = mat[index - 1][i];
        }
    }

    private static int binarySearchBetween(int[][] mat, int end, int v) {
        if (v > mat[end - 1][end - 1]) return end;
        if (v < mat[0][0]) return 0;
        int high = end, low = 0;
        while (low <= high) {
            if (low == high) return low;
            int mid = (low + high) / 2;
            if (mat[mid][mid] == v) return mid;
            if (mat[mid][mid] > v) high = mid;
            else low = mid + 1;
        }
        return -1;
    }

    /**
     * dynamic programming of LIS - get the length only (can be done with array instead of matrix)
     * Complexity: O(n*log n)
     */
    public static int LISDynamicLen(int[] arr) {
        int n = arr.length;
        int[][] mat = new int[n][n];
        mat[0][0] = arr[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            int index = binarySearchBetween(mat, len, arr[i]);
            mat[index][index] = arr[i];
            if (index == len) len++;
        }
        return len;
    }



    public static void main(String[] args) {
        System.out.println((LISDynamicLen(new int[]{1, -9})));//,2,100,101,3,4,5})));
    }
}