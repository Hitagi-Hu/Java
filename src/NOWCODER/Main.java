package NOWCODER;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            long number = input.nextLong();
            output(number);
        }
    }

    public static void output(long number) {
        for (long i = 2; i <= number; i++) {
            if (number % i == 0) {
                System.out.println(i + " ");
                output(number / i);
                return;
            }
        }
    }
}
