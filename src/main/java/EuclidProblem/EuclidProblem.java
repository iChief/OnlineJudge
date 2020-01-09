package EuclidProblem;

import java.util.Scanner;

/**
 * Euclid Problem (10104)
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=13&page=show_problem&problem=1045
 *
 * @author Ahmed Schahadeh (UVa: iChief)
 */

class Main {
    /**
     * Gesucht ist der grösste gemeinsame Teiler a.
     * Wobei a*x1 + b*y1 zusammen den ggT ergeben müssen.
     */

    public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            while (in.hasNext()) {
                int a = in.nextInt();
                int b = in.nextInt();

                int x = 0;
                int y = 1;
                int x1 = 1;
                int y1 = 0;
                while (b != 0) {
                    int d = a/b;

                    int tmp = b;
                    b = a%b;
                    a = tmp;

                    int next_x = x1 - d * x;
                    x1 = x;
                    x = next_x;

                    int next_y = y1 - d * y;
                    y1 = y;
                    y = next_y;
                }
                System.out.println(x1 + " " + y1 + " "  + a);
            }
        }
    }