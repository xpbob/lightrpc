package rpc.xp.client.zk;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import rpc.xp.client.Client;
import rpc.xp.common.CommonContext;
import rpc.xp.common.RpcRequest;
import rpc.xp.common.ServiceInfo;
import rpc.xp.common.zk.InfoCallBack;
import rpc.xp.common.zk.ZkUtil;

public class Observer implements InfoCallBack {

	public static volatile Map<String, Set<Client>> services = new HashMap<>();

	private ZkUtil zkUtil;

	public Observer(String address) {
		zkUtil = new ZkUtil(address);
		zkUtil.CreateIfNotExist(CommonContext.PATH);
		try {
			List<String> inofAndWatcher = zkUtil.getInofAndWatcher(CommonContext.PATH, this);
			getLastList(inofAndWatcher);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void send(RpcRequest request, String className) {
		Set<Client> set = services.get(className);
		if (set != null && !set.isEmpty()) {
			Client[] array = set.toArray(new Client[0]);
			Client client = array[new Random().nextInt(array.length)];
			client.write(request);
		}
	}

	@Override
	public void getLastList(List<String> lists) {
		Map<String, Set<Client>> serviceTmp = new HashMap<>();
		if (lists != null && !lists.isEmpty()) {
			for (String node : lists) {
				String info;
				try {
					info = new String(zkUtil.getData(CommonContext.PATH + "/" + node));
					ServiceInfo parse = JSONObject.parseObject(info, ServiceInfo.class);
					String address = parse.toString();
					List<String> interfaces = parse.getInterfaces();
					if (interfaces != null && !interfaces.isEmpty()) {
						for (String service : interfaces) {
							Set<Client> set = serviceTmp.get(service);
							if (set == null) {
								set = new HashSet<Client>();
							}
							if (!set.contains(address)) {
								Client client = new Client();
								client.connect(parse.getIp(), parse.getPort());
								set.add(client);
							}
							serviceTmp.put(service, set);

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

		services = serviceTmp;

	}

}
