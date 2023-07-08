package com.qa.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.qa.model.QaService;
import com.qa.model.QaVO;

public class QaServlet extends HttpServlet {
	
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
				String str = req.getParameter("qa_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入QA編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qa/select_page.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				String qa_no = null;
				try {
					qa_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("QA編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qa/select_page.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				QaService qaSvc = new QaService();
				QaVO qaVO = qaSvc.getOneQa(qa_no);
				if (qaVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty() ) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qa/select_page.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("qaVO", qaVO);
				String url = "/back-end/qa/listOneQa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qa/select_page.jsp");
				failureView.forward(req,  res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數****************************************/
				String qa_no = new String(req.getParameter("qa_no"));
				
				/***************************2.開始查詢資料****************************************/
				QaService qaSvc = new QaService();
				QaVO qaVO = qaSvc.getOneQa(qa_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("qaVO", qaVO);
				String url = "/back-end/qa/update_qa_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,  res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qa/Q&A.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
	
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String qa_no = req.getParameter("qa_no");			
				String q_content = req.getParameter("q_content").trim();
				if (q_content == null || q_content.trim().length() == 0) {
					errorMsgs.add("QA請勿空白");
				}
				
				String a_content = req.getParameter("a_content").trim();
				if (a_content == null || a_content.trim().length() == 0) {
					errorMsgs.add("QA請勿空白");
				}

				
				QaVO qaVO = new QaVO();
				qaVO.setQa_no(qa_no);
				qaVO.setQ_content(q_content);
				qaVO.setA_content(a_content);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("qaVO", qaVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/qa/update_qa_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				QaService qaSvc = new QaService();
				qaVO = qaSvc.updateQa(qa_no, q_content, a_content);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("qaVO", qaVO);
				String url = "/back-end/qa/Q&A.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/qa/update_qa_input.jsp");
				failureView.forward(req, res);		
		}
	}
		
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
				
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String q_content = req.getParameter("q_content").trim();
				if (q_content == null || q_content.trim().length() == 0) {
					errorMsgs.add("qa請勿空白");
				}
				
				String a_content = req.getParameter("a_content").trim();
				if (a_content == null || a_content.trim().length() == 0) {
					errorMsgs.add("qa請勿空白");
				}
						
				QaVO qaVO = new QaVO();
				qaVO.setQ_content(q_content);
				qaVO.setA_content(a_content);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("qaVO", qaVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qa/addQa.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				QaService qaSvc = new QaService();
				qaVO = qaSvc.addQa(q_content, a_content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/qa/Q&A.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);	
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/qa/addQa.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String qa_no = new String(req.getParameter("qa_no"));
				
				/***************************2.開始刪除資料***************************************/
				QaService qaSvc = new QaService();
				qaSvc.deleteQa(qa_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/qa/Q&A.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/qa/Q&A.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
