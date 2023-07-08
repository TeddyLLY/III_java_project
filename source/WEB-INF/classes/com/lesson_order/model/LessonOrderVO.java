package com.lesson_order.model;

import java.sql.Timestamp;

public class LessonOrderVO implements java.io.Serializable {
	private String lessonOrderNo;
	private String memberNo;
	private String lessonNo;
	private Integer lessonPrice;
	private Timestamp dateAcquisition;
	private Integer lessonStatus;
	
	public String getLessonOrderNo() {
		return lessonOrderNo;
	}
	public void setLessonOrderNo(String lessonOrderNo) {
		this.lessonOrderNo = lessonOrderNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getLessonNo() {
		return lessonNo;
	}
	public void setLessonNo(String lessonNo) {
		this.lessonNo = lessonNo;
	}	
	public Integer getLessonPrice() {
		return lessonPrice;
	}
	public void setLessonPrice(Integer lessonPrice) {
		this.lessonPrice = lessonPrice;
	}	
	public Timestamp getDateAcquisition() {
		return dateAcquisition;
	}
	public void setDateAcquisition(Timestamp dateAcquisition) {
		this.dateAcquisition = dateAcquisition;
	}	
	public Integer getLessonStatus() {
		return lessonStatus;
	}
	public void setLessonStatus(Integer lessonStatus) {
		this.lessonStatus = lessonStatus;
	}	
}
