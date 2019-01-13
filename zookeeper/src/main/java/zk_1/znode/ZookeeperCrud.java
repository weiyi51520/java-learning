package zk_1.znode;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yale.Wei
 * @date 2018/12/24 18:37
 */
public class ZookeeperCrud {
    private String address = "192.168.82.131:2181";

    private ZooKeeper zk;

    public ZookeeperCrud() {
        try {
            zk = new ZooKeeper(address,5000,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPersistent(){
        try {
            String path = "/yale";
            String value = "abc";
            if (null != zk.exists(path, false)){
                zk.delete(path,-1);
            }
            zk.create(path,value.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            zk.close();
            zk = new ZooKeeper(address,5000,null);
            byte[] data = zk.getData(path, false, null);
            Assert.assertArrayEquals(value.getBytes(),data);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void createEphemeral(){
        try {
            String path = "/yale";
            String value = "ephemeral";
            if (null != zk.exists(path, false)){
                zk.delete(path,-1);
            }
            zk.create(path, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            byte[] data = zk.getData(path, false, null);
            Assert.assertArrayEquals(value.getBytes(),data);
            zk.close();
            zk = new ZooKeeper(address,5000,null);
            try {
                data = zk.getData(path, false, null);
            } catch (KeeperException e) {
                e.printStackTrace();
                Assert.assertTrue(e instanceof KeeperException.NoNodeException);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
