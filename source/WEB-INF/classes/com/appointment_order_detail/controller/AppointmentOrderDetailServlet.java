package com.appointment_order_detail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLDataException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.appointment_order.model.AppointmentOrderService;
import com.appointment_order_detail.model.AppointmentOrderDetailService;

@WebServlet("/AppointmentOrderDetailServlet")
public class AppointmentOrderDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain;charset=UTF-8");
		String action = req.getParameter("action");
		
		if ("finishAPDetail".equals(action)) { 
			PrintWriter out = res.getWriter();
			try {
				String appointmentOrderDetailNo = req.getParameter("appointmentOrderDetailNo");
				int studentStatus =1;

				AppointmentOrderDetailService aptmtdSvc = new AppointmentOrderDetailService();
				
				aptmtdSvc.updateDetailStatus(appointmentOrderDetailNo, studentStatus);

			} catch (Exception e) {
				out.print(e.getMessage());
			}

		}
		if ("deleteAPdetailDATE".equals(action)) { 
			PrintWriter out = res.getWriter();
			try {
				String appointmentOrderNo = req.getParameter("appointmentOrderNo");
				java.sql.Date appointmentDate = null;
				try {
					appointmentDate = java.sql.Date.valueOf(req.getParameter("appointmentDate"));
				}catch(IllegalArgumentException e){
					out.print(e);
					return;
				}

				AppointmentOrderDetailService aptmtdSvc = new AppointmentOrderDetailService();
				
				aptmtdSvc.deleteDetailbyDate(appointmentOrderNo, appointmentDate);
				out.print('0');
			} catch (Exception e) {
				out.print(e.getMessage());
			}

		}		
		if ("insertDATE".equals(action)) { 
			PrintWriter out = res.getWriter();
			try {
				String appointmentOrderNo = req.getParameter("appointmentOrderNo");
				java.sql.Date lessonDate = null;
				try {
					lessonDate = java.sql.Date.valueOf(req.getParameter("appointmentDate"));
				}catch(IllegalArgumentException e){
					out.print(e);
					return;
				}
				
				int studentStatus =0;

				AppointmentOrderDetailService aptmtdSvc = new AppointmentOrderDetailService();
				
				aptmtdSvc.addMemberDetail(appointmentOrderNo, studentStatus, lessonDate);
				out.print('0');
			} catch (Exception e) {
				out.print(e.getMessage());
			}

		}		
	}

}
