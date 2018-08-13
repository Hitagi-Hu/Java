import org.junit.Test;
/*不使用等待唤醒机制:生产者进程运行太快就会在产品已满时仍然尝试生产.
生产者-消费者 类比为 数据的创建和销毁
*
*
* */
public class ProductorAndCusumer {


    public  static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Customer customer = new Customer(clerk);
        new Thread(producer,"生产者A").start();
        new Thread(customer,"消费者B").start();


    }
}

class Producer implements Runnable {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        int i = 0;
        for (; i < 20; i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }

    }
}

class Customer implements Runnable {
    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        int i = 0;
        for (; i < 10; i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.sale();
        }
    }
}

class Clerk {
    private int product = 0;

    //get
    public synchronized void get(){
        while (product >= 1){
            System.out.println("产品已满!");
            try {
                this.wait();
            } catch (InterruptedException e) {

            }
        }

            System.out.println(Thread.currentThread().getName() + " : " + ++product);
            this.notifyAll();

    }

    //sale
    public synchronized void sale() {
        while (product <= 0){
            System.out.println("产品缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {

            }
        }

            System.out.println(Thread.currentThread().getName() + " : " + --product);
            this.notifyAll();

    }
}
