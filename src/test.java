public class test {
    public static void main(String args[]) {

        Thread t = new Thread() {

            public void run() {
                pong();
            }
        };

        t.start();
//        t.run();
        for (int i = 0; i < 20; i++)
            System.out.println("ping");

    }

    static void pong() {
        for (int i = 0; i < 20; i++)
            System.out.println("pong");

    }
}
