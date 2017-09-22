package java8.date;

import java.time.Clock;
import java.util.Date;
import java.util.stream.Stream;

public class Clock1 {
	public static void main(String[] args) {
		
		Clock clock = Clock.systemDefaultZone();
		
		print(clock.millis());	//1506049805308
		print(System.currentTimeMillis()); //1506049805308
		
		print(Date.from(clock.instant()));
		
	}
	
	public static <E> void print(E e) {
		if (e instanceof Stream) {
			((Stream) e).forEach(System.out::println);
		} else {
			System.out.println(e.toString());
		}
	}
}
