package concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: shimh
 * @create: 2019年10月
 **/
public class TestLockSupport {
    /**
     * 使用 LockSupport 实现 wait notify效果（类似 join）
     * @param args
     */
    public static void main(String[] args) {
        Thread main = Thread.currentThread();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread start");
            System.out.println("before unpark blocker is :" + LockSupport.getBlocker(main));
            LockSupport.unpark(main);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after unpark blocker is :" + LockSupport.getBlocker(main));
            System.out.println("thread end");

        });
        thread.start();
        System.out.println("main before park");

        LockSupport.park("i am blocker");

        System.out.println("main after park");

    }
}
