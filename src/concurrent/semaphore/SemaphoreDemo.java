package concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
	
	/**
	 * 
	 * 功能说明：
	 * Semaphore是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源。
	 * 可以用于做流量控制，特别公用资源有限的应用场景，比如数据库连接。
	 *
	 */
	public static void main(String[] args) {
		final Semaphore semp = new Semaphore(5);  
		ExecutorService exec = Executors.newCachedThreadPool(); 


        for (int index = 0; index < 20; index++) {
            final int NO = index;  
            Runnable run = new Runnable() {  
                public void run() {  
                    try { 
                    	System.out.println("Starting: " + NO);  

                    	semp.acquire();  
                        
                        System.out.println("Accessing: " + NO);  
                        TimeUnit.MILLISECONDS.sleep(2000);
                        
                    } catch (InterruptedException e) {  
                    	e.printStackTrace();
                    }finally {
                    	semp.release(); 
					}
                }  
            };  
            
            exec.execute(run);  
        }  
        
        exec.shutdown(); 
	}
}
