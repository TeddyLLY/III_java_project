package com.toolclass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coach.model.CoachService;
import com.coach.model.CoachVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/MailServer")
public class MailServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MailServer() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");

		if ("sendOneUserMail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				CoachVO coachVO = (CoachVO) session.getAttribute("coachVO");
				MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
				String allowUser = (String) session.getAttribute("allowUser");
				String coachAccount = (String) session.getAttribute("coachAccount");
				String memberAccount = (String) session.getAttribute("memberAccount");

				String to = "";
				// String to = "ixlogic@pchome.com.tw";
				String name = "";
				RequestDispatcher failView;
				if (allowUser != null && coachAccount != null) {
//					to = coachVO.getCoach_account();
					to = "gcobc31619@gmail.com"; // coach
					name = coachVO.getCoach_name();

					if (coachVO == null || coachVO.toString().length() == 0) {
						errorMsgs.add("閒置過久請教練重新登入");
					}
					if (!errorMsgs.isEmpty()) {
						failView = req.getRequestDispatcher("/back-end/mail-server/coach_check.jsp");
						failView.forward(req, res);
						return;
					}
				} else if (allowUser != null && memberAccount != null) {
//					to=memberVO.getMember_account();
					to = "gcobc31619@gmail.com";// member
					name = memberVO.getMember_name();

					if (memberVO == null || memberVO.toString().trim().length() == 0) {
						errorMsgs.add("閒置過久請會員重新登入");
					}
					if (!errorMsgs.isEmpty()) {
						failView = req.getRequestDispatcher("/back-end/mail-server/member_check.jsp");
						failView.forward(req, res);
						return;
					}
				} else {
					errorMsgs.add("mail server 忙碌中 , 請稍後再試 。");
					failView = req.getRequestDispatcher(req.getContextPath() + "/fornt-end/gym_index/index.jsp");
					return;
				}

				// 驗證碼
				StringBuilder passRandom = new StringBuilder();
				passRandom.append("");

				for (int i = 0; i < 6; i++) {
					Integer a = (int) (Math.random() * 9);
					passRandom.append(a.toString());
				}
				if (passRandom.toString() == null || passRandom.toString().trim().length() == 0) {
					errorMsgs.add("請等待信件寄出後再填寫");
				}
				if (!errorMsgs.isEmpty()) {
					errorMsgs.add("閒置過久,請重新登入");
					failView = req.getRequestDispatcher("/back-end/mail-server/member_check.jsp");
					failView.forward(req, res);
					return;
				}
				session.setAttribute("passRandom", passRandom.toString());
				// 信箱主旨
				String subject = "驗證碼";

				// 信箱內容
				String messageText = "Hello! " + name + " \n 驗證碼為: " + passRandom.toString();

				sendMail(to, subject, messageText);
				if (!errorMsgs.isEmpty()) {
					errorMsgs.clear();
				}
				if (errorMsgs.isEmpty() && memberAccount != null) {
					errorMsgs.add("傳送成功 , 已傳送驗證碼給會員的 : " + memberVO.getMember_account() + "信箱。");
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/mail-server/member_check.jsp");
					successView.forward(req, res);
					return;
				} else if (errorMsgs.isEmpty() && coachAccount != null) {
					errorMsgs.add("傳送成功 , 已傳送驗證碼給教練的 : " + coachVO.getCoach_account() + "信箱。");
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/mail-server/coach_check.jsp");
					successView.forward(req, res);
					return;
				} else {
					errorMsgs.add("驗證碼過期,請重新輸入");
					failView = req.getRequestDispatcher("/back-end/mail-server/member_check.jsp");
					failView.forward(req, res);
					return;
				}

			} catch (Exception e) {
				errorMsgs.add("驗證碼過期,請重新輸入 " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gym_index/index.jsp");
				failureView.forward(req, res);
			}
		}

		if (action.equals("coach_passRandom")) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				CoachVO coachVO = (CoachVO) session.getAttribute("coachVO");
				if (coachVO == null) {
					errorMsgs.add("帳號過期,請重新登入");
				}
				String passRandom = (String) session.getAttribute("passRandom");
				String coach_passRandom = new String(req.getParameter("coach_passRandom").trim());
				if (passRandom.equals(coach_passRandom)) {

					CoachService coachSvc = new CoachService();
					Integer coach_review = 0;// 驗證中
					Integer coach_auth = 2;// 權限待驗證
					String coach_no = coachVO.getCoach_no();

					coachSvc.CoachUpdateStatus(coach_review, coach_auth, coach_no);
					session.removeAttribute("coachVO");
					coachVO = coachSvc.findOneCoach(coach_no);
					session.setAttribute("coachVO", coachVO);
					RequestDispatcher successView = req
							.getRequestDispatcher("/front-end/coach_no_lock/coach_review.jsp");
					successView.forward(req, res);
				} else {
					errorMsgs.add("錯誤驗證碼");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/mail-server/coach_check.jsp");
					failView.forward(req, res);
					return;
				}

			} catch (Exception e) {
				errorMsgs.add("認證碼錯誤" + e.getMessage());// TODO: handle exception
			}
		}

		if (action.equals("member_passRandom")) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
				if (memberVO == null) {
					errorMsgs.add("帳號過期,請重新登入");
				}
				String passRandom = (String) session.getAttribute("passRandom");
				String member_passRandom = new String(req.getParameter("member_passRandom").trim());
				if (passRandom.equals(member_passRandom)) {

					MemberService memberSvc = new MemberService();
					Integer member_review = 1; // 驗證成功
					Integer member_auth = 1;// good member
					String member_no = memberVO.getMember_no();

					memberSvc.updateMemberStatus(member_review, member_auth, member_no);
					session.removeAttribute("coachVO");
					memberVO = memberSvc.findOneMember(member_no);
					session.setAttribute("memberVO", memberVO);
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/member_center.jsp");
					successView.forward(req, res);
				} else {
					errorMsgs.add("錯誤驗證碼");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/mail-server/member_check.jsp");
					failView.forward(req, res);
					return;
				}

			} catch (Exception e) {
				errorMsgs.add("認證碼錯誤" + e.getMessage());// TODO: handle exception
			}
		}

		if ("CoachNewPsw".equals(action)) {
			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String coach_account = req.getParameter("coach_account");
				if (coach_account.trim().length() == 0) {
					errorMsgs.add("請輸入mail");
				}
				if (coach_account != null || coach_account.trim().length() != 0) {
					// 尋找是否有此教練帳號
					CoachService coachSvc = new CoachService();
					List<CoachVO> coachVO = new ArrayList<CoachVO>();
					String find_coach_account;
					// 查是否有這帳號
					coachVO = coachSvc.findAllCoach();
					for (CoachVO VO : coachVO) { // for search account
						find_coach_account = VO.getCoach_account();
						if (coach_account.equals(find_coach_account)) {// 正確的話寄出認證信
							String to = "x2589614785@yahoo.com.tw"; // coach mail
							// 新密碼
							StringBuilder passRandom = new StringBuilder();
							passRandom.append("");
							for (int i = 0; i < 6; i++) {
								Integer a = (int) (Math.random() * 9);
								passRandom.append(a.toString());
							}
							// 更改密碼
							CoachVO updateVO = coachSvc.findOneCoachAccount(find_coach_account);
							String coach_sex = updateVO.getCoach_sex();
							String coach_cellphone = updateVO.getCoach_cellphone();
							String coach_passward = passRandom.toString();// 修改密碼
							String coach_address = updateVO.getCoach_address();
							byte[] coach_photo = updateVO.getCoach_photo();
							Integer coach_total_evaluation = updateVO.getCoach_total_evaluation();
							Integer coach_total_people_evaluation = updateVO.getCoach_total_people_evaluation();
							Integer coach_review = updateVO.getCoach_review();
							Integer coach_auth = updateVO.getCoach_review();
							Double coach_average_evaluation = updateVO.getCoach_average_evaluation();
							byte[] coach_license = updateVO.getCoach_license();
							Integer coach_income = updateVO.getCoach_income();
							String coach_introduction = updateVO.getCoach_introduction();
							String coach_bank_account = updateVO.getCoach_bank_account();
							String coach_no = updateVO.getCoach_no();
							coachSvc.coachUpdate(coach_sex, coach_cellphone, coach_passward, coach_address, coach_photo,
									coach_total_evaluation, coach_total_people_evaluation, coach_review, coach_auth,
									coach_average_evaluation, coach_license, coach_income, coach_introduction,
									coach_bank_account, coach_no);

							// 信箱主旨
							String subject = "新密碼";
							// 信箱內容
							String messageText = "Hello! " + updateVO.getCoach_name() + "\n 新密碼為: "
									+ passRandom.toString();

							sendMail(to, subject, messageText);

							errorMsgs.add("信箱已寄出請至信箱收信");
							RequestDispatcher successView = req
									.getRequestDispatcher("/back-end/mail-server/forget_psw_ok.jsp");
							successView.forward(req, res);
							return;
						}
					} // for end
				}

				errorMsgs.add("請輸入正確信箱");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mail-server/coach_forget_psw.jsp");
				failureView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add("mail server 忙碌中" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mail-server/coach_forget_psw.jsp");
				failureView.forward(req, res);
				return;
			}

		} // coach reset psw end

		if ("MemberNewPsw".equals(action)) {
			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String member_account = req.getParameter("member_account");
				if (member_account.trim().length() == 0) {
					errorMsgs.add("請輸入mail");
				}
				if (member_account != null || member_account.trim().length() != 0) {
					// 尋找是否有此教練帳號
					MemberService memberSvc = new MemberService();
					List<MemberVO> memberVO = new ArrayList<MemberVO>();
					String find_member_account;
					// 查是否有這帳號
					memberVO = memberSvc.findAllMemberDesc();
					for (MemberVO VO : memberVO) { // for search account
						find_member_account = VO.getMember_account();
						if (member_account.equals(find_member_account)) {// 正確的話寄出認證信
							String to = "zzx94x94@gmail.com"; // member mail
							// 新密碼
							StringBuilder passRandom = new StringBuilder();
							passRandom.append("");
							for (int i = 0; i < 6; i++) {
								Integer a = (int) (Math.random() * 9);
								passRandom.append(a.toString());
							}
							// 更改密碼
							MemberVO updateVO = memberSvc.findOneMemberAccount(member_account);
							String member_sex = updateVO.getMember_sex();
							String member_cellphone = updateVO.getMember_cellphone();
							String member_passward = passRandom.toString();
							String member_address = updateVO.getMember_address();
							byte[] member_photo = updateVO.getMember_photo();
							Integer member_points = updateVO.getMember_points();
							Integer member_height = updateVO.getMember_height();
							Integer member_weight = updateVO.getMember_weight();
							Integer member_review = updateVO.getMember_review();
							Integer member_auth = updateVO.getMember_auth();
							Double member_bodyfat = updateVO.getMember_bodyfat();
							String member_card = updateVO.getMember_card();
							String member_no = updateVO.getMember_no();
							Integer member_wing_span = updateVO.getMember_wing_span();
							memberSvc.MemberUpdate(member_sex, member_cellphone, member_passward, member_address,
									member_photo, member_points, member_height, member_weight, member_review,
									member_auth, member_bodyfat, member_card, member_wing_span ,member_no);

							// 信箱主旨
							String subject = "新密碼";
							// 信箱內容
							String messageText = "Hello! " + updateVO.getMember_name() + "\n 新密碼為: "
									+ passRandom.toString();

							sendMail(to, subject, messageText);

							errorMsgs.add("信箱已寄出請至信箱收信");
							RequestDispatcher successView = req
									.getRequestDispatcher("/back-end/mail-server/forget_psw_ok.jsp");
							successView.forward(req, res);
							return;
						}
					} // for end
				}

				errorMsgs.add("請輸入正確信箱");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mail-server/member_forget_psw.jsp");
				failureView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add("mail server 忙碌中" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mail-server/member_forget_psw.jsp");
				failureView.forward(req, res);
				return;
			}

		}

	}// do post end

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●須將myGmail的【安全性較低的應用程式存取權】打開
//			final String myGmail = "ixlogic.wu@gmail.com";// 老師測試用帳號
//			final String myGmail_password = "AAA45678AAA";
			final String myGmail = "da105g6lailingyang";// 老師測試用帳號
			final String myGmail_password = "x2589614785";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}
}
