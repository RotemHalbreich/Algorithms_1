package PowerAndFibonacci;

public class Power {
    /**
     * returns x^n
     * Complexity: O(log n)
     */
    public static double powInd(double x, int n) {
        double ans = 1;
        while (n != 0) {
            if (n % 2 == 1) ans *= x;
            x = x * x;
            n /= 2;
        }
        return ans;
    }

    /**
     * returns x^n - recursive
     * Complexity: O(log n)
     */
    public static double powRec(double x, int n) {
        if (n == 0) return 1;
        if (n % 2 == 1) return powRec(x * x, n / 2) * x;
        return powRec(x * x, n / 2);
    }

    public static double powUsingBinary(double x, int n) {
        double ans = 1;
        while (n != 0) {
            ans *= ((n & 1) != 0) ? x : 1;
            x *= x;
            n >>= 1;
        }
        return ans;
    }
}