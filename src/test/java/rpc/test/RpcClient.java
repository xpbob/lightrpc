package rpc.test;

import com.xp.service.ITest;

import rpc.xp.client.ProxyInterface;
import rpc.xp.client.zk.Observer;

public class RpcClient {

	public static void main(String[] args) {
		new Observer("127.0.0.1:2181");
		ITest proxy = ProxyInterface.getProxy(ITest.class);
		String message = proxy.getMessage();
		System.out.println(message);

	}

}
