package EuclidProblem;

import java.util.Scanner;

class Main {

    public static void main(String args[]) {
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