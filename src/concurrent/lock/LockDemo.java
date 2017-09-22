package concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
	
	private final Lock lock = new ReentrantLock();
	private static ExecutorService executor = Executors.newFixedThreadPool(2);
	
	public static void main(String[] args) {
		//Lock
		LockDemo demo = new LockDemo();
		Runnable run = new Runnable() {
			@Override
			public void run() {
				demo.lockDemo();
				//demo.tryLockDemo();
				/*try {
					demo.lockInterruptiblyDemo();
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
		};
		executor.execute(run);
		executor.execute(run);
		executor.shutdown();
	}
	
	public void lockDemo(){
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "执行了。。。。");
		try{
			//doSomething
			TimeUnit.MILLISECONDS.sleep(2000);
		}catch(Exception e){
			
		}finally{
			System.out.println(Thread.currentThread().getName() + "执行完成。。。。");
			lock.unlock();
		}
	}
	
	/**
	 * 
	 * 功能说明：
	 * tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true；
	 * 如果获取失败（即锁已被其他线程获取），则返回false，也就是说，
	 * 这个方法无论如何都会立即返回（在拿不到锁时不会一直在那等待）。
	 * tryLock(long time, TimeUnit unit)方法和tryLock()方法是类似的，
	 * 只不过区别在于这个方法在拿不到锁时会等待一定的时间，在时间期限之内如果还拿不到锁，就返回false，
	 * 同时可以响应中断。如果一开始拿到锁或者在等待期间内拿到了锁，则返回true。
	 *
	 */
	public void tryLockDemo(){
		if(lock.tryLock()){
			System.out.println(Thread.currentThread().getName() + "执行了。。。。");
			try{
				//doSomething
				TimeUnit.MILLISECONDS.sleep(2000);
			}catch(Exception e){
				
			}finally{
				System.out.println(Thread.currentThread().getName() + "执行完成。。。。");
				lock.unlock();
			}
		}else{
			System.out.println(Thread.currentThread().getName() + "获取锁失败。。。。");
		}
		
	}
	
	public void lockInterruptiblyDemo() throws Exception{
		lock.lockInterruptibly();
		System.out.println(Thread.currentThread().getName() + "执行了。。。。");
		try{
			//doSomething
			TimeUnit.MILLISECONDS.sleep(2000);
		}catch(Exception e){
			
		}finally{
			System.out.println(Thread.currentThread().getName() + "执行完成。。。。");
			lock.unlock();
		}
	}
}
