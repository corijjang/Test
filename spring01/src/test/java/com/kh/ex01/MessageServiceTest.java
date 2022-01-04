package com.kh.ex01;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.ex01.service.MessageService;
import com.kh.ex01.vo.MessageVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class MessageServiceTest {
	@Inject
	private MessageService messageService;
	
	@Test
	public void testSendMessage() {
		MessageVo messageVo = new MessageVo();
		// user01 이 user02 Hello~~~ 메시지를 보냄
		messageVo.setSender("user01");
		messageVo.setReceiver("user02");
		messageVo.setMessage("Hello~~~");
		messageService.sendMessage(messageVo);
	}
}
