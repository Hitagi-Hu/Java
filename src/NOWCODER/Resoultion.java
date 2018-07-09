package NOWCODER;

import java.util.Scanner;

public class Resoultion{
    private static int n;
    private static int m;
    public static int[] Di = null;
    public static int[] Pi = null;
    public static int[] Ai = null;
    public static void mani(String[] args){
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        m = input.nextInt();
        Di = new int[n];
        Pi = new int[n];
        Ai = new int[m];
        for (int i = 0; i < n; i++){
            Di[i] = input.nextInt();
            Pi[i] = input.nextInt();
        }
        for (int i = 0; i < m; i++){
            Ai[i] = input.nextInt();
        }
        





    }
}
