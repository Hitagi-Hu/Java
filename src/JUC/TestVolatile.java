package JUC;

public class TestVolatile {
    public static void main(String[] args){
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while (true){
            if (td.isFlag()){
                System.out.println("-----------");
                break;
            }
        }

        int i = 10;
        i = i++;
        /*
        j = i++
        * 读> int temp = i
        * 改> i = i + 1
        * 写> j = temp*/
        System.out.println(i); //i = 10
    }
}

class ThreadDemo implements Runnable {
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
        flag = true;
        System.out.println("flag=" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}