package com.favorite_lesson.model;

public class FavoriteLessonVO implements java.io.Serializable {
	private String lessonNo;
	private String memberNo;
	
	public String getLessonNo() {
		return lessonNo;
	}
	public void setLessonNo(String lessonNo) {
		this.lessonNo = lessonNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
}
