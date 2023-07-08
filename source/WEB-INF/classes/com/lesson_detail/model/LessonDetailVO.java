package com.lesson_detail.model;

import java.sql.Date;

public class LessonDetailVO implements java.io.Serializable {

	private String lessonDetailNo;
	private String lessonNo;
	private Date lessonDate;	
	
	public String getLessonDetailNo() {
		return lessonDetailNo;
	}
	public void setLessonDetailNo(String lessonDetailNo) {
		this.lessonDetailNo = lessonDetailNo;
	}
	public String getLessonNo() {
		return lessonNo;
	}
	public void setLessonNo(String lessonNo) {
		this.lessonNo = lessonNo;
	}
	public Date getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	
}
