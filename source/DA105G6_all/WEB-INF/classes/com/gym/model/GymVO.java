package com.gym.model;

public class GymVO implements java.io.Serializable{
	private String gym_no;
	private String gym_name;
	private String gym_latitude;
	private String gym_longitude;
	private String gym_address;
	private String gym_content;
	
	public String getGym_no() {
		return gym_no;
	}
	public void setGym_no(String gym_no) {
		this.gym_no = gym_no;
	}
	public String getGym_name() {
		return gym_name;
	}
	public void setGym_name(String gym_name) {
		this.gym_name = gym_name;
	}
	public String getGym_latitude() {
		return gym_latitude;
	}
	public void setGym_latitude(String gym_latitude) {
		this.gym_latitude = gym_latitude;
	}
	public String getGym_longitude() {
		return gym_longitude;
	}
	public void setGym_longitude(String gym_longitude) {
		this.gym_longitude = gym_longitude;
	}
	public String getGym_address() {
		return gym_address;
	}
	public void setGym_address(String gym_address) {
		this.gym_address = gym_address;
	}

	public String getGym_content() {
		return gym_content;
	}
	public void setGym_content(String gym_content) {
		this.gym_content = gym_content;
	}

}
