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

import com.report_coach.model.ReportCoachService;
import com.report_coach.model.ReportCoachVO;

@WebServlet("/ReportCoachServlet")
public class ReportCoachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReportCoachServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("insertOneReportCoach".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String coach_no = new String(req.getParameter("coach_no"));
				if (coach_no == null || coach_no.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉教練的教練編號");
				}
				String member_no = new String(req.getParameter("member_no"));
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("請先登入會員");
				}
				String report_coach_content = new String(req.getParameter("report_coach_content"));
				if (report_coach_content == null || report_coach_content.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉教練內容");
				}
				Timestamp report_coach_time = new Timestamp(System.currentTimeMillis());
				Integer report_coach_status = new Integer(req.getParameter("report_coach_status"));

				ReportCoachVO reportCoachVO = new ReportCoachVO();
				reportCoachVO.setCoach_no(coach_no);
				reportCoachVO.setMember_no(member_no);
				reportCoachVO.setReport_coach_content(report_coach_content);
				reportCoachVO.setReport_coach_time(report_coach_time);
				reportCoachVO.setReport_coach_status(report_coach_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportCoachVO", reportCoachVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach_no_lock/coach_show.jsp");
					failureView.forward(req, res);
					return;
				}

				ReportCoachService reportCoachSvc = new ReportCoachService();
				reportCoachVO = reportCoachSvc.insertOneReportCoach(coach_no, member_no, report_coach_content,
						report_coach_time, report_coach_status);
				req.setAttribute("reportCoachVO", reportCoachVO);
				String url = "/front-end/coach_no_lock/coach_show.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				errorMsgs.add("檢舉成功");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach_no_lock/coach_show.jsp");
				failureView.forward(req, res);
			}
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}// post end

}// class end
