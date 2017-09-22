package java8.stream;

import java.util.stream.IntStream;

public class Stream3 {
	public static void main(String[] args) {
		
		
		IntStream.range(0, 10).forEach(System.out ::println); //0 ~ 9
		
		for (int i = 0; i < 10; i++) {
           System.out.println(i);
		}
		
		IntStream.builder().add(1).add(2).add(3).build().forEach(System.out ::println);;
		
	}
}
