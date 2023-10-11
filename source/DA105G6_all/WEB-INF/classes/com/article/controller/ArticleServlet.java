package com.article.controller;

import com.article.model.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ArticleServlet extends HttpServlet {

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

//			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("article_no");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/article_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String article_no = null;
				try {
					article_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/article_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);
				
				if (articleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/article_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				Integer article_visitors=articleVO.getArticle_visitors();
				articleVO.setArticle_visitors(article_visitors+1);
				articleSvc.updateArticle(articleVO.getArticle_no(), articleVO.getArticle_title(), articleVO.getArticle_content(), articleVO.getArticle_glass(), articleVO.getArticle_visitors(), articleVO.getArticle_replies(), articleVO.getArticle_status(), articleVO.getArticle_post_time(), articleVO.getArticle_editart_lasttime(), articleVO.getMember_no(), articleVO.getCoach_no(),articleVO.getArticle_picture());
				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/article/NewlistOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/article_select_page.jsp");
//				failureView.forward(req, res);
//			}
		}

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
				String article_title = req.getParameter("article_title");
				if (article_title == null || article_title.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				}
				String article_content = req.getParameter("article_content").trim();
				if (article_content == null || article_content.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				}System.out.println(article_content);
				String article_glass = req.getParameter("article_glass").trim();
				
				java.sql.Timestamp article_editart_lasttime = new Timestamp( System.currentTimeMillis() );
				Integer article_visitors = Integer.parseInt(req.getParameter("article_visitors"));
				try {
					
					article_visitors = new Integer(req.getParameter("article_visitors"));
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
				}
				Integer article_replies = Integer.parseInt(req.getParameter("article_replies"));
				try {

					article_replies = new Integer(req.getParameter("article_replies"));
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
				}
				Integer article_status = Integer.parseInt(req.getParameter("article_status"));
				try {

					article_status = new Integer(req.getParameter("article_status"));
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
				}
				byte[] article_picture =null;
			    InputStream input = req.getPart("article_picture").getInputStream();
			    if (input.available()!=0) {
			    	article_picture = new byte[input.available()];
			     input.read(article_picture);
			    }

				java.sql.Timestamp article_post_time = null;
				try {
					article_post_time = java.sql.Timestamp.valueOf(req.getParameter("article_post_time"));
					
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
				}
				String member_no=null;
				String coach_no=null;
				try {
					 member_no=req.getParameter("member_no");
				}catch(NullPointerException e) {
					  member_no=null;
				}
				try {
					 coach_no=req.getParameter("coach_no");
				}catch(NullPointerException e) {
					 coach_no=null;
				}
				
				
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArticle_no(article_no);
				articleVO.setArticle_title(article_title);
				articleVO.setArticle_content(article_content);
				articleVO.setArticle_glass(article_glass);
				articleVO.setArticle_visitors(article_visitors);
				articleVO.setArticle_replies(article_replies);
				articleVO.setArticle_status(article_status);
				articleVO.setArticle_post_time(article_post_time);
				articleVO.setArticle_editart_lasttime(article_editart_lasttime);
				articleVO.setMember_no(member_no);
				articleVO.setCoach_no(coach_no);
				articleVO.setArticle_picture(article_picture);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/update_article_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ArticleService articleSvc = new ArticleService();
				if(member_no==null) {
				articleVO = articleSvc.updateArticle(article_no, article_title, article_content, article_glass,
						article_visitors, article_replies, article_status, article_post_time,
						article_editart_lasttime,null,coach_no,article_picture);
				}else {
					articleVO = articleSvc.updateArticle(article_no, article_title, article_content, article_glass,
							article_visitors, article_replies, article_status, article_post_time,
							article_editart_lasttime,member_no,null,article_picture);
				}

//				articleVO = articleSvc.getOneArticle("A001");
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/article/NewlistOneArticle.jsp";
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
				String article_title = req.getParameter("article_title");
				if(article_title.length()>=30) {
					errorMsgs.add("文章標題: 不得超過30個字");
					
				}

				if (article_title == null || article_title.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				}

				String article_content = req.getParameter("article_content");
				if (article_content == null || article_content.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
					
				}
				System.out.println(article_content);
				Integer article_visitors = 0;
				Integer article_replies = 0;
                
				byte[] article_picture =null;
			    InputStream input = req.getPart("article_picture").getInputStream();
			    if (input.available()!=0) {
			    	article_picture = new byte[input.available()];
			     input.read(article_picture);
			    }
				
				java.sql.Timestamp article_post_time = new Timestamp( System.currentTimeMillis() );

				String article_glass = req.getParameter("article_glass");

				Integer article_status = Integer.parseInt(req.getParameter("article_status"));

				java.sql.Timestamp article_editart_lasttime = new Timestamp( System.currentTimeMillis() );
				
				String member_no=req.getParameter("member_no");
				if(member_no=="") {
					member_no=null;
				}
				String coach_no=req.getParameter("coach_no");
				if(coach_no=="") {
					coach_no=null;
				}
				ArticleVO articleVO = new ArticleVO();

				articleVO.setArticle_title(article_title);
				articleVO.setArticle_content(article_content);
				articleVO.setArticle_glass(article_glass);
				articleVO.setArticle_visitors(article_visitors);
				articleVO.setArticle_replies(article_replies);
				articleVO.setArticle_status(article_status);
				articleVO.setArticle_post_time(article_post_time);
				articleVO.setArticle_picture(article_picture);
				articleVO.setArticle_editart_lasttime(article_editart_lasttime);
				articleVO.setMember_no(member_no);
				articleVO.setCoach_no(coach_no);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/NewAddArticle.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ArticleService articleSvc = new ArticleService();
				if(member_no==null) {
				articleVO = articleSvc.addArticle(article_title, article_content, article_glass, article_visitors,
						article_replies, article_status, article_post_time, article_editart_lasttime,null,coach_no,article_picture);
				}else {
					articleVO = articleSvc.addArticle(article_title, article_content, article_glass, article_visitors,
							article_replies, article_status, article_post_time, article_editart_lasttime,member_no,null,article_picture);
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/article/NewlistAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
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
				String article_no = new String(req.getParameter("article_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(article_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/article/NewlistAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/NewlistAllArticle.jsp");
				failureView.forward(req, res);
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