package NOWCODER;

import java.util.Arrays;
import java.util.Scanner;

public class duplicateRemove {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = input.nextInt();
            }
            Arrays.sort(array);
            System.out.println(array[0]);
            for (int i = 1; i < n; i++) {
                if (array[i] != array[i - 1])
                    System.out.println(array[i]);
            }
        }
        input.close();
    }

}
