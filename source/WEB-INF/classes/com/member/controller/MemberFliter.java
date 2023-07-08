package com.member.controller;

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

import com.member.model.MemberVO;

@WebServlet("/MemberFliter")
public class MemberFliter extends HttpServlet implements Filter{
	private static final long serialVersionUID = 1L;

	private FilterConfig config;
	
	 public MemberFliter() {
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
		MemberVO memberVO =(MemberVO) session.getAttribute("memberVO");
		//取的request路徑
//		session.setAttribute("location", req.getRequestURI());
//		System.out.println(req.getRequestURI());
		
		// 【從 session 判斷此user是否登入過】
		Object allowUser = session.getAttribute("allowUser");//可以用在教練會員都可使用的頁面中
		Object memberAccount = session.getAttribute("memberAccount");
		if ( allowUser == null || memberAccount==null) { //session失效的話
			res.sendRedirect(req.getContextPath() + "/front-end/index/member_login.jsp");
		}else if(memberVO.getMember_review() == 0){ // member 驗證信箱
			res.sendRedirect(req.getContextPath() +"/back-end/mail-server/member_check.jsp");
		}else if(memberVO.getMember_review()==1 && memberVO.getMember_auth()==1){ //驗證成功 && good member 
			chain.doFilter(request, response);
		}else if(memberVO.getMember_review() == 2) { // 驗證失敗
			res.sendRedirect(req.getContextPath() +"/back-end/mail-server/member_check.jsp");
		}else {
			res.sendRedirect(req.getContextPath() + "/front-end/gym_index/index.jsp");
		}
	}
}
