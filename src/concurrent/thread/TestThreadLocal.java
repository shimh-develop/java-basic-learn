package concurrent.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: shimh
 * @create: 2019年10月
 **/
public class TestThreadLocal {


    public static void main(String[] args) {
        Person p = new Person();
        p.setAge(12);
        p.setName("shimh");
        ThreadLocal<Person> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set(p);
        Thread t1 = new Thread(() -> {
//            ThreadLocal<Person> threadLocal = new ThreadLocal<>();
//            threadLocal.set(p);
//            threadLocal.get().setAge(15);
//
//            ThreadLocal<String> t2 = new ThreadLocal<>();
//            t2.set("1");
//
//
//            System.out.println(threadLocal.get());
//            System.out.println(t2.get());
            inheritableThreadLocal.get().setAge(15);
            System.out.println(inheritableThreadLocal.get());
        });
        t1.start();
        Thread t2 = new Thread(() -> {
//            ThreadLocal<Person> threadLocal = new ThreadLocal<>();
//            threadLocal.set(p);
//            System.out.println(threadLocal.get());
            inheritableThreadLocal.get().setAge(15);
            System.out.println(inheritableThreadLocal.get());
        });
        t2.start();

        System.out.println();
        test1();
    }

    public static void test1() {
        for(int i =0;i<100;i++) {
            System.out.println(nextHashCode() & 127);
        }
    }

    private static final int HASH_INCREMENT = 0x61c88647;
    private static AtomicInteger nextHashCode =
            new AtomicInteger();
    /**
     * Returns the next hash code.
     */
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

}

class Person {
    private int age;

    private String name;

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

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}