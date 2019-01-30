package com.demo.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class Demo {
	
	
	public static void main(String[] args) throws Exception{
		
		//构造java的ZK客户端
		ZooKeeper zk = new ZooKeeper("172.16.100.14:2181,172.16.100.23:2181,172.16.100.24:2181", 30000, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getState());
				System.out.println(event.getType());
				System.out.println(event.getPath());
			}
		});
		
		//创建一个节点
//		String create = zk.create("/myGirls", "性感的".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		
		String create = zk.create("/myGirlsTemp", "性感的".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//		String create = zk.create("/myGirlss", "性感的".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		System.out.println(create);
		
		
		//设置为true相当于对节点myGirls设置了数据变化监听，一旦节点数据发生改变，监听就会触发
		//节点的watch监听是一次性的，所以后面的修改都是一次性的
//		byte[] data = zk.getData("/myGirls", true, null);
		
//		byte[] data = zk.getData("/myGirls", false, null);
//		System.out.println(new String(data));
		
		//这里对节点数据进行修改了，那么设置的监听就会触发，version设置为-1表示不主动设置
//		zk.setData("/myGirls", "不是很性感的".getBytes(), -1);
//		zk.setData("/myGirls", "美丽的".getBytes(), -1);
//		zk.setData("/myGirls", "婉约的".getBytes(), -1);
		
		Thread.sleep(10000);
		
		zk.close();
		
	}
	
	
}
