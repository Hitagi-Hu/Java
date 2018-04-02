package sort;

public class InsertionSort {

    public static <E extends Comparable<E> > void insertionSort(E[] list, int left, int right){
        for(int i = left + 1; i <= right; i++){   //从要排序的第二个元素开始插入
            E currentElement = list[i];   //待插入元素
            int j;
            for (j = i; j > left && list[j - 1].compareTo(currentElement) > 0; j--){     //从后往前查找：如果前面的元素比待插入元素大，把大元素往后移一位
                list[j] = list[j - 1];  //大于currentElement的元素后移一位
            }
            list[j] = currentElement; //插入
        }
    }
    /**Another insertionSort method*/
    public static <E extends Comparable<E> > void insertionSort(E[] list){
        for(int i = 1; i < list.length; i++){
            E currentElement = list[i];
            int j;
            for(j = i; j > 0 && list[j - 1].compareTo(currentElement) > 0; j--)
                list[j] = list[j - 1];
            list[j] = currentElement;
        }
    }
    public static void main(String[] args){
        Integer[] list = {6, 4, 5, 2, 3, 1, 10, 5};
        /*int length = list.length;
        insertionSort(list, 0, length -1);*/
        insertionSort(list);
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");

    }
}
