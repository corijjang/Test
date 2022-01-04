package com.kh.ex01.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.ex01.vo.MemberVo;

@Repository
public class MemberDao {
	private static final String NAMESPACE = "com.kh.ex01.mappers.member.";
	
	@Inject
	private SqlSession sqlSession;
	
	// 회원 추가
	public void insertMember(MemberVo memberVo) {
		sqlSession.insert(NAMESPACE + "insertMember", memberVo);
	}
	
	// 회원 정보 보기
	public MemberVo selectById(String userid) {
		MemberVo memberVo = sqlSession.selectOne(
				NAMESPACE + "selectById", userid);
		return memberVo;
	}
	
	// 회원 전체 목록
	public List<MemberVo> selectAll() {
		// java.util.List
		List<MemberVo> list = sqlSession.selectList(NAMESPACE + "selectAll");
		return list;
	}
	
	// 회원 정보 수정
	public void updateMember(MemberVo memberVo) {
		sqlSession.update(NAMESPACE + "updateMember", memberVo);
	}
	
	// 회원 정보 삭제
	public void deleteMember(String userid) {
		sqlSession.delete(NAMESPACE + "deleteMember", userid);
	}
	
	// 로그인
	public MemberVo login(String userid, String userpw) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		MemberVo memberVo = sqlSession.selectOne(NAMESPACE + "login", paramMap);
		return memberVo;
	}
	
	// 포인트
	public void updatePoint(int point_score, String userid) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("point_score", point_score);
		paramMap.put("userid", userid);
		sqlSession.update(NAMESPACE + "updatePoint", paramMap);
	}
	
	public int checkDupId(String userid) {
		int count = sqlSession.selectOne(NAMESPACE + "checkDupId", userid);
		return count;
	}
}
