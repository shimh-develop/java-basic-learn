package concurrent.thread;

/**
 * @author: shimh
 * @create: 2019年10月
 **/
public class TestWaitNotify {
    static int i = 1;

    /**
     * 一个线程打印 135 一个打印 246  交替打印直到100
     *
     * @param args
     */
    public static void main(String[] args) {
        Object o = new Object();

        Runnable r = () -> {
            synchronized (o) {
                while (i <= 100) {
                    o.notify();
                    System.out.println(Thread.currentThread().getName() + ":"+ i);
                    i++;
                    try {
                        if (i < 100) {
                            o.wait();

                        }else {
                            System.out.println(i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

//        Runnable r = () -> {
//            while (i <= 100) {
//                synchronized (o) {
//                    o.notify();
//                    System.out.println(Thread.currentThread().getName() + ":" + i);
//                    i++;
//                    try {
//                        if (i < 100) {
//                            o.wait();
//
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
        Thread a = new Thread(r);
        Thread b = new Thread(r);
        a.start();
        b.start();


    }
}
