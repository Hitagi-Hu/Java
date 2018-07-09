package NOWCODER;

import java.util.Scanner;

public class worldCup {
    public static void main(String[] str) {
        int max = (int) (1e5 + 5);
        int n, m, k,x,y;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        int[][] taocan = new int[max][];
        //init
        for (int[] i: taocan)
            for(int j: i){
            j = 0;

            }

        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            taocan[x][y] = 1;
        }
    }
}
