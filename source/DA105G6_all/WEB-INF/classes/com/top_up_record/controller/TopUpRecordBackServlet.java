package com.top_up_record.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.top_up_record.model.TopUpRecordService;
import com.top_up_record.model.TopUpRecordVO;

@WebServlet("/TopUpRecordBackServlet")
public class TopUpRecordBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TopUpRecordBackServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("findOneMemberRecord".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("請輸入會員資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/member-back/select_member.jsp");
					failView.forward(req, res);
					return;
				}

				List<TopUpRecordVO> list = new ArrayList<>();
				TopUpRecordService topUpRecordSvc = new TopUpRecordService();
				list = topUpRecordSvc.findOneMemberRecord(member_no);
				req.setAttribute("list", list);

				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/member-back/serach_member_record.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage() + "查詢失敗");
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/member-back/select_member.jsp");
				failView.forward(req, res);
				return;
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////
//以下為 back folder
		if ("findRecordByNum".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = new String(req.getParameter("top_up_record_no"));
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入儲值編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member-back/select_member.jsp");
					failureView.forward(req, res);
					return;
				}

				String top_up_record_no = null;
				try {
					top_up_record_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("儲值編號格式不正確");// TODO: handle exception
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member-back/select_member.jsp");
					failureView.forward(req, res);
					return;
				}
				TopUpRecordService topUpRecordSvc = new TopUpRecordService();
				TopUpRecordVO topUpRecordVO = topUpRecordSvc.findOneTopUpRecord(top_up_record_no);
				if (topUpRecordVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member-back/select_member.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("topUpRecordVO", topUpRecordVO);
				String url = "/back-end/member-back/list_one_record_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());// TODO: handle exception
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member-back/select_member.jsp");
				failureView.forward(req, res);
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////
		if ("findRecordByMember".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			String member_no = new String(req.getParameter("member_no"));
			if (member_no.trim().length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/member-back/select_member.jsp");
				failView.forward(req, res);
				return;
			}

			List<TopUpRecordVO> list = new ArrayList<TopUpRecordVO>();
			TopUpRecordService topUpRecordSvc = new TopUpRecordService();
			list = topUpRecordSvc.findOneMemberRecord(member_no);
			req.setAttribute("list", list);

			RequestDispatcher successView = req
					.getRequestDispatcher("/back-end/member-back/list_one_member_record.jsp");
			successView.forward(req, res);
			return;
			} catch (Exception e) {
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/member-back/select_member.jsp");
				failView.forward(req, res);
				return;
			}
		}

//以下為 base		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("findOneTopUpRecord".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String str = new String(req.getParameter("top_up_record_no"));
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入儲值編號");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/top-up-record/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				String top_up_record_no = null;
//				try {
//					top_up_record_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("儲值編號格是不正確");// TODO: handle exception
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/top-up-record/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				TopUpRecordService topUpRecordSvc = new TopUpRecordService();
//				TopUpRecordVO topUpRecordVO = topUpRecordSvc.findOneTopUpRecord(top_up_record_no);
//				if (topUpRecordVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/top-up-record/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				req.setAttribute("topUpRecordVO", topUpRecordVO);
//				String url = "/back-end/top-up-record/listOneTopUpRecord.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料" + e.getMessage());// TODO: handle exception
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/top-up-record/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
////////////////////////////////////////////////////////////////////////////////
//
//		if ("getOne_For_Update".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String top_up_record_no = new String(req.getParameter("top_up_record_no"));
//				TopUpRecordService topUpRecordSvc = new TopUpRecordService();
//				TopUpRecordVO topUpRecordVO = topUpRecordSvc.findOneTopUpRecord(top_up_record_no);
//				req.setAttribute("topUpRecordVO", topUpRecordVO);
//				String url = "/back-end/top-up-record/update_TopUpRecord_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料" + e.getMessage());// TODO: handle exception
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/top-up-record/listAllTopUpRecord.jsp");
//				failureView.forward(req, res);
//
//			}
//		}
//
////////////////////////////////////////////////////////////////////////////////
//
//		if ("updateTopUpRecord".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String member_no = req.getParameter("member_no").trim();
//				if (member_no == null || (member_no.trim()).length() == 0) {
//					errorMsgs.add("請輸入會員編號");
//				}
//				java.sql.Timestamp record_time = new Timestamp(System.currentTimeMillis());
//				Integer money_record = new Integer(req.getParameter("money_record").trim());
//				String top_up_record_no = req.getParameter("top_up_record_no").trim();
//				if (top_up_record_no == null || (top_up_record_no.trim()).length() == 0) {
//					errorMsgs.add("請輸入儲值編號");
//				}
//
//				TopUpRecordVO topUpRecordVO = new TopUpRecordVO();
//				topUpRecordVO.setMember_no(member_no);
//				topUpRecordVO.setRecord_time(record_time);
//				topUpRecordVO.setMoney_record(money_record);
//				topUpRecordVO.setTop_up_record_no(top_up_record_no);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("topUpRecordVO", topUpRecordVO);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/top-up-record/update_TopUpRecord_input.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				TopUpRecordService topUpRecordSvc = new TopUpRecordService();
//				topUpRecordVO = topUpRecordSvc.updateTopUpRecord(member_no, record_time, money_record,
//						top_up_record_no);
//				req.setAttribute("topUpRecordVO", topUpRecordVO);
//				String url = "/back-end/top-up-record/listOneTopUpRecord.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("修改失敗" + e.getMessage());// TODO: handle exception
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/top-up-record/update_TopUpRecord_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
////////////////////////////////////////////////////////////////////////////////
//		if ("insertOneTopUpRecord".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				String member_no = req.getParameter("member_no").trim();
//				if (member_no == null || (member_no.trim()).length() == 0) {
//					errorMsgs.add("請輸入會員編號");
//				}
//				java.sql.Timestamp record_time = new Timestamp(System.currentTimeMillis());
//				Integer money_record = new Integer(req.getParameter("money_record").trim());
//
//				TopUpRecordVO topUpRecordVO = new TopUpRecordVO();
//				topUpRecordVO.setMember_no(member_no);
//				topUpRecordVO.setRecord_time(record_time);
//				topUpRecordVO.setMoney_record(money_record);
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("topUpRecordVO", topUpRecordVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/top-up-record/addTopUpRecord.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				TopUpRecordService topUpRecordSvc = new TopUpRecordService();
//				topUpRecordVO = topUpRecordSvc.insertOneTopUpRecord(member_no, record_time, money_record);
//				req.setAttribute("topUpRecordVO", topUpRecordVO);
//				String url = "/back-end/top-up-record/listAllTopUpRecord.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("新增失敗" + e.getMessage());// TODO: handle exception
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/top-up-record/addTopUpRecord.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//		}
//
///////////////////////////////////////////////////////////////////////////////////////
//
//		if ("deleteOneUserTopUpRecord".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String top_up_record_no = new String(req.getParameter("top_up_record_no"));
//				TopUpRecordService topUpRecordSvc = new TopUpRecordService();
//
//				topUpRecordSvc.deleteOneUserTopUpRecord(top_up_record_no);
//
//				String url = "/back-end/top-up-record/listAllTopUpRecord.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗" + e.getMessage());// TODO: handle exception
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/top-up-record/listAllTopUpRecord.jsp");
//				failureView.forward(req, res);
//			}
//		}
////////////////////////////////////////////////////////////////////////////////////////////////

	}// do post end

}// class end
