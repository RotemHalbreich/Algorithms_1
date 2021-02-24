package SecretaryAndCompiler;

import java.util.Arrays;

public class SecretaryProblem {

    public static void main(String[] args) {
        int[] arr = new int[]{ 1,2,3,4,5,6,7,8,9,10 };
        System.out.println(getAverageTime(arr));
    }

    /**
     * Complexity: (Java sort --> QuickSort) O(n*logn), worst case: O(n^2)
     *
     * @param times
     * @return int[]
     */
    public static double getAverageTime(int[] times) {
        Arrays.sort(times);
        double avg = 0;
        for (int x : times) avg = avg + avg + x;
        return avg / times.length;
    }
}