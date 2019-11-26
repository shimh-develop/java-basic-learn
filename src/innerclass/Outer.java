package innerclass;

/**
 * @author: shimh
 * @create: 2019年11月
 **/
public class Outer {
    private String f1 = "f1";

    private static String sf2 = "sf2";

    // 普通内部类，可以访问外部类的所有成员，当某个外部类的对象创建一个内部类的对象时，内部类对象会有一个指向外部类对象的引用
    // 不能有static的方法和字段 不能包含嵌套类
    class Inner {
        private  String a1 = "a1";
        public  void p1() {
            System.out.println(f1);
            System.out.println(sf2);
        }

        public Outer outer() {
            // 返回外部类对象的实例
            return Outer.this;
        }
    }

    public Inner inner() {
        // 非静态方法 可以这样创建内部类对象  等价：return this.new Inner();
        return new Inner();
    }

    // 局部内部类
    public  InnerInterface localInner() {
        class LocalInner implements InnerInterface{
            public void l() {
                System.out.println(f1);
            }
        }

        return new LocalInner();
    }

    // 匿名内部类
    public InnerInterface anonymousInner() {
        return new InnerInterface (){
            // 不能有命名的构造函数
            {
                // 代替构造函数做一些初始化工作
            }
            public void l() {
                System.out.println(f1);
            }
        };
    }

    // 嵌套类即静态内部类
    // 创建嵌套类时，不需要外部类对象 不能访问非静态的外部类对象
    static class StaticInner {

        public void s() {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        //Outer.Inner inner = outer.inner();
        // 静态方法 显示的创建内部类对象
        Inner inner = outer.new Inner();
        inner.p1();
        System.out.println(outer == inner.outer());

        //嵌套类直接 new
        StaticInner staticInner = new StaticInner();
    }



}
interface InnerInterface {

}
class aa {

    public void aaa() {
        Outer outer = new Outer();
        Outer.Inner inner = outer.inner();

        //嵌套类直接 new
        Outer.StaticInner staticInner = new Outer.StaticInner();
    }

}