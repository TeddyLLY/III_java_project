package com.report_coach.controller;

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

import com.coach.model.CoachService;
import com.coach.model.CoachVO;
import com.report_coach.model.ReportCoachService;
import com.report_coach.model.ReportCoachVO;

@WebServlet("/ReportCoachBackServlet")
public class ReportCoachBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReportCoachBackServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("updateStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				ReportCoachVO reportCoachVO = new ReportCoachVO();
				ReportCoachService reportCoachSvc = new ReportCoachService();

				String report_coach_no = req.getParameter("report_coach_no");
				reportCoachVO = reportCoachSvc.findOneReportCoach(report_coach_no);

				Integer report_coach_status = new Integer(req.getParameter("report_coach_status"));
				String coach_no = reportCoachVO.getCoach_no();
				if (report_coach_status == 1) {
					CoachService coachSvc = new CoachService();
					Integer coach_review = 4;
					Integer coach_auth = 0;
					coachSvc.CoachUpdateStatus(coach_review, coach_auth, coach_no);
				}
				String member_no = reportCoachVO.getMember_no();
				String report_coach_content = reportCoachVO.getReport_coach_content();
				java.sql.Timestamp report_coach_time = reportCoachVO.getReport_coach_time();
				reportCoachVO = reportCoachSvc.updateOneReportCoach(coach_no, member_no, report_coach_content,
						report_coach_time, report_coach_status, report_coach_no);
				errorMsgs.add("處理成功");
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/report-coach-back/report_coach.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage() + "處理失敗");
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/report-coach-back/report_coach.jsp");
				failView.forward(req, res);
			}
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("deleteOkReport".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String report_coach_no = new String(req.getParameter("report_coach_no"));

				ReportCoachService reportCoachSvc = new ReportCoachService();
				reportCoachSvc.deleteOneReportCoach(report_coach_no);
				String url = "/back-end/report-coach-back/report_coach.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				errorMsgs.add("刪除成功");

				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除檢舉教練事件失敗" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report-coach-back/report_coach.jsp");
				failureView.forward(req, res);
			}
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("findOneReportCoach".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = new String(req.getParameter("report_coach_no"));
				if (str == null || (str.trim().length() == 0)) {
					errorMsgs.add("請輸入編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/report-coach/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String report_coach_no = null;
				try {
					report_coach_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉教練編號格式錯誤");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/report-coach/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				ReportCoachService reportCoachSvc = new ReportCoachService();
				ReportCoachVO reportCoachVO = reportCoachSvc.findOneReportCoach(report_coach_no);
				if (reportCoachVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/report-coach/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("reportCoachVO", reportCoachVO);
				String url = "/back-end/report-coach/listOneReportCoach.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/report-coach/select_page.jsp");
				failureView.forward(req, res);
			}
		}

//以下為 base
////////////////////////////////////////////////////////////////
//
//		if ("getOne_For_Update".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				String report_coach_no = new String(req.getParameter("report_coach_no"));
//
//				ReportCoachService reportCoachSvc = new ReportCoachService();
//				ReportCoachVO reportCoachVO = reportCoachSvc.findOneReportCoach(report_coach_no);
//				req.setAttribute("reportCoachVO", reportCoachVO);
//
//				String url = "/back-end/report-coach/update_ReportCoach_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
//
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/report-coach/listAllReportCoach.jsp");
//
//				failureView.forward(req, res);
//
//			}
//
//		}
////////////////////////////////////////////////////////////////
//
//		if ("updateOneReportCoach".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String coach_no = new String(req.getParameter("coach_no"));
//				if (coach_no == null || coach_no.trim().length() == 0) {
//					errorMsgs.add("請輸入檢舉教練的教練編號");
//				}
//				String member_no = new String(req.getParameter("member_no"));
//				if (member_no == null || member_no.trim().length() == 0) {
//					errorMsgs.add("請輸入檢舉教練的會員編號");
//				}
//				String report_coach_content = new String(req.getParameter("report_coach_content"));
//				if (report_coach_content == null || report_coach_content.trim().length() == 0) {
//					errorMsgs.add("請輸入檢舉教練內容");
//				}
//				Timestamp report_coach_time = new Timestamp(System.currentTimeMillis());
//				Integer report_coach_status = new Integer(req.getParameter("report_coach_status"));
//				String report_coach_no = new String(req.getParameter("report_coach_no"));
//				if (report_coach_no == null || report_coach_no.trim().length() == 0) {
//					errorMsgs.add("請輸入檢舉教練編號");
//				}
//
//				ReportCoachVO reportCoachVO = new ReportCoachVO();
//				reportCoachVO.setCoach_no(coach_no);
//				reportCoachVO.setMember_no(member_no);
//				reportCoachVO.setReport_coach_content(report_coach_content);
//				reportCoachVO.setReport_coach_time(report_coach_time);
//				reportCoachVO.setReport_coach_status(report_coach_status);
//				reportCoachVO.setReport_coach_no(report_coach_no);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("reportCoachVO", reportCoachVO);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/report-coach/update_ReportCoach_input.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				ReportCoachService reportCoachSvc = new ReportCoachService();
//				reportCoachVO = reportCoachSvc.updateOneReportCoach(coach_no, member_no, report_coach_content,
//						report_coach_time, report_coach_status, report_coach_no);
//				req.setAttribute("reportCoachVO", reportCoachVO);
//				String url = "/back-end/report-coach/listOneReportCoach.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/report-coach/update_ReportCoach_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
////////////////////////////////////////////////////////////////
//
//		if ("insertOneReportCoach".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String coach_no = new String(req.getParameter("coach_no"));
//				if (coach_no == null || coach_no.trim().length() == 0) {
//					errorMsgs.add("請輸入檢舉教練的教練編號");
//				}
//				String member_no = new String(req.getParameter("member_no"));
//				if (member_no == null || member_no.trim().length() == 0) {
//					errorMsgs.add("請輸入檢舉教練的會員編號");
//				}
//				String report_coach_content = new String(req.getParameter("report_coach_content"));
//				if (report_coach_content == null || report_coach_content.trim().length() == 0) {
//					errorMsgs.add("請輸入檢舉教練內容");
//				}
//				Timestamp report_coach_time = new Timestamp(System.currentTimeMillis());
//				Integer report_coach_status = new Integer(req.getParameter("report_coach_status"));
//
//				ReportCoachVO reportCoachVO = new ReportCoachVO();
//				reportCoachVO.setCoach_no(coach_no);
//				reportCoachVO.setMember_no(member_no);
//				reportCoachVO.setReport_coach_content(report_coach_content);
//				reportCoachVO.setReport_coach_time(report_coach_time);
//				reportCoachVO.setReport_coach_status(report_coach_status);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("reportCoachVO", reportCoachVO);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/report-coach/addReportCoach.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				ReportCoachService reportCoachSvc = new ReportCoachService();
//				reportCoachVO = reportCoachSvc.insertOneReportCoach(coach_no, member_no, report_coach_content,
//						report_coach_time, report_coach_status);
//				req.setAttribute("reportCoachVO", reportCoachVO);
//				String url = "/back-end/report-coach/listAllReportCoach.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/report-coach/addReportCoach.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("deleteOneReportCoach".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String report_coach_no = new String(req.getParameter("report_coach_no"));
//				if (report_coach_no == null || report_coach_no.trim().length() == 0) {
//					errorMsgs.add("請輸入檢舉教練編號");
//				}
//
//				ReportCoachService reportCoachSvc = new ReportCoachService();
//				reportCoachSvc.deleteOneReportCoach(report_coach_no);
//				String url = "/back-end/report-coach/listAllReportCoach.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("刪除檢舉教練事件失敗" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/report-coach/listAllReportCoach.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	}// post end

}// class end