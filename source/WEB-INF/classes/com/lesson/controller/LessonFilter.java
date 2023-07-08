package com.lesson.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LessonFilter")
public class LessonFilter implements Filter {
	
	private FilterConfig config;
	
	 public LessonFilter() {
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

		HttpSession session = req.getSession();

		
		// 【從 session 判斷此user是否登入過】
		Object coachAccount = session.getAttribute("coachAccount");
		if (coachAccount!=null) {// 沒有登入session
			res.sendRedirect(req.getContextPath() + "/front-end/lesson/Coach_lesson_page.jsp");
		}else {
			chain.doFilter(request, response); 
		}
	}

}
