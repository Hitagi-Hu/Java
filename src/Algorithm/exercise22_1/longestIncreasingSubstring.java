package Algorithm.exercise22_1;
/*
获得给定字符串中最长的递增子字符串
 */
import java.util.*;

public class longestIncreasingSubstring {
    public static void main(String[] args){
        while (true) {
            //get input string and it's length
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter a string: ");
            String srcStr = input.nextLine();
            int length = srcStr.length();

            //get all substring which increases by degrees
            Set<String> subString = new LinkedHashSet<>();
            String incString = "";
            int i = 0;
            while (i < length) {
                incString += srcStr.charAt(i);
                if (i + 1 < length && srcStr.charAt(i + 1) > srcStr.charAt(i)) {
                } else {
                    subString.add(incString);
                    incString = "";
                }
                i++;
            }
          /*  //遍历找出最长的字符串
            String[] array = new String[subString.size()];
            subString.toArray(array);
            int max = 0;
            String result = "";
            for (String str : array) {
                if (str.length() > max) {
                    max = str.length();
                    result = str;
                }
            }
            System.out.println(srcStr + "中的最长连续递增子字符串为: " + result);*/
            String[] array = new String[subString.size()];
            subString.toArray(array);
            int max = 0;
            Set<String> result = new LinkedHashSet<>();     //使用集合避免了相同字符串
            for(String str : array){
                if(str.length() > max){
                    max = str.length();
                    result.clear();
                    result.add(str);
                }
                else if (str.length() == max){
                    result.add(str);
                }
            }
           //输出结果
            System.out.print(srcStr + "中的最长连续递增子字符串为:");
            Iterator<String> it = result.iterator();
            while (it.hasNext()){
                System.out.println(it.next());
            }





        }
    }
}
/*
*注意：使用LinkedHashSet可以还原字符串顺序
*      空格也计入了字符串长度
*问题：
* 字符串有空格时，会忽略掉第一个空格前的内容，第一个空格以后(还有其他多个空格的情况)的内容正常处理
*debug：
* 输入"abc abd"得到的全部substring正常，为"abc"和" abd";
* emmmm,搞错了，" abd"长度为4，比"abc"长度3大，所以输出为" abd",之前没有看到空格。
*
 */

