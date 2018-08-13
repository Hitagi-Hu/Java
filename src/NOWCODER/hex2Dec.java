package NOWCODER;

import java.util.Scanner;

public class hex2Dec {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String str = input.next();
            str = str.substring(2);

            char[] chars = str.toCharArray();
            int length = chars.length;
            int sum = 0;
            for (int i = 0; i < length; i++){
                sum += toDecimal(chars[i]) * Math.pow(16,length - i - 1);
            }
            System.out.println(sum);

        }
    }

    public static int toDecimal(char ch) { //没有考虑大小写字母,可以直接用ascii码写多个判断语句
        char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0; i < 16; i++) {
            if (ch == table[i]) return i;
        }
        return -1;
    }
}
