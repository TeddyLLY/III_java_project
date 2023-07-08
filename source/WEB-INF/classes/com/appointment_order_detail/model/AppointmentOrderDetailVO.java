package com.appointment_order_detail.model;

import java.sql.Date;

public class AppointmentOrderDetailVO {
	private String appointmentOrderDetailNo;
	private String appointmentOrderNo;
	private Integer studentStatus;
	private Date appointmentDate;

	
	public String getAppointmentOrderDetailNo() {
		return appointmentOrderDetailNo;
	}
	public void setAppointmentOrderDetailNo(String appointmentOrderDetailNo) {
		this.appointmentOrderDetailNo = appointmentOrderDetailNo;
	}
	public String getAppointmentOrderNo() {
		return appointmentOrderNo;
	}
	public void setAppointmentOrderNo(String appointmentOrderNo) {
		this.appointmentOrderNo = appointmentOrderNo;
	}
	public Integer getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(Integer studentStatus) {
		this.studentStatus = studentStatus;
	}	
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}	

}
