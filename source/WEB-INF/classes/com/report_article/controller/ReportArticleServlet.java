package com.report_article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.report_article.model.ReportArticleService;
import com.report_article.model.ReportArticleVO;



public class ReportArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("image/gif");
		if ("getOne_For_Display".equals(action)) { // 來自article_select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				
//				
//				
//				
//				
//				
//				
//				
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/article_select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 2.開始查詢資料 *****************************************/
//				ArticleService articleSvc = new ArticleService();
//				
//				
//				if (articleVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/article_select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
//				String url = "/front-end/gym_index/NewlistOneArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/article_select_page.jsp");
//				failureView.forward(req, res);
			}
//		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String article_no = req.getParameter("article_no");
               
				/*************************** 2.開始查詢資料 ****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/article/update_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/NewlistAllArticle.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String article_no = req.getParameter("article_no");
				String member_no = req.getParameter("member_no");
				String report_article_no = req.getParameter("report_article_no");
				String report_article_content = req.getParameter("report_article_content");
				String report_article_reason = req.getParameter("report_article_reason");

				Integer report_article_status = Integer.parseInt(req.getParameter("report_article_status"));

				Timestamp report_article_time = null;
				try {
					report_article_time = java.sql.Timestamp.valueOf(req.getParameter("report_article_time"));
					
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
				}
				ReportArticleVO reportArticleVO = new ReportArticleVO();
				reportArticleVO.setArticle_no(article_no);
				reportArticleVO.setMember_no(member_no);
				reportArticleVO.setReport_article_no(report_article_no);
				reportArticleVO.setReport_article_content(report_article_content);
				reportArticleVO.setReport_article_reason(report_article_reason);
				reportArticleVO.setReport_article_status(report_article_status);
				reportArticleVO.setReport_article_time(report_article_time);
			
//				 Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportArticleVO", reportArticleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/update_article_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				ReportArticleService reportArticleSvc = new ReportArticleService();
				reportArticleVO = reportArticleSvc.updateReportArticle(report_article_no, report_article_content, report_article_time, report_article_status, article_no, member_no, report_article_reason);
//				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("reportArticleVO", reportArticleVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/report_article/report_article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/update_article_input.jsp");
				failureView.forward(req, res);

			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String report_article_content = req.getParameter("report_article_content");

				
				Timestamp report_article_time =new Timestamp( System.currentTimeMillis() );

				Integer report_article_status = 0;

				String article_no = req.getParameter("article_no");
                String report_article_reason=req.getParameter("report_article_reason");
				String member_no=req.getParameter("member_no");

				ReportArticleVO reportArticleVO = new ReportArticleVO();

				reportArticleVO.setReport_article_content(report_article_content);
				reportArticleVO.setArticle_no(article_no);
				reportArticleVO.setReport_article_status(report_article_status);
				reportArticleVO.setReport_article_time(report_article_time);
				reportArticleVO.setMember_no(member_no);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportArticleVO", reportArticleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				boolean sweetalert=true;
				req.setAttribute("sweetalert",sweetalert );
				/*************************** 2.開始新增資料 ***************************************/
				ReportArticleService reportArticleSvc = new ReportArticleService();
				reportArticleVO = reportArticleSvc.addReportArticle(report_article_content, report_article_time, report_article_status, article_no, member_no, report_article_reason);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);

				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				
              
                String url = "/front-end/article/NewlistOneArticle.jsp?sweetalert=true";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
                /*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String report_article_no =req.getParameter("report_article_no");

				/*************************** 2.開始刪除資料 ***************************************/
				ReportArticleService articleSvc = new ReportArticleService();
				articleSvc.deleteReportArticle(report_article_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/report_article/report_article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getReportModal".equals(action)) {
			try {
				// Retrieve form parameters.
				
				String article_no = req.getParameter("article_no");
				
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO=articleSvc.getOneArticle(article_no);

				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/article/NewlistOneArticle.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
			
			
			
			
		}
//		if ("query".equals(action)) {
//			List<ArticleVO> list = new ArrayList<ArticleVO>();
//			String article_no=req.getParameter("article_no");
//			String article_title=req.getParameter("article_title");
//			String article_content=req.getParameter("article_content");
//			String article_glass=req.getParameter("article_glass");
//			
//			
//			ArticleService articleSvc = new ArticleService();
//			list=articleSvc.query(article_no,article_title,article_content,article_glass);
//			
//			req.setAttribute("list", list); 
//			String url = "/front-end/article/listAllArticle.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//			successView.forward(req, res);
//		}
		
	}
}