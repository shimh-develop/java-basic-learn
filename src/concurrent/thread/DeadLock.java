package concurrent.thread;

/**
 * @author: shimh
 * @create: 2019年10月
 **/
public class DeadLock {

    static Object l1 = new Object();
    static Object l2 = new Object();

    public static void main(String[] args) {

        Runnable r1 = () -> {

            synchronized (l1) {
                System.out.println(Thread.currentThread().getName() + " l1...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(l2) {
                    System.out.println(Thread.currentThread().getName() + " l2");
                }

            }

        };

        Runnable r2 = () -> {

            synchronized (l2) {
                System.out.println(Thread.currentThread().getName() + " l2...");
                synchronized(l1) {
                    System.out.println(Thread.currentThread().getName() + " l1");
                }

            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();






    }




}
