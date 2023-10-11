package com.appointment_order.model;

import java.sql.*;

public class AppointmentOrderVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String appointmentOrderNo;
	private String memberNo;
	private String coachNo;
	private Integer appointmentPrice;
	private Timestamp orderTime;
	private Integer appointmentStatus;
	private String appointmentLocation;
	private String appointmentDemand;
	
	public String getAppointmentOrderNo() {
		return appointmentOrderNo;
	}
	public void setAppointmentOrderNo(String appointmentOrderNo) {
		this.appointmentOrderNo = appointmentOrderNo;
	}
	
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	
	public String getCoachNo() {
		return coachNo;
	}
	public void setCoachNo(String coachNo) {
		this.coachNo = coachNo;
	}
	
	public Integer getAppointmentPrice() {
		return appointmentPrice;
	}
	public void setAppointmentPrice(Integer appointmentPrice) {
		this.appointmentPrice = appointmentPrice;
	}
	
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	
	public Integer getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(Integer appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
	public String getAppointmentLocation() {
		return appointmentLocation;
	}
	public void setAppointmentLocation(String appointmentLocation) {
		this.appointmentLocation = appointmentLocation;
	}
	
	public String getAppointmentDemand() {
		return appointmentDemand;
	}
	public void setAppointmentDemand(String appointmentDemand) {
		this.appointmentDemand = appointmentDemand;
	}
}
