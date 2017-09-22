package concurrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {
	
	/**
	 * 
	 * 功能说明：
	 * 提供一个同步点，在这个同步点两个线程可以交换彼此的数据。
	 * 这两个线程通过exchange方法交换数据， 如果第一个线程先执行exchange方法，
	 * 它会一直等待第二个线程也执行exchange，当两个线程都到达同步点时，这两个线程就可以交换数据，
	 * 将本线程生产出来的数据传递给对方。
	 *
	 */
	public static void main(String[] args) throws InterruptedException {
		Exchanger<String> changer = new Exchanger<String>();
		
		new Thread(new CheckA(changer)).start();
		
		TimeUnit.MILLISECONDS.sleep(2000); //CheckA会阻塞等待CheckB
		
		new Thread(new CheckB(changer)).start();
	}
	
	static class CheckA implements Runnable{
		private Exchanger<String> exchanger;
		CheckA(Exchanger<String> exchanger){
			this.exchanger = exchanger;
		}

		@Override
		public void run() {
			System.out.println("A check 开始");
			String result = "A";
			System.out.println("A 交换前 result=" + result);
			try {
				result = exchanger.exchange(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A 交换后 result=" + result);
		}
	}
	
	static class CheckB implements Runnable{
		private Exchanger<String> exchanger;
		CheckB(Exchanger<String> exchanger){
			this.exchanger = exchanger;
		}

		@Override
		public void run() {
			System.out.println("B check 开始");
			String result = "B";
			System.out.println("B 交换前 result=" + result);
			try {
				result = exchanger.exchange(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("B 交换后 result=" + result);
		}
	}
}
