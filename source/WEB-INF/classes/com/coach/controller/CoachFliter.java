package com.coach.controller;

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

import com.coach.model.CoachVO;

@WebServlet("/CoachFliter")
public class CoachFliter  implements Filter{
	
	private FilterConfig config;
	
	 public CoachFliter() {
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
		CoachVO coachVO= (CoachVO)session.getAttribute("coachVO");
		//取得req.url
//		session.setAttribute("location", req.getRequestURI()); //取得來源網頁
//		System.out.println(req.getRequestURI());
		
		// 【從 session 判斷此user是否登入過】
		Object allowUser = session.getAttribute("allowUser"); //可以用在教練會員都可使用的葉面中
		Object coachAccount = session.getAttribute("coachAccount");
		if ( allowUser == null || coachAccount==null) {// 沒有登入session
			res.sendRedirect(req.getContextPath() + "/front-end/gym_index/index.jsp");
		}else if(coachVO.getCoach_review() == 1 && coachVO.getCoach_auth() == 2 ) { //教練註冊完    , 轉交mail
			res.sendRedirect(req.getContextPath() + "/back-end/mail-server/coach_check.jsp");
		}else if(coachVO.getCoach_review() == 0 && coachVO.getCoach_auth() == 2 ) { //教練mail驗證碼ok  上傳data
			res.sendRedirect(req.getContextPath() + "/front-end/coach_no_lock/coach_review.jsp");
		}else if(coachVO.getCoach_review() == 2 && coachVO.getCoach_auth() ==2) {  //等待後台確認審核
			res.sendRedirect(req.getContextPath() + "/front-end/coach_no_lock/coach_review_wait.jsp");
		}else if(coachVO.getCoach_auth() == 0 ){   //教練被停權回到重新審核
			res.sendRedirect(req.getContextPath() + "/front-end/coach_no_lock/coach_review_again.jsp");
		}else if(coachVO.getCoach_auth()  == 1 || coachVO.getCoach_auth() ==2) {  
			chain.doFilter(request, response); //權限ok的話可以使用功能
		}else if(coachVO.getCoach_review()  == 5 ) {   //教練審核失敗回到重新審核
			res.sendRedirect(req.getContextPath() + "/front-end/coach_no_lock/coach_review_again.jsp");
		}else {
			res.sendRedirect(req.getContextPath() + "/front-end/gym_index/index.jsp");
		}
	}
}
