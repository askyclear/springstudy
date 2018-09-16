package com.nts.spring.study;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/springbook?useUnicode=true&characterEncoding=utf8";
	private static String username = "root";
	private static String password= "gksmf12a";
    public static void main( String[] args ) throws Exception
    {
        Class.forName(driverName);
        Connection c = DriverManager.getConnection(url, username, password);
        
    }
}
