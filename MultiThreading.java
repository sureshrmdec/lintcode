//import java.util.concurrent;

class MyThread extends Thread {
    public MyThread() {
    }

    public void run() {
        for (int i = 0; i < 3; ++i)
            System.out.println("[ID " + this.getId() + "] " + i);
    }
}

public class MultiThreading {


    public static void main(String[] args) {
        System.out.println("MultiThreading test.");
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();

        t1.start();
        t2.start();
        t3.start();
    }
}
