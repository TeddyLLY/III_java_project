package com.livestream.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class HistoryVideoVO implements Serializable {
	private String history_video_no;
	private Timestamp history_video_time;
	private String history_video_title;
	private byte[] history_video_content;
	private String live_no;
	private String coach_no;
	public String getHistory_video_no() {
		return history_video_no;
	}
	public void setHistory_video_no(String history_video_no) {
		this.history_video_no = history_video_no;
	}
	public Timestamp getHistory_video_time() {
		return history_video_time;
	}
	public void setHistory_video_time(Timestamp history_video_time) {
		this.history_video_time = history_video_time;
	}
	public String getHistory_video_title() {
		return history_video_title;
	}
	public void setHistory_video_title(String history_video_title) {
		this.history_video_title = history_video_title;
	}
	public byte[] getHistory_video_content() {
		return history_video_content;
	}
	public void setHistory_video_content(byte[] history_video_content) {
		this.history_video_content = history_video_content;
	}
	public String getLive_no() {
		return live_no;
	}
	public void setLive_no(String live_no) {
		this.live_no = live_no;
	}
	public String getCoach_no() {
		return coach_no;
	}
	public void setCoach_no(String coach_no) {
		this.coach_no = coach_no;
	}
	
	
	
}
