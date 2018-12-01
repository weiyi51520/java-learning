import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Yale.Wei
 * @date 2018/11/30 14:29
 * -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\jvm.dump
 */
public class OOMTest {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();

        int i = 0;
        while (true){
            list.add(new User(i++, UUID.randomUUID().toString()));
        }
    }
}
