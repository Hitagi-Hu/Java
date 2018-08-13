package sort;
/*
* 归并排序
* */
public class MergeSort {
    private static <E extends Comparable<E> > void mergeSort(E[] list, E[] temp, int left, int right){
        if(left < right){
            int middle = (left + right) / 2;

            //merge sort the first half
            mergeSort(list, temp, left, middle);

            //merge sort the second half
            mergeSort(list, temp,middle + 1, right);

            //merge
            merge(list, temp, left, middle + 1, right);
        }
    }
    private static <E extends Comparable<E> > void merge(E[] list, E[] temp, int leftPos, int rightPos, int rightEnd){
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        //numbersOfChar loop
        while(leftPos <= leftEnd && rightPos <= rightEnd){
            if(list[leftPos].compareTo(list[rightPos]) <= 0)
                temp[tempPos++] = list[leftPos++];
            else
                temp[tempPos++] = list[rightPos++];
        }

        while (leftPos <= leftEnd)   //copy rest of first half
            temp[tempPos++] = list[leftPos++];

        while (rightPos <= rightEnd) //copy rest of second half
            temp[tempPos++] = list[rightPos++];

        //Copy temp array back
        for(int i = 0; i < numElements; i++, rightEnd--)
            list[rightEnd] = temp[rightEnd];
    }
    public static <E extends Comparable<E> > void mergeSort(E[] list){
        E[] temp = (E[])new Object[list.length];               //不能直接创建泛型实例，因为运行时E是不可用的。要先创建object型实例，再进行强制类型转换
        mergeSort(list, temp, 0, list.length - 1);
    }


    //test
    public static void main(String[] args){
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        mergeSort(list);
        for(int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
    }


}
