package zk_2;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.zookeeper.Watcher;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.zookeeper.Watcher.Event.KeeperState.Disconnected;
import static org.apache.zookeeper.Watcher.Event.KeeperState.SyncConnected;

/**
 * @author Yale.Wei
 * @date 2018/12/25 14:44
 */
public class ZkClientWathcer<T> {
    ZkClient zkClient;
    private String connectString = "192.168.82.131:2181";

    public ZkClientWathcer() {
        zkClient = new ZkClient(connectString,5000,5000,new MyZkSerialzer());
    }
    public  T readData(String path){
        return zkClient.readData(path);

    }

    public List<String> getChildren(String path){
        return zkClient.getChildren(path);

    }

    public  void writeData(String path,Object object){
        zkClient.writeData(path,object);

    }

    public  void deleteRecursive(String path){
        zkClient.deleteRecursive(path);

    }

    public void createPersistent(String path,Object data){
        zkClient.createPersistent(path,data);
    }

    public void listener(String path){
        //数据变更监听
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.printf("变更的节点为:%s,%s", dataPath,data);
                System.out.println();
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.printf("删除的节点为:%s", dataPath );
                System.out.println();
            }
        });
        //子节点变化监听
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("父节点路径: " + parentPath+"子节点目录: "+ currentChilds);
            }
        });
        //状态变更监听
        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
                if (state == SyncConnected){
                    System.out.println("连接成功!");
                }else if (state == Disconnected){
                    System.out.println("连接断开!");
                }else {
                    System.out.println("其他: " + state.name());
                }
            }

            @Override
            public void handleNewSession() throws Exception {
                System.out.println("重建session");
            }

            @Override
            public void handleSessionEstablishmentError(Throwable error) throws Exception {
                error.printStackTrace();
            }
        });

    }

    public static void main(String[] args) throws InterruptedException {
        ZkClientWathcer zkClientWathcer = new ZkClientWathcer();
        String path = "/yale";
        zkClientWathcer.listener(path);
    }
}
