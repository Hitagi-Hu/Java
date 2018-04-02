
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountWithoutSync {
    private static Account account = new Account();

    public static void main(String[] args){
        ExecutorService executor = Executors.newCachedThreadPool();

        //Create and lunch 100 threads
        for (int i = 0; i < 100; i++){
            executor.execute(new AddAPennyTask());
        }
        executor.shutdown();

        //Wait until all tasks are finished
        while (!executor.isTerminated()){}
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
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount){
            int newBalance = balance + amount;

            try {
                Thread.sleep(1);
            }
            catch (InterruptedException ex){
            }
            balance = newBalance;
        }
    }
}
