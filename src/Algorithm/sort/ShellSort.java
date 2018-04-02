package sort;
/**
 * Shell Sort:
 * 1.)选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 * 2.)按增量序列个数k，对序列进行k 趟排序；
 * 3.)每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 * Shell增量：n/2, n/4, n/8, ..., 1,n位待排序数组长度
 * */
public class ShellSort {
    public static <E extends Comparable<E> > void ShellSort(E[] list){
        int i;
        for(int gap = list.length / 2; gap > 0; gap /= 2)
            for(int j = gap; j < list.length; j++){   //与插入排序类似，避免了明显的使用交换
            E tmp = list[j];
            for(i = j; i >= gap && tmp.compareTo(list[i - gap]) < 0; i -= gap)
                list[i] = list[i - gap];
            list[i] = tmp;
            }
    }

    /**Test method*/
    public static void main(String[] args){
        Integer[] list = {4, 6, 8, 5, 7, 9, 2, 3, 1, 0, 6, 8};
        ShellSort(list);
        for(int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
    }


}
