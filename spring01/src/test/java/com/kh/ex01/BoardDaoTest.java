package com.kh.ex01;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.ex01.dao.BoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class BoardDaoTest {
	@Inject
	private BoardDao boardDao;
	
	@Test
	public void testGetDate() {
		boardDao.getDate();
	}
}
