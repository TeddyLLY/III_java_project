package com.lesson_order.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LessonOrderServlet")
public class TestLessonOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		LessonOrderJNDIDAO dao = new LessonOrderJNDIDAO();
		PrintWriter out =res.getWriter();
		
		// 新增
//		LessonOrderVO lessonOrderVO1 = new LessonOrderVO();
//		lessonOrderVO1.setMemberNo("M010");
//		lessonOrderVO1.setLessonNo("LE010");
//		lessonOrderVO1.setLessonPrice(50);
//		lessonOrderVO1.setDateAcquisition(java.sql.Timestamp.valueOf("2020-02-01 17:27:46"));
//		lessonOrderVO1.setLessonStatus(1);
//		dao.insert(lessonOrderVO1);

		// 修改
//		LessonOrderVO lessonOrderVO2 = new LessonOrderVO();
//		lessonOrderVO2.setMemberNo("M009");
//		lessonOrderVO2.setLessonNo("LE008");
//		lessonOrderVO2.setLessonPrice(1000);
//		lessonOrderVO2.setDateAcquisition(java.sql.Timestamp.valueOf("2020-02-01 17:27:46"));
//		lessonOrderVO2.setLessonStatus(0);
//		lessonOrderVO2.setLessonOrderNo("LO011");		
//		dao.update(lessonOrderVO2);

		// 只刪除訂單 沒明細時才可執行(FK問題)
//		dao.delete("LO011");

		//連帶刪除訂單明細
//		dao.delete2("LO008");
		// 單一訂單		
		LessonOrderVO lessonOrderVO3 = dao.findByPrimaryKey("LO001"); //
		out.print(lessonOrderVO3.getLessonOrderNo() + ",");
		out.print(lessonOrderVO3.getMemberNo() + ",");
		out.print(lessonOrderVO3.getLessonNo() + ",");
		out.print(lessonOrderVO3.getLessonPrice() + ",");
		out.print(lessonOrderVO3.getDateAcquisition() + ",");
		out.println(lessonOrderVO3.getLessonStatus());
		out.println("---------------------");

		// 所有訂單
		List<LessonOrderVO> list = dao.getAll();
		for (LessonOrderVO lessonOrderVO4 : list) {
			out.print(lessonOrderVO4.getLessonOrderNo() + ",");
			out.print(lessonOrderVO4.getMemberNo() + ",");
			out.print(lessonOrderVO4.getLessonNo() + ",");
			out.print(lessonOrderVO4.getLessonPrice() + ",");
			out.print(lessonOrderVO4.getDateAcquisition() + ",");
			out.println(lessonOrderVO4.getLessonStatus());
		}
			out.println("---------------------");

			//單一會員所有訂單
		List<LessonOrderVO> olist = dao.getOneAll("M009");
		for (LessonOrderVO lessonOrderVO5 : olist) {
			out.print(lessonOrderVO5.getLessonOrderNo() + ",");
			out.print(lessonOrderVO5.getMemberNo() + ",");
			out.print(lessonOrderVO5.getLessonNo() + ",");
			out.print(lessonOrderVO5.getLessonPrice() + ",");
			out.print(lessonOrderVO5.getDateAcquisition() + ",");
			out.println(lessonOrderVO5.getLessonStatus());
		}
			out.println("---------------------");		
	}


}
