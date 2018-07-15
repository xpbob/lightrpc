package rpc.xp.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Invoker {
	private static Map<String, Object> services = new HashMap<>();

	public static void put(Object value) {
		Class<?>[] interfaces = value.getClass().getInterfaces();
		for(Class<?> interfaceTmp:interfaces){
			services.put(interfaceTmp.getName(), value);
		}
		
	}

	public static Object getService(String key) {
		return services.get(key);
	}
	
	public static Set<String> getServices(){
		return services.keySet();
	}

}
