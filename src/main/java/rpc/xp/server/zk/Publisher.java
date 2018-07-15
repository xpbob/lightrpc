package rpc.xp.server.zk;

import com.alibaba.fastjson.JSONObject;

import rpc.xp.common.CommonContext;
import rpc.xp.common.ServiceInfo;
import rpc.xp.common.zk.ZkUtil;

public class Publisher {

	private ZkUtil zkUtil;

	public Publisher(String address) {
		zkUtil = new ZkUtil(address);
		zkUtil.CreateIfNotExist(CommonContext.PATH);
	}

	public void addService(ServiceInfo info) {
		zkUtil.create(CommonContext.PATH + "/" + info.toString(), JSONObject.toJSONString(info).getBytes());

	}

}
