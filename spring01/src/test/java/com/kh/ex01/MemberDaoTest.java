package com.kh.ex01;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.ex01.dao.MemberDao;
import com.kh.ex01.vo.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class MemberDaoTest {
	@Inject
	private MemberDao memberDao;
	
	@Test
	public void testInsertMember() {
		MemberVo memberVo = new MemberVo(
				"user02", "1234", "사용자02", "user02@email.com", 
				null, null);
		memberDao.insertMember(memberVo);
	}
	
	@Test
	public void testSelectById() {
		String userid = "user01";
		MemberVo memberVo = memberDao.selectById(userid);
		System.out.println("memberVo:" + memberVo);
	}
	
	@Test
	public void testSelectAll() {
		List<MemberVo> list = memberDao.selectAll();
		System.out.println("list:" + list);
	}
	
	@Test
	public void testUpdateMember() {
		MemberVo memberVo = new MemberVo(
				"user01", "5678", "사용자001", "user001@email.com", 
				null, null);
		memberDao.updateMember(memberVo);
	}
	
	@Test
	public void testDeleteMember() {
		String userid = "user01";
		memberDao.deleteMember(userid);
	}
	
	@Test
	public void testLogin() {
		String userid = "user02";
		String userpw = "1234";
		MemberVo memberVo = memberDao.login(userid, userpw);
		System.out.println("memberVo:" + memberVo);
	}
}
