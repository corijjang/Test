package com.kh.ex01.vo;

import java.sql.Timestamp;
import java.util.Arrays;

public class BoardVo {
	private int bno;
	private String title;
	private String content;
	private String userid;
	private Timestamp regdate;
	private int viewcnt;
	private int re_group;
	private int re_seq;
	private int re_level;
	private int comment_cnt;
	private String[] files;

	public BoardVo() {
		super();
	}

	public BoardVo(int bno, String title, String content, String userid, Timestamp regdate, int viewcnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.userid = userid;
		this.regdate = regdate;
		this.viewcnt = viewcnt;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public int getRe_group() {
		return re_group;
	}

	public void setRe_group(int re_group) {
		this.re_group = re_group;
	}

	public int getRe_seq() {
		return re_seq;
	}

	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "BoardVo [bno=" + bno + ", title=" + title + ", content=" + content + ", userid=" + userid + ", regdate="
				+ regdate + ", viewcnt=" + viewcnt + ", re_group=" + re_group + ", re_seq=" + re_seq + ", re_level="
				+ re_level + ", comment_cnt=" + comment_cnt + ", files=" + Arrays.toString(files) + "]";
	}

	

}
