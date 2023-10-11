package com.system_news.model;

import java.sql.Timestamp;

public class SystemNewsVO implements java.io.Serializable{
	private String system_news_no;
	private String member_no;
	private String coach_no;
	private String system_content;
	private Timestamp system_time;
	
	
	public SystemNewsVO() {
		super();
		// TODO Auto-generated constructor stub
	}

//----------------------------------------------------------

	public String getSystem_news_no() {
		return system_news_no;
	}


	public void setSystem_news_no(String system_news_no) {
		this.system_news_no = system_news_no;
	}


	public String getMember_no() {
		return member_no;
	}


	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}


	public String getCoach_no() {
		return coach_no;
	}


	public void setCoach_no(String coach_no) {
		this.coach_no = coach_no;
	}


	public String getSystem_content() {
		return system_content;
	}


	public void setSystem_content(String system_content) {
		this.system_content = system_content;
	}


	public Timestamp getSystem_time() {
		return system_time;
	}


	public void setSystem_time(Timestamp system_time) {
		this.system_time = system_time;
	}
	
//----------------------------------------------------------
	
	
	
	
}
