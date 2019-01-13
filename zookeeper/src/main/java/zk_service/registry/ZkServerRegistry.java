package zk_service.registry;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author Yale.Wei
 * @date 2018/12/25 15:45
 */
public class ZkServerRegistry {
    private ZooKeeper zk;
    public static final String root = "/yale";
    private static final String address = "192.168.82.131:2181";

    public ZooKeeper getConnection(Watcher watcher) throws IOException {
        zk = new ZooKeeper(address,500,watcher);
        return zk;
    }
}
