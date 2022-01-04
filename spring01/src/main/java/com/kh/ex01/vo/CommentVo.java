package com.kh.ex01.vo;

import java.sql.Timestamp;

public class CommentVo {
	private int cno;
	private int bno;
	private String userid;
	private String content;
	private Timestamp regdate;

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "CommentVo [cno=" + cno + ", bno=" + bno + ", userid=" + userid + ", content=" + content + ", regdate="
				+ regdate + "]";
	}

}
