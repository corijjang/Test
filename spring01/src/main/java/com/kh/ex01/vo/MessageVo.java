package com.kh.ex01.vo;

import java.sql.Timestamp;

public class MessageVo {
	private int mno;
	private String sender;
	private String receiver;
	private String message;
	private Timestamp senddate;
	private Timestamp opendate;

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getSenddate() {
		return senddate;
	}

	public void setSenddate(Timestamp senddate) {
		this.senddate = senddate;
	}

	public Timestamp getOpendate() {
		return opendate;
	}

	public void setOpendate(Timestamp opendate) {
		this.opendate = opendate;
	}

	@Override
	public String toString() {
		return "MessageVo [mno=" + mno + ", sender=" + sender + ", receiver=" + receiver + ", message=" + message
				+ ", senddate=" + senddate + ", opendate=" + opendate + "]";
	}

}
