# lightrpc
简单轻量的rpc  
客户端使用
```
		Client.connect("127.0.0.1", 6161);
		ITest proxy = ProxyInterface.getProxy(ITest.class);
```
服务端使用
```
		ITest test= new TestImpl();	
		Invoker.put(test);

		Server.bind(6161);
```
没有集成框架，现在需要手动注册到Invoker