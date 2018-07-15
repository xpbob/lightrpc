package rpc.xp.common.zk;

import static rpc.xp.common.CommonContext.ZK_TIMEOUT;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZkUtil {
	private ZooKeeper zk;

	public ZkUtil(String address) {

		final CountDownLatch latch = new CountDownLatch(1);
		try {
			zk = new ZooKeeper(address, ZK_TIMEOUT, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					if (event.getState() == Event.KeeperState.SyncConnected) {
						latch.countDown();
					}
				}
			});
			latch.await();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void create(String path, byte[] datas) {
		try {
			zk.create(path, datas, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CreateIfNotExist(String path) {
		try {
			Stat s = zk.exists(path, false);
			if (s == null) {
				zk.create(path, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public byte[] getData(String path) throws Exception {
		return zk.getData(path, false, null);
	}

	public List<String> getInofAndWatcher(final String path, final InfoCallBack callBack) throws Exception {
		List<String> nodeList = zk.getChildren(path, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if (event.getType() == Event.EventType.NodeChildrenChanged) {
					try {
						List<String> nodeList = zk.getChildren(path, false);
						callBack.getLastList(nodeList);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		});
		return nodeList;

	}

}
