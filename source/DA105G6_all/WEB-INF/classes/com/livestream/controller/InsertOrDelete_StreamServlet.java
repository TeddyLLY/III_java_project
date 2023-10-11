package com.livestream.controller;

import java.io.*;
import java.sql.Timestamp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.text.SimpleDateFormat;

import com.livestream.model.HistoryVideoService;
import com.livestream.model.HistoryVideoVO;


@WebServlet("/InsertOrDelete_StreamServlet")
public class InsertOrDelete_StreamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自直播主RTCPeerConnection.jsp-[結束錄影]的請求-1/2

			 System.out.println("insert...........................");
				
			 HistoryVideoService historyVideoSvc = new HistoryVideoService();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String strDate = sdf.format(new java.util.Date());
				java.sql.Timestamp history_video_time =java.sql.Timestamp.valueOf(strDate);
				
				HistoryVideoVO historyVideoVO = historyVideoSvc.insert(history_video_time,"111",null,"L001","C014");
				HttpSession session = req.getSession();
				session.setAttribute("historyVideoVO", historyVideoVO);
			
			 System.out.println("insert完成.......................");

        }
		

		if ("delete".equals(action)) { // 來直播管理listAllStream.jsp-[刪除]的請求

			 System.out.println("delete...........................");
			
			    HistoryVideoService historyVideoSvc=new HistoryVideoService();
				String history_video_no=req.getParameter("history_video_no").toUpperCase();
				historyVideoSvc.delete(history_video_no);
			
			 System.out.println("delete完成.......................");	
				
			    String url = "/listAllStream.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllStream.jsp.jsp
				successView.forward(req, res);
		}
		
	}
}
