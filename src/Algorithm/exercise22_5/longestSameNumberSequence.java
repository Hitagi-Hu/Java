package Algorithm.exercise22_5;

import java.util.*;

/*
*输入一个以0结束的整数序列
*找出有相同数字的最长的子序列
* 输出其起始下标、长度、值
 */
public class longestSameNumberSequence {
    public static void main(String[] args){
        while (true) {
            //get input number sequence
            Scanner input = new Scanner(System.in);
            ArrayList<Integer> numbers = new ArrayList<>();
            System.out.print("Please input a number sequence with end of number 0: ");
            int buffer = input.nextInt();
            while (buffer != 0) {
                numbers.add(buffer);
                buffer = input.nextInt();
            }
            //numbers.add(0);    不需要添加到list中，因为输入后不需要处理这个0


            //遍历数组线性表,当前相同数字序列长度更长时，更新输出的result值,初始化length


            /*
            ************************************************************************************************************
            *问题：不能输出多个一样长的序列
            int start = 0;
            int index = 0;
            int value = 0;
            int length = 1;
            int resultIndex = 0;
            int resultValue = 0;
            int resultLength = 0;
            while (start < numbers.size()) {
                for (int i = start; i < numbers.size(); i++) {
                    index = start;
                    value = numbers.get(index);
                    if (i + 1 < numbers.size() && numbers.get(i).equals(numbers.get(i + 1))) {
                        length++;
                    }
                    else {
                        if (length > resultLength) {
                            resultIndex = index;
                            resultValue = value;
                            resultLength = length;
                        }
                        length = 1;   //初始化length
                        start = i + 1;

                    }
                }
            }
            System.out.println("The longest same number sequence starts at index " + resultIndex + " with " + resultLength + " values of " + resultValue);
            *************************************************************************************************************
            */
            int start = 0;
            int index = 0;
            int value = 0;
            int length = 1;
            LinkedHashMap<Integer, Integer> result = new LinkedHashMap<>();
            int resultIndex = 0;
            int resultValue = 0;
            int resultLength = 1;
            while (start < numbers.size()) {
                for (int i = start; i < numbers.size(); i++) {
                    index = start;
                    value = numbers.get(index);
                    if (i + 1 < numbers.size() && numbers.get(i).equals(numbers.get(i + 1))) {
                        length++;
                    }
                    else {
                        if (length > resultLength) {
                            resultIndex = index;
                            resultValue = value;
                            resultLength = length;
                            result.clear();
                            result.put(resultIndex, resultValue);
                        }
                        else if (length == resultLength){
                            resultIndex = index;
                            resultValue = value;
                            resultLength = length;
                            result.put(resultIndex, resultValue);
                        }
                        length = 1;   //初始化length
                        start = i + 1;

                    }
                }
            }
            //System.out.println(result + "\n" + resultLength);     map输出格式为{key=value,...}

            //输出
            Set<Map.Entry<Integer, Integer>> entrySet = result.entrySet();
            for(Map.Entry<Integer,Integer> entry: entrySet){
                System.out.println("The longest same number sequence starts at index " + entry.getKey() + " with " + resultLength + " values of " + entry.getValue());
            }
        }

    }
}
/*考虑当有多个相同最大长度的序列的情况，用LinkedHashMap保存resultIndex和resultValue（length是一样的,如果有多个结果的话)
*输出：
* （1）可以分别获得键值集合来处理，
* （2）使用map类的内部Entry接口，来获得条目的键和值
*疑问：(值相等的时候，提取到集合里会不会被合并)
 */