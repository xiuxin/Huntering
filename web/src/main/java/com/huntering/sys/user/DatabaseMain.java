package com.huntering.sys.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DatabaseMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/huntering?useUnicode=true&amp;characterEncoding=UTF8&amp;autoReconnect=true&amp;failOverReadOnly=false");
		//
		/*dataSource.setUsername("bell");
		dataSource.setPassword("654321");*/
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.init();
		DruidPooledConnection connection = dataSource.getConnection();
		Statement createStatement = connection.createStatement();
		ResultSet query = createStatement.executeQuery("select id from account");
		while(query.next()) {
			System.out.println(query.getObject(1));
		}
		System.out.println("End");
	}

}
