package com.top_up_record.controller;

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
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.top_up_record.model.TopUpRecordService;
import com.top_up_record.model.TopUpRecordVO;

@WebServlet("/TopUpRecordServlet")
public class TopUpRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TopUpRecordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

////////////////////////////////////////////////////////////////////////////////
		if ("insertOneTopUpRecord".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				//取出session
				HttpSession session = req.getSession();
				MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
				
				String member_no = req.getParameter("member_no").trim();
				if (member_no == null || (member_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				java.sql.Timestamp record_time = new Timestamp(System.currentTimeMillis());
				Integer money_record = new Integer(req.getParameter("money_record").trim());
				if(money_record == null   || money_record == 0) {
					errorMsgs.add("請輸入正確數字");
				}
				
				//錯誤訊息顯示
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/member_cost.jsp");
					failureView.forward(req, res);
					return;
				}
				
				TopUpRecordVO topUpRecordVO = new TopUpRecordVO();
				topUpRecordVO.setMember_no(member_no);
				topUpRecordVO.setRecord_time(record_time);
				topUpRecordVO.setMoney_record(money_record);
				
				//member傳送過來錯誤處理?
				
				TopUpRecordService topUpRecordSvc = new TopUpRecordService();
				topUpRecordVO = topUpRecordSvc.insertOneTopUpRecord(member_no, record_time, money_record);
				req.setAttribute("topUpRecordVO", topUpRecordVO);
				
				//同步會員儲值紀錄
				Integer member_points = money_record +memberVO.getMember_points();
				
				
						try {
							MemberService memberSvc = new MemberService();
				memberSvc.MemberUpdate(memberVO.getMember_sex(),memberVO.getMember_cellphone() ,memberVO.getMember_password() 
, memberVO.getMember_address(),memberVO.getMember_photo() , member_points ,memberVO.getMember_height(),
memberVO.getMember_weight(), memberVO.getMember_review(),memberVO.getMember_auth(),memberVO.getMember_bodyfat(),memberVO.getMember_card(), memberVO.getMember_wing_span() , memberVO.getMember_no());
						
						//傳送更新過的Session給前端
							memberVO =memberSvc.findOneMember(memberVO.getMember_no());
							//舊的
							session.removeAttribute("memberVO");
							//傳送更新過的
							session.setAttribute("memberVO", memberVO);
						} catch (Exception e) {
							errorMsgs.add("後台同步金額失敗"+e.getMessage());
						}
						
						Thread.sleep(1000);
				//轉送成功路徑
				String url = "/front-end/member/member_cost.jsp";
				res.sendRedirect(req.getContextPath() +url);
				
			} catch (Exception e) {
				errorMsgs.add("新增失敗" + e.getMessage());// TODO: handle exception
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/member_cost.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		
	}// do post end

}// class end
