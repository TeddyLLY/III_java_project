package com.system_news.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system_news.model.SystemNewsService;
import com.system_news.model.SystemNewsVO;

@WebServlet("/SysterNewsServlet")
public class SystemNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SystemNewsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
//////////////////////////////////////////////////////////////////////////////////////////////////////

		if("deleteOneSystemNews".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String  system_news_no = new String (req.getParameter("system_news_no"));
				if(system_news_no==null || system_news_no.trim().length()==0 ) {
					errorMsgs.add("錯誤檢舉教練編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/front-end/member/member_system_news.jsp");
					failView.forward(req, res);
					return;
				}
				SystemNewsService systemNewsSvc = new SystemNewsService();
				systemNewsSvc.deleteOneSystemNews(system_news_no);
				
				String coach_news = req.getParameter("coach_news");
				String member_news = req.getParameter("member_news");
				
				if(member_news != null) {
					String url = "/front-end/member/member_system_news.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}else if(coach_news !=null) {
					String url = "/front-end/coach/coach_system_news.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				
			} catch (Exception e) {
				errorMsgs.add("刪除失敗"+e.getMessage());// TODO: handle exception
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/member_system_news.jsp");
				failureView.forward(req, res);
			}
		}
		

////////////////////////////////////////////////////////////////////////////////////////////////////

	}//do post end

}//class end
