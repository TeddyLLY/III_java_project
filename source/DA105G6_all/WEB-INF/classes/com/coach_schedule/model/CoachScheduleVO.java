package com.coach_schedule.model;

import java.sql.*;

public class CoachScheduleVO implements java.io.Serializable{
	private String coachScheduleNo;
	private String coachNo;
	private Date coachDate;
	
	public String getCoachScheduleNo() {
		return coachScheduleNo;
	}
	public void setCoachScheduleNo(String coachScheduleNo) {
		this.coachScheduleNo = coachScheduleNo;
	}
	public String getCoachNo() {
		return coachNo;
	}
	public void setCoachNo(String coachNo) {
		this.coachNo = coachNo;
	}
	public Date getCoachDate() {
		return coachDate;
	}
	public void setCoachDate(Date coachDate) {
		this.coachDate = coachDate;
	}

}
