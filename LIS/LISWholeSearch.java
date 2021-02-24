package LIS;

import java.util.Vector;

public class LISWholeSearch {
    public static int[] wholeSearch(int[] arr) {
        Vector<int[]> allseq = getAllSubSequence(arr);
        int[] ans = null;
        int max = 0;
        for (int[] a : allseq) {
            boolean increase = true;
            for (int i = 1; i < a.length; i++) {
                if (a[i] < a[i - 1]) increase = false;
            }
            if (increase) {
                if (a.length > max) {
                    max = a.length;
                    ans = a;
                }
            }
        }
        return ans;
    }

    private static Vector<int[]> getAllSubSequence(int[] arr) {
        Vector<int[]> ans = new Vector<int[]>();
        for (int binary = 0; binary < Math.pow(2, arr.length); binary++) {
            Vector<Integer> temp = new Vector<Integer>();
            int b = binary, i = 0;
            while (b != 0) {
                if (b % 2 == 1) temp.add(arr[i]);
                i++;
                b /= 2;
            }
            int[] sequence = new int[temp.size()];
            for (int j = 0; j < sequence.length; j++) {
                sequence[j] = temp.get(j);
            }
            ans.add(sequence);
        }
        return ans;
    }
}