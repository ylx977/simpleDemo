package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

public class QueryDemo {
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Instant now = Instant.now();
		System.out.println(now);
		
		String url = "jdbc:mysql://172.16.100.15:3306/serverauth?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&useSSL=false";
		String username = "root";
		String password = "agffxyCtt5*p";
		//1.加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//2.获得数据库链接
		Connection connection = DriverManager.getConnection(url, username, password);
		//3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from server_user1");
		
		while(resultSet.next()){
			System.out.println(resultSet.getString("username")+" "+resultSet.getString("password"));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		Instant now2 = Instant.now();
		System.out.println(now2);
		
	}
	
	
}