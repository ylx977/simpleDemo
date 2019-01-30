package com.tcm;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class WithDrawOrderInfo {

	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://172.16.100.129:3306/tcm?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&useSSL=false";
		String username = "tcm";
		String password = "MoGXl5GVxNdiZHt1";
		//1.加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//2.获得数据库链接
		Connection connection = DriverManager.getConnection(url, username, password);
		//3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select sum(count) as count,sum(amount) as moneys,restaurant_name,order_date from company_order where order_date like '201812%' and status = 5 group by order_date,restaurant_name");
		
		File file = new File("C:\\Users\\fuzamei\\Desktop\\订单明细.csv");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("GBK")));
		
		while(resultSet.next()){
			System.out.println(resultSet.getString("count")+","+resultSet.getString("moneys")+ "," + resultSet.getString("restaurant_name")+ "," + resultSet.getString("order_date"));
			bw.write(resultSet.getString("count")+","+resultSet.getString("moneys")+ "," + resultSet.getString("restaurant_name")+ "," + resultSet.getString("order_date"));
			bw.write("\r\n");
		}
		
		bw.flush();
		bw.close();
		
		resultSet.close();
		statement.close();
		connection.close();
	}
	//select sum(count) as count,sum(amount) as moneys,restaurant_name,order_date from company_order where order_date like "201812%" and status = 5 group by order_date,restaurant_name
	
}
