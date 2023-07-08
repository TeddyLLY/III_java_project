package com.employee.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.authority.model.AuthorityService;
import com.authority.model.AuthorityVO;

import com.employee.model.*;
import com.login.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 來自select_page.jsp的請求 // 來自 dept/listAllDept.jsp的請求
		if ("listAuthority_ByEmployee_A".equals(action) || "listAuthority_ByEmployee_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String employee_no = req.getParameter("employee_no");

				/*************************** 2.開始查詢資料 ****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				List<String> set = employeeSvc.getEmployeeByAuthority(employee_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listAuthority_ByEmployee", set); // 資料庫取出的set物件,存入request
				
				String url = null;
				if ("listAuthority_ByEmployee_A".equals(action))
					url = "/back-end/authority/listAuthority_ByEmployee.jsp"; // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listAuthority_ByEmployee_B".equals(action))
					url = "/back-end/authority/listAllAuthority.jsp"; // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("employee_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String employee_no = null;
				try {
					employee_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(employee_no);
				if (employeeVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("employeeVO", employeeVO);
				String url = "/back-end/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String employee_no = new String(req.getParameter("employee_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(employee_no);
				List<String> set = employeeSvc.getEmployeeByAuthority(employee_no);
				System.out.println("set"+set);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("employeeVO", employeeVO);
				req.setAttribute("listAuthority_ByEmployee", set); // 資料庫取出的set物件,存入request
				String url = "/back-end/employee/update_employee_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String employee_no = req.getParameter("employee_no");

				String employee_name = req.getParameter("employee_name");
				String employee_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (employee_name == null || employee_name.trim().length() == 0) {
					errorMsgs.add("員工姓名:請勿空白");

				} else if (!employee_name.trim().matches(employee_nameReg)) {
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String employee_title = req.getParameter("employee_title").trim();
				if (employee_title == null || employee_title.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}

				String employee_account = req.getParameter("employee_account").trim();
				if (employee_account == null || employee_account.trim().length() == 0) {
					errorMsgs.add("account請勿空白");
				}

//				String employee_password = req.getParameter("employee_password").trim();
//				if (employee_password == null || employee_password.trim().length() == 0) {
//					errorMsgs.add("password請勿空白");
//				}
//				
				String employee_mail = req.getParameter("employee_mail").trim();
				if (employee_mail == null || employee_mail.trim().length() == 0) {
					errorMsgs.add("mail請勿空白");
				}

				String employee_cellphone = req.getParameter("employee_cellphone").trim();
				if (employee_cellphone == null || employee_cellphone.trim().length() == 0) {
					errorMsgs.add("cellphone請勿空白");
				}

				EmployeeService employeeSvc2 = new EmployeeService();
				EmployeeVO employeeVO2 = employeeSvc2.getOneEmployee(employee_no);
				
				byte[] employee_photo = null;
				InputStream in = req.getPart("employee_photo").getInputStream();
				
				if (in.available() != 0) {
					employee_photo = new byte[in.available()];
					in.read(employee_photo);
					in.close();
				} else {
			       employee_photo=employeeVO2.getEmployee_photo();
				}
				List<AuthorityVO> insertAuthoritylist = new ArrayList<AuthorityVO>();
				String[] list = req.getParameterValues("list");	
				if(list.length!=0) {
				for(String function_no : list ) {
					AuthorityVO authorityVO =new AuthorityVO();
					authorityVO.setFunction_no(function_no);
					insertAuthoritylist.add(authorityVO);
					
				}
				}
			
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmployee_no(employee_no);
				employeeVO.setEmployee_name(employee_name);
				employeeVO.setEmployee_title(employee_title);
				employeeVO.setEmployee_account(employee_account);
//				employeeVO.setEmployee_password(employee_password);
				employeeVO.setEmployee_mail(employee_mail);
				employeeVO.setEmployee_cellphone(employee_cellphone);
				employeeVO.setEmployee_photo(employee_photo);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/update_employee_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				AuthorityService authoritySvc = new AuthorityService();
				authoritySvc.deleteAuthority(employee_no);
				if(!insertAuthoritylist.isEmpty())
				authoritySvc.addAuthority1(employee_no, insertAuthoritylist);
				
				
//				for(int i =1;i<16;i++) {
//					if(i<10) {
//						if(req.getParameter("F00"+i).trim()!=null) {	
//							authoritySvc.addAuthority(req.getParameter("F0"+i).trim(), employee_no);
//						}
//					}else if(i>=10) {
//						if(req.getParameter("F0"+i).trim()!=null) {	
//							authoritySvc.addAuthority(req.getParameter("F0"+i).trim(), employee_no);
//						}
//					}
//					
//				}
				
				
				EmployeeService employeeSvc = new EmployeeService();

				employeeVO = employeeSvc.updateEmployee(employee_no, employee_name, employee_title, employee_account,
						employee_mail, employee_cellphone, employee_photo);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("employeeVO", employeeVO);
				String url = "/back-end/employee/employee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/employee/update_employee_input.jsp");
//				failureView.forward(req, res);
//			}
		}

//		if ("insertWithAutjority1".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				String employee_name = req.getParameter("employee_name");
//				String employee_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (employee_name == null || employee_name.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if (!employee_name.trim().matches(employee_nameReg)) {
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}
//
//				String employee_no = req.getParameter("employee_no").trim();
//				if (employee_no == null || employee_no.trim().length() == 0) {
//					errorMsgs.add("mail請勿空白");
//				}
//				
//				String employee_title = req.getParameter("employee_title").trim();
//				if (employee_title == null || employee_title.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}
//
//				String employee_account = req.getParameter("employee_account").trim();
//				if (employee_account == null || employee_account.trim().length() == 0) {
//					errorMsgs.add("account請勿空白");
//				}
//
//
//
//				String employee_mail = req.getParameter("employee_mail").trim();
//				if (employee_mail == null || employee_mail.trim().length() == 0) {
//					errorMsgs.add("mail請勿空白");
//				}
//
//				String employee_cellphone = req.getParameter("employee_cellphone").trim();
//				if (employee_cellphone == null || employee_cellphone.trim().length() == 0) {
//					errorMsgs.add("cellphone請勿空白");
//				}
//
//				byte[] employee_photo = null;
//				InputStream in = req.getPart("employee_photo").getInputStream();
//				if (in.available() != 0) {
//					employee_photo = new byte[in.available()];
//					in.read(employee_photo);
//					in.close();
//				} else {
//					errorMsgs.add("請放圖片");
//				}
//
//				String[] list = req.getParameterValues("list");	
//				List<AuthorityVO> insertAuthoritylist = new ArrayList<AuthorityVO>();
//				for(String function_no : list ) {
//					AuthorityVO authorityVO =new AuthorityVO();
//					authorityVO.setFunction_no(function_no);
//					insertAuthoritylist.add(authorityVO);
//					
//				}
//
//
//				EmployeeVO employeeVO = new EmployeeVO();
//
//
//
//				employeeVO.setEmployee_name(employee_name);
//				employeeVO.setEmployee_title(employee_title);
//				employeeVO.setEmployee_account(employee_account);
//				
//				employeeVO.setEmployee_mail(employee_mail);
//				employeeVO.setEmployee_cellphone(employee_cellphone);
//				employeeVO.setEmployee_photo(employee_photo);
//
//				
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("employeeVO", employeeVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				/*************************** 2.開始新增資料 ***************************************/
//				AuthorityService authoritySvc = new AuthorityService();
//				authoritySvc.deleteAuthority(employee_no);			
//				
//				EmployeeService employeeSvc = new EmployeeService();
//				
//				employeeVO = employeeSvc.addEmployeeWithAuthority(employee_name, employee_title, employee_account, employee_mail,
//						employee_cellphone, employee_photo, insertAuthoritylist);
//
//				System.out.println(employee_account);
//
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/back-end/employee/employee.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/addEmployeeWithAuthority.jsp");
//				failureView.forward(req, res);
//			}
//		}
				
		
		if ("insertWithAutjority".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String employee_name = req.getParameter("employee_name");
				String employee_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (employee_name == null || employee_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!employee_name.trim().matches(employee_nameReg)) {
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String employee_title = req.getParameter("employee_title").trim();
				if (employee_title == null || employee_title.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}

				String employee_account = req.getParameter("employee_account").trim();
				if (employee_account == null || employee_account.trim().length() == 0) {
					errorMsgs.add("account請勿空白");
				}

//				String employee_password = req.getParameter("employee_password").trim();
//				if (employee_password == null || employee_password.trim().length() == 0) {
//					errorMsgs.add("password請勿空白");
//				}

				String employee_mail = req.getParameter("employee_mail").trim();
				if (employee_mail == null || employee_mail.trim().length() == 0) {
					errorMsgs.add("mail請勿空白");
				}

				String employee_cellphone = req.getParameter("employee_cellphone").trim();
				if (employee_cellphone == null || employee_cellphone.trim().length() == 0) {
					errorMsgs.add("cellphone請勿空白");
				}

				byte[] employee_photo = null;
				InputStream in = req.getPart("employee_photo").getInputStream();
				if (in.available() != 0) {
					employee_photo = new byte[in.available()];
					in.read(employee_photo);
					in.close();
				} else {
					errorMsgs.add("請放圖片");
				}

//=====================================checkbox取多值用=start========================================
				
				String[] list = req.getParameterValues("list");	
				List<AuthorityVO> insertAuthoritylist = new ArrayList<AuthorityVO>();
				for(String function_no : list ) {
					AuthorityVO authorityVO =new AuthorityVO();
					authorityVO.setFunction_no(function_no);
					insertAuthoritylist.add(authorityVO);
					
				}
				
//=====================================checkbox取多值用=end==========================================				
				
//				List<AuthorityVO> insertAuthoritylist = null;
//				if(insertAuthority!=null) {												
//					insertAuthoritylist=Arrays.asList(insertAuthority);
//				}


				EmployeeVO employeeVO = new EmployeeVO();

				String employee_password = employeeVO.getRandomPassword(); // 產生亂數密碼

				employeeVO.setEmployee_name(employee_name);
				employeeVO.setEmployee_title(employee_title);
				employeeVO.setEmployee_account(employee_account);
				employeeVO.setEmployee_password(employee_password);
				employeeVO.setEmployee_mail(employee_mail);
				employeeVO.setEmployee_cellphone(employee_cellphone);
				employeeVO.setEmployee_photo(employee_photo);
				
//=====================================送出員工認證信=start==========================================
			
				String to = employee_mail;
				String subject = "WorkOutAnyWhere員工密碼通知";

				String messageText = "恭喜" + employee_name + "成為此平台員工 " + "\n" + " 此為後台登入密碼 :  " + employee_password
						+ "\n" + "倘若遺失，後果自行負責。" + "\n" + " (已經啟用)";

				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);

//=====================================送出員工認證信=end============================================
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/addEmployeeWithAuthority.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				
				employeeVO = employeeSvc.addEmployeeWithAuthority(employee_name, employee_title, employee_account, employee_mail,
						employee_cellphone, employee_photo, employee_password, insertAuthoritylist);

				System.out.println(employee_account);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/employee/employee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/addEmployeeWithAuthority.jsp");
				failureView.forward(req, res);
			}
		}
		

//=====================================新增員工但無權限=============================================		
//		
//		if ("insert".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				String employee_name = req.getParameter("employee_name");
//				String employee_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (employee_name == null || employee_name.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if (!employee_name.trim().matches(employee_nameReg)) {
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}
//
//				String employee_title = req.getParameter("employee_title").trim();
//				if (employee_title == null || employee_title.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}
//
//				String employee_account = req.getParameter("employee_account").trim();
//				if (employee_account == null || employee_account.trim().length() == 0) {
//					errorMsgs.add("account請勿空白");
//				}
//
//
//				String employee_mail = req.getParameter("employee_mail").trim();
//				if (employee_mail == null || employee_mail.trim().length() == 0) {
//					errorMsgs.add("mail請勿空白");
//				}
//
//				String employee_cellphone = req.getParameter("employee_cellphone").trim();
//				if (employee_cellphone == null || employee_cellphone.trim().length() == 0) {
//					errorMsgs.add("cellphone請勿空白");
//				}
//
//				byte[] employee_photo = null;
//				InputStream in = req.getPart("employee_photo").getInputStream();
//				if (in.available() != 0) {
//					employee_photo = new byte[in.available()];
//					in.read(employee_photo);
//					in.close();
//				} else {
//					errorMsgs.add("請放圖片");
//				}
//				
////				String[] deletPic = req.getParameterValues("deletPic");
////				List<String> deletPiclist = null;
////				if(deletPic!=null) {												
////					deletPiclist=Arrays.asList(deletPic);
////				}
//
//
//				EmployeeVO employeeVO = new EmployeeVO();
//
//				String employee_password = employeeVO.getRandomPassword(); // 產生亂數密碼
//
//				employeeVO.setEmployee_name(employee_name);
//				employeeVO.setEmployee_title(employee_title);
//				employeeVO.setEmployee_account(employee_account);
//				employeeVO.setEmployee_password(employee_password);
//				employeeVO.setEmployee_mail(employee_mail);
//				employeeVO.setEmployee_cellphone(employee_cellphone);
//				employeeVO.setEmployee_photo(employee_photo);
//
//				String to = employee_mail;
//				String subject = "WorkOutAnyWhere員工密碼通知";
//
//				String messageText = "恭喜" + employee_name + "成為此平台員工 " + "\n" + " 此為後台登入密碼 :  " + employee_password
//						+ "\n" + "倘若遺失，後果自行負責。" + "\n" + " (已經啟用)";
//
//				MailService mailService = new MailService();
//
//				mailService.sendMail(to, subject, messageText);
//
//				employeeVO.setEmployee_photo(employee_photo);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("employeeVO", employeeVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				/*************************** 2.開始新增資料 ***************************************/
//				EmployeeService employeeSvc = new EmployeeService();
//				employeeVO = employeeSvc.addEmployee(employee_name, employee_title, employee_account, employee_mail,
//						employee_cellphone, employee_photo, employee_password);
//
//				System.out.println(employee_account);
//
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/back-end/employee/listAllEmployee.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
//=====================================新增員工但無權限=============================================		

		
		
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String employee_no = new String(req.getParameter("employee_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				EmployeeService empSvc = new EmployeeService();
				empSvc.deleteEmployee(employee_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
