package com.lesson_detail.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestLessonDetailServlet")
public class TestLessonDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		LessonDetailJNDIDAO dao = new LessonDetailJNDIDAO();
		PrintWriter out =res.getWriter();
		
		// 新增
//		LessonDetailVO lessonDetailVO1 = new LessonDetailVO();
//		lessonDetailVO1.setLessonNo("LE010");
//		lessonDetailVO1.setLessonDate(java.sql.Date.valueOf("2020-02-29"));
//		dao.insert(lessonDetailVO1);
	
		//修改
		
//		LessonDetailVO lessonDetailVOu = new LessonDetailVO();
//		lessonDetailVOu.setLessonNo("LE010");
//		lessonDetailVOu.setLessonDate(java.sql.Date.valueOf("2020-03-01"));
//		lessonDetailVOu.setLessonDetailNo("LCD009");
//		dao.update(lessonDetailVOu);			
		
		//刪除單一課程明細
//		dao.deleteByLesson("LCD022");
		// 刪除一個課程底下全部明細
//		dao.delete("LE010"); //依課程編號刪除
		
		//查詢單一課程明細
		out.println("單一課程明細");
		out.println("<br>");
		LessonDetailVO ld = dao.findByPrimaryKey("LCD001");
		out.print(ld.getLessonDetailNo() + ",");
		out.print(ld.getLessonNo() + ",");
		out.println(ld.getLessonDate());
		out.println("<br>");
		out.println("---------------------");
		out.println("<br>");

		

//		 查詢一個課程下的所有日期明細
		out.println("單一課程所有明細");
		List<LessonDetailVO> alist = dao.findByLesson("LE010");
		for (LessonDetailVO lessonDetailVO2 : alist) {
		out.print(lessonDetailVO2.getLessonDetailNo() + ",");
		out.print(lessonDetailVO2.getLessonNo() + ",");
		out.println(lessonDetailVO2.getLessonDate());
		out.println("<br>");
		}	
		out.println("<br>");
		out.println("---------------------");
		out.println("<br>");

		// 查詢
		out.println("所有明細");
		out.println("<br>");
		List<LessonDetailVO> list = dao.getAll();
		for (LessonDetailVO lessonDetailVO3 : list) {
			out.print(lessonDetailVO3.getLessonDetailNo() + ",");
			out.print(lessonDetailVO3.getLessonNo() + ",");
			out.println(lessonDetailVO3.getLessonDate());
			out.println("<br>");
		}
	}

}
