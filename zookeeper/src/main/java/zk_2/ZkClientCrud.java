package zk_2;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * @author Yale.Wei
 * @date 2018/12/25 14:19
 */
public class ZkClientCrud<T> {
    private static String connectString = "192.168.82.131:2181";
    static ZkClient zkClient;

    static {
        zkClient = new ZkClient(connectString,5000,5000,new SerializableSerializer());
    }

    public static void main(String[] args) {
        String path = "/user";
        User user = new User();
        user.setName("Yale");
        user.setAge(199);
        zkClient.createPersistent(path,user);
        System.out.println((User)zkClient.readData(path));
    }
}
