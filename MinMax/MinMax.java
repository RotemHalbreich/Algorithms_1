package MinMax;

public class MinMax {
    /**
     * Find the minimum and the maximum elements in array
     * Complexity: O(n) - 2n comparisons
     *
     * @return number of comparisons
     */
    public static int minMaxM1(int[] arr) {
        int comparisons = 0;
        int max = arr[0], min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            comparisons++;
            if (arr[i] > max) max = arr[i];
            else {
                comparisons++;
                if (arr[i] < min) min = arr[i];
            }
        }
        System.out.println("Max = " + max + " , Min = " + min + " , Comparisons = " + comparisons);
        return comparisons;
    }

    /**
     * Find the minimum and the maximum elements in array
     * Complexity: O(n) ----> 1.5(n) comparisons
     *
     * @return number of comparisons
     */
    public static int minMaxM2(int[] arr) {
        if (arr.length == 1) {
            System.out.println("Max = " + arr[0] + " , Min = " + arr[0] + " , Comparisons = " + 0);
            return 0;
        }
        int comparisons = 0;
        int max, min;
        comparisons++;
        if (arr[0] > arr[1]) {
            max = arr[0];
            min = arr[1];
        } else {
            max = arr[1];
            min = arr[0];
        }
        for (int i = 3; i < arr.length; i += 2) {
            comparisons++;
            if (arr[i] > arr[i - 1]) {
                comparisons += 2;
                if (arr[i] > max) max = arr[i];
                if (arr[i - 1] < min) min = arr[i - 1];
            } else {
                comparisons += 2;
                if (arr[i - 1] > max) max = arr[i - 1];
                if (arr[i] < min) min = arr[i];
            }
        }
        comparisons++;
        if (arr[arr.length - 1] > max) max = arr[arr.length - 1];
        else {
            comparisons++;
            if (arr[arr.length - 1] < min) min = arr[arr.length - 1];
        }
        System.out.println("Max = " + max + " , Min = " + min + " , Comparisons = " + comparisons);
        return comparisons;
    }

    /**
     * @param method
     * @param checks
     * @param max_size Prints the average number of comparisons of given method
     */
    public static void getAverageofMethod(int method, int checks, int max_size) {
        int[] arr;
        double sum = 0;
        for (int i = 0; i < checks; i++) {
            arr = new int[(int) (Math.random() * max_size + 10)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (int) (Math.random() * max_size * 10 + 1);
            }
            if (method == 1) sum += (double) minMaxM1(arr) / arr.length;
            if (method == 2) sum += (double) minMaxM2(arr) / arr.length;
        }
        System.out.println("avarage: " + sum / checks);
    }
}
