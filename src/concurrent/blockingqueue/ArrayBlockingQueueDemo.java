package concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDemo {
	public static void main(String[] args) throws Exception {
		//BlockingQueue
	    //ArrayBlockingQueue
	    //DelayQueue
	    //LinkedBlockingQueue
	    //PriorityBlockingQueue
	    //SynchronousQueue
		
		arrayBlockingQueue();
	}
	/**
	 * 
	 * 功能说明：ArrayBlockingQueue 是一个有界的阻塞队列，其内部实现是将对象放到一个数组里。
	 * 有界也就意味着，它不能够存储无限多数量的元素。它有一个同一时间能够存储元素数量的上限。
	 * 你可以在对其初始化的时候设定这个上限，但之后就无法对这个上限进行修改了。
	 * ArrayBlockingQueue 内部以 FIFO(先进先出)的顺序对元素进行存储。
	 * 队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
	 *
	 * @throws Exception
	 *
	 */
	private static void arrayBlockingQueue() throws Exception{
		ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(4);
		//队列满时 add 抛异常  offer 返回false put 阻塞
		abq.add("1");
		abq.offer("2");
		abq.put("3");
		abq.put("4");
		
		System.out.println(abq.remainingCapacity());//剩余空间
		//队列空时 remove 抛异常 poll 返回null take 阻塞
		abq.remove();
		abq.poll();
		abq.take();
		abq.remove();
		// 返回第一个元素 element 为空时抛异常 peek 返回null
		abq.element();
		abq.peek();
		System.out.println(abq.toString());
		
	}
}
