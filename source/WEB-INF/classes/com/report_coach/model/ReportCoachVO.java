package com.report_coach.model;

import java.sql.Timestamp;

public class ReportCoachVO implements java.io.Serializable{
	private String report_coach_no;
	private String coach_no;
	private String member_no;
	private String report_coach_content;
	private Timestamp report_coach_time;
	private Integer report_coach_status;
	
	
	public ReportCoachVO() {
		super();
		// TODO Auto-generated constructor stub
	}

//---------------------------------------------------------

	public String getReport_coach_no() {
		return report_coach_no;
	}


	public void setReport_coach_no(String report_coach_no) {
		this.report_coach_no = report_coach_no;
	}


	public String getCoach_no() {
		return coach_no;
	}


	public void setCoach_no(String coach_no) {
		this.coach_no = coach_no;
	}


	public String getMember_no() {
		return member_no;
	}


	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}


	public String getReport_coach_content() {
		return report_coach_content;
	}


	public void setReport_coach_content(String report_coach_content) {
		this.report_coach_content = report_coach_content;
	}


	public Timestamp getReport_coach_time() {
		return report_coach_time;
	}


	public void setReport_coach_time(Timestamp report_coach_time) {
		this.report_coach_time = report_coach_time;
	}


	public Integer getReport_coach_status() {
		return report_coach_status;
	}


	public void setReport_coach_status(Integer report_coach_status) {
		this.report_coach_status = report_coach_status;
	};

	
//------------------------------------------------------------
	
	
	
}
