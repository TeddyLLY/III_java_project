package com.lesson_detail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.lesson_detail.model.LessonDetailService;
import com.lesson_detail.model.LessonDetailVO;


public class LessonDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LessonDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain;charset=UTF-8");
		String action = req.getParameter("action");
		
		if ("get_LessonDetail_By_Lesson".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("lessonNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課程編號");
				}
			
				String lessonNo = null;
				try {
					lessonNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lesson_detail/lessonDetail_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				LessonDetailService lessonDetailSvc = new LessonDetailService();
				List<LessonDetailVO> lessonDetailVO = lessonDetailSvc.getDetailByLesson(lessonNo);
				if (lessonDetailVO.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lesson_detail/lessonDetail_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("onlyForLessonDetail", lessonDetailVO);
				String url = "/front-end/lesson_detail/listOneLessonDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/lesson_detail/lessonDetail_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	       if ("insert".equals(action)) {  
				
				List<String> errorMsgs = new LinkedList<String>();

				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String lessonNo = req.getParameter("lessonNo");
					if (lessonNo == null || lessonNo.trim().length() == 0) {
						errorMsgs.add("課程編號: 請勿空白");
					}

					java.sql.Date lessonDate = null;
					try {
						lessonDate = java.sql.Date.valueOf(req.getParameter("lessonDate").trim());
					} catch (IllegalArgumentException e) {
						lessonDate=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}	
					
					LessonDetailVO lessonDetailVO = new LessonDetailVO();
					lessonDetailVO.setLessonNo(lessonNo);
					lessonDetailVO.setLessonDate(lessonDate);



					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("LessonDetailVO", lessonDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/favorite_lesson/addFavoriteLesson.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					LessonDetailService lessonDetailSvc = new LessonDetailService();
					lessonDetailVO = lessonDetailSvc.addLessonDetail(lessonNo,lessonDate);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/				
					String url = "/back-end/favorite_lesson/listFavoriteLessonM.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/favorite_lesson/addFavoriteLesson.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if ("delete".equals(action)) { // 來自listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					String lessonDetailNo = new String(req.getParameter("lessonDetailNo"));
					
					/***************************2.開始刪除資料***************************************/
					LessonDetailService lessonDetailSvc = new LessonDetailService();
					lessonDetailSvc.deleteLessonDetailOnly(lessonDetailNo);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/back-end/lesson_detail/lessonDetail_select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lesson_detail/lessonDetail_select_page.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if ("get_one_lesson_All_date_ajax".equals(action)) { 
				PrintWriter out = res.getWriter();

				try {
					String lessonNo = req.getParameter("lessonNo");
					LessonDetailService ldSvc = new LessonDetailService();
					String leDate = ldSvc.getDetailByLesson_ajax(lessonNo);

					out.print(leDate);
					
				} catch (Exception e) {
					out.print(e);
				}	
			}
								
			
	}

}
