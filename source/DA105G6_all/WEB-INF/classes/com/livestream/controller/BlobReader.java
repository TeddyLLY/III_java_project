
package com.livestream.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.livestream.model.HistoryVideoService;
import com.livestream.model.HistoryVideoVO;

@WebServlet("/BlobReader")  // 來直播管理listAllStream.jsp的請求
public class BlobReader extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setBufferSize(50*1024*1024);  //KeepAlive
		res.setContentType("video/webm");
		ServletOutputStream out = res.getOutputStream();

		try {
			String history_video_no = req.getParameter("history_video_no");
			HistoryVideoService historyVideoService = new HistoryVideoService();
			HistoryVideoVO historyVideoVO = historyVideoService.findByPrimaryKey(history_video_no);
			byte[] buf = historyVideoVO.getHistory_video_content();
			out.write(buf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}