package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 采用流的方式对数据库中100万条数据进行查询
 * @author fuzamei
 *
 */
public class BigInfoQuery_ByPage {

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
		
		
		int pageSize = 1000;
		int count = 0;
		
		for(int k = 0;k <1000;k++){
			PreparedStatement prepareStatement = connection.prepareStatement("select * from user limit ?,?");
			prepareStatement.setInt(1, count * pageSize);
			prepareStatement.setInt(2, pageSize);
			ResultSet resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				System.out.println(resultSet.getInt("id")+" "+resultSet.getString("username")+" "+resultSet.getString("password")+" "+resultSet.getInt("age")+" "+resultSet.getInt("sex"));
			}
			resultSet.close();
			prepareStatement.close();
			count++;
		}
		
		
		connection.close();
		
		Thread.sleep(50000);
	}
	
}
