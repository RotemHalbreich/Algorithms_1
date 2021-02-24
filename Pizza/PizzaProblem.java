package Pizza;

public class PizzaProblem {

    public static void main(String[] args) {

        System.out.println(getNumberOfPieces(2));
    }

    /**
     * returns the optimal division for the faster man
     * Complexity: O(1)
     *
     *: בעיית הפיצה
     * 3 .ממש את האלגוריתם לפתרון בעיית הפיצה – הפונקציה תקבל את היחס בין 2 האוכלים – כאשר
     * השני אוכל יותר מהר מהראשון (ולכן היחס גדול מ-1) (מספר ממשי) ותחזיר לכמה משולשים
     * כדאי לחלק את הפיצה (מספר שלם).
     *
     */
    public static int getNumberOfPieces(double k) {
        if (k == (int) k) return (int) k + 1;
        return (int) k + 2;
    }
}