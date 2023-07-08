//package com.qa.model;
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
//@WebServlet("/Qa")
//public class TestQaJNDIDAO extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/plain;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		QaJNDIDAO dao = new QaJNDIDAO();
//
//		// 新增
//		QaVO qaVO1 = new QaVO();	
//		qaVO1.setQa_content("問:有氧課還有其它注意事項嗎？");
//		dao.insert(qaVO1);
//		out.println("新增成功");
//		
//		// 修改
//		QaVO qaVO2 = new QaVO();
//		qaVO2.setQa_no("Q011");
//		qaVO2.setQa_content("問:有氧課還有其它注意事項嗎？, 答:上課中，請勿使用手機及大聲交談，避免影響他人。");
//		dao.update(qaVO2);
//		out.println("修改成功");
//
//		// 刪除
////		dao.delete("Q011");
////		dao.delete("Q012");
////		out.println("刪除成功");
//
//		// 單筆查詢
//		QaVO qaVO3 = dao.findByPrimaryKey("Q010");
//		System.out.print(qaVO3.getQa_no() + ",");
//		System.out.print(qaVO3.getQa_content());
//		System.out.println();
//
//		// 多筆查詢	
//		List<QaVO> list = dao.getAll();
//		
//		for (QaVO aQa : list) {
//			out.print(aQa.getQa_no() + ",");
//			out.print(aQa.getQa_content());
//			out.println();
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
