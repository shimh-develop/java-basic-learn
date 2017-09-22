package java8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.stream.Stream;

public class LocalDate1 {
	public static void main(String[] args) {
		
		LocalDate now = LocalDate.now();
		print(now); //2017-09-22
		print(now.getDayOfMonth()); //22
		print(now.getDayOfWeek()); //FRIDAY
		print(now.getDayOfYear()); //265
		print(now.getEra()); //CE
		print(now.getMonthValue()); //9
		print(now.getYear()); //2017


		LocalDate yesterday =  now.minusDays(1);
		print(yesterday); //2017-09-21
		
		LocalDate lastMonth =  now.minusMonths(1);
		print(lastMonth); //2017-08-22
		
		LocalDate week =  now.minusWeeks(1);
		print(week); //2017-09-15
		
		LocalDateTime time = now.atTime(12, 9);
		print(time);
		
		
		
		
		
		
		
		//print(now);
		
	}
	
	public static <E> void print(E e) {
		if (e instanceof Stream) {
			((Stream) e).forEach(System.out::println);
		} else {
			System.out.println(e.toString());
		}
	}
}
