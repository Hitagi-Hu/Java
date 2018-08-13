package NOWCODER;

import java.util.Scanner;

public class numbersOfChar {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.next().toLowerCase();
        char ch = input.next().toLowerCase().toCharArray()[0];
        System.out.println(count(str,ch));
        input.close();
    }

    public static int count(String str, char ch) {
        int count = 0;
        int temp = 0;
        for (int i = 0; i < str.length(); ) {
            if ((temp = str.indexOf(ch, i)) != -1) {
                count++;
                i = temp + 1;
            } else
                break;
        }
        return count;
    }
}
