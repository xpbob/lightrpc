package rpc.test;

import java.util.ArrayList;
import java.util.List;

import com.xp.service.ITest;
import com.xp.service.TestImpl;

import rpc.xp.common.ServiceInfo;
import rpc.xp.server.Invoker;
import rpc.xp.server.Server;
import rpc.xp.server.zk.Publisher;

public class RpcServer {

	public static void main(String[] args) throws InterruptedException {
		ITest test= new TestImpl();	
		Invoker.put(test);
		List<String> list = new ArrayList<String>(Invoker.getServices());
		ServiceInfo info = new ServiceInfo("127.0.0.1", 6161, list);
		Publisher pub = new Publisher("127.0.0.1:2181");
		pub.addService(info);
		
		

		Server.bind(6161);

	}

}
