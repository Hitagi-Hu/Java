public class TaskThreadDemo {
    public static void main(String[] args) {
        // Create tasks
        Runnable printA = new PrintChar('a', 10000);
        Runnable printB = new PrintChar('b', 100);
        Runnable print100 = new PrintNum(100);

        // Create threads
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();
        /*printA.run();
        printB.run();
        print100.run();*/
        //直接调用run（）方法会顺序执行

    }
}

    // Task for printing a character by a specified number of times
    class PrintChar implements Runnable{
        private char charToPrint;
        private int times;

        public PrintChar(char c, int t){
            charToPrint = c;
            times = t;
        }

        @Override
        public void run(){
            for (int i = 0; i < times; i++){
                System.out.print(charToPrint);
            }
        }
    }

    // Task for printing numbers form 1 to a given n
    class PrintNum implements Runnable{
        private int lastNum;

        public PrintNum(int n){
            lastNum = n;
        }
        @Override
        public void run(){
            for (int i = 1; i <= lastNum; i++){
                System.out.print(" " + i);
            }
        }
    }

