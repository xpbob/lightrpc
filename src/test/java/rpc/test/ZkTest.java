package rpc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

import org.junit.Test;

import rpc.xp.client.zk.Observer;
import rpc.xp.common.ServiceInfo;
import rpc.xp.server.zk.Publisher;

public class ZkTest {

	@Test
	public void test() {

		new Observer("127.0.0.1:2181");
		LockSupport.park();
	}

	@Test
	public void publish() {
		List<String> list = new ArrayList<String>();
		list.add("ggg");
		ServiceInfo info = new ServiceInfo("127.0.0.1", 5000, list);

		Publisher pub = new Publisher("127.0.0.1:2181");
		pub.addService(info);
		LockSupport.park();
	}

}
