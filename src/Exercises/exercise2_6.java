package Exercises;
/*求输入整数各位数之和*/
import java.util.Scanner;

public class exercise2_6 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number between 0 with 1000: ");
        int number = input.nextInt();
        if(number < 0 || number > 1000)
            return;
        int result = 0;
        while(number > 10){
            int digit = number % 10;
            result += digit;
            number = number / 10;
        }
        result += number;
        System.out.println(result);
    }
}
