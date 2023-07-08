package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.system_news.model.SystemNewsService;

@WebServlet("/MemberServlet")
@MultipartConfig
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
/////////////////////////////////////////////////////////////////////////////////////////////
		if ("MemberUpdate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				// 取sessionVO值
				MemberVO sessionMemberVO = (MemberVO) session.getAttribute("memberVO");

				MemberService memSvc = new MemberService();// service
				MemberVO memberPicVO = new MemberVO();
				memberPicVO = memSvc.findOneMember(sessionMemberVO.getMember_no());

				String member_sex = req.getParameter("member_sex").trim();//
				if (member_sex == null || member_sex.trim().length() == 0) {
					errorMsgs.add("請輸入性別");
				}
				String member_cellphone = req.getParameter("member_cellphone").trim();//
				String member_password = req.getParameter("member_password").trim();
				String member_password_check = req.getParameter("member_password_check").trim();
				if ((member_password == null || member_password.trim().length() == 0)
						&& (member_password_check == null || member_password_check.trim().length() == 0)) {
					member_password = sessionMemberVO.getMember_password();
					// 要改的話
				} else if (member_password.equals(member_password_check)) {
					member_password = member_password_check;
				} else {
					// 錯誤
					errorMsgs.add("密碼不一致");
					member_password = "";
					member_password_check = "";
				}

				String member_address = req.getParameter("member_address").trim();
				if (member_address == null || member_address.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				}

				InputStream in = req.getPart("member_photo").getInputStream();
				byte[] member_photo = null;
				if (in.available() != 0) {
					member_photo = new byte[in.available()];
					in.read(member_photo);
					in.close();
				} else {
					member_photo = memberPicVO.getMember_photo();
				}

				Integer member_points = sessionMemberVO.getMember_points();
				Integer member_height = null;
				try {
					member_height = new Integer(req.getParameter("member_height").trim());

				} catch (NumberFormatException e) {
					errorMsgs.add("請選擇正確身高");
				}

				Integer member_weight = null;
				try {
					member_weight = new Integer(req.getParameter("member_weight").trim());

				} catch (NumberFormatException e) {
					errorMsgs.add("請選擇正確體重");
				}

				Integer member_review = sessionMemberVO.getMember_review();

				Integer member_auth = sessionMemberVO.getMember_auth();

				Double member_bodyfat = null;
				try {
					member_bodyfat = new Double(req.getParameter("member_bodyfat").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請填寫正確體脂");
				}

				String member_card = req.getParameter("member_card").trim();
				if (member_card == null || member_card.trim().length() == 0) {
					errorMsgs.add("請輸入信用卡");
				}
				
				Integer member_wing_span = null;
				try {
					member_wing_span = new Integer(req.getParameter("member_wing_span").trim());

				} catch (NumberFormatException e) {
					errorMsgs.add("請選擇正確臂展");
				}

				String member_no = sessionMemberVO.getMember_no();

				String check = req.getParameter("check");
				if (check == null || check.trim().length() == 0) {
					errorMsgs.add("請勾選確認更改");
				}

				MemberVO memberVO = new MemberVO();
				memberVO.setMember_sex(member_sex);
				memberVO.setMember_cellphone(member_cellphone);
				memberVO.setMember_password(member_password);
				memberVO.setMember_address(member_address);
				memberVO.setMember_photo(member_photo);
				memberVO.setMember_points(member_points);
				memberVO.setMember_height(member_height);
				memberVO.setMember_weight(member_weight);
				memberVO.setMember_review(member_review);
				memberVO.setMember_auth(member_auth);
				memberVO.setMember_bodyfat(member_bodyfat);
				memberVO.setMember_card(member_card);
				memberVO.setMember_wing_span(member_wing_span);
				memberVO.setMember_no(member_no);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/member_modify.jsp");

					failureView.forward(req, res);
					return; // 程式中斷
				}

				// 刪除舊session
				session.removeAttribute("memberVO");
				memberVO = memSvc.MemberUpdate(member_sex, member_cellphone, member_password, member_address,
						member_photo, member_points, member_height, member_weight, member_review, member_auth,
						member_bodyfat, member_card, member_wing_span , member_no );
				memberVO = memSvc.findOneMember(member_no);
				session.setAttribute("memberVO", memberVO);// 資料庫update成功後,正確的的物件,

				String url = "/front-end/member/member_data.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/member_modify.jsp");
				failureView.forward(req, res);
			}
		}
//if end		

/////////////////////////////////////////////////////////////////////////////////////////////
		if ("memberInsert".equals(action)) { // memberInsert

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String member_name = req.getParameter("member_name").trim();
				String member_name_reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (member_name == null || member_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!member_name.trim().matches(member_name_reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String member_sex = req.getParameter("member_sex").trim();
				if (member_sex == null || member_sex.trim().length() == 0) {
					errorMsgs.add("請選擇性別");
				}
				String member_cellphone = "";

				String member_address = "";

//				InputStream in = req.getPart("member_photo").getInputStream();
				byte[] member_photo = null;

//				if (in.available() != 0) {
//					member_photo = new byte[in.available()];
//					in.read(member_photo);
//					in.close();
//				} else {
//				}
				String member_account = req.getParameter("member_account").trim();
				if (member_account == null || member_account.trim().length() == 0) {
					errorMsgs.add("請填寫帳號");
				}
				String member_password = req.getParameter("member_password").trim();
				if (member_password == null || member_password.trim().length() == 0) {
					errorMsgs.add("請填寫密碼");
				}
				String member_psw_check = req.getParameter("member_psw_check");
				if (member_psw_check == null || member_psw_check.trim().length() == 0) {
					errorMsgs.add("請填寫第二個密碼");
				}
				Integer member_wing_span = 0;

//		Integer member_points = null;
//		try {
//			member_points = new Integer(req.getParameter("member_points").trim());
//		}catch (NumberFormatException e){
//			errorMsgs.add("請選擇正確點數");
//		}
				Integer member_points = 0;

				Integer member_height = 0;
//				try {
//					member_height = new Integer(req.getParameter("member_height").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請選擇正確身高");
//				}
				Integer member_weight = 0;
//				try {
//					member_weight = new Integer(req.getParameter("member_weight").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請選擇正確體重");
//				}
//		Integer member_review = null;
//		try {
//			member_review = new Integer(req.getParameter("member_review").trim());
//		}catch (NumberFormatException e){
//			member_review = 0; //改成驗證中(預設)
//			errorMsgs.add("請選擇正確審核狀態");
//		}
				Integer member_review = 0;

//		Integer member_auth = null;
//		try {
//			member_auth = new Integer(req.getParameter("member_auth").trim());
//		}catch (NumberFormatException e){
//			member_auth = 0; //改成驗證中(預設)
//			errorMsgs.add("請選擇正確權限狀態");
//		}
				Integer member_auth = 1;

				Double member_bodyfat = 0.d;
//				try {
//					member_bodyfat = new Double(req.getParameter("member_bodyfat").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請填寫正確體脂");
//				}

				String member_card = "";
//				String member_card = req.getParameter("member_card").trim();

				MemberVO memberVO = new MemberVO();
				memberVO.setMember_name(member_name);
				memberVO.setMember_sex(member_sex);
				memberVO.setMember_cellphone(member_cellphone);
				memberVO.setMember_account(member_account);
				memberVO.setMember_password(member_password);
				memberVO.setMember_address(member_address);
				memberVO.setMember_photo(member_photo);
				memberVO.setMember_points(member_points);
				memberVO.setMember_height(member_height);
				memberVO.setMember_weight(member_weight);
				memberVO.setMember_review(member_review);
				memberVO.setMember_auth(member_auth);
				memberVO.setMember_bodyfat(member_bodyfat);
				memberVO.setMember_card(member_card);
				memberVO.setMember_wing_span(member_wing_span);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/member_sign_in.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				MemberService memSvc = new MemberService();
				memberVO = memSvc.memberInsert(member_name, member_sex, member_cellphone, member_account,
						member_password, member_address, member_photo, member_points, member_height, member_weight,
						member_review, member_auth, member_bodyfat, member_card , member_wing_span);
				
				SystemNewsService  sysSvc = new SystemNewsService();
				java.sql.Timestamp system_time =new Timestamp(System.currentTimeMillis());
				sysSvc.insertOneSystemNews( memberVO.getMember_no(), null , memberVO.getMember_name()+"會員你好 !歡迎使用平台", system_time);
				
				
				HttpSession session = req.getSession();
				memberVO = memSvc.findOneMemberAccount(member_account);
				session.setAttribute("memberVO", memberVO);
				session.setAttribute("allowUser", "allowUser");
				session.setAttribute("memberAccount", "memberAccount");
				String url = "/back-end/mail-server/member_check.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("新增會員失敗:" + "<br>" + "帳號重複  " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/member_sign_in.jsp");
				failureView.forward(req, res);
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////
		if ("deleteMember".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String member_no = new String(req.getParameter("member_no"));

				MemberService memSvc = new MemberService();
				memSvc.deleteMember(member_no);

				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}

		}

//////////////////////////////////////////////////////////////////////////////////////////////
		if ("findOneMemberAccount".equals(action)) { // 登入
			Map<String, String> errorMsgs = new LinkedHashMap();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String member_account = req.getParameter("member_account").trim();
				if (member_account == null || member_account.trim().length() == 0) {
					errorMsgs.put("member_account", "請輸入會員帳號");
				}
				String member_password = req.getParameter("member_password").trim();
				if (member_password == null || member_password.trim().length() == 0) {
					errorMsgs.put("member_password", " 請輸入會員密碼 ");
				}

				MemberVO memberVO = new MemberVO();
				memberVO.setMember_account(member_account);
				memberVO.setMember_password(member_password);

				// service
				MemberService memberSvc = new MemberService();

				// 查是否有這帳號
				memberVO = memberSvc.findOneMemberAccount(member_account);

				// 放入req scope
				req.setAttribute("memberVO", memberVO);

				if (!memberVO.getMember_password().equals(member_password.trim())) {
					errorMsgs.put("fail", "沒有此教練帳號或密碼" + memberVO.getMember_password() + member_password);
					errorMsgs.put("error", "密碼錯誤");
				} else if (memberVO.getMember_password().equals(member_password.trim())) {
					// 帳號密碼正確 vo 與判斷是否登入條件放入 session scope
					HttpSession session = req.getSession();
					session.setAttribute("memberVO", memberVO);
					session.setAttribute("allowUser", "allowUser");
					session.setAttribute("memberAccount", "memberAccount");
				}

				// 錯誤return
				if (!errorMsgs.isEmpty()) {
					errorMsgs.put("member_login_error", "登入失敗");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/member_login.jsp");
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
//
//				}
				res.sendRedirect(req.getContextPath() + "/front-end/gym_index/index.jsp"); // *工作3:

			} catch (Exception e) {
				errorMsgs.put("find_account_fail", "沒有此帳號密碼,請重新登入!" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/member_login.jsp");
				failureView.forward(req, res);

			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////
		if ("clearMemberAccount".equals(action)) {//登出
			Map<String, String> errorMsgs = new LinkedHashMap();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				String allowUser = (String) session.getAttribute("allowUser");
				if (allowUser == null || allowUser.trim().length() == 0) {
					errorMsgs.put("allowUser", "沒有使用者登入");
				}
				String memberAccount = (String) session.getAttribute("memberAccount");
				if (memberAccount == null || memberAccount.trim().length() == 0) {
					errorMsgs.put("memberAccount", "沒有會員登入");
				}

				if (!errorMsgs.isEmpty()) {
					errorMsgs.put("error", "delete_member_session_fail");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/login.jsp");
					failureView.forward(req, res);
					return;
				}
				session.removeAttribute("allowUser");
				session.removeAttribute("memberAccount");
				session.removeAttribute("memberVO");

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/gym_index/index.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.put("error", "delete_coach_session_fail" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/login.jsp");
				failureView.forward(req, res);
			}

		} //

	}// do post end

}// class end
