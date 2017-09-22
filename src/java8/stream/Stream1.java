package java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Stream1 {
	public static void main(String[] args) {
		//Collection  Map

		//List<String> world = Arrays.asList("ab","ca","defb","ghdt","yuu");

		List<String> world = new ArrayList<String>();
		world.add("ab");
		world.add("ca");
		world.add("defb");
		world.add("ghdt");
		world.add("ayuu");

		// Match
		boolean allLengthGt5 = world.stream().allMatch(a -> a.length() > 5);
		print(allLengthGt5); //false

		boolean anyLengthGt1 = world.stream().anyMatch(a -> a.length() > 1);
		print(anyLengthGt1); //true

		boolean noneLengthGt4 = world.stream().noneMatch(a -> a.length() > 4);
		print(noneLengthGt4); //true 

		// Filter
		world.stream().filter(a -> a.contains("a")).forEach(System.out::println); // ab ca 不影响world
		
		// count
		long count = world.stream().count();
		print(count); //5
		
		long count2 = world.stream().filter(a -> a.startsWith("a")).count();
		print(count2); //2
		
		// limit
		world.stream().limit(3).forEach(System.out::println); //ab, ca, defb
		
		// sorted
		world.stream().sorted().forEach(System.out::println); //ab ayuu ca defb ghdt
		
		world.stream().sorted((a,b) -> b.length() - a.length()).forEach(System.out::println); 
		
		// map
		world.stream().map((a) -> a.toUpperCase()).forEach(System.out::println); //AB CA DEFB GHDT AYUU
		
		List<String> number = Arrays.asList("1","2","3","4","5");
		int sum = number.stream().mapToInt(Integer :: valueOf).sum();
		print(sum); //15
		
		// reduce
		Optional<String> result = number.stream().reduce((a, b) -> a + "-" + b);
		print(result.get()); //1-2-3-4-5
		
		//world.removeIf((a) -> a.length() > 2);
		//print(world);

	}

	public static <E> void print(E e) {
		if (e instanceof Stream) {
			((Stream) e).forEach(System.out::println);
		} else {
			System.out.println(e.toString());
		}
	}
}
