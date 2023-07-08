package com.system_news.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system_news.model.SystemNewsService;
import com.system_news.model.SystemNewsVO;

@WebServlet("/SystemNewsBackServlet")
public class SystemNewsBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SystemNewsBackServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

////////////////////////////////////////////////////////////////////////////////////////////////////
//以下為後台

		if ("deleteOneNews".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String system_news_no = new String(req.getParameter("system_news_no"));
				SystemNewsService systemNewsSvc = new SystemNewsService();
				systemNewsSvc.deleteOneSystemNews(system_news_no);

				String url = req.getContextPath() + "/back-end/system-news-back/review_news.jsp";
				res.sendRedirect(url);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗" + e.getMessage());// TODO: handle exception
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/system-news-back/review_news.jsp");
				failureView.forward(req, res);
			}
		}
////////////////////////////////////////////////////////////////////////////////////////////////////

		if ("insertNews".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String member_no = req.getParameter("member_no").trim();
				String coach_no = req.getParameter("coach_no").trim();
				String system_content = req.getParameter("system_content").trim();
				if (system_content == null || system_content.trim().length() == 0) {
					errorMsgs.add("請輸入訊息內容");
				}
				java.sql.Timestamp system_time = new Timestamp(System.currentTimeMillis());

				SystemNewsVO systemNewsVO = new SystemNewsVO();
				systemNewsVO.setMember_no(member_no);
				systemNewsVO.setCoach_no(coach_no);
				systemNewsVO.setSystem_content(system_content);
				systemNewsVO.setSystem_time(system_time);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("systemNewsVO", systemNewsVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/system-news-back/review_news.jsp");
					failureView.forward(req, res);
					return;
				}

				SystemNewsService systemNewsSvc = new SystemNewsService();
				systemNewsSvc.insertOneSystemNews(member_no, coach_no, system_content, system_time);
				res.sendRedirect(req.getContextPath() + "/back-end/system-news-back/review_news.jsp");
			} catch (Exception e) {
				errorMsgs.add("新增失敗" + e.getMessage());// TODO: handle exception
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/system-news-back/review_news.jsp");
				failureView.forward(req, res);
			}
		}

////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("UpdateNews".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String system_news_no = new String(req.getParameter("system_news_no"));
				SystemNewsService systemNewsSvc = new SystemNewsService();
				SystemNewsVO systemNewsVO = systemNewsSvc.findOneSystemNews(system_news_no);
				req.setAttribute("systemNewsVO", systemNewsVO);
				String url = "/back-end/system-news-back/update_SystemNews_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());// TODO: handle exception
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/system-news-back/review_news.jsp");
				failureView.forward(req, res);
			}
		}

////////////////////////////////////////////////////////////////////////////////////////////////////	
		if ("updateOneNew".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String member_no = req.getParameter("member_no").trim();
				String coach_no = req.getParameter("coach_no").trim();
				String system_content = req.getParameter("system_content").trim();
				if (system_content == null || system_content.trim().length() == 0) {
					errorMsgs.add("請輸入訊息內容");
				}
				java.sql.Timestamp system_time = new Timestamp(System.currentTimeMillis());
				String system_news_no = req.getParameter("system_news_no");

				SystemNewsVO systemNewsVO = new SystemNewsVO();
				systemNewsVO.setMember_no(member_no);
				systemNewsVO.setCoach_no(coach_no);
				systemNewsVO.setSystem_content(system_content);
				systemNewsVO.setSystem_time(system_time);
				systemNewsVO.setSystem_news_no(system_news_no);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("systemNewsVO", systemNewsVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/system-news-back/update_SystemNews_input.jsp");
					failureView.forward(req, res);
					return;
				}

				SystemNewsService systemNewsSvc = new SystemNewsService();
				systemNewsVO = systemNewsSvc.updateOneSystemNews(member_no, coach_no, system_content, system_time,
						system_news_no);
				req.setAttribute("systemNewsVO", systemNewsVO);
				String url = "/back-end/system-news-back/review_news.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());// TODO: handle exception
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/system-news-back/update_SystemNews_input.jsp");
				failureView.forward(req, res);
			}
		}

//以下為base
//////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("findOneSystemNews".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String str = new String(req.getParameter("system_news_no"));
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入系統訊息編號");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/system-news/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				String system_news_no = null;
//
//				try {
//					system_news_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("系統訊息編號不正確");// TODO: handle exception
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/system-news/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				SystemNewsService systemNewsSvc = new SystemNewsService();
//				SystemNewsVO systemNewsVO = systemNewsSvc.findOneSystemNews(system_news_no);
//
//				if (systemNewsVO == null) {
//					errorMsgs.add("查無資料");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/system-news/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				req.setAttribute("systemNewsVO", systemNewsVO);
//				String url = "/back-end/system-news/listOneSystemNews.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/system-news/select_page.jsp");
//				failureView.forward(req, res);
//			}
//
//		}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("getOne_For_Update".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String system_news_no = new String(req.getParameter("system_news_no"));
//				SystemNewsService systemNewsSvc = new SystemNewsService();
//				SystemNewsVO systemNewsVO = systemNewsSvc.findOneSystemNews(system_news_no);
//				req.setAttribute("systemNewsVO", systemNewsVO);
//				String url = "/back-end/system-news/update_SystemNews_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料" + e.getMessage());// TODO: handle exception
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/system-news/listAllSystemNews.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("updateOneSystemNews".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String member_no = req.getParameter("member_no").trim();
//				String coach_no = req.getParameter("coach_no").trim();
//				String system_content = req.getParameter("system_content").trim();
//				if (system_content == null || system_content.trim().length() == 0) {
//					errorMsgs.add("請輸入訊息內容");
//				}
//				java.sql.Timestamp system_time = new Timestamp(System.currentTimeMillis());
//				String system_news_no = req.getParameter("system_news_no");
//
//				SystemNewsVO systemNewsVO = new SystemNewsVO();
//				systemNewsVO.setMember_no(member_no);
//				systemNewsVO.setCoach_no(coach_no);
//				systemNewsVO.setSystem_content(system_content);
//				systemNewsVO.setSystem_time(system_time);
//				systemNewsVO.setSystem_news_no(system_news_no);
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("systemNewsVO", systemNewsVO);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/system-news/update_SystemNews_input.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				SystemNewsService systemNewsSvc = new SystemNewsService();
//				systemNewsVO = systemNewsSvc.updateOneSystemNews(member_no, coach_no, system_content, system_time,
//						system_news_no);
//				req.setAttribute("systemNewsVO", systemNewsVO);
//				String url = "/back-end/system-news/listOneSystemNews.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗" + e.getMessage());// TODO: handle exception
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/system-news/update_SystemNews_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("insertOneSystemNews".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String member_no = req.getParameter("member_no").trim();
//				String coach_no = req.getParameter("coach_no").trim();
//				String system_content = req.getParameter("system_content").trim();
//				if (system_content == null || system_content.trim().length() == 0) {
//					errorMsgs.add("請輸入訊息內容");
//				}
//				java.sql.Timestamp system_time = new Timestamp(System.currentTimeMillis());
//
//				SystemNewsVO systemNewsVO = new SystemNewsVO();
//				systemNewsVO.setMember_no(member_no);
//				systemNewsVO.setCoach_no(coach_no);
//				systemNewsVO.setSystem_content(system_content);
//				systemNewsVO.setSystem_time(system_time);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("systemNewsVO", systemNewsVO);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/system-news/update_SystemNews_input.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				SystemNewsService systemNewsSvc = new SystemNewsService();
//				systemNewsVO = systemNewsSvc.insertOneSystemNews(member_no, coach_no, system_content, system_time);
//				req.setAttribute("systemNewsVO", systemNewsVO);
//				String url = "/back-end/system-news/listAllSystemNews.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("新增失敗" + e.getMessage());// TODO: handle exception
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/system-news/addSystemNews.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("deleteOneSystemNews".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String system_news_no = new String(req.getParameter("system_news_no"));
//				SystemNewsService systemNewsSvc = new SystemNewsService();
//				systemNewsSvc.deleteOneSystemNews(system_news_no);
//
//				String url = "/back-end/system-news/listAllSystemNews.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("刪除失敗" + e.getMessage());// TODO: handle exception
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/system-news/listAllSystemNews.jsp");
//				failureView.forward(req, res);
//			}
//		}
//////////////////////////////////////////////////////////////////////////////////////////////////////

	}// do post end

}// class end
