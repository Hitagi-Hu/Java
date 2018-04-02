import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithSyncUsingLock {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        //Create and lunch 100 threads
        for (int i = 0; i < 100; i++) {
            executor.execute(new AddAPennyTask());
        }
        executor.shutdown();

        //Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }
        System.out.println("What is balance? " + account.getBalance());
    }

    /**A thread for adding a penny to the account*/
    private static class AddAPennyTask implements Runnable {
        public void run(){
            account.deposit(1);
        }
    }

    /**Inner class for account*/
    private static class Account {
        private static Lock lock = new ReentrantLock();
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount){
            lock.lock();
            try {
                int newBalance = balance + amount;
                Thread.sleep(1);
                balance = newBalance;
            }

            catch (InterruptedException ex){
            }
            finally {
                lock.unlock();
            }
        }
    }
}


