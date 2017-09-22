package java8.stream;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream2 {
	public static void main(String[] args) {
		
		List<Person> person = new ArrayList<Person>();
		person.add(new Person(20,"a","北京"));
		person.add(new Person(40,"b","上海"));
		person.add(new Person(30,"c","北京"));
		person.add(new Person(50,"d","上海"));
		person.add(new Person(60,"e","北京"));
		
		
		List<Person> personCopy = person.stream().collect(Collectors.toList());
		print(personCopy);
		
		Map<String,Person> personMap = person.stream().collect(Collectors.toMap(Person :: getName,a -> a));
		print(personMap); //{a=Person [age=20, name=a, address=北京], ....
		
		double avaerAge = person.stream().collect(Collectors.averagingInt(Person :: getAge));
		print(avaerAge); //40.0
		
		String join = person.stream().map(Person :: getName).collect(Collectors.joining("#"));
		print(join); //a#b#c#d#e
		
		Map<String,List<Person>> group = person.stream().collect(Collectors.groupingBy(Person :: getAddress));
		print(group); //{上海=[Person [age=40, name=b, address=上海], Person [age=50, name=d, address=上海]], 北京=[Person [age=20,
		
		Optional<Integer> maxAge = person.stream().map(Person :: getAge).collect(Collectors.maxBy(Integer ::compare));
		print(maxAge.get()); //60
		
		IntSummaryStatistics ageSummary =
				person
	                .stream()
	                .collect(Collectors.summarizingInt(Person :: getAge));

		print(ageSummary); //IntSummaryStatistics{count=5, sum=200, min=20, average=40.000000, max=60}
		
	}
	
	public static <E> void print(E e) {
		if (e instanceof Stream) {
			((Stream) e).forEach(System.out::println);
		} else {
			System.out.println(e.toString());
		}
	}
}
class Person{
	private int age;
	private String name;
	private String address;
	
	public Person() {
		super();
	}
	
	public Person(int age, String name, String address) {
		super();
		this.age = age;
		this.name = name;
		this.address = address;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + ", address=" + address + "]";
	}
	
}