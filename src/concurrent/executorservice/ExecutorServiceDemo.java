package concurrent.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {
	public static void main(String[] args) throws Exception {
		//ExecutorService
		//ThreadPoolExecutor
	    //ScheduledThreadPoolExecutor
		//Executors
		threadPoolExecutor();
	}
	
	private static void  threadPoolExecutor() throws InterruptedException, ExecutionException{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		Future<?> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("线程1执行了---");
				try {
					TimeUnit.MILLISECONDS.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		System.out.println(future1.get()); //阻塞等待任务结束
		
		Future<String> future2 = executor.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println("线程2执行了---");
				
				return "ok";
			}
		});
		
		System.out.println(future2.get()); //阻塞等待任务结束
		
		System.out.println("最后结束");
		executor.shutdown();
	}
}
