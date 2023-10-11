package com.member_lesson_detail.model;

import java.sql.Date;

public class MemberLessonDetailVO {
	private String memberLessonDetailNo;
	private String lessonOrderNo;
	private Integer studentStatus;
	private Date lessonDate;

	
	public String getMemberLessonDetailNo() {
		return memberLessonDetailNo;
	}
	public void setMemberLessonDetailNo(String memberLessonDetailNo) {
		this.memberLessonDetailNo = memberLessonDetailNo;
	}
	public String getLessonOrderNo() {
		return lessonOrderNo;
	}
	public void setLessonOrderNo(String lessonOrderNo) {
		this.lessonOrderNo = lessonOrderNo;
	}
	public Integer getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(Integer studentStatus) {
		this.studentStatus = studentStatus;
	}	
	public Date getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}	

}
