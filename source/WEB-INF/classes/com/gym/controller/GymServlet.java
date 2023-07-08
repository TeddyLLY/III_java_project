package com.gym.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.gym.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GymServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {	// 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("gym_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入健身房編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/gym/select_page.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				String gym_no = null;
				try {
					gym_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("健身房編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/gym/select_page.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				GymService gymSvc = new GymService();
				GymVO gymVO = gymSvc.getOneGym(gym_no);
				if (gymVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty() ) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/gym/select_page.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("gymVO", gymVO);
				String url = "/back-end/gym/listOneGym.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/gym/select_page.jsp");
				failureView.forward(req,  res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String gym_no = new String(req.getParameter("gym_no"));
				
				/***************************2.開始查詢資料****************************************/
				GymService gymSvc = new GymService();
				GymVO gymVO = gymSvc.getOneGym(gym_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("gymVO", gymVO);
				String url = "/back-end/gym/update_gym_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,  res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/gym/gym.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String gym_no = req.getParameter("gym_no");
				
				String gym_name = req.getParameter("gym_name");
				String gym_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (gym_name == null || gym_name.trim().length() == 0) {
					errorMsgs.add("健身房名稱:請勿空白");
					
				}else if (!gym_name.trim().matches(gym_nameReg)){
					errorMsgs.add("只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String gym_latitude = req.getParameter("gym_latitude").trim();
				if (gym_latitude == null || gym_latitude.trim().length() == 0) {
					errorMsgs.add("latitude請勿空白");
				}
				
				String gym_longitude = req.getParameter("gym_longitude").trim();
				if (gym_longitude == null || gym_longitude.trim().length() == 0) {
					errorMsgs.add("longitude請勿空白");
				}
				
				String gym_address = req.getParameter("gym_address").trim();
				if (gym_address == null || gym_address.trim().length() == 0) {
					errorMsgs.add("address請勿空白");
				}
				
				
				String gym_content = req.getParameter("gym_content").trim();
				if (gym_content == null || gym_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}	
				
//				GymService gymSvc2 = new GymService();
//				GymVO gymVO2 = gymSvc2.getOneGym(gym_no);
//								
//								byte[] gym_photo = null;
//								InputStream in = req.getPart("gym_photo").getInputStream();
//								if (in.available() != 0) {
//									gym_photo = new byte[in.available()];
//									in.read(gym_photo);
//									in.close();
//								}else {
//				gym_photo=gymVO2.getGym_photo();
//								}
//				
				
				GymVO gymVO = new GymVO();
				gymVO.setGym_no(gym_no);
				gymVO.setGym_name(gym_name);
				gymVO.setGym_latitude(gym_latitude);
				gymVO.setGym_longitude(gym_longitude);
				gymVO.setGym_address(gym_address);
				gymVO.setGym_content(gym_content);
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gymVO", gymVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/gym/update_gym_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				GymService gymSvc = new GymService();
				gymVO = gymSvc.updateGym(gym_no, gym_name, gym_latitude, gym_longitude, gym_address,gym_content);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("gymVO", gymVO);
				String url = "/back-end/gym/gym.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/gym/update_gym_input.jsp");
				failureView.forward(req, res);		
		}
	}
		
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
				
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String gym_name = req.getParameter("gym_name");
				String gym_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (gym_name == null || gym_name.trim().length() == 0) {
					errorMsgs.add("健身房名稱:請勿空白");
					
				}else if (!gym_name.trim().matches(gym_nameReg)){
					errorMsgs.add("只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String gym_latitude = req.getParameter("gym_latitude").trim();
				if (gym_latitude == null || gym_latitude.trim().length() == 0) {
					errorMsgs.add("latitude請勿空白");
				}
				
				String gym_longitude = req.getParameter("gym_longitude").trim();
				if (gym_longitude == null || gym_longitude.trim().length() == 0) {
					errorMsgs.add("longitude請勿空白");
				}
				
				String gym_address = req.getParameter("gym_address").trim();
				if (gym_address == null || gym_address.trim().length() == 0) {

					errorMsgs.add("address請勿空白");
				}
				
				String gym_content = req.getParameter("gym_content").trim();
				if (gym_content == null || gym_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				GymVO gymVO = new GymVO();
				gymVO.setGym_name(gym_name);
				gymVO.setGym_latitude(gym_latitude);
				gymVO.setGym_longitude(gym_longitude);
				gymVO.setGym_address(gym_address);
				gymVO.setGym_content(gym_content);
		
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gymVO", gymVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/gym/addGym.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				GymService gymSvc = new GymService();
				gymVO = gymSvc.addGym(gym_name, gym_latitude, gym_longitude, gym_address,gym_content);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/gym/gym.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);	
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/gym/addGym.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String gym_no = new String(req.getParameter("gym_no"));
				
				/***************************2.開始刪除資料***************************************/
				GymService gymSvc = new GymService();
				gymSvc.deleteGym(gym_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/gym/gym.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/gym/gym.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
