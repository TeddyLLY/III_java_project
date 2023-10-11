package com.chat_room.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chat_room.model.ChatRoomService;
import com.coach.model.CoachService;
import com.coach.model.CoachVO;
import com.jedis_pool.JedisUtil;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet("/ChatRoomServlet")
public class ChatRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChatRoomServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected synchronized void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("changeOneCoachRoom".equals(action)) {
			// findByPk
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String chat_room_no = req.getParameter("chat_room_no");
				req.setAttribute("chat_room_no", chat_room_no);

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/coach/coach_talk.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("已被對方刪除 " + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/front-end/coach/coach_chose_friend.jsp");
				failView.forward(req, res);
			}
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		if ("changeOneMemberRoom".equals(action)) {
			// findByPk
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String chat_room_no = req.getParameter("chat_room_no");
				req.setAttribute("chat_room_no", chat_room_no);

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/member_talk.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("已被對方刪除 " + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/front-end/member/member_chose_friend.jsp");
				failView.forward(req, res);
			}
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		if ("memberInsertOneUser".equals(action)) {
			// insertChatRoom
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				String coach_no = req.getParameter("coach_no").trim();
				if (coach_no == null || coach_no.trim().length() == 0) {
					errorMsgs.add("請輸入教練編號");
				}
				CoachVO coachVO = new CoachVO();
				CoachService coachSvc = new CoachService();
				coachVO = coachSvc.findOneCoach(coach_no);
				if (coachVO == null || coachVO.toString().trim().length() == 0) {
					errorMsgs.add("沒有此教練");
				}
				MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
				String member_no = memberVO.getMember_no();

				if (!errorMsgs.isEmpty()) {
					errorMsgs.add("請修正以上錯誤");
					RequestDispatcher failView = req.getRequestDispatcher("front-end/member/member_chose_friend.jsp");
					failView.forward(req, res);
					return;
				}

				ChatRoomService chatRoomSvc = new ChatRoomService();
				chatRoomSvc.insertChatRoom(member_no + coach_no, member_no, coach_no);
				res.sendRedirect(req.getContextPath() + "/front-end/member/member_chose_friend.jsp");
//				RequestDispatcher successView = req.getRequestDispatcher("front-end/member/member_chose_friend.jsp");
//				successView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add("已存在好友" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("front-end/member/member_chose_friend.jsp");
				failView.forward(req, res);
				return;
			}
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		if ("coachInsertOneUser".equals(action)) {
			// insertChatRoom
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				String member_no = req.getParameter("member_no").trim();
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				MemberVO memberVO = new MemberVO();
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.findOneMember(member_no);
				if (memberVO == null || memberVO.toString().trim().length() == 0) {
					errorMsgs.add("沒有此會員");
				}
				CoachVO coachVO = (CoachVO) session.getAttribute("coachVO");
				String coach_no = coachVO.getCoach_no();

				if (!errorMsgs.isEmpty()) {
					errorMsgs.add("請修正以上錯誤");
					RequestDispatcher failView = req.getRequestDispatcher("front-end/coach/coach_chose_friend.jsp");
					failView.forward(req, res);
					return;
				}

				ChatRoomService chatRoomSvc = new ChatRoomService();
				chatRoomSvc.insertChatRoom(member_no + coach_no, member_no, coach_no);
				res.sendRedirect(req.getContextPath() + "/front-end/coach/coach_chose_friend.jsp");
				return;
			} catch (Exception e) {
				errorMsgs.add("已存在好友" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("front-end/coach/coach_chose_friend.jsp");
				failView.forward(req, res);
				return;
			}
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		if ("deleteCoachOneUserRoom".equals(action)) {
			// deleteChatRoom
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String chat_room_no = req.getParameter("chat_room_no").trim();
				if (chat_room_no == null || chat_room_no.trim().length() == 0) {
					errorMsgs.add("你已被對方刪除");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("front-end/coach/coach_talk.jsp");
					failView.forward(req, res);
					return;
				}

				// 刪除sql chatroom
				ChatRoomService chatRoomSvc = new ChatRoomService();
				chatRoomSvc.deleteChatRoom(chat_room_no);

				// 刪除redis 聊天訊息
				JedisPool pool = JedisUtil.getJedisPool();
				Jedis jedis = pool.getResource();
				jedis.auth("123456");
				try {
					if (jedis.llen("room:" + chat_room_no) == null || jedis.llen("room:" + chat_room_no).toString().length() == 0) {
					
					} else {
						System.out.println(chat_room_no);
						jedis.del("room:" + chat_room_no);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage() + "刪除空聊天訊息excption");
				}
				jedis.close();

				errorMsgs.add("刪除成功");
				// 轉送successView
				RequestDispatcher successView = req.getRequestDispatcher("front-end/coach/coach_chose_friend.jsp");
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				errorMsgs.add("無法取的要修改的資料" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("front-end/coach/coach_talk.jsp");
				failView.forward(req, res);
				return;
			}
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("deleteMemberOneUserRoom".equals(action)) {
			// deleteChatRoom
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String chat_room_no = req.getParameter("chat_room_no").trim();
				if (chat_room_no == null || chat_room_no.trim().length() == 0) {
					errorMsgs.add("你已被對方刪除");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("front-end/member/member_talk.jsp");
					failView.forward(req, res);
					return;
				}

				// 刪除sql chatroom
				ChatRoomService chatRoomSvc = new ChatRoomService();
				chatRoomSvc.deleteChatRoom(chat_room_no);

				// 刪除redis 聊天訊息
				JedisPool pool = JedisUtil.getJedisPool();
				Jedis jedis = pool.getResource();
				jedis.auth("123456");
				try {
					if (jedis.llen("room:" + chat_room_no) == null || jedis.llen("room:" + chat_room_no).toString().length() == 0) {
					
					} else {
						System.out.println(chat_room_no);
						jedis.del("room:" + chat_room_no);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage() + "刪除空聊天訊息excption");
				}
				jedis.close();

				errorMsgs.add("刪除成功");
				// 轉送successView
				RequestDispatcher successView = req.getRequestDispatcher("front-end/member/member_chose_friend.jsp");
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				errorMsgs.add("無法取的要修改的資料" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("front-end/member/member_talk.jsp");
				failView.forward(req, res);
				return;
			}
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}// do post end
}
