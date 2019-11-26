package concurrent.thread;

/**
 * @author: shimh
 * @create: 2019年10月
 **/
public class TestJoin {

    public static void main(String[] args) throws Exception{

        test3();

    }

    /**
     *
     */
    public static void test1() throws Exception{
        Thread thread = new Thread(() -> {
            System.out.println("test1 执行了。。。。");
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test1 执行了 结束了。。。。");
        });
        thread.start();
        thread.join();
        System.out.println("main 结束了。。。。");
    }

    /**
     * 可以看到 thread.join(); 释放的是thread这把锁
     * @throws Exception
     */
    public static void test2() throws Exception{
        class Task extends Thread {
            @Override
            public void run() {
                System.out.println("test2 执行了。。。。");
                synchronized (this) {
                    System.out.println("test2 获取锁");
                    try {
                        Thread.currentThread().sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("test2 执行完了。。。。");
            }
        }
        Task thread = new Task();

        synchronized (thread) {
            thread.start();
            Thread.currentThread().sleep(2000);
            System.out.println("join...");
            thread.join();
            System.out.println("main 结束了。。。。");
        }

    }

    /**
     * 超时时间
     * @throws Exception
     */
    public static void test3() throws Exception{
        class Task extends Thread {
            @Override
            public void run() {
                System.out.println("test3 执行了。。。。");
                    System.out.println("test3 获取锁");
                try {
                    Thread.currentThread().sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("test3 执行完了。。。。");
            }
        }
        Task thread = new Task();

        thread.start();
        thread.join(1000);
        System.out.println("main 结束了。。。。");

    }
}


