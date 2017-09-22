package concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
	
	/**
	 * 
	 * 功能说明：
	 * 就是一个所有线程必须等待的一个栅栏，直到所有线程都到达这里，然后所有线程才可以继续做其他事情
	 *
	 */
	public static void main(String[] args) throws Exception {
		CyclicBarrier cb = new CyclicBarrier(2,new Runnable() {
			
			@Override
			public void run() {
				System.out.println("任务1和2 中间阶段完成");
				
			}
		}); 
		
		Thread t1 = new Thread(new TaskService("任务1",cb));
		t1.start();
		TimeUnit.MILLISECONDS.sleep(2000);
		Thread t2 = new Thread(new TaskService("任务2",cb));
		t2.start();
		
		//cb.reset();如果某个任务线程出现错误，可以重置计数器，（但此时有阻塞的线程时，会抛错）并让线程们重新执行一次。
		TimeUnit.MILLISECONDS.sleep(2000);
		cb.reset();
		new Thread(new TaskService("任务1",cb)).start();
		new Thread(new TaskService("任务2",cb)).start();
		
	}
	
	static class TaskService implements Runnable{
		
		private String name;
		private CyclicBarrier cb;
		
		TaskService(String name,CyclicBarrier cb){
			this.name = name;
			this.cb = cb;
		}
		
		@Override
		public void run() {
			System.out.println(this.name + "执行了");
			try {
				cb.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(this.name + "执行完成了");
		}
	}
}
