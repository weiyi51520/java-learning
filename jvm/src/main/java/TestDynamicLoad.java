/**
 * @author Yale.Wei
 * @date 2018/11/29 18:31
 * 启动时加上参数打印类加载顺序 -verbose:class
 */
public class TestDynamicLoad {
    static {
        System.out.println("****** static code ******");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("****** main execute ******");
        new B();
    }
}

class A {
    public A() {
        System.out.println("****** initial A ******");
    }
}

class B {
    public B() {
        System.out.println("****** initial B ******");
    }
}
