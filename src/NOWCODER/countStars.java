package NOWCODER;
/*
* 一闪一闪亮晶晶，满天都是小星星，牛牛晚上闲来无聊，便躺在床上数星星。
牛牛把星星图看成一个平面，左上角为原点(坐标为(1, 1))。现在有n颗星星，他给每颗星星都标上坐标(xi，yi)，表示这颗星星在第x行，第y列。
现在，牛牛想问你m个问题，给你两个点的坐标(a1, b1)(a2，b2)，表示一个矩形的左上角的点坐标和右下角的点坐标，请问在这个矩形内有多少颗星星（边界上的点也算是矩形内）。
输入描述:
第一行输入一个数字n(1≤n≤100000)，表示星星的颗数。
接下来的n行，每行输入两个数xi和yi(1≤xi，yi≤1000），表示星星的位置。
然后输入一个数字m(1≤m≤100000), 表示牛牛询问问题的个数。
接下来m行，每行输入四个数字a1，b1，a2，b2(1≤a1＜a2≤1000), (1≤b1＜b2≤1000）
题目保证两颗星星不会存在于同一个位置。


输出描述:
输出一共包含m行，每行表示与之对应的每个问题的答案。

输入例子1:
4
1 1
2 2
3 3
1 3
4
1 1 2 2
1 1 3 3
2 2 3 3
1 2 2 3

输出例子1:
2
4
2
2

为减小算法复杂度，用矩形右下角左上方的数量 - 左下角左上方数量 - 右上角左上方数量 + 左上角左上方数量
*/

import java.util.Scanner;

public class countStars {
    /*public  static int count(int x, int y,int[][] array) {
        int count = 0;
        for (int i = 0; i <= x; i++)
            for (int j = 0; j <= y; j++) {
                if (array[i][j] == 1) count++;
            }
        return count;
    }*/

    public static void main(String[] args) {
       /* Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[][] array = new int[max][max];
        //init
        for (int i = 0; i < max; i++)
            for (int j = 0; j < max; j++) {
                array[i][j] = 0;
            }

        for (int i = 0; i < n; i++) {
            int xi = input.nextInt();
            int yi = input.nextInt();
            array[xi][yi] = 1;
        }
        int m = input.nextInt();
        for (int i = 0; i < m; i++) {
            int count = 0;
            int a1, b1, a2, b2;
            a1 = input.nextInt();
            b1 = input.nextInt();
            a2 = input.nextInt();
            b2 = input.nextInt();

            //计算矩形四角左上方星星数量
            int rigthBottom = count(a2,b2,array);
            int leftBottom = count(a2,b1-1,array);
            int rightTop = count(a1 - 1,b2,array);
            int leftTop = count(a1 -1,b1-1,array);
            int result = rigthBottom - leftBottom - rightTop + leftTop;
            System.out.println(result);
        }
    }*/
        int max = (int) (1e3 + 5);
        int n, m;
        int[][] num = new int[max][max];
        int[][] mp = new int[max][max];
        int x, y;
        int a1, b1, a2, b2;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        for (int i = 0; i < max; i++)
            for (int j = 0; j < max; j++) {
                mp[i][j] = 0;
                num[i][j] = 0;
            }
            //输入n个星星的坐标
        for (int i = 0; i < n; i++){
            x = input.nextInt();
            y = input.nextInt();
            mp[x][y]++;  //mp[x][y] = 1
        }

        for (int i = 1; i < max; i++)
            for (int j = 1; j < max; j++){
                num[i][j] = num[i - 1][j] + num[i][j - 1] + mp[i][j] - num[i - 1][j - 1];
            }

            m = input.nextInt();
        for (int i = 0; i < m; i++){
            a1 = input.nextInt();
            b1 = input.nextInt();
            a2 = input.nextInt();
            b2 = input.nextInt();
            System.out.println(num[a2][b2] - num[a1 - 1][b2] - num[a2][b1 - 1] + num[a1 - 1][b1 - 1]);
        }
    }
}
