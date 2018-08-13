package Algorithm.sort;
/*
* package sort;
/*
* 改进的快速排序算法：
* 1) 三数中值分割法寻找枢纽点（pivot）
* 2）逻辑改进：先交换pivot到数组最后
* 3) 增加泛型匹配
*/




import java.util.stream.Stream;

import static sort.InsertionSort.insertionSort;

/*
    * CUTOFF = 10,10个数以上使用quickSort，否则使用insertionSort
    */
public class  QuickSort_modified<E extends Comparable>{
    private final static int CUTOFF = 10;
    private static <E extends Comparable<E> > void quickSort(E[] list, int left, int right){
        if(left + CUTOFF <= right ){
            E pivot = median3(list, left, right);
            int i = left, j = right - 1;  //or j = right - 2??
            for(; ;) {
                while (list[++i].compareTo(pivot) < 0) {
                }
                while (list[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swap(list, i, j);
                else
                    break;
            }
            swap(list, i, right - 1);
            quickSort(list, left, i - 1);  //Sort small elements
            quickSort(list, i + 1, right);          //Sort large elements
        }
        else //Do an insertion sort on the subArray
            insertionSort(list, left, right);
    }

    /*
    * Return median(中位数） of left, center, and right
    * place pivot at position right - 1
    * */
    private static <E extends Comparable<E> > E median3(E[] list, int left, int right){
        int center = (left + right) / 2;
        if(list[center].compareTo(list[left]) < 0)
            swap(list, center, left);
        if(list[right].compareTo(list[left]) < 0)
            swap(list, left, right);
        if (list[right].compareTo(list[center]) <0)
            swap(list, center, right);

        //place pivot at position right - 1
        swap(list, center, right - 1);
        return list[right - 1];
    }

    /*Swap two elements of a array*/
    private static <E extends Comparable<E> > void swap(E[] list, int index1, int index2){
        E tmp = list[index1];
        list[index1] = list[index2];
        list[index2] = tmp;
     }

    public static <E extends Comparable<E> > void quickSort(E[] list){
        quickSort(list, 0, list.length - 1);
    }

    /*Test method*/
    public static void main(String[] args){
        Integer[] list = {3, 5, 8, 2, 1, 3, 10, 8};
        quickSort(list);
        for (int aList : list) {
            System.out.print(aList + " ");
        }
    }
}

