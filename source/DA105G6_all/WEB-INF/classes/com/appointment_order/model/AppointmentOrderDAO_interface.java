package com.appointment_order.model;

import java.util.*;

import com.appointment_order_detail.model.AppointmentOrderDetailVO;

public interface AppointmentOrderDAO_interface {

	public void insert(AppointmentOrderVO appointmentorderVO);
	public void update(AppointmentOrderVO appointmentorderVO);
	public void delete(String appointmentOrderNo);
	public AppointmentOrderVO findByPrimaryKey(String appointmentOrderNo);
	
	public List<AppointmentOrderVO> getMemAll(String memberNo);
	
	public List<AppointmentOrderVO> getCoachAll(String coachNo);

	public List<AppointmentOrderVO> getAll();
	
	public void updatePrice(AppointmentOrderVO appointmentorderVO);
	public void insert2(AppointmentOrderVO appointmentOrderVO, List<AppointmentOrderDetailVO> list);
	public List<AppointmentOrderVO> getCoachCertainDate(String coachNo);
	public List<AppointmentOrderVO> getMemberCertainDate(String memberNo);

	
	
	//Android-------------------------------------
	public List<AppointmentOrderVO> getMemAllAndroid(String memberNo);
	public List<AppointmentOrderVO> getCoaAllAndroid(String coachNo);
	
	
}
