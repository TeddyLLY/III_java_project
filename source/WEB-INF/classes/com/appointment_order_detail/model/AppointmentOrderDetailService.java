package com.appointment_order_detail.model;

import java.sql.Date;
import java.util.List;

import org.json.JSONArray;

public class AppointmentOrderDetailService {

	private AppointmentOrderDetailDAO_interface dao;
	
	public AppointmentOrderDetailService() {
		dao = new AppointmentOrderDetailDAO();
	}
	
	public AppointmentOrderDetailVO addMemberDetail(String appointmentOrderNo,Integer studentStatus,Date lessonDate) {
		AppointmentOrderDetailVO appointmentOrderDetailVO = new AppointmentOrderDetailVO();
		appointmentOrderDetailVO.setAppointmentOrderNo(appointmentOrderNo);
		appointmentOrderDetailVO.setStudentStatus(studentStatus);
		appointmentOrderDetailVO.setAppointmentDate(lessonDate);
		
		dao.insert(appointmentOrderDetailVO);
		return appointmentOrderDetailVO;
	}
	
	public void updateDetailStatus(String appointmentOrderDetailNo,Integer studentStatus) {
		AppointmentOrderDetailVO appointmentOrderDetailVO = new AppointmentOrderDetailVO();
		appointmentOrderDetailVO.setAppointmentOrderDetailNo(appointmentOrderDetailNo);
		appointmentOrderDetailVO.setStudentStatus(studentStatus);

		dao.updateStatus(appointmentOrderDetailVO);
	}
	
	public AppointmentOrderDetailVO updateMemberDetail(String appointmentOrderDetailNo, String appointmentOrderNo,Integer studentStatus,Date lessonDate) {
		AppointmentOrderDetailVO appointmentOrderDetailVO = new AppointmentOrderDetailVO();
		appointmentOrderDetailVO.setAppointmentOrderDetailNo(appointmentOrderDetailNo);
		appointmentOrderDetailVO.setAppointmentOrderNo(appointmentOrderNo);
		appointmentOrderDetailVO.setStudentStatus(studentStatus);
		appointmentOrderDetailVO.setAppointmentDate(lessonDate);
		appointmentOrderDetailVO.setStudentStatus(studentStatus);
		
		dao.update(appointmentOrderDetailVO);
		return appointmentOrderDetailVO;
	}
	
	public void deleteAppointmentOrderDetail(String appointmentOrderDetailNo) {
		dao.delete(appointmentOrderDetailNo);
	}
	public void deleteDetailbyOrderNo(String appointmentOrderNo) {
		dao.delete2(appointmentOrderNo);
	}
	public void deleteDetailbyDate(String appointmentOrderNo,Date appointmentDate) {
		AppointmentOrderDetailVO appointmentOrderDetailVO = new AppointmentOrderDetailVO();

		appointmentOrderDetailVO.setAppointmentOrderNo(appointmentOrderNo);
		appointmentOrderDetailVO.setAppointmentDate(appointmentDate);
		
		dao.delete3(appointmentOrderDetailVO);
	}	
	
	public AppointmentOrderDetailVO getOneAppointmentOrderDetail(String appointmentOrderDetailNo) {
		return dao.findByPrimaryKey(appointmentOrderDetailNo);
	}
	
	public List<AppointmentOrderDetailVO> getDetailByAppointmentOrder(String appointmentOrderNo){
		return dao.getOneAll(appointmentOrderNo);
	}
	
	public String getDetailByAppointmentOrder_json(String appointmentOrderNo){
		String jsonStr = "";
		jsonStr = new JSONArray(dao.getOneAll(appointmentOrderNo)).toString();
		return jsonStr;
	}
	
	List<AppointmentOrderDetailVO> getAll(){
		return dao.getAll();
	}

}
