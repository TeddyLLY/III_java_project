package com.appointment_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

import com.appointment_order.model.AppointmentOrderService;
import com.appointment_order.model.AppointmentOrderVO;
import com.appointment_order_detail.model.AppointmentOrderDetailService;
import com.appointment_order_detail.model.AppointmentOrderDetailVO;

import com.member.model.MemberService;
import com.member.model.MemberVO;


public class AppointmentOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain;charset=UTF-8");
		String action = req.getParameter("action");

		if("changeclass".equals(action)) {
		
				String appointmentOrderNo = new String(req.getParameter("appointmentOrderNo"));
				AppointmentOrderService apSvc = new AppointmentOrderService();
				AppointmentOrderVO apOrderVO = apSvc.getOneAppointmentOrder2(appointmentOrderNo);
				req.setAttribute("apOrderVO", apOrderVO);	

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/appointment_order/CoachChangeAppointmentDate.jsp");
				successView.forward(req, res);				
		}		
		
		
		//===================--------------注意!還未寫入扣款!----------==========================		
		if ("AppointmentCancel".equals(action)) {  //未完成

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

			Integer appointmentPrice = 0;				
			String appointmentOrderNo = req.getParameter("appointmentOrderNo");
			int appointmentStatus =5;
			AppointmentOrderVO appointmentOrderVO = new AppointmentOrderVO();
			appointmentOrderVO.setAppointmentPrice(appointmentPrice);
			appointmentOrderVO.setAppointmentOrderNo(appointmentOrderNo);
			
			/***************************2.開始查詢資料*****************************************/
			AppointmentOrderService aptmtSvc = new AppointmentOrderService();
			appointmentOrderVO = aptmtSvc.updatePrice(appointmentOrderNo, appointmentPrice,appointmentStatus);

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/appointment_order/MemUnCheckedAppointment.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/

			String url = "/front-end/appointment_order/MemAppointmentHistory.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/appointment_order/MemUnCheckedAppointment.jsp");
			failureView.forward(req, res);
		}
	}		
	
		if ("payforAppointment".equals(action)) {  
			PrintWriter out = res.getWriter();
			try {
				
				String memberNo = req.getParameter("memberNo");
				MemberService memberSvc = new MemberService();  //取得會員點數
				MemberVO memberVO = memberSvc.findOnePoints(memberNo);
				Integer appointmentPrice = new Integer(req.getParameter("appointmentPrice"));

				if(memberVO.getMember_points()<appointmentPrice){//比對會員點數是否足夠扣款
					out = res.getWriter();
					out.print("1");//會員點數不足
					return;
				}
				int memPoint=memberVO.getMember_points()-appointmentPrice;

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String appointmentOrderNo = req.getParameter("appointmentOrderNo");
				int appointmentStatus =1;

				/***************************2.開始查詢資料*****************************************/
				AppointmentOrderService aptmtSvc = new AppointmentOrderService();
				aptmtSvc.updatePrice2(appointmentOrderNo, appointmentPrice,appointmentStatus,memberNo,memPoint);				
					out.print("0");
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				out.print(e.getMessage());
				System.out.println(e.getMessage());
			}
		}
		
		
		if ("quoteprice".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("appointmentPrice");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入報價");
				}
				Integer appointmentPrice;
				try {
					appointmentPrice = new Integer(str);
				} catch (NumberFormatException e) {
					appointmentPrice = 0;
					errorMsgs.add("報價請填數字.");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/appointment_order/CoachUnCheckedAppointment.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String appointmentOrderNo = req.getParameter("appointmentOrderNo");
				int appointmentStatus =2;
				AppointmentOrderVO appointmentOrderVO = new AppointmentOrderVO();
				appointmentOrderVO.setAppointmentPrice(appointmentPrice);
				appointmentOrderVO.setAppointmentOrderNo(appointmentOrderNo);
				
				/***************************2.開始查詢資料*****************************************/
				AppointmentOrderService aptmtSvc = new AppointmentOrderService();
				appointmentOrderVO = aptmtSvc.updatePrice(appointmentOrderNo, appointmentPrice,appointmentStatus);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/appointment_order/CoachUnCheckedAppointment.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/

				String url = "/front-end/appointment_order/CoachUnCheckedAppointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/appointment_order/CoachUnCheckedAppointment.jsp");
				failureView.forward(req, res);
			}
		}
		if ("transferDate".equals(action)||"finishAP".equals(action)||"changeDateDone".equals(action)) { 
			PrintWriter out = res.getWriter();
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String appointmentOrderNo = req.getParameter("appointmentOrderNo");
				int appointmentStatus = 0;
				if("transferDate".equals(action))
				appointmentStatus =4; //調課
				if("changeDateDone".equals(action))
				appointmentStatus =1; //恢復 進行中
				if("finishAP".equals(action))
				appointmentStatus =0; //完課
				
				/***************************2.開始查詢資料*****************************************/
				AppointmentOrderService aptmtSvc = new AppointmentOrderService();

				JSONObject j = new JSONObject(aptmtSvc.getOneAppointmentOrder(appointmentOrderNo));

				Integer appointmentPrice = j.getInt("appointmentPrice");

				aptmtSvc.updatePrice(appointmentOrderNo, appointmentPrice,appointmentStatus);

			} catch (Exception e) {
				out.print(e.getMessage());
			}

		}
	

        if ("insert".equals(action)) {
			PrintWriter out = res.getWriter();
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memberNo = req.getParameter("memberNo");
				String coachNo = req.getParameter("coachNo");
							
				Integer appointmentPrice = 0;


				
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//取得當前日期+改格式			
				Timestamp orderTime = java.sql.Timestamp.valueOf(bartDateFormat.format(timestamp));
				
				Integer appointmentStatus = 3;// 0:已完成(已上課) 1:進行中(已付款,未上課) 2:確認中 3:等待教練報價  4:申請調課 5:退課
				String appointmentLocation = req.getParameter("appointmentLocation").trim();
				if (appointmentLocation == null || appointmentLocation.trim().length() == 0) {
					errorMsgs.add("請輸入上課地點");
				}				
				
				String appointmentDemand = req.getParameter("appointmentDemand").trim();
				if (appointmentDemand == null || appointmentDemand.trim().length() == 0) {
					errorMsgs.add("請輸入預約需求");
				}				
				
				AppointmentOrderVO appointmentOrderVO = new AppointmentOrderVO();
				appointmentOrderVO.setMemberNo(memberNo);
				appointmentOrderVO.setCoachNo(coachNo);
				appointmentOrderVO.setAppointmentLocation(appointmentLocation);
				appointmentOrderVO.setAppointmentPrice(appointmentPrice);
				appointmentOrderVO.setAppointmentDemand(appointmentDemand);

				List<AppointmentOrderDetailVO> aodVO = new ArrayList<>();
				
				try {
					String str = req.getParameter("appointmentDate");
					ArrayList<String> list = new ArrayList<>(Arrays.asList(str.split(",")));

					for(String date1:list) {
						AppointmentOrderDetailVO appointmentOrderDetailVO = new AppointmentOrderDetailVO();
						appointmentOrderDetailVO.setAppointmentDate(java.sql.Date.valueOf(date1));
						aodVO.add(appointmentOrderDetailVO);
					}
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請選擇預約日期!");
				}				
				
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("appointmentOrderVO", appointmentOrderVO);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/appointment_order/MemSendAppointment.jsp");
//					failureView.forward(req, res);
//					return;
//				}

				/***************************2.開始新增資料***************************************/
				AppointmentOrderService apmtSvc = new AppointmentOrderService();
				appointmentOrderVO = apmtSvc.addAppointmentOrderXDetail(memberNo, coachNo, appointmentPrice, orderTime, appointmentStatus, appointmentLocation, appointmentDemand,aodVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
			out.print(true);
//				String url = "/front-end/appointment_order/MemAppointmentList.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				out.print(e);
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/appointment_order/MemAppointmentList.jsp");
//				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { //取消訂單
	
			try {
				/***************************1.接收請求參數***************************************/
				String appointmentOrderNo = new String(req.getParameter("appointmentOrderNo"));						
				/***************************2.開始刪除資料***************************************/
				AppointmentOrderDetailService apmtdSvc = new AppointmentOrderDetailService();
				apmtdSvc.deleteDetailbyOrderNo(appointmentOrderNo);	
				AppointmentOrderService apmtSvc = new AppointmentOrderService();
				apmtSvc.deleteAppointmentOrder(appointmentOrderNo);			

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if ("get_coach_appointment_date_ajax".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String coachNo = req.getParameter("coachNo");
				AppointmentOrderService apSvc = new AppointmentOrderService();
				List<String> coapDate = apSvc.getCoachCertainDate(coachNo);
				
				Object[] array = coapDate.toArray();
		        String s = Arrays.toString(array);
				out.print(s);
				
			} catch (Exception e) {
				out.print(e);
			}	
		}	
		if ("get_mem_appointment_date_ajax".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String memberNo = req.getParameter("memberNo");
				AppointmentOrderService apSvc = new AppointmentOrderService();
				List<String> memapDate = apSvc.getMemCertainDate(memberNo);
				
				Object[] array = memapDate.toArray();
		        String s = Arrays.toString(array);
				out.print(s);
				
			} catch (Exception e) {
				out.print(e);
			}	
		}	
		if ("get_appointment_ajax".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String appointmentOrderNo = req.getParameter("appointmentOrderNo");
				AppointmentOrderService apSvc = new AppointmentOrderService();
				String apdetail = apSvc.getOneAppointmentOrder(appointmentOrderNo);
				out.print(apdetail);
				return;
			} catch (Exception e) {
				out.print(e);
			}	
		}
		if ("get_appointment_detail_ajax".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String appointmentOrderNo = req.getParameter("appointmentOrderNo");
				AppointmentOrderDetailService apdSvc = new AppointmentOrderDetailService();
				String apdetail = apdSvc.getDetailByAppointmentOrder_json(appointmentOrderNo);
				out.print(apdetail);
				return;
			} catch (Exception e) {
				out.print(e);
			}	
		}
		if ("get_appointment_memName_ajax".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String coachNo = req.getParameter("coachNo");
				String lessonDate = req.getParameter("lessonDate");
				AppointmentOrderService apSvc = new AppointmentOrderService();
				String name = apSvc.getCoachCertainDate2(coachNo, lessonDate);

				out.print(name);
				
			} catch (Exception e) {
				out.print(e);
			}	
		}			
		
		
	}

}
