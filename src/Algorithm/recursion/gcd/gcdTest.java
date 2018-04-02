package Algorithm.recursion.gcd;

import java.util.Scanner;

public class gcdTest {
    public static int getGCD(int m, int n)
    {
        if(m % n ==0)
            return n;
        else return getGCD(n, m % n);
    }
    public static void main(String[] args){
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter two Integer: ");
            int m = input.nextInt();
            int n = input.nextInt();
            System.out.println(m + " 和 " + n + "的最大公约数为： " + getGCD(m, n));
        }
    }
}
