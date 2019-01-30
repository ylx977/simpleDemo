package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;

public class TXDemo {
	
	
	public static void main(String[] args) throws Exception {
		
		long time = System.currentTimeMillis();
		
		String url = "jdbc:mysql://172.16.100.15:3306/serverauth?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&useSSL=false";
		String username = "root";
		String password = "agffxyCtt5*p";
		//1.加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//2.获得数据库链接
		Connection connection = DriverManager.getConnection(url, username, password);
		
		connection.setAutoCommit(false);//开启事务start transaction
		
		//3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
		Statement statement = connection.createStatement();
		
		statement.addBatch("insert into server_user1 (username,password,role,ctime,utime) values ('jack','123456','1',"+time+","+time+")");
		System.out.println("完成事务中的第一个插入语句，等待20秒");
		Thread.sleep(20000);
		
		
		statement.addBatch("insert into server_user1 (username,password,role,ctime,utime) values ('jack2','123456','1',"+time+","+time+")");
		System.out.println("完成事务中的第一个插入语句，等待20秒");
		Thread.sleep(20000);
		
		
		int[] executeBatch = statement.executeBatch();
		System.out.println(Arrays.toString(executeBatch));
		
		connection.commit();//提交事务commit
		
		//关闭操作，按开启的顺序反向关闭
//		resultSet.close();
		statement.close();
		connection.close();
		

	}
	
	
}