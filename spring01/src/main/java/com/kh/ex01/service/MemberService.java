package com.kh.ex01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kh.ex01.dao.MemberDao;
import com.kh.ex01.vo.MemberVo;

@Service
public class MemberService {
	
	@Inject
	private MemberDao memberDao;
	
	// 회원 추가
	public void insertMember(MemberVo memberVo) {
		memberDao.insertMember(memberVo);
	}
	
	// 특정 회원 조회
	public MemberVo selectById(String userid) {
		MemberVo memberVo = memberDao.selectById(userid);
		return memberVo;
	}
	
	// 모든 회원 조회
	public List<MemberVo> selectAll() {
		List<MemberVo> list = memberDao.selectAll();
		return list;
	}
	
	// 회원 정보 수정
	public void updateMember(MemberVo memberVo) {
		memberDao.updateMember(memberVo);
	}
	
	// 회원 정보 삭제
	public void deleteMember(String userid) {
		memberDao.deleteMember(userid);
	}
	
	// 로그인
	public MemberVo login(String userid, String userpw) {
		MemberVo memberVo = memberDao.login(userid, userpw);
		return memberVo;
	}
	
	public int checkDupId(String userid) {
		int count = memberDao.checkDupId(userid);
		return count;
	}
}
