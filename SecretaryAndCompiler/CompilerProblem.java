package SecretaryAndCompiler;

class Program {
    String name;
    int len, freq;

    public Program(String name, int len, int freq) {
        this.name = name;
        this.len = len;
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "[" + name + " ," + len + " ," + freq + "]";
    }
}

public class CompilerProblem {

    /**
     * בעיית הקומפיילר:
     * ממש את האלגוריתם עבור בעיית הקומפיילר – כתוב מחלקה המייצגת תוכנית ובה 3 משתני
     * מחלקה – שם התוכנית, אורך התוכנית ותדירות הקריאה לתוכנית. כתוב פונקציה המקבלת
     * מערך של תוכניות ומדפיסה את התוכניות לפי הסדר האופטימאלי (כלומר, הסדר שיגרום לזמן
     * ריצה כולל מינימאלי) ואת זמן הריצה הכולל.
     *
     * @param programs
     */
    public static void getOptimalOrder(Program[] programs) {
        mergeSort(programs, 0, programs.length);
        int totalTime = 0;
        int totallen = 0;
        for (int i = 0; i < programs.length; i++) {
            System.out.println(programs[i]);
            totalTime += (totallen + programs[i].len) * programs[i].freq;
            totallen += programs[i].len;
        }
        System.out.println("Total time: " + totalTime);
    }

    private static void mergeSort(Program[] p, int low, int high) {  //Complexity: O(n*logn)
        int n = high - low;
        if (n <= 1) return;
        int mid = (low + high) / 2;
        mergeSort(p, low, mid);
        mergeSort(p, mid, high);
        int i = low, j = mid, k = 0;
        Program[] temp = new Program[n];
        while (i < mid && j < high) {
            double t1 = (double) p[i].len / p[i].freq;
            double t2 = (double) p[j].len / p[j].freq;
            if (t1 < t2) temp[k++] = p[i++];
            else temp[k++] = p[j++];
        }
        while (i < mid) temp[k++] = p[i++];
        while (j < high) temp[k++] = p[j++];
        for (int l = 0; l < n; l++) {
            p[low + l] = temp[l];
        }
    }
}