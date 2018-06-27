package rpc.test;

import com.xp.service.ITest;

import rpc.xp.client.Client;
import rpc.xp.client.ProxyInterface;

public class RpcClient {

	public static void main(String[] args) throws InterruptedException {
		Client.connect("127.0.0.1", 6161);
		ITest proxy = ProxyInterface.getProxy(ITest.class);
		String message = proxy.getMessage();
		System.out.println(message);
		
	}

}


