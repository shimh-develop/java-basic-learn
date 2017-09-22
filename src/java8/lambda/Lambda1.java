package java8.lambda;

import java.util.Random;

public class Lambda1 {
	
	@FunctionalInterface
	interface Operation {
	   int add(int a, int b);
	}
	@FunctionalInterface
	interface Operation2 {
		int echo(int a);
	}
	interface Operation3 {
		int random();
	}
	
	public static void main(String[] args) {

		//(parameters) -> expression
		//(parameters) ->{ statements;}
		
		/*
		 * Operation operation = new Operation(){
		 *		@Override
		 *		public int add(int a, int b) {
		 *			return a + b;
		 *		}
		 *	};
		 * 
		 */
		Operation operation = (int a, int b) -> a + b;
		print(operation.add(1, 2));
		
		Operation2 operation2 = a -> a;
		print(operation2.echo(4));
		
		Operation3 operation3 = () -> new Random().nextInt();
		print(operation3.random());
		
		Operation3 operation4 = () -> {
			print("测试{}时，要加return");
			return new Random().nextInt();
		};
		print(operation4.random());
	}
	
	public static <E> void print(E e){
		System.out.println(e.toString());
	}
}
