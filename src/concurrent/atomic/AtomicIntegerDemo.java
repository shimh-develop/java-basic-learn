package concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
	public static void main(String[] args) {
		AtomicInteger i = new AtomicInteger();
		
		print(i.get());
		print(i.incrementAndGet());
		print(i.addAndGet(2));
		print(i.getAndAdd(2));
		print(i.decrementAndGet());
		
	}
	
	public static void print(int i){
		System.out.println(i);
	}
}
