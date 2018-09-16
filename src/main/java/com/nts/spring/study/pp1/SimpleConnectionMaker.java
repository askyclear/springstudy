package com.nts.spring.study.pp1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker implements ConnectionMaker {

	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/springbook?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "gksmf12a";
		Class.forName(driverName);

		return DriverManager.getConnection(url, username, password);
	}

}
