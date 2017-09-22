package concurrent.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {
	/**
	 * 
	 * 功能说明：LinkedBlockingQueue 内部以一个链式结构(链接节点)对其元素进行存储。
	 * 如果需要的话，这一链式结构可以选择一个上限。如果没有定义上限，将使用 Integer.MAX_VALUE 作为上限。
	 * LinkedBlockingQueue 内部以 FIFO(先进先出)的顺序对元素进行存储。
	 * 队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
	 *
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		LinkedBlockingQueue<String> lbkq = new LinkedBlockingQueue<String>();
		
		lbkq.add("1");
		lbkq.add("2");
		
		System.out.println(lbkq.remove());
		System.out.println(lbkq.remove());
	}
}
