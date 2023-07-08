package com.lesson_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.appointment_order.model.AppointmentOrderService;
import com.appointment_order_detail.model.AppointmentOrderDetailVO;
import com.lesson.model.LessonService;
import com.lesson.model.LessonVO;
import com.lesson_detail.model.LessonDetailService;
import com.lesson_detail.model.LessonDetailVO;
import com.lesson_order.model.LessonOrderService;
import com.lesson_order.model.LessonOrderVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.member_lesson_detail.model.MemberLessonDetailService;
import com.member_lesson_detail.model.MemberLessonDetailVO;


public class LessonOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LessonOrderServlet() {
        super();
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("delete".equals(action)){ //for_ajax
			PrintWriter out = res.getWriter();		
			try {
				String lessonOrderNo = new String(req.getParameter("lessonOrderNo"));
				
				LessonOrderService loSvc = new LessonOrderService();
				LessonOrderVO leoVO = loSvc.getOneLessonOrder(lessonOrderNo);
				int lessonPrice = leoVO.getLessonPrice();
				String memberNo = leoVO.getMemberNo();
				String lessonNo = leoVO.getLessonNo();
			
				
				loSvc.deleteLessonOrder2(lessonOrderNo, memberNo, lessonNo, lessonPrice);
				
				out.print('0'); //success!
				
			}catch(Exception e) {
				out.print(e.getMessage());
				System.out.println(e.getMessage());
			}
			
		}
		
		if("get_mem_Lesson_Order_ajax".equals(action)) {//查會員有哪些課程
			

			PrintWriter out = res.getWriter();
			try {
				String memberNo = new String(req.getParameter("memberNo"));
					
				LessonOrderService loSvc = new LessonOrderService();
				String lolistVO = loSvc.getMemberAllLessonOrder_ajax(memberNo);

						out.print(lolistVO);

			}catch(Exception e){
				out.print("查詢資料失敗"+e.getMessage());

			}
		}
		
		if("get_mem_Lesson_Order".equals(action)) {
			
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			String requestURL=req.getParameter("requestURL");
			
			try {
				String memberNo = new String(req.getParameter("memberNo"));
				if (memberNo == null || (memberNo.trim()).length() == 0) {
					errorMsgs.add("請輸入關鍵字");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}

						
				LessonOrderService loSvc = new LessonOrderService();
				List<LessonOrderVO> lolistVO = loSvc.getMemberAllLessonOrder(memberNo);
				HttpSession session = req.getSession();
				session.setAttribute("lolistVO", lolistVO);

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/lesson_order/mem_lesson_order.jsp");
				successView.forward(req, res);

			}catch(Exception e){
				errorMsgs.add("查詢資料失敗"+e.getMessage());

				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
			}
		}
		
		
		if("get_lessonOrder_ajax".equals(action)) { 
			
			String lessonOrderNo = new String(req.getParameter("lessonOrderNo"));
			LessonOrderService loSvc = new LessonOrderService();
			String lessonOrderVO = loSvc.getOneLessonOrder_ajax(lessonOrderNo);
			PrintWriter out = res.getWriter();
			out.print(lessonOrderVO);

	}	
					
		
		if("getOne_For_Update".equals(action)) {
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				String lessonOrderNo = new String(req.getParameter("lessonOrderNo"));
				LessonOrderService loSvc = new LessonOrderService();
				LessonOrderVO lessonOrderVO = loSvc.getOneLessonOrder(lessonOrderNo);

				req.setAttribute("lessonOrderVO", lessonOrderVO);
				
				String url = "/front-end/lesson_order/one_lesson_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e){
				errorMsgs.add("查詢資料失敗"+e.getMessage());
				String url = "/back-end/lesson_order/lesson_order_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		
		if("insert_Lesson_OrderxDetail".equals(action)) { //新增訂單(***含明細***)
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			PrintWriter out = res.getWriter();
			try {

				String memberNo = new String(req.getParameter("memberNo"));
				String lessonNo = new String(req.getParameter("lessonNo"));	
				
				LessonService lessonSvc = new LessonService();
				LessonVO lessonVO = lessonSvc.getOneLesson(lessonNo); //取得課程資訊
				
				LessonDetailService ldSvc = new LessonDetailService();
				List<LessonDetailVO> ldVO = ldSvc.getDetailByLesson(lessonNo);//取得本課程的上課日期
				
				LessonOrderService loSvc = new LessonOrderService();
				List<LessonOrderVO> lessonOrder = loSvc.getMemberAllLessonOrder(memberNo);//會員所有課程	
				MemberLessonDetailService mldSvc = new MemberLessonDetailService(); 

				
				for(LessonOrderVO lesson:lessonOrder) {
					List<MemberLessonDetailVO> mldVO= mldSvc.getDetailByLessonOrder(lesson.getLessonOrderNo());
					for(MemberLessonDetailVO mld:mldVO) {
						for(LessonDetailVO ld:ldVO) {
							if(ld.getLessonDate().equals(mld.getLessonDate())) {
								out.print("3"); //衝堂
							return;
							}
						}
					}
				}
				

				AppointmentOrderService apSvc = new AppointmentOrderService();
				List<AppointmentOrderDetailVO> apdlist = apSvc.getMemCertainDate2(memberNo);				
				for(AppointmentOrderDetailVO apdVO:apdlist) {
						for(LessonDetailVO ld:ldVO) {
							if(ld.getLessonDate().equals(apdVO.getAppointmentDate())) {
								out.print("3"); //衝堂
							return;
							}
						}
					}
								
				
						
				if(lessonVO.getLessonRegistration()==lessonVO.getLessonMaximumPeople()){//比對上課人數
					out.print("2");//上課人數已滿
					return;
				}
				
				MemberService memberSvc = new MemberService();  //取得會員點數
				MemberVO memberVO = memberSvc.findOnePoints(memberNo);


				if(memberVO.getMember_points()<lessonVO.getLessonPrice()){//比對會員點數是否足夠扣款
					out = res.getWriter();
					out.print("1");//會員點數不足
					return;
				}
					
				LessonOrderVO lessonOrderVO = new LessonOrderVO();
				
				int lessonStatus =1; //訂單狀態預設為1	
				int lessonPrice =lessonVO.getLessonPrice();
				
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp.valueOf(bartDateFormat.format(timestamp)); //用於取得當前日期			
				Timestamp dateAcquisition = java.sql.Timestamp.valueOf(bartDateFormat.format(timestamp));
				
				
				loSvc.addLessonOrder2(memberNo, lessonNo, lessonPrice, dateAcquisition, lessonStatus, ldVO);
				
				lessonOrderVO.setLessonNo(lessonNo);
				lessonOrderVO.setMemberNo(memberNo);		
				lessonOrderVO.setDateAcquisition(dateAcquisition);		
				lessonOrderVO.setLessonPrice(lessonPrice);	
				lessonOrderVO.setLessonStatus(lessonStatus);

//				req.setAttribute("lessonOrderVO", lessonOrderVO); //傳資料至購買課程成功頁面or訂單頁面
//				req.setAttribute("ldVO", ldVO);
				
				out.print("0");
	
//				String url = "/front-end/lesson_order/lesson_purchase_success.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);    //購買成功則不回應(ajax)
			}catch(Exception e){
				errorMsgs.add("查詢資料失敗"+e.getMessage());
				out.print(e.getMessage());
//				String url = "/back-end/lesson_order/lesson_order_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res); 
			}
		}
		
		if("lesson_conflict".equals(action)) { //衝突查詢 for ajax
			
			PrintWriter out = res.getWriter();
			try {

				String memberNo = new String(req.getParameter("memberNo"));
				String lessonNo = new String(req.getParameter("lessonNo"));	
				
				LessonService leSvc = new LessonService();
							
				LessonDetailService ldSvc = new LessonDetailService();
				List<LessonDetailVO> ldVO = ldSvc.getDetailByLesson(lessonNo);//取得本課程的上課日期
				
				LessonOrderService loSvc = new LessonOrderService();
				List<LessonOrderVO> lessonOrder = loSvc.getMemberAllLessonOrder(memberNo); //取得會員所有參加課程
	
				MemberLessonDetailService mldSvc = new MemberLessonDetailService();
				
				
				for(LessonOrderVO lesson:lessonOrder) { 
					List<MemberLessonDetailVO> mldVO= mldSvc.getDetailByLessonOrder(lesson.getLessonOrderNo());//取得每筆會員課程明細
					for(MemberLessonDetailVO mld:mldVO) {//課程明細中取出日期
						for(LessonDetailVO ld:ldVO) {//取出本課程上課日期 與之比對
							if(ld.getLessonDate().equals(mld.getLessonDate()))
								out.print("<li>"+ld.getLessonDate().toString()+"("+leSvc.getOneLesson(lesson.getLessonNo()).getLessonName()+")"+"</li>");
						}
					}
				}
				
		
				AppointmentOrderService apSvc = new AppointmentOrderService();
				List<AppointmentOrderDetailVO> apdlist = apSvc.getMemCertainDate2(memberNo);			
				for(AppointmentOrderDetailVO apdVO:apdlist) {
						for(LessonDetailVO ld:ldVO) {
							if(ld.getLessonDate().equals(apdVO.getAppointmentDate())) {
								out.print("<li>"+ld.getLessonDate().toString()+"(個人預約課程)</li>");
							}
						}
					}				
				
				return;

				
			}catch(Exception e){
				out.print(e.getMessage());

			}
		}
		if ("get_mem_lesson_date_ajax".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String memberNo = req.getParameter("memberNo");
				LessonOrderService lSvc = new LessonOrderService();
				List<String> memLeDate = lSvc.getLessonDateByMember(memberNo);
				
				Object[] array = memLeDate.toArray();
		        String s = Arrays.toString(array);
				out.print(s);
				
			} catch (Exception e) {
				out.print(e);
			}	
		}

		if ("get_lesson_join_member".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String lessonNo = req.getParameter("lessonNo");
				LessonOrderService lSvc = new LessonOrderService();
				List<LessonOrderVO> lessonOrderlist = lSvc.getLessonJoinMember(lessonNo);				
				req.setAttribute("lessonOrderlist", lessonOrderlist); //傳資料至購買課程成功頁面or訂單頁面

	
				String url = "/front-end/lesson/Coach_lesson_MemList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				out.print(e);
			}	
		}		

		if ("change_memDetail_ajax".equals(action)) { 
			PrintWriter out = res.getWriter();


				String lessonNo = req.getParameter("lessonNo");
				LessonOrderService lSvc = new LessonOrderService();
				

				
				lSvc.changeMemberLessonStatus(lessonNo,2,0);//開放報到
				out.print(0);

		}	
		if ("stop_change_memDetail_ajax".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String lessonNo = req.getParameter("lessonNo");
				LessonOrderService lSvc = new LessonOrderService();

				lSvc.changeMemberLessonStatus(lessonNo, 0,2);//關閉報到
				
				out.print(0);
				
			} catch (Exception e) {
				out.print(e);
			}	
		}
		
		if ("finishLO".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String lessonOrderNo = req.getParameter("lessonOrderNo");
				LessonOrderService lSvc = new LessonOrderService();
				
				int lessonStatus = 0;

				lSvc.updateLessonOrderStatus(lessonOrderNo, lessonStatus);
				
				out.print(0);
				
			} catch (Exception e) {
				out.print(e);
			}	
		}			
		
	}		
		
}
	
	


