package Prisoners;

public class Prison {
    private static final int NumOfPrisoners = 10; // 0...9
    private static final int counterMan = 0;

    /**
     * prison problem when the initial state of light is known (ON)
     * Complexity: O(?)
     *
     * Best case complexity: O(2n)
     */
    public static void prisonProblem() {
        boolean light = true;
        boolean firstTime = true;
        boolean[] enter = new boolean[NumOfPrisoners];
        int count = 0;
        int steps = 0;
        while (count < NumOfPrisoners) {
            steps++;
            int turnToEnter = (int) (Math.random() * NumOfPrisoners);
            if (turnToEnter == counterMan) {
                if (firstTime) {
                    enter[counterMan] = true;
                    count++;
                    firstTime = false;
                }
                if (!light) {
                    light = true;
                    count++;
                }
            } else {
                if (light && !enter[turnToEnter]) {
                    light = false;
                    enter[turnToEnter] = true;
                }
            }
        }
        for (int i = 0; i < enter.length; i++) {
            if (!enter[i]) {
                System.out.println("Fail!");
                return;
            }
        }
        System.out.println("We are free!");
        System.out.println("Number of steps = " + steps);
    }

    /**
     * prison problem when the initial state of light is Unknown (ON/OFF)
     */
    public static void prisonProblemUnknownState() {
        boolean light = (int) (Math.random() * 2) == 0 ? false : true;
        boolean firstTime = true;
        int[] enter = new int[NumOfPrisoners];
        int count = 0;
        int steps = 0;
        while (count < 2 * NumOfPrisoners) {
            steps++;
            int turnToEnter = (int) (Math.random() * NumOfPrisoners);
            if (turnToEnter == counterMan) {
                enter[counterMan]++;
                if (firstTime) {
                    count += 2;
                    firstTime = false;
                }
                if (light) {
                    light = false;
                    count++;
                }
            } else {
                if (!light && enter[turnToEnter] < 2) {
                    light = true;
                    enter[turnToEnter]++;
                }
            }
        }
        for (int i = 0; i < enter.length; i++) {
            if (enter[i] == 0) {
                System.out.println("Fail!");
                return;
            }
        }
        System.out.println("We are free!");
        System.out.println("Number of steps = " + steps);
    }

    public static void main(String[] args) {
        prisonProblemUnknownState();
    }
}