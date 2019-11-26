package concurrent.blockingqueue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author: shimh
 * @create: 2019年11月
 **/
public class Test {

    public static void main(String[] args) {

        int a = 1;
        int b = 2;
        System.out.println(a == (a = b));

        //SynchronousQueue
        //LinkedTransferQueue
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
