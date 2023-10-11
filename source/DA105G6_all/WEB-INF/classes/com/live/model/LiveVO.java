package com.live.model;

import java.sql.Date;

public class LiveVO {
private String live_no;
private Date live_notice;
private Date live_time;
private String live_title;
private String live_imformation;
private String coach_no;
public String getLive_no() {
	return live_no;
}
public void setLive_no(String live_no) {
	this.live_no = live_no;
}
public Date getLive_notice() {
	return live_notice;
}
public void setLive_notice(Date live_notice) {
	this.live_notice = live_notice;
}
public Date getLive_time() {
	return live_time;
}
public void setLive_time(Date live_time) {
	this.live_time = live_time;
}
public String getLive_title() {
	return live_title;
}
public void setLive_title(String live_title) {
	this.live_title = live_title;
}
public String getLive_imformation() {
	return live_imformation;
}
public void setLive_imformation(String live_imformation) {
	this.live_imformation = live_imformation;
}
public String getCoach_no() {
	return coach_no;
}
public void setCoach_no(String coach_no) {
	this.coach_no = coach_no;
}

}
