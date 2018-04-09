package MultiThreads;
/**
 * 创建线程的两种方法：继承Thread类或实现Runnable接口*/
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {
    private static Buffer buffer = new Buffer();
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ProducerTask());
        executor.execute(new ConsumerTask());
        executor.shutdown();
    }

    private static class ProducerTask extends Thread {
        public void run(){
            try {
                int i = 1;
                while (true){
                    System.out.println("Producer writes " + i);
                    buffer.write(i++);
                    Thread.sleep((int)(Math.random() * 10000));
                }
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
    private static class ConsumerTask implements Runnable {
        public void run(){
            try {
                while (true){
                    System.out.println("\t\t\tConsumer reads " + buffer.read());
                    Thread.sleep((int)(Math.random() * 10000));
                }
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
    /**Inner class*/
    private static class Buffer {
        private static final int CAPACITY = 1;
        private LinkedList<Integer> queue = new LinkedList<>();
        //Lock
        private static Lock lock = new ReentrantLock();
        //Conditions
        private static Condition notEmpty = lock.newCondition();
        private static Condition notFull = lock.newCondition();

        public void write(int value){
            lock.lock();
            try{
                while (queue.size() == CAPACITY){  //Full,wait for notFull condition to go on
                    System.out.println("Wait for notFull condition");
                    notFull.await();  //当前线程等待直到被唤醒
                }
                queue.add(value);
                notEmpty.signal();
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }

        public int read() {
            int value = 0;
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    System.out.println("\t\t\tWait for notEmpty condition");
                    notEmpty.await();
                }
                value = queue.remove();
                notFull.signal();
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
            finally {
                lock.unlock();
                return value;
            }
        }
    }
}
