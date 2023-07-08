//package com.gym.model;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@WebServlet("/GymTest")
//public class TestGymJNDIDAO extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		response.setContentType("text/plain;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		GymJNDIDAO dao = new GymJNDIDAO();
//
//		// 新增
//		GymVO gymVO1 = new GymVO();
//		
//		gymVO1.setGym_name("臺北市松山運動中");
//		gymVO1.setGym_latitude("25.04849");
//		gymVO1.setGym_longitude("121.55044");
//		gymVO1.setGym_address("台北市松山區敦化北路1");
//		gymVO1.setGym_content("06:00-22:00");
//		dao.insert(gymVO1);
//		out.println("新增成功");
		
		// 修改
//		GymVO gymVO2 = new GymVO();
//		gymVO2.setGym_no("G010");
//		gymVO2.setGym_name("臺北市松山運動中");
//		gymVO2.setGym_latitude("25.04849");
//		gymVO2.setGym_longitude("121.55044");
//		gymVO2.setGym_address("台北市松山區敦化北路");
//		gymVO2.setGym_content("06:00-22:00");
//		dao.update(gymVO2);
//		out.println("修改成功");
//		
		// 刪除
//		dao.delete("G011");
//		out.println("刪除成功");
		
		// 單筆查詢
//		GymVO gymVO3 = dao.findByPrimaryKey("G010");
//		out.println(gymVO3.getGym_no() + ",");
//		out.println(gymVO3.getGym_name() + ",");
//		out.println(gymVO3.getGym_latitude() + ",");
//		out.println(gymVO3.getGym_longitude() + ",");
//		out.println(gymVO3.getGym_address() + ",");
//		out.println(gymVO3.getGym_status() + ",");
//		out.println(gymVO3.getGym_now_people() + ",");
//		out.println(gymVO3.getGym_total_people() + ",");
//		out.println(gymVO3.getGym_content());
//
//		// 多筆查詢
//		List<GymVO> list = dao.getAll();
//		for (GymVO gym : list) {
//			out.print(gym.getGym_no() + ",");
//			out.print(gym.getGym_name() + ",");
//			out.print(gym.getGym_latitude() + ",");
//			out.print(gym.getGym_longitude() + ",");
//			out.print(gym.getGym_address() + ",");
//			out.print(gym.getGym_status() + ",");
//			out.print(gym.getGym_now_people() + ",");
//			out.print(gym.getGym_total_people() + ",");
//			out.print(gym.getGym_content());
//			out.println();
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
