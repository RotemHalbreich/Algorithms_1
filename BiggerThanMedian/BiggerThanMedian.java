package BiggerThanMedian;

import java.util.Arrays;

public class BiggerThanMedian {


    public static void main(String[] args) {
        int[] arr = {3,2,8,4,5,6,7,8};
        System.out.println(getBiggerThanMedian(arr, 3));
    }

    /**
     * return one element in the array that bigger than median
     * Complexity: O(check)
     */
    public static int getBiggerThanMedian(int[] arr, int check) {
        int max = arr[0];
        for (int i = 1; i < check; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * checks the probability to make a mistake if we're checking elements in the array with arr_size
     */
    public static double getMistakeProb(int arr_size, int check, int count) {
        int incorrect = 0;
        int[] arr = new int[arr_size];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (int) (Math.random() * count * 10);
            }
            int x = getBiggerThanMedian(arr, check);
            Arrays.sort(arr);
            if (x < arr[arr.length / 2]) incorrect++;
        }
        return (double) incorrect / count;
    }
}