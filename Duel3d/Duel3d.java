package Duel3d;

class Player {
    String name;
    boolean isAlive = true;
    double p;

    public Player(String name, double p) {
        this.name = name;
        this.p = p;
    }

}

public class Duel3d {
    public static void duel2d(Player p1, Player p2, int steps) {
        int turn = 1;
        while (p1.isAlive && p2.isAlive) {
            if (steps > 200) {
                System.out.println("Time is over - no winner");
                return;
            }
            steps++;
            switch (turn) {
                case 1:
                    if (Math.random() < p1.p) {
                        p2.isAlive = false;
                        System.out.println(p2.name + " was killed by " + p1.name);
                    } else {
                        System.out.println(p1.name + " missed");
                    }
                    break;
                case 2:
                    if (Math.random() < p2.p) {
                        p1.isAlive = false;
                        System.out.println(p1.name + " was killed by " + p2.name);
                    } else {
                        System.out.println(p2.name + " missed");
                    }
                    break;
            }
            turn = 3 - turn;
        }
        System.out.println("The winner is: " + (p1.isAlive ? p1.name : p2.name));
        System.out.println("Steps = " + steps);
    }

    /**
     * Fight between 3 soldiers each one have his strategy
     * Complexity: O(?)
     */
    public static void duel3d(Player p1, Player p2, Player p3) {
        int turn = (int) (Math.random() * 3 + 1);
        int steps = 0;
        Player target;
        while (p1.isAlive && p2.isAlive && p3.isAlive) {
            if (steps > 200) {
                System.out.println("Time is over - no winner");
                return;
            }
            steps++;
            turn++;
            if (turn > 3) turn = 1;
            switch (turn) {
                case 1:
                    target = strategy(p1, p2, p3);
                    if (target != null && Math.random() < p1.p) {
                        target.isAlive = false;
                        System.out.println(target.name + " was killed by " + p1.name);
                    } else {
                        System.out.println(p1.name + " missed");
                    }
                    break;
                case 2:
                    target = strategy(p2, p1, p3);
                    if (target != null && Math.random() < p2.p) {
                        target.isAlive = false;
                        System.out.println(target.name + " was killed by " + p2.name);
                    } else {
                        System.out.println(p2.name + " missed");
                    }
                    break;
                case 3:
                    target = strategy(p3, p1, p2);
                    if (target != null && Math.random() < p3.p) {
                        target.isAlive = false;
                        System.out.println(target.name + " was killed by " + p3.name);
                    } else {
                        System.out.println(p3.name + " missed");
                    }
                    break;
            }
        }
        if (!p1.isAlive) {
            if (turn == 2) duel2d(p3, p2, steps);
            else duel2d(p3, p2, steps);
        } else if (!p2.isAlive) {
            if (turn == 3) duel2d(p1, p3, steps);
            else duel2d(p3, p1, steps);
        } else if (!p3.isAlive) {
            if (turn == 2) duel2d(p1, p2, steps);
            else duel2d(p2, p1, steps);
        }
    }

    private static Player strategy(Player p1, Player p2, Player p3) {
        if (p1.p < p2.p && p1.p < p3.p) {
            return null;
        }
        return p2.p > p3.p ? p2 : p3;
    }

    public static void main(String[] args) {
        duel3d(new Player("B", 1.0), new Player("C", 0.8), new Player("D", 0.5));
    }
}