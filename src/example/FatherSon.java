package example;

/**
 * @author: shimh
 * @create: 2019年11月
 **/
public class FatherSon {

    public static void main(String[] args) {
        Father s = new Son();
        //s.f2();
        System.out.println(s.f2);
        System.out.println(s.sf1);
    }

}

class Father {
     static String sf1 = "aaa";
    String f2 = "bbb";

    public void sf1() {
        System.out.println(sf1);
    }
    public void f2() {
        System.out.println(f2);
    }

    public final void aa() {}


}

class Son extends Father{
     static String sf1 = "aaa1";

    String f2 = "bbb1";

    public void sf1() {
        System.out.println(sf1);
    }
    public void f2() {
        System.out.println(f2);
    }


}