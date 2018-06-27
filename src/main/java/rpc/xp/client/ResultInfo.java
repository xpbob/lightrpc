package rpc.xp.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

public class ResultInfo {

	private static ConcurrentHashMap<String, SynchronousQueue> mapInfo = new ConcurrentHashMap<>();

	public static void putSunchronousQuee(String id, SynchronousQueue queue) {
		mapInfo.put(id, queue);
	}

	public static SynchronousQueue getSynchronousQueue(String id) {
		return mapInfo.get(id);
	}
	public static void removeById(String id){
		mapInfo.remove(id);
	}

}
