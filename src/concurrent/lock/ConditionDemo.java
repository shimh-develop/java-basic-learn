package concurrent.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class ConditionDemo {
	/**
	 * 
	 * 功能说明：
	 * 简单模拟阻塞队列
	 *
	 */
	public static void main(String[] args) {
		BlockList<String> list = new BlockList<String>(3);
		
		for(int i=0;i<3;i++){
			final int element = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while(true){
							list.put(Integer.toString(element));
							System.out.println(Thread.currentThread().getName() + "放入了"+ element);
							TimeUnit.MILLISECONDS.sleep(2000);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}).start();
		}
		
		for(int i=0;i<3;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while(true){
							String element = list.take();
							System.out.println(Thread.currentThread().getName() + "取出了"+ element);
							TimeUnit.MILLISECONDS.sleep(2000);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}).start();
		}
		
		
	}
}

class BlockList<E>{
	final List<E> items;
	private int capacity;
	
	final ReentrantLock lock;

	private final Condition notEmpty;

	private final Condition notFull;
	
	
	BlockList(int capacity){
		this.items = new LinkedList<E>();
		this.capacity = capacity;
		lock = new ReentrantLock();
		notEmpty = lock.newCondition();
		notFull =  lock.newCondition();
	}
	
	public void put(E e) throws Exception{
		lock.lock();
		try{
			while(capacity == items.size()) {
				notFull.await();
			}
			
			items.add(e);
			notEmpty.signal();
		}finally {
			lock.unlock();
		}
		
	}
	
	public E take() throws Exception{
		lock.lock();
		try{
			while(items.isEmpty()){
				notEmpty.await();
			}
			E e = ((LinkedList<E>)items).poll();
			notFull.signal();
			return e;
			
		}finally{
			lock.unlock();
		}
		
	}
	public int size(){
		return items.size();
	}
}