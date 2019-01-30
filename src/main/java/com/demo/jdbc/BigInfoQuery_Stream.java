package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 采用流的方式对数据库中100万条数据进行查询
 * @author fuzamei
 *
 */
public class BigInfoQuery_Stream {

	public static void main(String[] args) throws Exception{
		
		Thread.sleep(20000);
		
//		String url = "jdbc:mysql://localhost:3306/biginfo?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&useSSL=false";
		String url = "jdbc:mysql://localhost:3306/biginfo?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&useSSL=false";
		String username = "root";
		String password = "root";
		//1.加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//2.获得数据库链接
		Connection connection = DriverManager.getConnection(url, username, password);
		
//		Statement statement = connection.createStatement();
		
		Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		statement.setFetchSize(Integer.MIN_VALUE);
		ResultSet resultSet = statement.executeQuery("select * from user");
		
		while(resultSet.next()){
			System.out.println(resultSet.getInt("id")+" "+resultSet.getString("username")+" "+resultSet.getString("password")+" "+resultSet.getInt("age")+" "+resultSet.getInt("sex"));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		Thread.sleep(50000);
	}
	
}
