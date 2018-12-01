import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/11/30 14:43
 */
public class DeadLockTest {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
           synchronized (lock1){
               System.out.println("thread1 begin");
               try {
                   TimeUnit.SECONDS.sleep(3);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (lock2){
                   System.out.println("thread1 end");
               }
           }
        },"thread1").start();

        new Thread(() -> {
            synchronized (lock2){
                System.out.println("thread2 begin");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("thread2 end");
                }
            }
        },"thread2").start();

        System.out.println("main thread end");
    }
}
