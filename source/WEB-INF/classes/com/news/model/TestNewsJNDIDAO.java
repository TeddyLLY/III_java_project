package com.news.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NewsTest")
public class TestNewsJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		NewsJNDIDAO dao = new NewsJNDIDAO();

		// 新增
//		NewsVO newsVO1 = new NewsVO();
//		newsVO1.setNews_title("員工地區淨灘活動");
//		newsVO1.setNews_content("善盡企業的社會責任，不間斷地進行慈善公益與捐款活動");
//		newsVO1.setNews_time(java.sql.Date.valueOf("2020-01-30"));
//		newsVO1.setNews_time(new java.sql.Date(new GregorianCalendar(2020, 01, 21).getTime().getTime()));
//		dao.insert(newsVO1);
//		out.println("新增成功");
//
//		// 修改
//		NewsVO newsVO2 = new NewsVO();
//		newsVO2.setNews_no("N011");
//		newsVO2.setNews_title("員工淨灘活動");
//		newsVO2.setNews_content("員工淨灘活動，善盡企業的社會責任，不間斷地進行慈善公益與捐款活動");
//		newsVO2.setNews_time(java.sql.Date.valueOf("2020-01-30"));
//		dao.update(newsVO2);
//		out.println("修改成功");

		// 刪除
//		dao.delete("N011");
//		out.println("刪除成功");
		
		// 單筆查詢
//		NewsVO newsVO3 = dao.findByPrimaryKey("N010");
//		out.println(newsVO3.getNews_no());
//		out.println(newsVO3.getNews_title());
//		out.println(newsVO3.getNews_content());
//		out.println(newsVO3.getNews_time());
//
		// 多筆查詢
		List<NewsVO> list = dao.getAll();
		for (NewsVO aEmp : list) {
			out.print(aEmp.getNews_no() + ",");
			out.print(aEmp.getNews_title() + ",");
			out.print(aEmp.getNews_content() + ",");
			out.print(aEmp.getNews_time());
			out.println();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
