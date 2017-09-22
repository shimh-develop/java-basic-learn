package concurrent.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CustomerThreadPool1 {
	
	public static void main(String[] args) throws InterruptedException {
		ThreadPool threadPool = new ThreadPool(2,3);
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("任务1执行了。。。");
				try {
					TimeUnit.MILLISECONDS.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("任务1执行结束。。。");
			}
		});
		
		TimeUnit.MILLISECONDS.sleep(4000);
		threadPool.stop();
	}
	
}
class ThreadPool{
	private BlockingQueue<Runnable> taskQueue = null;
	private List<PoolThread> threads = new ArrayList<PoolThread>();
	private boolean isStopped = false;

	public ThreadPool(int noOfThreads, int maxNoOfTasks) {
		taskQueue = new ArrayBlockingQueue<Runnable>(maxNoOfTasks);

	    for (int i=0; i<noOfThreads; i++) {
	      threads.add(new PoolThread(taskQueue));
	    }
	    for (PoolThread thread : threads) {
	      thread.start();
	    }
	}

	public synchronized void  execute(Runnable task) {
		if(this.isStopped) 
			throw new IllegalStateException("ThreadPool is stopped");
		this.taskQueue.offer(task);
	}

	public synchronized boolean stop() {
		this.isStopped = true;
	    for (PoolThread thread : threads) {
	      //thread.stop();
	    	thread.toStop();
	    }
	    return this.isStopped;
	}
}
class PoolThread extends Thread {

	private BlockingQueue<Runnable> taskQueue = null;
	private boolean isStopped = false;

	public PoolThread(BlockingQueue<Runnable> queue) {
		taskQueue = queue;
	}

	public void run() {
		while (!isStopped()) {
	      try {
	        Runnable runnable =taskQueue.take();
	        runnable.run();
	      } catch(Exception e) {
	    	  // 写日志或者报告异常,
	    	  // 但保持线程池运行.
	      }
		}
	}

	public synchronized void toStop() {
		isStopped = true;
		//this.interrupt(); 
		this.stop();
	}

	public synchronized boolean isStopped() {
		return isStopped;
	}
}
