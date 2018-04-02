package sort;
/*
* 快速排序
* */
public class QuickSort {
    private static void quickSort(int[] list, int first, int last){
        if(last > first){
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    //partition the array list[first .. last]
    private static int partition(int[] list, int first, int last){ //划分要排列的数组并返回选定的中心点下标
        int pivot = list[first]; // Choose the first element as the pivot(center point)
        int low = first + 1; //Index for forward search
        int high = last; // Index for backward search
        while (high > low){
            //search forward form left
            while (low <= high && list[low] <= pivot)
                low++;

            //search backward from right
            while (low <= high && list[high] > pivot)
                high--;

            //Swap two elements in the list
            if(high > low){
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;
        //Swap pivot with list[high]a
        if(pivot > list[high]){
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else
            return first;

    }

    public static void quickSort(int[] list){
        quickSort(list, 0, list.length - 1);
    }

    //test
    public static void main(String[] args){
        int[] list = {1, 3, 5, 2, 4, 6};
        quickSort(list);
        for(int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
    }
}
