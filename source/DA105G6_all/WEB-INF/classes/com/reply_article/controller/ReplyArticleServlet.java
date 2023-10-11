package com.reply_article.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.reply_article.model.ReplyArticleService;
import com.reply_article.model.ReplyArticleVO;

public class ReplyArticleServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("reply_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/reply_article_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String reply_no = null;
				try {
					reply_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("回覆編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ReplyArticleService replyArticleSvc = new ReplyArticleService();
				ReplyArticleVO replyArticleVO = replyArticleSvc.getOneReplyArticle(reply_no);
				if (replyArticleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/reply_article_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("replyArticleVO", replyArticleVO); // 資料庫取出的reply_articleVO物件,存入req
				String url = "/reply_article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneReplyArticle.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllReplyArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String reply_no = req.getParameter("reply_no");

				/*************************** 2.開始查詢資料 ****************************************/
				ReplyArticleService replyArticleSvc = new ReplyArticleService();
				ReplyArticleVO reply_articleVO = replyArticleSvc.getOneReplyArticle(reply_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("reply_articleVO", reply_articleVO); // 資料庫取出的reply_articleVO物件,存入req
				String url = "/front-end/reply_article/update_reply_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_reply_article_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/reply_article/listAllReplyArticle.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_reply_article_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			String reply_no = req.getParameter("reply_no");
			System.out.println(reply_no);
			String reply_content = req.getParameter("reply_content");
			System.out.println(reply_content);
			if (reply_content == null || reply_content.trim().length() == 0) {
				errorMsgs.add("回覆內容: 請勿空白");
			}
			Timestamp reply_time = null;
			try {
				reply_time = Timestamp.valueOf(req.getParameter("reply_time"));

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
			}
			
			LocalDate date = LocalDate.now();
			Timestamp last_updated = new Timestamp( System.currentTimeMillis() );

			

			String article_no = req.getParameter("article_no");

			String member_no = req.getParameter("member_no");
			String coach_no = req.getParameter("coach_no");
			ReplyArticleVO replyArticleVO = new ReplyArticleVO();
			replyArticleVO.setReply_content(reply_content);
			replyArticleVO.setReply_time(reply_time);
			replyArticleVO.setLast_updated(last_updated);
			replyArticleVO.setArticle_no(article_no);
			replyArticleVO.setMember_no(member_no);
			replyArticleVO.setCoach_no(coach_no);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("reply_articleVO", replyArticleVO); // 含有輸入格式錯誤的reply_articleVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/reply_article/listOneArticle.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			/*************************** 2.開始修改資料 *****************************************/
			ReplyArticleService replyArticleSvc = new ReplyArticleService();
			if (member_no == null) {
				replyArticleVO = replyArticleSvc.updateReplyArticle(reply_no, reply_content, reply_time, last_updated,
						article_no, null, coach_no);
			} else {
				replyArticleVO = replyArticleSvc.updateReplyArticle(reply_no, reply_content, reply_time, last_updated,
						article_no, member_no, null);
			}
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(article_no);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("reply_articleVO", replyArticleVO); // 資料庫update成功後,正確的的reply_articleVO物件,存入req
			req.setAttribute("articleVO", articleVO);
			String url = "/front-end/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneReplyArticle.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/reply_article/update_reply_article_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addReplyArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String reply_content = req.getParameter("reply_content");
				if (reply_content == null) {
					errorMsgs.add("回覆內容:不得為空");
				}
				LocalDate date = LocalDate.now();
				Timestamp reply_time =  new Timestamp( System.currentTimeMillis() );

				

				Timestamp last_updated = new Timestamp( System.currentTimeMillis() );
				
				String article_no = req.getParameter("article_no");
				String member_no = req.getParameter("member_no");
				String coach_no = req.getParameter("coach_no");
				ReplyArticleVO replyArticleVO = new ReplyArticleVO();
				replyArticleVO.setReply_content(reply_content);
				replyArticleVO.setReply_time(reply_time);
				replyArticleVO.setLast_updated(last_updated);
				replyArticleVO.setArticle_no(article_no);
				replyArticleVO.setMember_no(member_no);
				replyArticleVO.setCoach_no(coach_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reply_articleVO", replyArticleVO); // 含有輸入格式錯誤的reply_articleVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/reply_article/addReplyArticle.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ReplyArticleService replyArticleSvc = new ReplyArticleService();
				
				if (member_no == null) {
					replyArticleVO = replyArticleSvc.addReplyArticle( reply_content, reply_time, last_updated,
							article_no, null, coach_no);
				} else {
					replyArticleVO = replyArticleSvc.addReplyArticle( reply_content, reply_time, last_updated,
							article_no, member_no, null);
				}
               
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				ArticleService articleSvc=new ArticleService();
				ArticleVO articleVO=articleSvc.getOneArticle(article_no);
				Integer article_replies=articleVO.getArticle_replies();
				articleVO.setArticle_replies(article_replies+1);
				req.setAttribute("articleVO", articleVO);
				articleSvc.updateArticle(articleVO.getArticle_no(), articleVO.getArticle_title(), articleVO.getArticle_content(), articleVO.getArticle_glass(), articleVO.getArticle_visitors(), articleVO.getArticle_replies(), articleVO.getArticle_status(), articleVO.getArticle_post_time(), articleVO.getArticle_editart_lasttime(), articleVO.getMember_no(), articleVO.getCoach_no(),articleVO.getArticle_picture());
				String url = "/front-end/article/NewlistOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllReplyArticle.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/reply_article/addReplyArticle.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllReplyArticle.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
            String article_no=req.getParameter("article_no");
            System.out.println(article_no);
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String reply_no = new String(req.getParameter("reply_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				ReplyArticleService replyArticleSvc = new ReplyArticleService();
				replyArticleSvc.deleteReplyArticle(reply_no);
				
                
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				ArticleService articleSvc=new ArticleService();
				ArticleVO articleVO=articleSvc.getOneArticle(article_no);
				List<ReplyArticleVO> list=articleSvc.getAllReplyByArticleNo(article_no);
				list.size();
				articleSvc.updateArticle(articleVO.getArticle_no(), articleVO.getArticle_title(), articleVO.getArticle_content(), articleVO.getArticle_glass(), articleVO.getArticle_visitors(), list.size(), articleVO.getArticle_status(), articleVO.getArticle_post_time(), articleVO.getArticle_editart_lasttime(), articleVO.getMember_no(), articleVO.getCoach_no(),articleVO.getArticle_picture());
				articleVO=articleSvc.getOneArticle(article_no);
				req.setAttribute("articleVO", articleVO);
				String url = "/front-end/article/NewlistOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listOneArticle.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
