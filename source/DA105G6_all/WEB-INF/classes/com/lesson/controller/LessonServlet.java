package com.lesson.controller;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.lesson.model.*;
import com.lesson_detail.model.LessonDetailService;
import com.lesson_detail.model.LessonDetailVO;

@MultipartConfig(maxFileSize=1024 * 1024 * 500)
public class LessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static byte[] getPictureByteArray(String url) { //傳圖片用
		byte[] response=null;
		try {
			URL myTokenURL = new URL(url);
			InputStream in = myTokenURL.openConnection().getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			byte[] buf = new byte[4096];
			int n = 0;
			while (-1!=(n=in.read(buf)))
			{
			   out.write(buf, 0, n);
			}
			out.close();
			in.close();
			
			response = out.toByteArray();
		} catch (IOException e ) {
			e.printStackTrace();
		}
		
		return response;
	}	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("search_lesson2".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 

				/***************************2.開始複合查詢***************************************/
				LessonService lessonSvc = new LessonService();
				List<LessonVO> list  = lessonSvc.compositeQuery(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listLessons_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/lesson/search_lesson.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/lesson/lesson_page.jsp");
				failureView.forward(req, res);
			}	

		}

		if ("search_lesson".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("string");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入關鍵字");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/gym_index/lesson_manager.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				
				/***************************2.開始查詢資料*****************************************/
				String sql = "%"+str+"%";
				LessonService lessonSvc = new LessonService();
				List<LessonVO> lessonVO = lessonSvc.searchLesson(sql);
				if (lessonVO.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/gym_index/lesson_manager_search_result.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				List<LessonVO> list = lessonVO;
				session.setAttribute("onlyForSearch",list);
				String url = "/back-end/gym_index/lesson_manager_search_result.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/gym_index/lesson_manager.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("get_One_Lesson".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("lessonNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課程編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lesson/listAllLesson.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				
				/***************************2.開始查詢資料*****************************************/
				LessonService lessonSvc = new LessonService();
				LessonVO lessonVO = lessonSvc.getOneLesson(str);
				if (lessonVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lesson/listAllLesson.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("lessonVO", lessonVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/lesson/view_one_lesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/lesson/listAllLesson.jsp");
				failureView.forward(req, res);
			}
		}
		if ("emp_get_Coach_Lesson".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("coachNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入教練編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/gym_index/lesson_manager_search_result.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				
				/***************************2.開始查詢資料*****************************************/
				LessonService lessonSvc = new LessonService();
				List<LessonVO> lessonVO = lessonSvc.getLessonByCoach(str);
				if (lessonVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/gym_index/lesson_manager_search_result.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("onlyForSearch", lessonVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/gym_index/lesson_manager_search_result.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/gym_index/lesson_manager_search_result.jsp");
				failureView.forward(req, res);
			}
		}		
		if ("C_get_One_Lesson".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("lessonNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課程編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lesson/listAllLesson.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				
				/***************************2.開始查詢資料*****************************************/
				LessonService lessonSvc = new LessonService();
				LessonVO lessonVO = lessonSvc.getOneLesson(str);
				if (lessonVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lesson/listAllLesson.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("lessonVO", lessonVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/lesson/Coach_view_one_lesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/lesson/listAllLesson.jsp");
				failureView.forward(req, res);
			}
		}		
			
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String lessonNo = new String(req.getParameter("lessonNo"));
				/***************************2.開始查詢資料****************************************/
				LessonService lessonSvc = new LessonService();
				LessonVO lessonVO = lessonSvc.getOneLesson(lessonNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("lessonVO", lessonVO);        
				String url = "/front-end/lesson/Coach_update_lesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("error","無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/lesson/Coach_lesson_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			LessonVO lessonVO = new LessonVO(); //用於頁面呈現
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String lessonNo = req.getParameter("lessonNo");
				String coachNo = req.getParameter("coachNo");
				String lessonName = req.getParameter("lessonName");
				if (lessonName == null || lessonName.trim().length() == 0) {
					errorMsgs.put("lessonName","課程名稱: 請勿空白");
				}
				
				String lessonPoint = req.getParameter("lessonPoint").trim();
				if (lessonPoint == null || lessonPoint.trim().length() == 0) {
					errorMsgs.put("lessonPoint","課程簡介請勿空白");
				}
				
				Integer lessonPrice = null;
				try {
					lessonPrice = new Integer(req.getParameter("lessonPrice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("lessonPrice","請瑱入課程價格.");
				}
				
				String lessonContent = req.getParameter("lessonContent").trim();
				if (lessonContent == null || lessonContent.trim().length() == 0) {
					errorMsgs.put("lessonContent","課程內容請勿空白");
				}
				
//				java.sql.Date lessonStartDate = null;
//				try {
//					lessonStartDate = java.sql.Date.valueOf(req.getParameter("lessonStartDate").trim());
//				} catch (IllegalArgumentException e) {
//					lessonStartDate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.put("lessonStartDate","請選擇開放報名日期!");
//				}
				java.util.Date date = new java.util.Date();
				SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date lessonStartDate = java.sql.Date.valueOf(bartDateFormat.format(date));

				
				java.sql.Date lessonEndDate = null;
				try {
					lessonEndDate = java.sql.Date.valueOf(req.getParameter("lessonEndDate").trim());
				} catch (IllegalArgumentException e) {
					lessonEndDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("lessonEndDate","請選擇報名截止日期!");
				}

				LessonVO lessonVOpic = new LessonVO();
				LessonService lessonService=new LessonService();
				lessonVOpic = lessonService.getOneLesson(lessonNo);	
				
				Integer lessonRegistration = null; //報名人數不予修改
				try {
					lessonRegistration = lessonVOpic.getLessonRegistration();
				} catch (NumberFormatException e) {
					errorMsgs.put("error","載入報名人數資料錯誤.");
				}
				
				Integer lessonMaximumPeople = null;
				try {
					lessonMaximumPeople = new Integer(req.getParameter("lessonMaximumPeople").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("error","報名人數請填數字.");
				}				

				Part part = null;

				part = req.getPart("lessonPicture");		
				
		
				InputStream in = part.getInputStream();
				byte[] img = null;

				if(in.available()!=0) {
					img= new byte[in.available()];
					in.read(img);
					in.close();
				} else 
					img=lessonVOpic.getLessonPicture();
				
				
				
				String lessonLocation = new String(req.getParameter("lessonLocation"));
				if (lessonLocation == null || (lessonLocation.trim()).length() == 0) {
					errorMsgs.put("lessonLocation","請輸入地點");
				}

				Integer lessonClass = new Integer(req.getParameter("lessonClass").trim());
				Integer lessonStatus = lessonVOpic.getLessonStatus();
				
				
				List<LessonDetailVO> lcdVO = new ArrayList<>(); 

				if(req.getParameter("lessonDate").isEmpty()) {
					LessonDetailService ldSvc = new LessonDetailService();
					lcdVO =ldSvc.getDetailByLesson(lessonNo);
				}else {
					LessonDetailService ldSvc = new LessonDetailService();
					lcdVO =ldSvc.getDetailByLesson(lessonNo);

					String str = req.getParameter("lessonDate");
					ArrayList<String> list = new ArrayList<>(Arrays.asList(str.split(",")));
					
					boolean updateDate = false;
					int x = lcdVO.size();
					int y = list.size();
					int z = 0;
					if(x!=y){ //天數不同
					updateDate = true;
					}else {
					for(LessonDetailVO ldVO:lcdVO) {
						for(String date1:list) {
							if(ldVO.getLessonDate()==java.sql.Date.valueOf(date1)) {
								z++;
							}
						}							
					}
					if(z!=y) //天數相同,日期不同
						updateDate = true;
					}

					
				if(updateDate==true) {	
				try {
					ldSvc.deleteLessonDetail(lessonNo);

					for(String date1:list) {
						ldSvc.addLessonDetail(lessonNo, (java.sql.Date.valueOf(date1)));
					}					
				} catch (Exception e) {
					errorMsgs.put("lessonDate","在有人報名課程後，即不可再更動上課日期!");
				}
				}
				}
				
				
				lessonVO.setLessonNo(lessonNo);
				lessonVO.setCoachNo(coachNo);
				lessonVO.setLessonName(lessonName);
				lessonVO.setLessonPoint(lessonPoint);
				lessonVO.setLessonPrice(lessonPrice);
				lessonVO.setLessonContent(lessonContent);
				lessonVO.setLessonStartDate(lessonStartDate);
				lessonVO.setLessonEndDate(lessonEndDate);
				lessonVO.setLessonRegistration(lessonRegistration);
				lessonVO.setLessonMaximumPeople(lessonMaximumPeople);
				lessonVO.setLessonPicture(img);
				lessonVO.setLessonLocation(lessonLocation);
				lessonVO.setLessonClass(lessonClass);				
				lessonVO.setLessonStatus(lessonStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("lessonVO", lessonVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/lesson/Coach_update_lesson.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				LessonService lessonSvc = new LessonService();

				lessonSvc.updateLesson(lessonNo, lessonName, lessonPoint, lessonPrice, lessonContent, lessonStartDate, lessonEndDate, lessonMaximumPeople, img, lessonLocation, lessonClass, lessonStatus);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("lessonVO", lessonVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/back-end/lesson/listOneLesson.jsp";
				String url = "/front-end/lesson/Coach_view_one_lesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				req.setAttribute("lessonVO", lessonVO);
				errorMsgs.put("error","無法取得要修改的資料:" +e+ e.getMessage());
				System.out.println("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/lesson/Coach_update_lesson.jsp");
				failureView.forward(req, res);
			}
		}
		

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String coachNo = req.getParameter("coachNo");
				String lessonName = req.getParameter("lessonName");
				if (lessonName == null || lessonName.trim().length() == 0) {
					errorMsgs.put("lessonName","課程名稱: 請勿空白");
				}
				
				String lessonPoint = req.getParameter("lessonPoint").trim();
				if (lessonPoint == null || lessonPoint.trim().length() == 0) {
					errorMsgs.put("lessonPoint","課程簡介請勿空白");
				}
				
				Integer lessonPrice = null;
				try {
					lessonPrice = new Integer(req.getParameter("lessonPrice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("lessonPrice","請瑱入課程價格.");
				}
				
				String lessonContent = req.getParameter("lessonContent").trim();
				if (lessonContent == null || lessonContent.trim().length() == 0) {
					errorMsgs.put("lessonContent","課程內容請勿空白");
				}
				
//				java.sql.Date lessonStartDate = null;
//				try {
//					lessonStartDate = java.sql.Date.valueOf(req.getParameter("lessonStartDate").trim());
//				} catch (IllegalArgumentException e) {
//					lessonStartDate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.put("lessonStartDate","請選擇開放報名日期!");
//				}
				java.util.Date date = new java.util.Date();
				SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date lessonStartDate = java.sql.Date.valueOf(bartDateFormat.format(date));

				
				java.sql.Date lessonEndDate = null;
				try {
					lessonEndDate = java.sql.Date.valueOf(req.getParameter("lessonEndDate").trim());
				} catch (IllegalArgumentException e) {
					lessonEndDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("lessonEndDate","請選擇報名截止日期!");
				}

				Integer lessonRegistration =0;

				Integer lessonMaximumPeople = null;
				try {
					lessonMaximumPeople = new Integer(req.getParameter("lessonMaximumPeople").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("lessonMaximumPeople","請瑱入人數上限.");
				}
				
				Part part = null;
				part = req.getPart("lessonPicture");
				
				if (part.getSize() == 0) {
					errorMsgs.put("lessonPicture","請選擇上傳圖片");
					}
				
				part.getInputStream();
				byte[] img = null;
				try {
					BufferedInputStream in = new BufferedInputStream(part.getInputStream());
					img= new byte[in.available()];
					in.read(img);
					in.close();
				} catch (IOException e ) {
					e.printStackTrace();
					errorMsgs.put("lessonPicture","圖片上傳失敗");
				}

				
				String lessonLocation = req.getParameter("lessonLocation").trim();
				if (lessonLocation == null || lessonLocation.trim().length() == 0) {
					errorMsgs.put("lessonLocation","上課地點請勿空白");
				}
				
				Integer lessonClass = new Integer(req.getParameter("lessonClass").trim());

				Integer lessonStatus = new Integer(req.getParameter("lessonStatus").trim());
				
				
				
				LessonVO lessonVO = new LessonVO();
				lessonVO.setCoachNo(coachNo);
				lessonVO.setLessonName(lessonName);
				lessonVO.setLessonPoint(lessonPoint);
				lessonVO.setLessonPrice(lessonPrice);
				lessonVO.setLessonContent(lessonContent);
				lessonVO.setLessonStartDate(lessonStartDate);
				lessonVO.setLessonEndDate(lessonEndDate);
				lessonVO.setLessonRegistration(lessonRegistration);
				lessonVO.setLessonMaximumPeople(lessonMaximumPeople);
				lessonVO.setLessonPicture(img);
				lessonVO.setLessonLocation(lessonLocation);
				lessonVO.setLessonClass(lessonClass);
				lessonVO.setLessonStatus(lessonStatus);
				
				
	
				List<LessonDetailVO> lcdVO = new LinkedList<>();
				
				try {
					String str = req.getParameter("lessonDate");
					LinkedList<String> list = new LinkedList<>(Arrays.asList(str.split(",")));

					for(String date1:list) {
						LessonDetailVO lessonDetailVO = new LessonDetailVO();
						lessonDetailVO.setLessonDate(java.sql.Date.valueOf(date1));
						lcdVO.add(lessonDetailVO);
					}
				} catch (IllegalArgumentException e) {
					errorMsgs.put("lessonDate","請選擇開課日期!");
				}

				
		
//		java.util.Date date = new java.util.Date();
//		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		memVO.setMemJoinDate(java.sql.Date.valueOf(bartDateFormat.format(date))); //用於取得當前日期


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("lessonVO", lessonVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/lesson/Coach_add_lesson.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				LessonService lessonSvc = new LessonService();
				lessonVO = lessonSvc.addLessonXDetail(coachNo, lessonName, lessonPoint, lessonPrice, lessonContent, lessonStartDate, lessonEndDate, lessonRegistration, lessonMaximumPeople, img, lessonLocation, lessonClass, lessonStatus, lcdVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/lesson/Coach_lesson_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("errorMsgs",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/lesson/Coach_add_lesson.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // for_ajax!

//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
			PrintWriter out = res.getWriter();
			try {
				/***************************1.接收請求參數***************************************/
				String lessonNo = new String(req.getParameter("lessonNo"));
				
				/***************************2.開始刪除資料***************************************/
				LessonService lessonSvc = new LessonService();
				lessonSvc.deleteLesson(lessonNo);
				out.print('0');
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/back-end/lesson/listAllLesson.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				out.print('1');
//				if(e.getMessage().substring(26, 35).equals("ORA-02292")) {
//					errorMsgs.add("由於已有會員購買課程，因此無法逕行刪除!");
//				}else {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				}
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/lesson/listAllLesson.jsp");
//				failureView.forward(req, res);
			}
		}

		if ("get_coach_lesson_date_ajax".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String coachNo = req.getParameter("coachNo");
				LessonService lSvc = new LessonService();
				List<String> coLeDate = lSvc.getLessonDateByCoach(coachNo);
				
				Object[] array = coLeDate.toArray();
		        String s = Arrays.toString(array);
				out.print(s);
				
			} catch (Exception e) {
				out.print(e);
			}	
		}
		if ("get_coach_lesson_by_date_ajax".equals(action)) { 
			PrintWriter out = res.getWriter();

			try {
				String coachNo = req.getParameter("coachNo");
				String lessonDate = req.getParameter("lessonDate");
				LessonService lSvc = new LessonService();
				String lessonName = lSvc.getLessonDateByCoach2(coachNo, lessonDate);
				out.print(lessonName);
				
			} catch (Exception e) {
				out.print(e);
				System.out.println(e.getMessage());
			}	
		}
				
		
		
		if ("lesson_update_status".equals(action)) {
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String url = req.getParameter("requestURL");
				String lessonNo = req.getParameter("lessonNo");
				Integer lessonStatus = new Integer(req.getParameter("lessonStatus"));
		
				/***************************2.開始查詢資料*****************************************/
				LessonService lSvc = new LessonService();
				lSvc.updateLessonStatus(lessonNo, lessonStatus);
			
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	
		
		if ("lesson_update_picture".equals(action)) {
			PrintWriter out = res.getWriter();

				LessonJNDIDAO dao = new LessonJNDIDAO();
				
			
			try {
				Map<String,String>picMap = new HashMap<String,String>();
				picMap.put("LE001",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/0.jpeg");
				picMap.put("LE002",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/1.jpg");
				picMap.put("LE003",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/2.jpg");
				picMap.put("LE004",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/3.jpg");
				picMap.put("LE005",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/4.jfif");
				picMap.put("LE006",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/5.jfif");
				picMap.put("LE007",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/6.jpg");
				picMap.put("LE008",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/7.jfif");
				picMap.put("LE009",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/8.jpg");
				picMap.put("LE010",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/9.jpg");
				picMap.put("LE014",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/10.jpg");
				picMap.put("LE012",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/11.jpg");
				picMap.put("LE013",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/12.jpg");
				picMap.put("LE014",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/13.jpg");
				picMap.put("LE015",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/14.jpg");
				picMap.put("LE016",req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/lesson_pkg/lesson_pic/15.jpg");
				
				ArrayList<LessonVO> picArray = new ArrayList<LessonVO>();
				
			    for (Map.Entry<String, String> entry : picMap.entrySet()) {
					LessonVO lessonVO = new LessonVO();
					lessonVO.setLessonNo(entry.getKey());
					lessonVO.setLessonPicture(getPictureByteArray(entry.getValue()));			
					picArray.add(lessonVO);	
			      }
							
			    dao.updateLessonPic(picArray);
				
				out.print('0');

		}catch (Exception e) {
			System.out.println(e.getMessage());
			}
		}
		
		}
}
