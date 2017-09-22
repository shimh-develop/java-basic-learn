package java8.stream;

import java.util.Optional;
import java.util.stream.Stream;

public class Optional1 {
	public static void main(String[] args) {
		Optional<String> optional = Optional.of("1");	//参数不能为null
		Optional<String> optional2 = Optional.ofNullable(null);
		
		boolean isPresent = optional.isPresent();
		boolean isPresent2 = optional2.isPresent();
		print(isPresent); //true
		print(isPresent2); //false
		
		print(optional.get()); //1
		//print(optional2.get()); // 抛错
		
		print(optional.orElse("2")); //1
		print(optional2.orElse("2")); //2
		
		print(optional.toString()); // Optional[1]
		print(optional2.toString()); // Optional.empty
		
	}
	public static <E> void print(E e) {
		if (e instanceof Stream) {
			((Stream) e).forEach(System.out::println);
		} else {
			System.out.println(e.toString());
		}
	}
}	
