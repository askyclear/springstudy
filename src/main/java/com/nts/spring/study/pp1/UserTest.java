package com.nts.spring.study.pp1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@DirtiesContext
public class UserTest {
	@Autowired
	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	@Before
	public void setUp(){
//		ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
//		this.dao = ac.getBean("userDao", UserDao.class);
		DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/testdb",
																"test", "test123", true);
		dao.setDataSource(dataSource);
		this.user1 = new User("kim1","킴일","user1!");
		this.user2 = new User("kim2","킴이","user2!");
		this.user3 = new User("kim3","킴삼","user3!");
		this.user4 = new User("kim4","킴사","user4!");
	}
	@Test
	public void testAddAndGet() throws SQLException {
		ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = ac.getBean("userDao", UserDao.class);
		// deleteAll이 아직 검증이 안됬으므로 count가 0인지 확인
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		
		dao.add(user1);
		dao.add(user2);
		// getCount 기능이 검증안됫으므로 현 상황에서 검증
		assertThat(dao.getCount(), is(2));
		
		User userget = dao.get(user1.getId());
		assertThat(userget.getName(), is(user1.getName()));
		assertThat(userget.getPassword(), is(user1.getPassword()));
		
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void testGetUserException() throws SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("알수없는아이디");
	}
	//getCount의 추가적인 검증 테스트
	@Test
	public void testGetCount() throws SQLException {
		ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = ac.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
		
		dao.add(user4);
		assertThat(dao.getCount(), is(4));
		
	}
	
}
