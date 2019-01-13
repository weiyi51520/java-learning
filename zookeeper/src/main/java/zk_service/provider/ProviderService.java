package zk_service.provider;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import zk_service.registry.ServerStatus;
import zk_service.registry.StatDto;
import zk_service.registry.ZkServerRegistry;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * @author Yale.Wei
 * @date 2018/12/25 15:50
 */
public class ProviderService implements Watcher{
    ZkServerRegistry server = new ZkServerRegistry();

    void register(String serviceName) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = server.getConnection(this);
        String node = zooKeeper.create(ZkServerRegistry.root + "/service", serviceName.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("注册成功,服务名:"+serviceName+",节点:"+node);
    }

    @Override
    public void process(WatchedEvent event) {

    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        String[] address = new String[]{"192.168.0.2","9990","provider02"};
        ProviderService provider = new ProviderService();
        StatDto dto = new StatDto();
        int i=0;
        dto.setIp(address[i++]);
        dto.setPort(address[i++]);
        dto.setName(address[i++]);
        dto.setNum(0);
        dto.setStatus(ServerStatus.WAIT);
        provider.register(JSON.toJSONString(dto));
        TimeUnit.DAYS.sleep(1);
    }
}
