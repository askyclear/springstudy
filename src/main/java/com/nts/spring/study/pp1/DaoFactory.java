package com.nts.spring.study.pp1;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
	
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/springbook?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "gksmf12a";
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	/* datasource 적용으로 삭제?
	@Bean
	public ConnectionMaker connectionMaker(){
		return new SimpleConnectionMaker();
	}*/
}
