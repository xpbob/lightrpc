package rpc.test;

import com.xp.service.ITest;
import com.xp.service.TestImpl;

import rpc.xp.server.Invoker;
import rpc.xp.server.Server;

public class RpcServer {

	public static void main(String[] args) throws InterruptedException {
		ITest test= new TestImpl();	
		Invoker.put(test);

		Server.bind(6161);

	}

}
