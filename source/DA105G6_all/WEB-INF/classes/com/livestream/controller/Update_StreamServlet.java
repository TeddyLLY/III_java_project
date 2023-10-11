package com.livestream.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.livestream.model.HistoryVideoService;
import com.livestream.model.HistoryVideoVO;

import javax.servlet.annotation.WebServlet;


@WebServlet("/Update_StreamServlet")  // 來自直播主RTCPeerConnection.jsp-[儲存錄影]的請求-2/2
public class Update_StreamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("download影片，並且upload上傳到資料庫...............");
		
			req.setCharacterEncoding("UTF-8");
			res.setContentType("Content-Type; video/webm");
			
			byte[] buffer = new byte[1024 * 1024];
			InputStream in = req.getInputStream();  
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1){
			    out.write(buffer, 0, bytesRead);
			}
			out.close();
			in.close();
			byte[] blob = out.toByteArray();
			
			HttpSession session = req.getSession();
			HistoryVideoVO historyVideoSvcVO = (HistoryVideoVO)session.getAttribute("historyVideoVO");
			historyVideoSvcVO.setHistory_video_content(blob);
			
			HistoryVideoService HistoryVideoSvc = new HistoryVideoService();
			HistoryVideoSvc.update(historyVideoSvcVO);
			
			session.removeAttribute("historyVideoVO");
			
		System.out.println("資料庫Update完成....................................");	
		
	}

}
