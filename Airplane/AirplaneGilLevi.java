package Airplane;

import java.awt.*;

public class AirplaneGilLevi {


    static class Node {
        int x, y, price, numOfPaths;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Original - Airplane problem (0,0)-->(n,m) | n = Rows, m = Columns
     * Seeks for the lowest price from (0,0)-->(n,m) (Shortest path distance)
     *
     * Complexity: O(n*m) + O(n+m) ----> O(n*m)
     *
     */
    public static class MinPrice {

        public static int minPriceOriginal(Node[][] mat) {
            int n = mat.length, m = mat[0].length;
            mat[0][0].price = 0;
            for (int i = 1; i < n; i++) {
                mat[i][0].price = mat[i - 1][0].price + mat[i - 1][0].y;
            }
            for (int i = 1; i < m; i++) {
                mat[0][i].price = mat[0][i - 1].price + mat[0][i - 1].x;
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    mat[i][j].price = Math.min(mat[i - 1][j].price + mat[i - 1][j].y, mat[i][j - 1].price + mat[i][j - 1].x);
                }
            }
            return mat[n - 1][m - 1].price;
        }


        public static String minPrice(Node[][] mat) {
            int n = mat.length, m = mat[0].length;
            mat[0][0].price = 0;
            mat[0][0].numOfPaths = 1;
            for (int i = 1; i < n; i++) {
                mat[i][0].price = mat[i - 1][0].price + mat[i - 1][0].y;
                mat[i][0].numOfPaths = 1;
            }
            for (int i = 1; i < m; i++) {
                mat[0][i].price = mat[0][i - 1].price + mat[0][i - 1].x;
                mat[0][i].numOfPaths = 1;
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    int fromDown = mat[i - 1][j].price + mat[i - 1][j].y;
                    int fromLeft = mat[i][j - 1].price + mat[i][j - 1].x;
                    mat[i][j].price = Math.min(fromDown, fromLeft);
                    if (fromDown < fromLeft) {
                        mat[i][j].numOfPaths = mat[i - 1][j].numOfPaths;
                    } else if (fromDown > fromLeft) {
                        mat[i][j].numOfPaths = mat[i][j - 1].numOfPaths;
                    } else mat[i][j].numOfPaths = mat[i - 1][j].numOfPaths + mat[i][j - 1].numOfPaths;
                }
            }
            return path(mat);
        }

        private static String path(Node[][] mat) {
            int i = mat.length - 1, j = mat[0].length - 1;
            String ans = "";
            while (i != 0 && j != 0) {
                if (mat[i - 1][j].price + mat[i - 1][j].y < mat[i][j - 1].price + mat[i][j - 1].x) {
                    ans = "1" + ans;
                    i--;
                } else {
                    ans = "0" + ans;
                    j--;
                }
            }
            while (i != 0) {
                ans = "1" + ans;
                i--;
            }
            while (j != 0) {
                ans = "0" + ans;
                j--;
            }
            return ans;
        }

        /**
         * Returns the price between two points p1, p2
         * Complexity: O(n*m) + O(n+m) ----> O(n*m)
         *
         */
        public static int minPriceBetween(Node[][] mat, Point p1, Point p2) {
            int n = p2.y - p1.y + 1, m = p2.x - p1.x + 1;
            mat[p1.y][p1.x].price = 0;
            for (int i = p1.y + 1; i < p1.y + n; i++) {
                mat[i][p1.x].price = mat[i - 1][p1.x].price + mat[i - 1][p1.x].y;
            }
            for (int i = p1.x + 1; i < p1.x + m; i++) {
                mat[p1.y][i].price = mat[p1.y][i - 1].price + mat[p1.y][i - 1].x;
            }
            for (int i = p1.y + 1; i < p1.y + n; i++) {
                for (int j = p1.x + 1; j < p1.x + m; j++) {
                    mat[i][j].price = Math.min(mat[i - 1][j].price + mat[i - 1][j].y, mat[i][j - 1].price + mat[i][j - 1].x);
                }
            }
            return mat[p2.y][p2.x].price;
        }

        public static boolean isOnMinPath(Node[][] mat, Point p1, Point p2) {
            if (p2.x <= p1.x && p2.y <= p1.y) {
                Point t = p1;
                p1 = p2;
                p2 = t;
            }
            if (p1.x <= p2.x && p1.y <= p2.y) {
                int allPrice = minPriceBetween(mat, new Point(0, 0), new Point(mat.length - 1, mat[0].length - 1));
                int SrctoP1 = minPriceBetween(mat, new Point(0, 0), p1);
                int p1toP2 = minPriceBetween(mat, p1, p2);
                int p2toDst = minPriceBetween(mat, p2, new Point(mat.length - 1, mat[0].length - 1));
                if (allPrice == SrctoP1 + p1toP2 + p2toDst) return true;
                else return false;
            } else return false;
        }

        public static void main(String[] args) {
            Node[][] mat = {
                    {new Node(1, 5), new Node(4, 1), new Node(0, 6)},
                    {new Node(4, 7), new Node(2, 5), new Node(0, 3)},
                    {new Node(1, 0), new Node(2, 0), new Node(0, 0)}};
            System.out.println(minPriceOriginal(mat));
            // System.out.println(isOnMinPath(mat, new Point(1, 1), new Point(1, 2)));

        }
    }

}
