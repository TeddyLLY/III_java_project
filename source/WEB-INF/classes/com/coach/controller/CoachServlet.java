package com.coach.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
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
import javax.swing.JOptionPane;

import com.coach.model.CoachService;
import com.coach.model.CoachVO;
import com.system_news.model.SystemNewsService;

@WebServlet("/CoachServlet")
@MultipartConfig
public class CoachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CoachServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

////////////////////////////////////////////////////////////////////////////////////////
		if ("updateCoachDetail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			HttpSession session = req.getSession();
			// 取sessionVO值
			CoachVO sessionCoachVO = (CoachVO) session.getAttribute("coachVO");

			CoachService coachSvc = new CoachService();
			CoachVO coachPicVo = new CoachVO();
			coachPicVo = coachSvc.findOneCoach(sessionCoachVO.getCoach_no());//
			String coach_sex = sessionCoachVO.getCoach_sex();//
			String coach_cellphone = req.getParameter("coach_cellphone");//
			if (coach_cellphone == null || coach_cellphone.trim().length() == 0) {
				errorMsgs.add("請輸入手機");
			}
			String coach_password = sessionCoachVO.getCoach_password();

			String coach_address = req.getParameter("coach_address");//
			if (coach_address == null || coach_address.trim().length() == 0) {
				errorMsgs.add("請輸入地址");
			}
			InputStream in = req.getPart("coach_photo").getInputStream();
			byte[] coach_photo = null;
			if (in.available() != 0) {
				coach_photo = new byte[in.available()];
				in.read(coach_photo);
				in.close();
			} else {
				errorMsgs.add("請上傳照片");
			}

			Integer coach_total_evaluation = sessionCoachVO.getCoach_total_evaluation();
			Integer coach_total_people_evaluation = sessionCoachVO.getCoach_total_people_evaluation();
			Integer coach_review = 2; // 教練狀態   review =  2.待審  
			Integer coach_auth = 2; //auth 2.權限待驗證 
			Double coach_average_evaluation = sessionCoachVO.getCoach_average_evaluation();
			InputStream in1 = req.getPart("coach_license").getInputStream();
			byte[] coach_license = null;
			if (in1.available() != 0) {
				coach_license = new byte[in1.available()];
				in1.read(coach_license);
				in.close();
			} else {
				errorMsgs.add("請上傳教練證照");
			}
			Integer coach_income = new Integer(sessionCoachVO.getCoach_income());
			if ((coach_income.toString().trim()).length() == 0) {
				errorMsgs.add("insert income error");
			}
			String coach_introduction = new String(req.getParameter("coach_introduction"));
			if (coach_introduction.trim().length() == 0) {
				errorMsgs.add("請輸入自我介紹");
			}
			String coach_bank_account = new String(req.getParameter("coach_bank_account"));
			if (coach_bank_account.trim().length() == 0) {
				errorMsgs.add("請上傳帳戶");
			}
			String coach_no = sessionCoachVO.getCoach_no();
			String check = req.getParameter("check");
			if (check == null || check.trim().length() == 0) {
				errorMsgs.add("請勾選填寫完成");
			}

			CoachVO coachVO = new CoachVO();
			coachVO.setCoach_sex(coach_sex);
			coachVO.setCoach_cellphone(coach_cellphone);
			coachVO.setCoach_password(coach_password);
			coachVO.setCoach_address(coach_address);
			coachVO.setCoach_photo(coach_photo);
			coachVO.setCoach_total_evaluation(coach_total_evaluation);
			coachVO.setCoach_total_people_evaluation(coach_total_people_evaluation);
			coachVO.setCoach_review(coach_review);
			coachVO.setCoach_auth(coach_auth);
			coachVO.setCoach_average_evaluation(coach_average_evaluation);
			coachVO.setCoach_license(coach_license);
			coachVO.setCoach_income(coach_income);
			coachVO.setCoach_introduction(coach_introduction);
			coachVO.setCoach_bank_account(coach_bank_account);
			coachVO.setCoach_no(coach_no);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("coachVO", coachVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach_no_lock/coach_review.jsp");
				failureView.forward(req, res);
				return;
			}
//成功後刪除舊session
			session.removeAttribute("coachVO");
			coachVO = coachSvc.coachUpdate(coach_sex, coach_cellphone, coach_password, coach_address, coach_photo,
					coach_total_evaluation, coach_total_people_evaluation, coach_review, coach_auth,
					coach_average_evaluation, coach_license, coach_income, coach_introduction, coach_bank_account,
					coach_no);
			coachVO = coachSvc.findOneCoach(coach_no);
			session.setAttribute("coachVO", coachVO);

			String url = "/front-end/coach_no_lock/coach_review_wait.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach_no_lock/coach_review_wait.jsp");
				failureView.forward(req, res);
			}
		}
//					

///////////////////////////////////////////////////////////////////////////////////

		if ("coachUpdate".equals(action)) {// coach_modify.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				// 取sessionVO值
				CoachVO sessionCoachVO = (CoachVO) session.getAttribute("coachVO");

				CoachService coachSvc = new CoachService();
				CoachVO coachPicVo = new CoachVO();
				coachPicVo = coachSvc.findOneCoach(sessionCoachVO.getCoach_no());//
				String coach_sex = req.getParameter("coach_sex");//
				String coach_cellphone = req.getParameter("coach_cellphone");//
				if (coach_cellphone == null || coach_cellphone.trim().length() == 0) {
					errorMsgs.add("請輸入手機");
				}
				String coach_password = req.getParameter("coach_password");
				String coach_password_check = req.getParameter("coach_password_check");
				// 如果不修改帳號密碼的話
				if ((coach_password == null || coach_password.trim().length() == 0)
						&& (coach_password_check == null || coach_password_check.trim().length() == 0)) {
					coach_password = sessionCoachVO.getCoach_password();
					// 要改的話
				} else if (coach_password.equals(coach_password_check)) {
					coach_password = coach_password_check;
				} else {
					// 錯誤
					errorMsgs.add("密碼不一致");
					coach_password = "";
					coach_password_check = "";
				}

				String coach_address = req.getParameter("coach_address");//
				if (coach_address == null || coach_address.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				}
				InputStream in = req.getPart("coach_photo").getInputStream();
				byte[] coach_photo = null;
				if (in.available() != 0) {
					coach_photo = new byte[in.available()];
					in.read(coach_photo);
					in.close();
				} else {
					coach_photo = coachPicVo.getCoach_photo();
				}

				Integer coach_total_evaluation = sessionCoachVO.getCoach_total_evaluation();
				Integer coach_total_people_evaluation = sessionCoachVO.getCoach_total_people_evaluation();
				Integer coach_review = sessionCoachVO.getCoach_review();
				Integer coach_auth = sessionCoachVO.getCoach_auth();
				Double coach_average_evaluation = sessionCoachVO.getCoach_average_evaluation();
				InputStream in1 = req.getPart("coach_license").getInputStream();
				byte[] coach_license = null;
				if (in1.available() != 0) {
					coach_license = new byte[in1.available()];
					in1.read(coach_license);
					in.close();
				} else {
					coach_license = coachPicVo.getCoach_license();
				}
				Integer coach_income = new Integer(sessionCoachVO.getCoach_income());
				if ((coach_income.toString().trim()).length() == 0) {
					errorMsgs.add("輸入收益失敗");
				}
				String coach_introduction = new String(req.getParameter("coach_introduction"));
				String coach_bank_account = new String(req.getParameter("coach_bank_account"));
				String coach_no = sessionCoachVO.getCoach_no();
				String check = req.getParameter("check");
				if (check == null || check.trim().length() == 0) {
					errorMsgs.add("請勾選確認更改");
				}

				CoachVO coachVO = new CoachVO();
				coachVO.setCoach_sex(coach_sex);
				coachVO.setCoach_cellphone(coach_cellphone);
				coachVO.setCoach_password(coach_password);
				coachVO.setCoach_address(coach_address);
				coachVO.setCoach_photo(coach_photo);
				coachVO.setCoach_total_evaluation(coach_total_evaluation);
				coachVO.setCoach_total_people_evaluation(coach_total_people_evaluation);
				coachVO.setCoach_review(coach_review);
				coachVO.setCoach_auth(coach_auth);
				coachVO.setCoach_average_evaluation(coach_average_evaluation);
				coachVO.setCoach_license(coach_license);
				coachVO.setCoach_income(coach_income);
				coachVO.setCoach_introduction(coach_introduction);
				coachVO.setCoach_bank_account(coach_bank_account);
				coachVO.setCoach_no(coach_no);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coachVO", coachVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/coach_modify.jsp");
					failureView.forward(req, res);
					return;
				}
//成功後刪除舊session
				session.removeAttribute("coachVO");
				coachVO = coachSvc.coachUpdate(coach_sex, coach_cellphone, coach_password, coach_address, coach_photo,
						coach_total_evaluation, coach_total_people_evaluation, coach_review, coach_auth,
						coach_average_evaluation, coach_license, coach_income, coach_introduction, coach_bank_account,
						coach_no);
				coachVO = coachSvc.findOneCoach(coach_no);
				session.setAttribute("coachVO", coachVO);
				String url = "/front-end/coach/coach_data.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/coach_modify.jsp");
				failureView.forward(req, res);
			}
		}
		
///////////////////////////////////////////////////////////////////////////////////
		if ("coachInsert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			String coach_name = req.getParameter("coach_name").trim();
			String coach_name_reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
			if (coach_name == null || coach_name.trim().length() == 0) {
				errorMsgs.add("教練姓名請勿空白");
			} else if (!coach_name.trim().matches(coach_name_reg)) {
				errorMsgs.add("教練姓名: 只能是中、英文字母_ , 且長度必需在1到10之間");
			}

			String coach_sex = req.getParameter("coach_sex").trim();
			if (coach_sex == null || coach_sex.trim().length() == 0) {
				errorMsgs.add("請選擇性別");
			}

			String coach_cellphone = "";
//				String coach_cellphone = req.getParameter("coach_cellphone").trim();
			String coach_account = req.getParameter("coach_account").trim();
			if (coach_account == null || coach_account.trim().length() == 0) {
				errorMsgs.add("請輸入帳號");
			}
			String coach_password = req.getParameter("coach_password");
			if (coach_password == null || coach_password.trim().length() == 0) {
				errorMsgs.add("請輸入密碼");
			}
			String coach_psw_check = req.getParameter("coach_psw_check");
			if (coach_psw_check == null || coach_psw_check.trim().length() == 0) {
				errorMsgs.add("請填寫第二個密碼");
			}
			if ((coach_password != null && coach_psw_check != null) && (coach_password.equals(coach_psw_check))) {

			} else {
				coach_psw_check = "";
				coach_password = "";
				errorMsgs.add("密碼不一致");
			}

			String coach_address = "";
//				String coach_address = req.getParameter("coach_address");
//				InputStream in = req.getPart("coach_photo").getInputStream();
			byte[] coach_photo = null;
//				try {
//					if (in.available() != 0) {
//						coach_photo = new byte[in.available()];
//						in.read(coach_photo);
//						in.close();
//					}
//				} catch (Exception e) {
//					errorMsgs.add("上傳失敗,請上傳正確格式照片" + e.getMessage());
//				}
//				Integer coach_total_evaluation = null;
//				try {
//					coach_total_evaluation = new Integer(req.getParameter("coach_total_evaluation"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入總評價分數");
//				}
			Integer coach_total_evaluation = 0;
//				Integer coach_total_people_evaluation = null;
//				try {
//					coach_total_people_evaluation = new Integer(req.getParameter("coach_total_people_evaluation"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入總評價人數");
//				}
			Integer coach_total_people_evaluation = 0;
//				Integer coach_review = null;
//				try {
//					coach_review = new Integer(req.getParameter("coach_review"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入教練審核狀態");// TODO: handle exception
//				}
			Integer coach_review = 1;
//				Integer coach_auth = null;
//				try {
//					coach_auth = new Integer(req.getParameter("coach_auth"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入教練權限狀態");// TODO: handle exception
//				}
			Integer coach_auth = 2;
//				Double coach_average_evaluation = null;
//				try {
//					coach_average_evaluation = new Double(req.getParameter("coach_average_evaluation"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入教練平均評價分數");// TODO: handle exception
//				}
			Double coach_average_evaluation = 0.d;
//				InputStream in1 = req.getPart("coach_license").getInputStream();
			byte[] coach_license = null;
//				try {
//					if (in1.available() != 0) {
//						coach_license = new byte[in1.available()];
//						in1.read(coach_license);
//						in1.close();
//					}
//
//				} catch (Exception e) {
//					errorMsgs.add("上傳證照失敗");
//				}
//				Integer coach_income = null;
//				try {
//					coach_income = new Integer(req.getParameter("coach_income"));
//				} catch (Exception e) {
//					errorMsgs.add("請輸入教練收益");// TODO: handle exception
//				}
			Integer coach_income = 0;
			String coach_introduction = null;
//				String coach_introduction = req.getParameter("coach_introduction");
//				if (coach_introduction == null || coach_introduction.trim().length() == 0) {
//					errorMsgs.add("請填寫教練介紹");
//				}
			String coach_bank_account = null;
//				String coach_bank_account = req.getParameter("coach_bank_account");
//				if (coach_bank_account == null || coach_bank_account.trim().length() == 0) {
//					errorMsgs.add("請輸入銀行帳號");
//				}

			CoachVO coachVO = new CoachVO();
			coachVO.setCoach_name(coach_name);
			coachVO.setCoach_sex(coach_sex);
			coachVO.setCoach_cellphone(coach_cellphone);
			coachVO.setCoach_account(coach_account);
			coachVO.setCoach_password(coach_password);
			coachVO.setCoach_address(coach_address);
			coachVO.setCoach_photo(coach_photo);
			coachVO.setCoach_total_evaluation(coach_total_evaluation);
			coachVO.setCoach_total_people_evaluation(coach_total_people_evaluation);
			coachVO.setCoach_review(coach_review);
			coachVO.setCoach_auth(coach_auth);
			coachVO.setCoach_average_evaluation(coach_average_evaluation);
			coachVO.setCoach_license(coach_license);
			coachVO.setCoach_income(coach_income);
			coachVO.setCoach_introduction(coach_introduction);
			coachVO.setCoach_bank_account(coach_bank_account);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("coachVO", coachVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/coach_sign_in.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			CoachService coachSvc = new CoachService();
			coachVO = coachSvc.coachInsert(coach_name, coach_sex, coach_cellphone, coach_account, coach_password,
					coach_address, coach_photo, coach_total_evaluation, coach_total_people_evaluation, coach_review,
					coach_auth, coach_average_evaluation, coach_license, coach_income, coach_introduction,
					coach_bank_account);
			
			//傳送系統訊息給coach
			SystemNewsService  sysSvc = new SystemNewsService();
			java.sql.Timestamp system_time =new Timestamp(System.currentTimeMillis());
			sysSvc.insertOneSystemNews( null,coachVO.getCoach_no(), coachVO.getCoach_name()+"教練你好 !歡迎使用平台", system_time);
			
			
			
			HttpSession session = req.getSession();
			coachVO = coachSvc.findOneCoachAccount(coach_account);
			session.setAttribute("coachVO", coachVO); // 資料庫update成功後,正確的的empVO物件,存入session
			session.setAttribute("allowUser", "allowUser");
			session.setAttribute("coachAccount", "coachAccount");
			
			String url = "/back-end/mail-server/coach_check.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增教練失敗 " + "email重複註冊!" + "<br>" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/coach_sign_in.jsp");
				failureView.forward(req, res);
			}
		}
/////////////////////////////////////////////////////////////////////////////////////
		if ("findOneCoachAccount".equals(action)) {// 登入
			Map<String, String> errorMsgs = new LinkedHashMap();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String coach_account = new String(req.getParameter("coach_account").trim());
				if (coach_account == null || coach_account.trim().length() == 0) {
					errorMsgs.put("coach_account", "請輸入教練帳號");
				}
				String coach_password = req.getParameter("coach_password").trim();
				if (coach_password == null || coach_password.trim().length() == 0) {
					errorMsgs.put("coach_password", "請輸入教練密碼");
				}

				CoachVO coachVO = new CoachVO();
				coachVO.setCoach_account(coach_account);
				coachVO.setCoach_password(coach_password);

				// service
				CoachService coachSvc = new CoachService();

				// 查是否有這帳號
				coachVO = coachSvc.findOneCoachAccount(coach_account);

				// 放入req scope
				req.setAttribute("coachVO", coachVO);

				if (!(coachVO.getCoach_password().equals(coach_password.trim()))) {
					errorMsgs.put("fail", "沒有此教練帳號或密碼" + coachVO.getCoach_password() + coach_password);
					errorMsgs.put("error", "密碼錯誤");
				} else if (coachVO.getCoach_password().equals(coach_password.trim())) {
					// 帳號密碼正確 vo 與判斷是否登入條件放入 session scope
					HttpSession session = req.getSession();
					session.setAttribute("coachVO", coachVO);
					session.setAttribute("allowUser", "allowUser");
					session.setAttribute("coachAccount", "coachAccount");
				}

				// 錯誤return
				if (!errorMsgs.isEmpty()) {
					errorMsgs.put("coach_login_error", "登入失敗");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/coach_login.jsp");
					failureView.forward(req, res);
					return;
				}

				// 跳轉回原網址
//				try {
//					HttpSession session = req.getSession();
//					String location = (String) session.getAttribute("location");
//					if (location != null) {
//						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
//						res.sendRedirect(location);
//						return;
//					}
//				} catch (Exception ignored) {
//					ignored.printStackTrace();
//				}
				res.sendRedirect(req.getContextPath() + "/front-end/gym_index/index.jsp"); // *工作3:
			} catch (Exception e) {
				errorMsgs.put("find_account_fail", "沒有此帳號密碼,請重新登入!" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/coach_login.jsp");
				failureView.forward(req, res);
			}
		}

///////////////////////////////////////////////////////////////////////////////////
		if ("clearCoachAccount".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				String allowUser = (String) session.getAttribute("allowUser");
				if (allowUser == null || allowUser.trim().length() == 0) {
					errorMsgs.put("allowUser", "沒有使用者登入");
				}
				String coachAccount = (String) session.getAttribute("coachAccount");
				if (coachAccount == null || coachAccount.trim().length() == 0) {
					errorMsgs.put("coachAccount", "不是教練登入");
				}

				if (!errorMsgs.isEmpty()) {
					errorMsgs.put("error", "delete_coach_session_fail");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/login.jsp");
					failureView.forward(req, res);
					return;
				}
				session.removeAttribute("allowUser");
				session.removeAttribute("coachAccount");
				session.removeAttribute("coachVO");

				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/gym_index/index.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.put("error", "delete_coach_session_fail" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/login.jsp");
				failureView.forward(req, res);
			}
		} //
	}
}
