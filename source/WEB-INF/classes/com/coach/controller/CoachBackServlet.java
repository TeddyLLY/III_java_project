package com.coach.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coach.model.CoachService;
import com.coach.model.CoachVO;

@WebServlet("/CoachBackServlet")
@MultipartConfig
public class CoachBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CoachBackServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("listCoach_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<>(req.getParameterMap());
					session.setAttribute("map", map1);
					map = map1;
				}

				CoachService coachSvc = new CoachService();
				List<CoachVO> list = coachSvc.findQueryCoach(map);

				session.removeAttribute("listCoach_ByCompositeQuery");
				session.setAttribute("listCoach_ByCompositeQuery", list);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/coach-back/listCoach_ByQuery.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("fail" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/coach-back/select_coach.jsp");
				failView.forward(req, res);
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("deleteCoachForStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			try {
				String coach_no = new String(req.getParameter("coach_no"));
				if (coach_no != null || coach_no.trim().length() != 0) {
					errorMsgs.add("刪除成功");
				}
				CoachService coachSvc = new CoachService();
				coachSvc.coachDelete(coach_no);

				if (requestURL.equals("/back-end/coach-back/listCoach_ByQuery.jsp")) {
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
					List<CoachVO> list = coachSvc.findQueryCoach(map);
					req.setAttribute("listCoach_ByCompositeQuery", list);
				}

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				errorMsgs.add(e.getMessage() + "刪除失敗,不能刪除有關連的會員");
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/coach-back/listCoach_ByQuery.jsp");
				failView.forward(req, res);
			}

		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("UpdateStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String coach_no = req.getParameter("coach_no").trim();
				CoachService coachSvc = new CoachService();
				CoachVO coachVO = coachSvc.findOneCoach(coach_no);
				req.setAttribute("coachVO", coachVO);

				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/coach-back/update_coach_status.jsp");
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add("無法取的要修改的資料" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("back-end/coach-back/listCoach_ByQuery.jsp");
				failView.forward(req, res);
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("UpdateStatusSuccess".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			try {
				String coach_no = req.getParameter("coach_no").trim();
				Integer coach_review = new Integer(req.getParameter("coach_review").trim());
				if (coach_review == null || coach_review.toString().trim().length() == 0) {
					errorMsgs.add("請選擇教練狀態");
				}
				Integer coach_auth = new Integer(req.getParameter("coach_auth").trim());
				if (errorMsgs == null || errorMsgs.toString().trim().length() == 0) {
					errorMsgs.add("請選擇教練權限");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req
							.getRequestDispatcher("/back-end/coach-back/update_coach_status.jsp");
					failView.forward(req, res);
					return;
				}

				CoachVO coachVO = new CoachVO();
				coachVO.setCoach_no(coach_no);
				CoachService coachSvc = new CoachService();
				coachSvc.CoachUpdateStatus(coach_review, coach_auth, coach_no);
				coachVO = coachSvc.findOneCoach(coach_no);
				req.setAttribute("coachVO", coachVO);
				
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				List<CoachVO> list = coachSvc.findQueryCoach(map);
				req.setAttribute("listCoach_ByCompositeQuery", list);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				errorMsgs.add("修改教練權限失敗" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("back-end/coach-back/update_coach_status.jsp");
				failView.forward(req, res);
				return;
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("backUpdateStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String coach_no = req.getParameter("coach_no");
				Integer coach_review = new Integer(req.getParameter("coach_review"));
				Integer coach_auth;
				String coach_name = req.getParameter("coach_name");

				if (coach_review == 4) { // 審核成功正常權限
					coach_auth = new Integer(1);
					CoachService coachSvc = new CoachService();
					coachSvc.CoachUpdateStatus(coach_review, coach_auth, coach_no);
					errorMsgs.add(coach_name + "已通過審核");
					RequestDispatcher successView = req.getRequestDispatcher("back-end/coach-back/review_coach.jsp");
					successView.forward(req, res);
					return;
				} else if (coach_review == 5) {
					coach_auth = new Integer(0);
					CoachService coachSvc = new CoachService();
					coachSvc.CoachUpdateStatus(coach_review, coach_auth, coach_no);
					errorMsgs.add(coach_name + "審核失敗");
					RequestDispatcher successView = req.getRequestDispatcher("back-end/coach-back/review_coach.jsp");
					successView.forward(req, res);
					return;
				} 
				errorMsgs.add( "請選擇正確狀態");
				RequestDispatcher successView = req.getRequestDispatcher("back-end/coach-back/review_coach.jsp");
				successView.forward(req, res);
				return;
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("back-end/coach-back/review_coach.jsp");
				failView.forward(req, res);
				return;
			}
		}

//以下為base
////////////////////////////////////////////////////////////////////////////////////
//
//		if ("findOneCoach".contentEquals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
////try error
//				String str = new String(req.getParameter("coach_no"));
//				if (str == null || (str.trim().length() == 0)) {
//					errorMsgs.add("請輸入教練編號");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coach/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				String coach_no = null;
//				try {
//					coach_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("教練編號格式不正確");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coach/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
////start serach
//				CoachService coachSvc = new CoachService();
//				CoachVO coachVO = coachSvc.findOneCoach(coach_no);
//				if (coachVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coach/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				req.setAttribute("coachVO", coachVO);
//				String url = "/back-end/coach/listOneCoach.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coach/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
/////////////////////////////////////////////////////////////////////////////////////
//
//		if ("getOne_For_Update".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String coach_no = new String(req.getParameter("coach_no"));
//				CoachService coachSvc = new CoachService();
//				CoachVO coachVO = coachSvc.findOneCoach(coach_no);
//				req.setAttribute("coachVO", coachVO);
//				String url = "/back-end/coach/update_coach_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coach/listAllCoach.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
/////////////////////////////////////////////////////////////////////////////////////
//
//		if ("coachUpdate".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String coach_sex = req.getParameter("coach_sex");
//				String coach_cellphone = req.getParameter("coach_cellphone");
//				String coach_password = req.getParameter("coach_password");
//				String coach_address = req.getParameter("coach_address");
//
//				InputStream in = req.getPart("coach_photo").getInputStream();
//				byte[] coach_photo = null;
//				if (in.available() != 0) {
//					coach_photo = new byte[in.available()];
//					in.read(coach_photo);
//					in.close();
//				} else {
//
//					errorMsgs.add("請上傳照片");
//				}
//
//				Integer coach_total_evaluation = new Integer(req.getParameter("coach_total_evaluation"));
//				Integer coach_total_people_evaluation = new Integer(req.getParameter("coach_total_people_evaluation"));
//				Integer coach_review = new Integer(req.getParameter("coach_review"));
//				Integer coach_auth = new Integer(req.getParameter("coach_auth"));
//				Double coach_average_evaluation = new Double(req.getParameter("coach_average_evaluation"));
//
//				InputStream in1 = req.getPart("coach_license").getInputStream();
//				byte[] coach_license = null;
//				if (in1.available() != 0) {
//					coach_license = new byte[in1.available()];
//					in1.read(coach_license);
//					in.close();
//				} else {
//					errorMsgs.add("請上傳證照");
//				}
//
//				Integer coach_income = new Integer(req.getParameter("coach_income"));
//				String coach_introduction = new String(req.getParameter("coach_introduction"));
//				String coach_bank_account = new String(req.getParameter("coach_bank_account"));
//				String coach_no = new String(req.getParameter("coach_no"));
//
//				CoachVO coachVO = new CoachVO();
//				coachVO.setCoach_sex(coach_sex);
//				coachVO.setCoach_cellphone(coach_cellphone);
//				coachVO.setCoach_password(coach_password);
//				coachVO.setCoach_address(coach_address);
//				coachVO.setCoach_photo(coach_photo);
//				coachVO.setCoach_total_evaluation(coach_total_evaluation);
//				coachVO.setCoach_total_people_evaluation(coach_total_people_evaluation);
//				coachVO.setCoach_review(coach_review);
//				coachVO.setCoach_auth(coach_auth);
//				coachVO.setCoach_average_evaluation(coach_average_evaluation);
//				coachVO.setCoach_license(coach_license);
//				coachVO.setCoach_income(coach_income);
//				coachVO.setCoach_introduction(coach_introduction);
//				coachVO.setCoach_bank_account(coach_bank_account);
//				coachVO.setCoach_no(coach_no);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("coachVO", coachVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coach/update_coach_input.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				CoachService coachSvc = new CoachService();
//				coachVO = coachSvc.coachUpdate(coach_sex, coach_cellphone, coach_password, coach_address, coach_photo,
//						coach_total_evaluation, coach_total_people_evaluation, coach_review, coach_auth,
//						coach_average_evaluation, coach_license, coach_income, coach_introduction, coach_bank_account,
//						coach_no);
//				req.setAttribute("coachVO", coachVO);
//				String url = "/back-end/coach/listOneCoach.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coach/update_coach_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
/////////////////////////////////////////////////////////////////////////////////////
//
//		if ("coachInsert".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				String coach_name = req.getParameter("coach_name").trim();
//				String coach_name_reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (coach_name == null || coach_name.trim().length() == 0) {
//					errorMsgs.add("教練姓名請勿空白");
//				} else if (!coach_name.trim().matches(coach_name_reg)) {
//					errorMsgs.add("教練姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}
//				String coach_sex = req.getParameter("coach_sex").trim();
//				if (coach_sex == null || coach_sex.trim().length() == 0) {
//					errorMsgs.add("請選擇性別");
//				}
//				String coach_cellphone = req.getParameter("coach_cellphone").trim();
//				String coach_account = req.getParameter("coach_account").trim();
//				if (coach_account == null || coach_account.trim().length() == 0) {
//					errorMsgs.add("請輸入帳號");
//				}
//				String coach_password = req.getParameter("coach_password");
//				if (coach_password == null || coach_password.trim().length() == 0) {
//					errorMsgs.add("請輸入密碼");
//				}
//				String coach_address = req.getParameter("coach_address");
//
//				InputStream in = req.getPart("coach_photo").getInputStream();
//				byte[] coach_photo = null;
//				if (in.available() != 0) {
//					coach_photo = new byte[in.available()];
//					in.read(coach_photo);
//					in.close();
//				} else {
//					errorMsgs.add("請上傳照片");
//				}
//
//				Integer coach_total_evaluation = null;
//				try {
//					coach_total_evaluation = new Integer(req.getParameter("coach_total_evaluation"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入總評價分數");
//				}
//
//				Integer coach_total_people_evaluation = null;
//				try {
//					coach_total_people_evaluation = new Integer(req.getParameter("coach_total_people_evaluation"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入總評價人數");
//				}
//
//				Integer coach_review = null;
//				try {
//					coach_review = new Integer(req.getParameter("coach_review"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入教練審核狀態");// TODO: handle exception
//				}
//
//				Integer coach_auth = null;
//				try {
//					coach_auth = new Integer(req.getParameter("coach_auth"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入教練權限狀態");// TODO: handle exception
//				}
//
//				Double coach_average_evaluation = null;
//				try {
//					coach_average_evaluation = new Double(req.getParameter("coach_average_evaluation"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入教練平均評價分數");// TODO: handle exception
//				}
//
//				InputStream in1 = req.getPart("coach_license").getInputStream();
//				byte[] coach_license = null;
//				try {
//					if (in1.available() != 0) {
//						coach_license = new byte[in1.available()];
//						in1.read(coach_license);
//						in1.close();
//					} else {
//						errorMsgs.add("請上傳證照");
//					}
//
//				} catch (Exception e) {
//					errorMsgs.add("上傳證照失敗");
//				}
//
//				Integer coach_income = null;
//				try {
//					coach_income = new Integer(req.getParameter("coach_income"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入教練收益");// TODO: handle exception
//				}
//
//				String coach_introduction = req.getParameter("coach_introduction");
//				if (coach_introduction == null || coach_introduction.trim().length() == 0) {
//					errorMsgs.add("請填寫教練介紹");
//				}
//				String coach_bank_account = req.getParameter("coach_bank_account");
//				if (coach_bank_account == null || coach_bank_account.trim().length() == 0) {
//					errorMsgs.add("請輸入銀行帳號");
//				}
//
//				CoachVO coachVO = new CoachVO();
//				coachVO.setCoach_name(coach_name);
//				coachVO.setCoach_sex(coach_sex);
//				coachVO.setCoach_cellphone(coach_cellphone);
//				coachVO.setCoach_account(coach_account);
//				coachVO.setCoach_password(coach_password);
//				coachVO.setCoach_address(coach_address);
//				coachVO.setCoach_photo(coach_photo);
//				coachVO.setCoach_total_evaluation(coach_total_evaluation);
//				coachVO.setCoach_total_people_evaluation(coach_total_people_evaluation);
//				coachVO.setCoach_review(coach_review);
//				coachVO.setCoach_auth(coach_auth);
//				coachVO.setCoach_average_evaluation(coach_average_evaluation);
//				coachVO.setCoach_license(coach_license);
//				coachVO.setCoach_income(coach_income);
//				coachVO.setCoach_introduction(coach_introduction);
//				coachVO.setCoach_bank_account(coach_bank_account);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("coachVO", coachVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coach/addCoach.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
//
//				CoachService coachSvc = new CoachService();
//				coachVO = coachSvc.coachInsert(coach_name, coach_sex, coach_cellphone, coach_account, coach_password,
//						coach_address, coach_photo, coach_total_evaluation, coach_total_people_evaluation, coach_review,
//						coach_auth, coach_average_evaluation, coach_license, coach_income, coach_introduction,
//						coach_bank_account);
//				req.setAttribute("coachVO", coachVO);
//				String url = "/back-end/coach/listAllCoach.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("新增教練失敗" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coach/addCoach.jsp");
//				failureView.forward(req, res);
//			}
//
//		}
//
/////////////////////////////////////////////////////////////////////////////////////
//
//		if ("coachDelete".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String coach_no = new String(req.getParameter("coach_no"));
//				CoachService coachSvc = new CoachService();
//				coachSvc.coachDelete(coach_no);
//				String url = "/back-end/coach/listAllCoach.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("刪除教練失敗" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coach/listAllCoach.jsp");
//				failureView.forward(req, res);
//			}
//
//		}
//
/////////////////////////////////////////////////////////////////////////////////////

	}// do post end

}
