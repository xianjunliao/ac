package com.ac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class test {

	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://112.126.85.175:3306/economize?useUnicode=true&characterEncoding=utf8";
			String driverClassName = "com.mysql.jdbc.Driver";
			String username = "root";
			String password = "lxj";
			Class.forName(driverClassName);
			Connection conn = (Connection) DriverManager.getConnection(url, username, password);
	
			String sql = "CREATE TABLE test (id int not null, name varchar(20) not null, age int null, primary key (id));";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			conn.close(); 
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
