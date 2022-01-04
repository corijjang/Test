package com.kh.ex01;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class OracleConnectTest {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String ID = "javaweb";
	private static final String PW = "1234";
	
	// 반환타입이 반드시 void, 파라미터는 없어야 함
	@Test
	public void testConnection() {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ID, PW);
			System.out.println("conn:" + conn);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
