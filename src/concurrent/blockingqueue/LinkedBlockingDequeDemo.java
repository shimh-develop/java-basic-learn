package concurrent.blockingqueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeDemo {
	/**
	 * 
	 * 功能说明：
	 * 在线程既是一个队列的生产者又是这个队列的消费者的时候可以使用到 BlockingDeque。
	 * 如果生产者线程需要在队列的两端都可以插入数据，消费者线程需要在队列的两端都可以移除数据。
	 * @throws Exception 
	 *
	 */
	public static void main(String[] args) throws Exception {
		//BlockingDeque
		//LinkedBlockingDeque
		
		
		BlockingDeque<String> deque = new LinkedBlockingDeque<String>();  
		  
		deque.addFirst("1");  
		deque.addLast("2");  
		
		System.out.println(deque.takeFirst());
		System.out.println(deque.takeLast());
	}
}
