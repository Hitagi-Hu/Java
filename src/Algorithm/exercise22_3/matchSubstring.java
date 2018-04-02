package Algorithm.exercise22_3;
/*
*检测输入的第二个字符串是否是第一个字符串的字串(不使用string.indexof()方法)
* 目标：检测到所有匹配字串并输出其匹配位置的下标index
 */
import java.util.ArrayList;
import java.util.Scanner;

public class matchSubstring {
    public static void main(String[] args){
        while (true) {
            //get input string and it's length
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter first string: ");
            String src1 = input.nextLine();
            System.out.println("Please enter second string: ");
            String src2 = input.nextLine();
            int length1 = src1.length();
            int length2 = src2.length();

            //在src1上滑动src2，寻找匹配字符串，其index保存到集合里
            ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i <= length1 - length2; i++) {     //注意字符串末尾是否覆盖到
                if (src1.substring(i, i + length2).equals(src2)) {
                    result.add(i);
                }
            }

            //输出结果
            if (result.size() > 0) {
                System.out.print("matched an index: ");
                for (int i : result) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            else
                System.out.println("not matched");
        }
    }
}
