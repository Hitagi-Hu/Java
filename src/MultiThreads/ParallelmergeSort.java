import Algorithm.sort.AnotherMergeSort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
public class ParallelmergeSort {
    public static void main(String[] args) {
        final int SIZE = 7000000;
        int[] list1 = new int[SIZE];
        int[] list2 = new int[SIZE];

        for (int i = 0; i < list1.length; i++)
            list1[i] = list2[i] = (int)(Math.random() * 10000000);
        long startTime = System.currentTimeMillis();
        parallelAnotherMergeSort(list1);
        long endTime = System.currentTimeMillis();
        System.out.println("\nParallel time with " + Runtime.getRuntime().availableProcessors() + " precessors is " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        AnotherMergeSort.mergeSort(list2);
        endTime = System.currentTimeMillis();
        System.out.println("\nSequential time is " + (endTime - startTime) + " milliseconds");
    }
    /**Key codes*/
    public static void parallelAnotherMergeSort(int[] list){
        RecursiveAction mainTask = new SortTask(list);
        ForkJoinPool pool = new ForkJoinPool(); //使用所有可用处理器创建ForkJoinPool
        pool.invoke(mainTask);
    }
    private static class SortTask extends RecursiveAction {
        private final int THRESHOLD = 500;    //临界值,数目大于THRESHOLD使用并行合并排序，小于直接使用Arrays类的静态方法排序
        private int[] list;

        SortTask(int[] list) {
            this.list = list;
        }
        @Override
        protected void compute() {
            if(list.length < THRESHOLD)
                Arrays.sort(list);
            else {
                int[] firstHalf = new int[list.length / 2];
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

                int secondHalfLength = list.length - list.length / 2;
                int[] secondHalf = new int[secondHalfLength];
                System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);

                invokeAll(new SortTask(firstHalf), new SortTask(secondHalf));

                AnotherMergeSort.merge(firstHalf, secondHalf, list);
            }
        }
    }
}
