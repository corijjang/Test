package com.kh.ex01.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex01.dao.MemberDao;
import com.kh.ex01.dao.MessageDao;
import com.kh.ex01.dao.PointDao;
import com.kh.ex01.vo.MessageVo;

@Service
public class MessageService {
	private static final int SEND_MESSAGE_POINT = 10;
	private static final int OPEN_MESSAGE_POINT = 5;
	private static final String SEND_MESSAGE_CODE = "1001";
	private static final String OPEN_MESSAGE_CODE = "1002";
	

	@Inject
	private MessageDao messageDao;
	
	@Inject
	private MemberDao memberDao;
	
	@Inject
	private PointDao pointDao;
	
	@Transactional
	public void sendMessage(MessageVo messageVo) {
		messageDao.insertMessage(messageVo);
		memberDao.updatePoint(SEND_MESSAGE_POINT, messageVo.getSender());
		pointDao.insertPoint(messageVo.getSender(), 
				SEND_MESSAGE_POINT, SEND_MESSAGE_CODE);
	}
	
}
