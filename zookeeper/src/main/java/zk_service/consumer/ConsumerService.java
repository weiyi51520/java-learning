package zk_service.consumer;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import zk_service.registry.ServerStatus;
import zk_service.registry.StatDto;
import zk_service.registry.ZkServerRegistry;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/12/25 16:01
 */
public class ConsumerService implements Watcher{
    ZkServerRegistry registry = new ZkServerRegistry();
    private ZooKeeper zooKeeper;
    private String clientName;

    public StatDto useServer(List<String> data, int dom, int i)
            throws Exception {
        if (data.size() == i) {
            throw new Exception("没有可用的服务");
        }
        String node = data.get(dom);
        byte[] bytes = zooKeeper.getData(ZkServerRegistry.root + "/" + node,
                true, null);
        String datas = new String(bytes);
        StatDto dto = JSON.parseObject(datas, StatDto.class);
        if (ServerStatus.STOP.equals(dto.getStatus())) {
            i++;
            useServer(data, dom, i);
        }
        dto.setNode(node);
        return dto;

    }

    public void subscribe(String clientName) throws Exception {
        this.clientName = clientName;
        zooKeeper = registry.getConnection(this);
        List<String> data = zooKeeper.getChildren(ZkServerRegistry.root, true);
        if (data.isEmpty()) {
            throw new Exception("没有可用的服务");

        }
        //随机
        int dom = new SecureRandom().nextInt(data.size());
        StatDto dto = useServer(data, dom, 0);
        dto.setNum(dto.getNum() + 1);
        dto.setName(dto.getName());
        dto.setClient(dto.getClient() == null ? clientName : dto.getClient() + "," + clientName);
        dto.setStatus(ServerStatus.RUN);
        zooKeeper.setData(ZkServerRegistry.root + "/" + dto.getNode(), JSON
                .toJSONString(dto).getBytes(), -1);

    }

    public void call() throws InterruptedException {
        System.out.println("客户端开启,建立netty连接");
    }
    @Override
    public void process(WatchedEvent event) {
        try {
            if (event.getType() == Event.EventType.NodeChildrenChanged) {
                System.out.println("服务器发生改变,重新订阅");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ConsumerService consumer = new ConsumerService();
        consumer.subscribe("consumer1");
        consumer.call();
        TimeUnit.DAYS.sleep(1);
    }
}
