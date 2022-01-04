package com.kh.ex01.vo;

import java.sql.Timestamp;

public class PointVo {
	private int pno;
	private String userid;
	private String point_code;
	private int point_score;
	private Timestamp point_date;

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPoint_code() {
		return point_code;
	}

	public void setPoint_code(String point_code) {
		this.point_code = point_code;
	}

	public int getPoint_score() {
		return point_score;
	}

	public void setPoint_score(int point_score) {
		this.point_score = point_score;
	}

	public Timestamp getPoint_date() {
		return point_date;
	}

	public void setPoint_date(Timestamp point_date) {
		this.point_date = point_date;
	}

	@Override
	public String toString() {
		return "PointVo [pno=" + pno + ", userid=" + userid + ", point_code=" + point_code + ", point_score="
				+ point_score + ", point_date=" + point_date + "]";
	}

}
