package zk_service.admin;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zk_service.registry.ServerStatus;
import zk_service.registry.StatDto;
import zk_service.registry.ZkServerRegistry;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	ZkServerRegistry bitZook = new ZkServerRegistry();
	private ZooKeeper zook;

	@RequestMapping("/list")
	public String getList(HttpServletRequest request) throws Exception {
		zook = bitZook.getConnection(new Watcher() {
			@Override
			public void process(WatchedEvent event) {
			}
		});
		List<String> data = zook.getChildren(ZkServerRegistry.root, true);
		List<StatDto> serverList = new ArrayList<StatDto>();
		for (String server : data) {
			byte[] bytes = zook.getData(ZkServerRegistry.root + "/" + server,
					false, null);
			String datas = new String(bytes);
			StatDto dto = JSON.parseObject(datas, StatDto.class);
			serverList.add(dto);
		}
		request.setAttribute("serverList", serverList);
		return "admin";

	}

	@RequestMapping("/stop")
	public String stop(HttpServletRequest request) throws Exception {
		zook = bitZook.getConnection(new Watcher() {
			@Override
			public void process(WatchedEvent event) {
			}
		});
		String server = request.getParameter("server");
		byte[] bytes = zook.getData(ZkServerRegistry.root + "/" + server,
				false, null);
		String datas = new String(bytes);
		StatDto dto = JSON.parseObject(datas, StatDto.class);
		dto.setStatus(ServerStatus.STOP);
		zook.setData(ZkServerRegistry.root + "/" + server,
				JSON.toJSONString(dto).getBytes(), -1);
		
		return "redirect:/admin/list";
	}
	
	@RequestMapping("/run")
	public String run(HttpServletRequest request) throws Exception {
		zook = bitZook.getConnection(new Watcher() {
			@Override
			public void process(WatchedEvent event) {
			}
		});
		String server = request.getParameter("server");
		byte[] bytes = zook.getData(ZkServerRegistry.root + "/" + server,
				false, null);
		String datas = new String(bytes);
		StatDto dto = JSON.parseObject(datas, StatDto.class);
		dto.setStatus(ServerStatus.RUN);
		zook.setData(ZkServerRegistry.root + "/" + server,JSON.toJSONString(dto).getBytes(), -1);
		return "redirect:/admin/list";
	}
	
	

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request) throws Exception {
		zook = bitZook.getConnection(new Watcher() {
			@Override
			public void process(WatchedEvent event) {
			}
		});
		String server = request.getParameter("server");
		byte[] bytes = zook.getData(ZkServerRegistry.root + "/" + server,
				false, null);
		String datas = new String(bytes);
		StatDto dto = JSON.parseObject(datas, StatDto.class);
		dto.setStatus(ServerStatus.STOP);
		zook.delete(ZkServerRegistry.root + "/" + server, -1);
		return "redirect:/admin/list";
	}

}
