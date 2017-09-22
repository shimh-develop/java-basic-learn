package concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Main {
	
	/**
	 * 
	 * 功能说明：
	 *
	 * ountDownLatch这个类能够使一个或多个线程等待其他线程完成各自的工作后再执行。
	 * 例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。
	 * 
	 * 
	 * 调用thread.join() 方法必须等thread 执行完毕，当前线程才能继续往下执行。
	 * 而CountDownLatch通过计数器提供了更灵活的控制，
	 * 只要检测到计数器为0当前线程就可以往下执行而不用管相应的thread是否执行完毕
	 */
	public static void main(String[] args) throws Exception {
		CountDownLatch cdl = new CountDownLatch(2);
		
		System.out.println(cdl.getCount()); //2
		
		new Thread(new TaskService(cdl,"task1")).start();
		new Thread(new TaskService(cdl,"task2")).start();
		
		//可以让多个线程进行等待  
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					cdl.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("另一个等待");
				
			}
		}).start();
		cdl.await();
		System.out.println(cdl.getCount());	//0
		
		System.out.println("所有任务执行完成");
	}
}
