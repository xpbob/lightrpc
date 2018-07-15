# lightrpc
简单轻量的rpc  
使用netty传输，protostuff序列化。  
使用zk做集群管理  
现在维护长连接，并且支持客户端调用，不同机器的上的服务，开始了集群化管理

客户端使用
```
		new Observer("127.0.0.1:2181");
		ITest proxy = ProxyInterface.getProxy(ITest.class);
		String message = proxy.getMessage();
		System.out.println(message);
```
服务端使用
```
		ITest test= new TestImpl();	
		Invoker.put(test);
		List<String> list = new ArrayList<String>(Invoker.getServices());
		ServiceInfo info = new ServiceInfo("127.0.0.1", 6161, list);
		Publisher pub = new Publisher("127.0.0.1:2181");
		pub.addService(info);
		Server.bind(6161);
```
没有集成框架，现在需要手动注册到Invoker