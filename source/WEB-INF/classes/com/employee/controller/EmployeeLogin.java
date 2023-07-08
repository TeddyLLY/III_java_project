package com.employee.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeVO;
import com.employee.model.EmployeeJNDIDAO;

@WebServlet("/LoginServlet")
public class EmployeeLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
	    res.setContentType("text/html; charset=UTF-8");
		
		String employee_account = req.getParameter("employee_account");
		String employee_password = req.getParameter("employee_password");

		EmployeeJNDIDAO EmployeeJNDIDAO = new EmployeeJNDIDAO(); 
		EmployeeVO EmployeeVO = EmployeeJNDIDAO.employeeLogin(employee_account, employee_password);

		if (EmployeeVO != null) {
		
			HttpSession session = req.getSession();
			session.setAttribute("EmployeeVO", EmployeeVO);
			res.sendRedirect(req.getContextPath()+"/back-end/gym_index/index.jsp");
		
		} else {
			req.setAttribute("error", "帳密錯誤，請重新輸入");
			req.getRequestDispatcher("/back-end/login/loginEmployee.jsp").forward(req, res);

		}
	}

}