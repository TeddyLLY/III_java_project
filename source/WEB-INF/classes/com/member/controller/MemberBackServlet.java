package com.member.controller;

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

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/MemberBackServlet")
@MultipartConfig
public class MemberBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberBackServlet() {
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

		
//////////////////////////////////////////////////////////////////////////////////////////////
		if("listMember_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				HttpSession session = req.getSession();
				Map<String , String[]> map =(Map<String , String[]>)session.getAttribute("map");
				if(req.getParameter("whichPage") == null ) {
					HashMap<String , String[]> map1 = new HashMap<String , String[]>(req.getParameterMap());
					session.setAttribute("map", map1);
					map=map1;
				}
				MemberService memberSvc = new MemberService();
				List<MemberVO> list = memberSvc.findQueryMember(map);
				
				session.removeAttribute("listMember_ByCompositeQuery");
				session.setAttribute("listMember_ByCompositeQuery" , list);
				RequestDispatcher successview = req.getRequestDispatcher("/back-end/member-back/listMember_ByQuery.jsp");
				successview.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/member-back/select_member.jsp");
				failView.forward(req, res);
			}
		
		} 
		

//////////////////////////////////////////////////////////////////////////////////////////////
		if("deleteMemberForStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				String member_no = new String(req.getParameter("member_no"));
				if(member_no!= null || member_no.trim().length()!=0) {
					errorMsgs.add("刪除成功");
				}
				MemberService memSvc = new MemberService();			
				memSvc.deleteMember(member_no);

				if(requestURL.equals("/back-end/member-back/listMember_ByQuery.jsp" )){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<MemberVO> list  =memSvc.findQueryMember(map);
					req.setAttribute("listMember_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add("不能刪除審核通過會員:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member-back/listMember_ByQuery.jsp");
				failureView.forward(req, res);
			}
		}
		
//////////////////////////////////////////////////////////////////////////////////////////////
		if("UpdateStatus".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_no = new String(req.getParameter("member_no"));
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.findOneMember(member_no);
				req.setAttribute("memberVO", memberVO);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member-back/update_member_status.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member-back/listMember_ByQuery.jsp");
				failureView.forward(req, res);
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////
		if("UpdateStatusSuccess".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				String member_no = req.getParameter("member_no");
				Integer member_review =new Integer(req.getParameter("member_review")) ;
				if(member_review == null|| member_review.toString().trim().length()==0) {
					errorMsgs.add("請選擇會員狀態");
				}
				Integer member_auth = new Integer(req.getParameter("member_auth"));
				if(member_auth == null || member_auth.toString().trim().length()==0) {
					errorMsgs.add("請選擇會員權限");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("back-end/member-back/update_member_status.jsp");
					failView.forward(req, res);
					return;
				}
				MemberVO memberVO = new MemberVO();
				memberVO.setMember_no(member_no);
				MemberService memberSvc = new MemberService();
				memberSvc.updateMemberStatus(member_review, member_auth, member_no);
				memberVO = memberSvc.findOneMember(member_no);
				req.setAttribute("memberVO", memberVO);
				
				//send success view
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				List<MemberVO> list  =memberSvc.findQueryMember(map);
				req.setAttribute("listMember_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				
			} catch (Exception e) {
				errorMsgs.add("更改權限失敗" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("back-end/member-back/update_member_status.jsp");
				failView.forward(req, res);
				return;
			}
		}
		
//以下為base		
//////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("findOneMember".equals(action)) { // 搜尋單一會員// 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String str = new String(req.getParameter("member_no"));
//				if (str == null || (str.trim().length() == 0)) {
//					errorMsgs.add("請輸入會員編號");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				String member_no = null;
//				try {
//					member_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("會員編號格式不正確");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				MemberService memSvc = new MemberService();
//				MemberVO memberVO = memSvc.findOneMember(member_no);
//				if (memberVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				req.setAttribute("memberVO", memberVO);
//				String url = "/back-end/member/listOneMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("getOne_For_Update".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String member_no = new String(req.getParameter("member_no"));
//				MemberService memSvc = new MemberService();
//				MemberVO memberVO = memSvc.findOneMember(member_no);
//				req.setAttribute("memberVO", memberVO);
//				String url = "/back-end/member/update_member_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/listAllMember.jsp");
//				failureView.forward(req, res);
//			}
//		} // listAllEmp.jsp req end
//
/////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("MemberUpdate".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String member_sex = req.getParameter("member_sex").trim();//
//				String member_cellphone = req.getParameter("member_cellphone").trim();//
//
//				String member_password = req.getParameter("member_password").trim();//
//				if (member_password == null || member_password.trim().length() == 0) {
//
//					errorMsgs.add("請填寫密碼");
//				}
//
//				String member_address = req.getParameter("member_address").trim();//
//
//				InputStream in = req.getPart("member_photo").getInputStream();//
//				byte[] member_photo = null;
//				if (in.available() != 0) {
//					member_photo = new byte[in.available()];
//					in.read(member_photo);
//					in.close();
//				} else {
//					errorMsgs.add("請上傳照片");
//				}
//
//				Integer member_points = new Integer(req.getParameter("member_points").trim()); //
//				Integer member_height = null;
//				try {
//					member_height = new Integer(req.getParameter("member_height").trim());
//
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請選擇正確身高");
//				}
//
//				Integer member_weight = null;
//				try {
//					member_weight = new Integer(req.getParameter("member_weight").trim());
//
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請選擇正確體重");
//				}
//
//				Integer member_review = null;
//				try {
//					member_review = new Integer(req.getParameter("member_review").trim());
//				} catch (NumberFormatException e) {
//					member_review = 0; // 改成驗證中(預設)
//					errorMsgs.add("請選擇正確審核狀態");
//				}
//
//				Integer member_auth = null;
//				try {
//					member_auth = new Integer(req.getParameter("member_auth").trim());
//				} catch (NumberFormatException e) {
//					member_auth = 0; // 改成驗證中(預設)
//					errorMsgs.add("請選擇正確權限狀態");
//				}
//
//				Double member_bodyfat = null;
//				try {
//					member_bodyfat = new Double(req.getParameter("member_bodyfat").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請填寫正確體脂");
//				}
//
//				String member_card = req.getParameter("member_card").trim();
//				String member_no = req.getParameter("member_no");
//
//				MemberVO memberVO = new MemberVO();
//				memberVO.setMember_sex(member_sex);
//				memberVO.setMember_cellphone(member_cellphone);
//				memberVO.setMember_password(member_password);
//				memberVO.setMember_address(member_address);
//				memberVO.setMember_photo(member_photo);
//				memberVO.setMember_points(member_points);
//				memberVO.setMember_height(member_height);
//				memberVO.setMember_weight(member_weight);
//				memberVO.setMember_review(member_review);
//				memberVO.setMember_auth(member_auth);
//				memberVO.setMember_bodyfat(member_bodyfat);
//				memberVO.setMember_card(member_card);
//				memberVO.setMember_no(member_no);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
//
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
//				MemberService memSvc = new MemberService();
//				memberVO = memSvc.MemberUpdate(member_sex, member_cellphone, member_password, member_address,
//						member_photo, member_points, member_height, member_weight, member_review, member_auth,
//						member_bodyfat, member_card, member_no);
//				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/back-end/member/listOneMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
////if end		
//
///////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("memberInsert".equals(action)) { // memberInsert
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				String member_name = req.getParameter("member_name").trim();
//				String member_name_reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (member_name == null || member_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else if (!member_name.trim().matches(member_name_reg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}
//				String member_sex = req.getParameter("member_sex").trim();
//				if (member_sex == null || member_sex.trim().length() == 0) {
//					errorMsgs.add("請選擇性別");
//				}
//				String member_cellphone = req.getParameter("member_cellphone").trim();
//				String member_address = req.getParameter("member_address").trim();
//
//				InputStream in = req.getPart("member_photo").getInputStream();
//				byte[] member_photo = null;
//
//				if (in.available() != 0) {
//					member_photo = new byte[in.available()];
//					in.read(member_photo);
//					in.close();
//				} else {
//					errorMsgs.add("請上傳照片");
//				}
//				String member_account = req.getParameter("member_account").trim();
//				if (member_account == null || member_account.trim().length() == 0) {
//					errorMsgs.add("請填寫帳號");
//				}
//				String member_password = req.getParameter("member_password").trim();
//				if (member_password == null || member_password.trim().length() == 0) {
//					errorMsgs.add("請填寫密碼");
//				}
//				Integer member_points = null;
//				try {
//					member_points = new Integer(req.getParameter("member_points").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請選擇正確點數");
//				}
//				Integer member_height = null;
//				try {
//					member_height = new Integer(req.getParameter("member_height").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請選擇正確身高");
//				}
//				Integer member_weight = null;
//				try {
//					member_weight = new Integer(req.getParameter("member_weight").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請選擇正確體重");
//				}
//				Integer member_review = null;
//				try {
//					member_review = new Integer(req.getParameter("member_review").trim());
//				} catch (NumberFormatException e) {
//					member_review = 0; // 改成驗證中(預設)
//					errorMsgs.add("請選擇正確審核狀態");
//				}
//				Integer member_auth = null;
//				try {
//					member_auth = new Integer(req.getParameter("member_auth").trim());
//				} catch (NumberFormatException e) {
//					member_auth = 0; // 改成驗證中(預設)
//					errorMsgs.add("請選擇正確權限狀態");
//				}
//				Double member_bodyfat = null;
//				try {
//					member_bodyfat = new Double(req.getParameter("member_bodyfat").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請填寫正確體脂");
//				}
//				String member_card = req.getParameter("member_card").trim();
//
//				MemberVO memberVO = new MemberVO();
//				memberVO.setMember_name(member_name);
//				memberVO.setMember_sex(member_sex);
//				memberVO.setMember_cellphone(member_cellphone);
//				memberVO.setMember_account(member_account);
//				memberVO.setMember_password(member_password);
//				memberVO.setMember_address(member_address);
//				memberVO.setMember_photo(member_photo);
//				memberVO.setMember_points(member_points);
//				memberVO.setMember_height(member_height);
//				memberVO.setMember_weight(member_weight);
//				memberVO.setMember_review(member_review);
//				memberVO.setMember_auth(member_auth);
//				memberVO.setMember_bodyfat(member_bodyfat);
//				memberVO.setMember_card(member_card);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/addMember.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
//
//				MemberService memSvc = new MemberService();
//				memberVO = memSvc.memberInsert(member_name, member_sex, member_cellphone, member_account,
//						member_password, member_address, member_photo, member_points, member_height, member_weight,
//						member_review, member_auth, member_bodyfat, member_card);
//				req.setAttribute("memberVO", memberVO); 
//				String url = "/back-end/member/listAllMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOne
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("新增會員失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/addMember.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
////////////////////////////////////////////////////////////////////////////////////////////////
//
//		if ("deleteMember".equals(action)) { 
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String member_no = new String(req.getParameter("member_no"));
//
//				MemberService memSvc = new MemberService();
//				memSvc.deleteMember(member_no);
//
//				String url = "/back-end/member/listAllMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/listAllMember.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
////////////////////////////////////////////////////////////////////////////////////////////////

	}// do post end
}// class end
