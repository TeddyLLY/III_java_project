package com.news.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.news.model.NewsService;
import com.news.model.NewsVO;


public class NewsServlet extends HttpServlet {
	
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
				String str = req.getParameter("news_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入news編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/select_page.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				String news_no = null;
				try {
					news_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("news編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/select_page.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(news_no);
				if (newsVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty() ) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/select_page.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("newsVO", newsVO);
				String url = "/back-end/news/news.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/select_page.jsp");
				failureView.forward(req,  res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String news_no = new String(req.getParameter("news_no"));
				
				/***************************2.開始查詢資料****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(news_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("newsVO", newsVO);
				String url = "/back-end/news/update_news_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,  res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/news.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String news_no = req.getParameter("news_no");			
				String news_title = req.getParameter("news_title").trim();
				if (news_title == null || news_title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				
				String news_content = req.getParameter("news_content").trim();
				if (news_content == null || news_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				java.sql.Date news_time = null;
				try {
					news_time = java.sql.Date.valueOf(req.getParameter("news_time").trim());
				} catch (IllegalArgumentException e) {
					news_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				NewsVO newsVO = new NewsVO();
				newsVO.setNews_no(news_no);
				newsVO.setNews_title(news_title);
				newsVO.setNews_content(news_content);
				newsVO.setNews_time(news_time);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/news/update_news_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.updateNews(news_no, news_title, news_content, news_time);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("newsVO", newsVO);
				String url = "/back-end/news/news.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/news/update_news_input.jsp");
				failureView.forward(req, res);		
		}
	}
		
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
				
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String news_title = req.getParameter("news_title").trim();
				if (news_title == null || news_title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
					
				String news_content = req.getParameter("news_content").trim();
				if (news_content == null || news_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				java.sql.Date news_time = null;
				try {
					news_time = java.sql.Date.valueOf(req.getParameter("news_time").trim());
				} catch (IllegalArgumentException e) {
					news_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				NewsVO newsVO = new NewsVO();
				newsVO.setNews_title(news_title);
				newsVO.setNews_content(news_content);
				newsVO.setNews_time(news_time);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/addNews.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.addNews(news_title,news_content, news_time);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/news/news.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);	
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/news/addNews.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String news_no = new String(req.getParameter("news_no"));
				
				/***************************2.開始刪除資料***************************************/
				NewsService newsSvc = new NewsService();
				newsSvc.deleteNews(news_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/news/news.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/news.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
