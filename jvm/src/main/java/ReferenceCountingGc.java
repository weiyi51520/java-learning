/**
 * @author Yale.Wei
 * @date 2018/11/30 10:06
 */
public class ReferenceCountingGc {
    Object instance = null;

    public static void main(String[] args) {
        ReferenceCountingGc objA = new ReferenceCountingGc();
        ReferenceCountingGc objB = new ReferenceCountingGc();
        objA.instance = objA;
        objB.instance = objB;
        objA = null;
        objB = null;
        while (true){

        }
    }
}
