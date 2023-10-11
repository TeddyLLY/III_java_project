package com.authority.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.authority.model.AuthorityService;
import com.authority.model.AuthorityVO;



public class AuthorityServlet extends HttpServlet {
	
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
				String str = req.getParameter("employee_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("employee_no");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/listAllAuthority.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				String employee_no = null;
				try {
					employee_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("employee編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/listAllAuthority.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				AuthorityService authoritySvc = new AuthorityService();
				List<AuthorityVO> authorityVO = authoritySvc.getOneAuthority(employee_no);
				if (authorityVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty() ) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/listAllAuthority.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("authorityVO", authorityVO);
				String url = "/back-end/authority/listAllAuthority.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/listAllAuthority.jsp");
				failureView.forward(req,  res);
			}
		}
			
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
				
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String employee_no = req.getParameter("employee_no").trim();
				if (employee_no == null ||employee_no.trim().length() == 0) {
					errorMsgs.add("員工編號請勿空白");
				}
					
				String function_no = req.getParameter("function_no").trim();
			
				
				AuthorityVO authorityVO = new AuthorityVO();
				authorityVO.setEmployee_no(employee_no);
				authorityVO.setFunction_no(function_no);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("authorityVO", authorityVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/addAuthority.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AuthorityService authoritySvc = new AuthorityService();
				authorityVO = authoritySvc.addAuthority(employee_no, function_no);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/authority/listAllAuthority.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);	
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/authority/listAllAuthority.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String employee_no = req.getParameter("employee_no");

				/***************************2.開始刪除資料***************************************/
				AuthorityService authoritySvc = new AuthorityService();
				authoritySvc.deleteAuthority(employee_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/authority/listAllAuthority.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/authority/listAllAuthority.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
