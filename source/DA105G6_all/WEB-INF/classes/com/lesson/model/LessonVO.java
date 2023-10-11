package com.lesson.model;

import java.sql.Date;
import oracle.sql.*;

public class LessonVO implements java.io.Serializable{
	private String lessonNo;
	private String coachNo;
	private String lessonName;
	private String lessonPoint;
	private Integer lessonPrice;
	private String lessonContent;
	private Date lessonStartDate;
	private Date lessonEndDate;
	private Integer lessonRegistration;
	private Integer lessonMaximumPeople;
	private byte[] lessonPicture;
	private String lessonLocation;
	private Integer lessonClass;
	private Integer lessonStatus;
	
	public String getLessonNo() {
		return lessonNo;
	}
	public void setLessonNo(String lessonNo) {
		this.lessonNo = lessonNo;
	}
	
	public String getCoachNo() {
		return coachNo;
	}
	public void setCoachNo(String coachNo) {
		this.coachNo = coachNo;
	}
	
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
	public String getLessonPoint() {
		return lessonPoint;
	}
	public void setLessonPoint(String lessonPoint) {
		this.lessonPoint = lessonPoint;
	}
	
	public Integer getLessonPrice() {
		return lessonPrice;
	}
	public void setLessonPrice(Integer lessonPrice) {
		this.lessonPrice = lessonPrice;
	}

	public String getLessonContent() {
		return lessonContent;
	}
	public void setLessonContent(String lessonContent) {
		this.lessonContent = lessonContent;
	}
	public Date getLessonStartDate() {
		return lessonStartDate;
	}
	public void setLessonStartDate(Date lessonStartDate) {
		this.lessonStartDate = lessonStartDate;
	}
	public Date getLessonEndDate() {
		return lessonEndDate;
	}
	public void setLessonEndDate(Date lessonEndDate) {
		this.lessonEndDate = lessonEndDate;
	}
	public Integer getLessonRegistration() {
		return lessonRegistration;
	}
	public void setLessonRegistration(Integer lessonRegistration) {
		this.lessonRegistration = lessonRegistration;
	}	
	public Integer getLessonMaximumPeople() {
		return lessonMaximumPeople;
	}
	public void setLessonMaximumPeople(Integer lessonMaximumPeople) {
		this.lessonMaximumPeople = lessonMaximumPeople;
	}	
	public byte[] getLessonPicture() {
		return lessonPicture;
	}
	public void setLessonPicture(byte[] lessonPicture) {
		this.lessonPicture = lessonPicture;
	}		
	public String getLessonLocation() {
		return lessonLocation;
	}
	public void setLessonLocation(String lessonLocation) {
		this.lessonLocation = lessonLocation;
	}
	public Integer getLessonClass() {
		return lessonClass;
	}
	public void setLessonClass(Integer lessonClass) {
		this.lessonClass = lessonClass;
	}
	public Integer getLessonStatus() {
		return lessonStatus;
	}
	public void setLessonStatus(Integer lessonStatus) {
		this.lessonStatus = lessonStatus;
	}
	
}
