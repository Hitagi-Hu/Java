package NOWCODER;

import java.util.Scanner;

public class StringSplit {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String str = input.next();
            if (str.length() % 8 != 0)
                str += "00000000";
            while (str.length() >= 8) {
                System.out.println(str.substring(0, 8));
                str = str.substring(8);
            }

        }
    }
}

