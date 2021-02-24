package GlassBalls;

public class GlassBalls {
    /**
     * Returns the minimum floor that causes breaking ball - using 2 balls and dividing the building into equal parts
     * Complexity: O(sqrt(n)) - 2*sqrt(n)
     */
    public static int glassBall1(int[] floors, int ball) {
        int n = floors.length; //100
        int step = (int) Math.sqrt(n); //10
        int currentFloor = step;
        boolean isBreak = false;
        while (!isBreak) {
            if (floors[currentFloor] > ball) {
                currentFloor = currentFloor - step + 1;
                while (!isBreak) {
                    if (floors[currentFloor] > ball) {
                        return currentFloor;
                    }
                    currentFloor++;
                }
            }
            if (currentFloor == n - 1) break;
            currentFloor += step;
            if (currentFloor > n - 1) currentFloor = n - 1;
        }
        return n;
    }

    /**
     * Returns the minimum floor that causes breaking ball - using 2 balls and dividing the building into different parts
     * Complexity: O(sqrt(n)) - sqrt(2*n)
     */
    public static int glassBall2(int[] floors, int ball) {
        int n = floors.length;
        int step = 1;
        while ((step * (step + 1)) / 2 < n) { //  (k*(k+1))/2 = n  -  Triangle numbers
            step++;
        }
        int currentFloor = step;
        boolean isBreak = false;
        while (!isBreak) {
            if (floors[currentFloor] > ball) {
                currentFloor = currentFloor - step + 1;
                while (!isBreak) {
                    if (floors[currentFloor] > ball) {
                        return currentFloor;
                    }
                    currentFloor++;
                }
            }
            if (currentFloor == n - 1) break;
            step--;
            currentFloor += step;
            if (currentFloor > n - 1) currentFloor = n - 1;
        }
        return n;
    }


    /**
     * Complexity: O(k*n^2)
     * Where ‘k’ is the number of glass balls and ‘n’ is the number of floors, as we use a nested for loop ‘n^2’ times for each ball
     * Auxiliary Space: O(n*k).
     * As a 2-D array of size ‘n*k’ is used for storing elements.
     *
     */
    /* Function to get minimum number of trials needed in worst case with k eggs and n floors */
    static int eggDrop(int k, int n) {

        /* A 2D table where entry eggFloor[i][j]
        will represent minimum number of trials
        needed for i eggs and j floors. */

        int[][] eggFloor = new int[k + 1][n + 1];
        int res;
        int i, j, x;

        // We need one trial for one floor and 0 trials for 0 floors
        for (i = 1; i <= k; i++) {
            eggFloor[i][1] = 1;
            eggFloor[i][0] = 0;
        }

        // We always need j trials for one egg and j floors.
        for (j = 1; j <= n; j++)
            eggFloor[1][j] = j;

        // Fill rest of the entries in table using optimal substructure property
        for (i = 2; i <= k; i++) {
            for (j = 2; j <= n; j++) {
                eggFloor[i][j] = Integer.MAX_VALUE;
                for (x = 1; x <= j; x++) {
                    res = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]);
                    if (res < eggFloor[i][j])
                        eggFloor[i][j] = res;
                }
            }
        }
        // eggFloor[k][n] holds the result
        return eggFloor[k][n];
    }


    public static void main(String[] args) {
        System.out.println(glassBall2(new int[]{10, 20, 30, 40, 50, 60, 70}, 55));

        int n = 100, k = 2;
        System.out.println("Minimum number of trials in worst case with "
                + k + " glass balls and "
                + n + " floors is " + eggDrop(k, n));

    }
}