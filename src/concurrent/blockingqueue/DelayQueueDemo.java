package concurrent.blockingqueue;

import java.util.Calendar;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * *****************************************************************
 * Created on 2017年9月19日 下午2:39:49
 * @author shimh
 * DelayQueue队列没有大小限制，因此向队列插数据不会阻塞
 * DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。否则线程阻塞。
 * DelayQueue中的元素不能为null
 * DelayQueue内部是使用PriorityQueue实现的。compareTo()比较后越小的越先取出来。
 */
public class DelayQueueDemo {

	public static void main(String[] args) throws Exception {
		DelayQueue<myDelayed> dq = new DelayQueue<myDelayed>();
		
		dq.add(new myDelayed(5000));
		dq.add(new myDelayed(3000));
		//最快过时的在最前面
		System.out.println(dq);
        while (true) {
        	Delayed  poll = dq.poll();
            System.out.println("poll result \n" + poll);
            Thread.sleep(2000);
            
            if(dq.isEmpty()) break;
        }
	}
	
	static class myDelayed implements Delayed {

        private final long delay;

        private final long expire;

        public myDelayed(long delay) {
            this.delay = delay;
            expire = Calendar.getInstance().getTimeInMillis() + delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return expire - Calendar.getInstance().getTimeInMillis();
        }
        @Override
        public int compareTo(Delayed o) {
        	return (int) (this.delay - o.getDelay(TimeUnit.MILLISECONDS));
        }
        @Override
        public String toString() {
            return "myDelayed is " + delay + "\n" + Calendar.getInstance().getTime().toString() + "\n elapsed time is " + getDelay(TimeUnit.DAYS);
        }

    }
}
