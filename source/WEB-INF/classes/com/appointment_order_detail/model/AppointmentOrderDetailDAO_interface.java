package com.appointment_order_detail.model;

import java.util.List;

public interface AppointmentOrderDetailDAO_interface {

    public void insert(AppointmentOrderDetailVO appointmentOrderDetailVO);
    public void update(AppointmentOrderDetailVO appointmentOrderDetailVO);
    public void delete(String appointmentOrderDetailNo);
    public void delete2(String appointmentOrderNo); //刪除明細(依訂單編號)
    public AppointmentOrderDetailVO findByPrimaryKey(String appointmentOrderDetailNo);//查看單一訂單明細
    public List<AppointmentOrderDetailVO> getAll();  //查看所有明細
    public List<AppointmentOrderDetailVO> getOneAll(String appointmentOrderNo);  //查看單一預約所有明細
	public void updateStatus(AppointmentOrderDetailVO appointmentOrderDetailVO);
	public void delete3(AppointmentOrderDetailVO appointmentOrderDetailVO);

	
	//Android
		public AppointmentOrderDetailVO findById(String appointmentOrderDetailNo);//查看單一訂單明細
		
}
