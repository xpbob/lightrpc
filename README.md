# lightrpc
��������rpc  
ʹ��netty���䣬protostuff���л���  
ʹ��zk����Ⱥ����  
����ά�������ӣ�����֧�ֿͻ��˵��ã���ͬ�������ϵķ��񣬿�ʼ�˼�Ⱥ������

�ͻ���ʹ��
```
		new Observer("127.0.0.1:2181");
		ITest proxy = ProxyInterface.getProxy(ITest.class);
		String message = proxy.getMessage();
		System.out.println(message);
```
�����ʹ��
```
		ITest test= new TestImpl();	
		Invoker.put(test);
		List<String> list = new ArrayList<String>(Invoker.getServices());
		ServiceInfo info = new ServiceInfo("127.0.0.1", 6161, list);
		Publisher pub = new Publisher("127.0.0.1:2181");
		pub.addService(info);
		Server.bind(6161);
```
û�м��ɿ�ܣ�������Ҫ�ֶ�ע�ᵽInvoker