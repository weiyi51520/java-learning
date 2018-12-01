import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/11/30 14:54
 */
public class GCTest {
    private final static int MB = 1024*1024;

    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[50*MB];
        allocation2 = new byte[20*MB];
        TimeUnit.HOURS.sleep(1);
    }
}
