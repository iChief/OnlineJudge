package TheGrandDinner;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Grand Dinner (10249)
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1190
 *
 * @author Ahmed Schahadeh (UVa username: iChief)
 * (Hilfestellung Mike Nöthiger)
 */

class Main {

    public static void main(String[] args) throws IOException {
        /**
         * Das Ziel ist es, dass nie zwei Mitglieder des selben Teams am selben Tisch zusammen Sitzen. Sprich das
         * möglichst viele verschiedene Teammitglieder jeweils an einem Tisch sitzen. Gibt es mehrere mögliche Lösungen
         * ist es egal welche genommen wird. Die Team werden in einem Array gespeichert und die Mitglieder jeweils
         * an die Tische mit den jeweils höchsten noch verfügbaren Kapazitäten verteillt. Es gibt keine Lösung für ein
         * Team, dass die Anzahl verfügbarere Tische überschreitet.
         */

        int[][] TeamPool;
        Scanner input = new Scanner(System.in);
        int NumberOfTeams;

        while ((NumberOfTeams = input.nextInt()) != 0) { // for each problem
            int NumberOfTable = input.nextInt();
            int largest_team = 0;

            TeamPool = new int[NumberOfTable + 1][NumberOfTeams + 2];

            for (int team = 2; team < TeamPool[0].length; team++) {
                TeamPool[0][team] = input.nextInt();
                if(TeamPool[0][team] > largest_team) largest_team = TeamPool[0][team];
            }

            for (int table = 1; table < TeamPool.length; table++) {
                TeamPool[table][0] = table;
                TeamPool[table][1] = input.nextInt();
            }

            if (largest_team > NumberOfTable) print(false, TeamPool);
            else {
                for (int team = 2; team < TeamPool[0].length; team++) {
                    mergeSort(TeamPool, TeamPool.length - 1, 1, 1, false);

                    for (int table = 1; table < TeamPool[0][team] + 1; table++) {
                        TeamPool[table][team] = 1;
                        TeamPool[table][1]--;
                    }
                }
                int i = 1;
                while (i < TeamPool.length - 1 && TeamPool[i][1] >= 0) {
                    i++;
                }
                if (TeamPool[i][1] >= 0) print(true, TeamPool);
                else print(false, TeamPool);
            }
        }
    }

    static void mergeSort(int[][] Pool, int number, int row, int col, boolean flag) {
        if (number < 2) return;

        int m = number / 2;
        int[][] l = new int[m][Pool[0].length];
        int[][] r = new int[number - m][Pool[0].length];

        for (int i = 0; i < m; i++) l[i] = Pool[i + row];
        for (int i = m; i < number; i++) r[i - m] = Pool[i + row];
        mergeSort(l, m, 0, col, flag);
        mergeSort(r, number - m, 0, col, flag);

        mergeTogether(Pool, l, r, m, number - m, row, col, flag);
    }

    static void mergeTogether(int[][] Pool, int[][] Left, int[][] Right,
                              int left, int right, int row, int col, boolean flag) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left && j < right) {
            if (!flag && Left[i][col] >= Right[j][col]) Pool[k++ + row] = Left[i++];
            else if (flag  && Left[i][col] <= Right[j][col]) Pool[k++ + row] = Left[i++];
            else Pool[k++ + row] = Right[j++];
        }
        while (i < left) Pool[k++ + row] = Left[i++];
        while (j < right) Pool[k++ + row] = Right[j++];
    }

    static void print(boolean works, int[][]TeamPool) {
        System.out.println(works ? 1 : 0);
        if (!works) return;
        for (int team = 2; team < TeamPool[0].length; team++) {
            for (int table = 1; table < TeamPool.length; table++) {
                if (TeamPool[table][team] == 1) {
                    System.out.print(TeamPool[table][0] + " ");
                }
            }
            System.out.println();
        }
    }

}
