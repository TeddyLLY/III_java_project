//package com.gym.controller;
//
//
//import java.io.*;
//import java.util.*;
//
//import javax.servlet.*;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.*;
//
//import com.gym.model.GymService;
//import com.gym.model.GymVO;
//
//public class DBGifReader extends HttpServlet {
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		res.setContentType("image/gif");
//		ServletOutputStream out = res.getOutputStream();	
//	
//		try {
//			
//			String gym_no = req.getParameter("gym_no");
//			GymService gymSvc = new GymService();
//			GymVO gymVO = gymSvc.getOneGym(gym_no);
//			out.write(gymVO.getGym_photo());
//		} catch (Exception e) {
//			InputStream in = getServletContext().getResourceAsStream("/back-end/gym/images/none2.jpg");
//			byte[] gym_photo = new byte[in.available()];
//			in.read(gym_photo);
//			out.write(gym_photo);
//			in.close();
//		}
//		
//	}
//
//}
