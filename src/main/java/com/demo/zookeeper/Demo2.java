package com.demo.zookeeper;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class Demo2 {
	
	
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
		
		List<String> children = zk.getChildren("/", true);
		children.stream().forEach(System.out::println);
		
		Thread.sleep(100000);
		
		zk.close();
		
	}
	
	
}
