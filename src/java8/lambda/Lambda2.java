package java8.lambda;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda2 {
	/*
	 * jdk FunctionInterface
	 */
	public static void main(String[] args) throws Exception {
		List<String> names = Arrays.asList("a", "d", "b", "c");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        print(names);
        
        Collections.reverse(names);
        print(names);
        
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        print(names);
        
        //Predicate
        Predicate<String> predicate = (a) -> a.startsWith("a");
        print(predicate.test("abc")); //true
        print(predicate.test("bc")); //false
        print(predicate.negate().test("abc")); //false
        print(predicate.and((b) -> b.length() >3).test("abcd")); //true
        print(predicate.and((b) -> b.length() >3).test("abc")); //false
        
        //Function String > Integer
        Function<String,Integer> function = (a) -> Integer.valueOf(a);
        print(function.apply("123"));
        
        print(function.andThen((b) -> String.valueOf(b)).apply("456"));
        
        // Suppliers
        Supplier<Double> personSupplier = () -> Math.random();
        print(personSupplier.get()); // 
        
        // Consumers
        Consumer<String> greeter =  (a) -> print(a.substring(2));
        greeter.accept("abcdef");
        
        // Runnables
        Runnable runnable = () -> print(UUID.randomUUID());
        runnable.run();

        // Callables
        Callable<UUID> callable = UUID::randomUUID;
        print(callable.call());
	}
	
	
	public static <E> void print(E e){
		System.out.println(e.toString());
	}
}
