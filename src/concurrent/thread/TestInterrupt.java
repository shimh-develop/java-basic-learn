package concurrent.thread;

/**
 * @author: shimh
 * @create: 2019年10月
 **/
public class TestInterrupt {

    public static void main(String[] args) throws Exception{

        test4();



    }

    /**
     * Runnable状态不受 interrupt 影响
     */
    public static void test1() {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("test1 执行了。。。。");
            }
        });
        thread.start();
        thread.interrupt();
    }

    /**
     * Runnable状态不受 interrupt 影响 自己根据 isInterrupted 显示判断
     *
     * @throws Exception
     */
    public static void test2() throws Exception{
        class Task extends Thread {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    System.out.println("test2 执行了。。。。");
                }
            }
        }
        Task thread = new Task();
        thread.start();
        Thread.currentThread().sleep(2000);
        System.out.println("interrupt...");
        thread.interrupt();
    }

    /**
     * WAITING/TIMED_WAITING 状态下抛出异常
     * @throws Exception
     */
    public static void test3() throws Exception{
        class Task extends Thread {
            @Override
            public void run() {
                System.out.println("test3 执行了。。。。");
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("test3 执行完了。。。。");
            }
        }
        Task thread = new Task();
        thread.start();
        Thread.currentThread().sleep(2000);
        System.out.println("interrupt...");
        thread.interrupt();
    }

    /**
     * 同 test3
     * @throws Exception
     */
    public static void test4() throws Exception{
        class Task extends Thread {
            @Override
            public void run() {
                System.out.println("test4 执行了。。。。");
                synchronized (TestInterrupt.class) {
                    System.out.println("test4 获取锁");
                    try {
                        TestInterrupt.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("test4 释放锁");
                }
                System.out.println("test4 执行完了。。。。");
            }
        }
        synchronized (TestInterrupt.class) {
            Task thread = new Task();
            thread.start();
            Thread.currentThread().sleep(2000);
            System.out.println("interrupt...");
            thread.interrupt();
            Thread.currentThread().sleep(2000);
            System.out.println("main end");
        }

    }




}

class TaskA implements Runnable {

    @Override
    public void run() {
        System.out.println("TaskA 执行了。。。。");
    }
}
