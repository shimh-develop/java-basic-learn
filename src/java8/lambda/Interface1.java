package java8.lambda;

public class Interface1 {
	
	@FunctionalInterface
	interface Star{
		
		void sing();
		
		default void eat(){
			print("i am eating");
		}
		
		static void sleep(){
			print("zzzzzzz");
		}
	}
	
	//@FunctionalInterface  函数式接口：只能有一个抽象方法
	interface StarCopy extends Star{
		void dance();
	}
	
	public static void main(String[] args) {
		Star jayZhou = new Star(){
			@Override
			public void sing() {
				print("快使用双截棍");
			}
		};
		
		jayZhou.sing();
		jayZhou.eat();
		//jayZhou.sleep(); 静态方法只能接口调用
		Star.sleep();
		
		StarCopy me = new StarCopy(){

			@Override
			public void sing() {
				print("wwwwwww");
			}
			@Override
			public void dance() {
				print("oooooo");
			}
		};
		me.eat();
		me.dance();
		me.sing();
		//StarCopy.sleep(); 静态不能继承
		
	}
	
	public static <E> void print(E e){
		System.out.println(e.toString());
	}
}
