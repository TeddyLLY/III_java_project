package com.employee.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeVO;
import com.member.model.MemberVO;

@WebServlet("/EmployeeFilter")
public class EmployeeFilter extends HttpServlet implements Filter{
	private static final long serialVersionUID = 1L;

	private FilterConfig config;
	
	 public EmployeeFilter() {
        super();
    }

	@Override
	public void init(FilterConfig config) throws ServletException{
		this.config = config;
	        return;
	    }
	
	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response , FilterChain chain)throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 【取得 session】
		HttpSession session = req.getSession();
		EmployeeVO employeeVO =(EmployeeVO) session.getAttribute("EmployeeVO");
		//取的request路徑
		session.setAttribute("location", req.getRequestURI());
		System.out.println(req.getRequestURI());
		
		// 【從 session 判斷此user是否登入過】
//		Object allowUser = session.getAttribute("allowUser");//可以用在教練會員都可使用的頁面中
//		Object memberAccount = session.getAttribute("memberAccount");
		if (employeeVO==null) { //session失效的話
			res.sendRedirect(req.getContextPath() + "/back-end/login/loginEmployee.jsp");
			return;
		}else {
			chain.doFilter(request, response);
		}
	}
}