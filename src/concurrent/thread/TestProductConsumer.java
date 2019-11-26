package concurrent.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shimh
 * @create: 2019年10月
 **/
public class TestProductConsumer {

    static List<String> store = new ArrayList<>();
    static int size = 3;

    public static void main(String[] args) {
        Object o = new Object();

        Runnable add = () -> {
            while (true) {
                synchronized (o) {
                    while (store.size() >= 3) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    store.add("1");
                    System.out.println(Thread.currentThread().getName() + "添加1 现在数量： " + store.size());
                    o.notifyAll();
                }
            }
        };

        Runnable remove = () -> {
            while (true) {
                synchronized (o) {
                    while (store.size() <= 0) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    store.remove("1");
                    System.out.println(Thread.currentThread().getName() + "减少1 现在数量： " + store.size());
                    o.notifyAll();
                }
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(add).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(remove).start();
        }




    }



}
