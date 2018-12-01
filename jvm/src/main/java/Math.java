import java.util.Map;

/**
 * @author Yale.Wei
 * @date 2018/11/29 17:46
 * javap -c Math.class >Math.text 反编译Math.class文件
 */
public class Math {

    public int math() {
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.math();
        System.out.println("end");
    }
}
