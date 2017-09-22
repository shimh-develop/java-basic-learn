package concurrent.blockingqueue;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {
	/**
	 * 
	 * 功能说明：
	 *
	 * PriorityBlockingQueue 是一个无界的并发队列。
	 * 它使用了和类 java.util.PriorityQueue 一样的排序规则。
	 * 你无法向这个队列中插入 null 值。
	 * 所有插入到 PriorityBlockingQueue 的元素必须实现 java.lang.Comparable 接口。
	 * 因此该队列中元素的排序就取决于你自己的 Comparable 实现。
	 */
	public static void main(String[] args) throws Exception {
		PriorityBlockingQueue<String> pbq   = new PriorityBlockingQueue<String>();  
		  
		pbq.put("Value"); 
		pbq.put("Aalue"); 
		pbq.put("Balue");
	  
		//迭代时无序 Aalue Value Balue
	    Iterator<String> it = pbq.iterator();
	    while(it.hasNext()){
	    	System.out.println(it.next());
	    }
	    
	    System.out.println(pbq.take());	//Aalue
	    System.out.println(pbq.take()); //Balue
	    System.out.println(pbq.take()); //Value
	}
}
