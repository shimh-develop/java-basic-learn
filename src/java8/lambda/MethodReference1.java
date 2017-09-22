package java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MethodReference1 {
	/*
	 * 这个难理解。。。。copy代码
	 */
	public static void main(String[] args) {
		
		 Car car = Car.create( Car::new );
		 List< Car > cars = Arrays.asList( car );
		
		 cars.forEach( Car::collide );
		 
		 cars.forEach( Car::repair );
		 
		 final Car police = Car.create( Car::new );
		 cars.forEach( police::follow );
	}
}
class Car {
	
	/*Car(int a){
		
	}*/
	
    public static Car create( final Supplier< Car > supplier ) {
        return supplier.get();
    }              
         
    public static void collide( final Car car ) {
        System.out.println( "Collided " + car.toString() );
    }
         
    public void follow( final Car another ) {
        System.out.println( "Following the " + another.toString() );
    }
         
    public void repair() {   
        System.out.println( "Repaired " + this.toString() );
    }
}