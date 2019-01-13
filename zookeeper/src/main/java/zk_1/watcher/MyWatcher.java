package zk_1.watcher;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/12/25 09:41
 */
public class MyWatcher {
    private String address = "192.168.82.131:2181";
    private ZooKeeper zk;

    public MyWatcher() {
        try {
            zk = new ZooKeeper(address, 5000, new Watching());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Watching implements Watcher {
        public Watching() {
        }

        @Override
        public void process(WatchedEvent event) {
            Event.KeeperState state = event.getState();
            Event.EventType type = event.getType();
            String path = event.getPath();
            System.out.println("连接状态: " + state + ",事件类型: " + type + ",受影响的路径: " + path);
            try {
                if (null != zk.exists("/yale",true)){
                    System.out.println("内容: " + zk.getData("/yale",true,null));
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--------------->>>>>>>");
        }
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        MyWatcher watcher = new MyWatcher();
        watcher.zk.getData("/yale",true,null);
        TimeUnit.SECONDS.sleep(3);
        watcher.zk.delete("/yale",-1);
        TimeUnit.SECONDS.sleep(3);
    }
}
