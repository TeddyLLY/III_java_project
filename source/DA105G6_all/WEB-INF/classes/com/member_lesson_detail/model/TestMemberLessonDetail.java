package com.member_lesson_detail.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesson_detail.model.LessonDetailJNDIDAO;

@WebServlet("/TestMemberLessonDetail")
public class TestMemberLessonDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		MemberLessonDetailJNDIDAO dao = new MemberLessonDetailJNDIDAO();
		PrintWriter out =res.getWriter();
		
		// 新增
//		MemberLessonDetailVO memberLessonDetailVO1 = new MemberLessonDetailVO();
//		memberLessonDetailVO1.setLessonOrderNo("LO010");
//		memberLessonDetailVO1.setStudentStatus(0);
//		memberLessonDetailVO1.setLessonDate(java.sql.Date.valueOf("2020-02-28"));
//
//		dao.insert(memberLessonDetailVO1);

		// 修改
//		MemberLessonDetailVO memberLessonDetailVO2 = new MemberLessonDetailVO();
//		memberLessonDetailVO2.setLessonOrderNo("LO009");
//		memberLessonDetailVO2.setStudentStatus(1);
//		memberLessonDetailVO2.setLessonDate(java.sql.Date.valueOf("2020-02-27"));
//		memberLessonDetailVO2.setMemberLessonDetailNo("LMD011");
//		
//		dao.update(memberLessonDetailVO2);

		//刪除某訂單詳情
//		dao.delete("LMD011");
		// 刪除某訂單下的所有訂單詳情
//		dao.delete2("LO009");


		// 單一訂單
		out.println("單一訂單");
		out.println("<br>");
		MemberLessonDetailVO memberLessonDetailVO3 = dao.findByPrimaryKey("LMD001"); //
		out.print(memberLessonDetailVO3.getMemberLessonDetailNo() + ",");
		out.print(memberLessonDetailVO3.getLessonOrderNo() + ",");
		out.print(memberLessonDetailVO3.getStudentStatus() + ",");
		out.println(memberLessonDetailVO3.getLessonDate() );
		out.println("<br>");
		out.println("---------------------");
		out.println("<br>");


		// 所有訂單
		out.println("所有訂單");
		out.println("<br>");
		List<MemberLessonDetailVO> list = dao.getAll();
		for (MemberLessonDetailVO memberLessonDetailVO4 : list) {
			out.print(memberLessonDetailVO4.getMemberLessonDetailNo() + ",");
			out.print(memberLessonDetailVO4.getLessonOrderNo() + ",");
			out.print(memberLessonDetailVO4.getStudentStatus() + ",");
			out.println(memberLessonDetailVO4.getLessonDate() );
			out.println("<br>");

		}
			out.println("---------------------");
			out.println("<br>");


			//單一訂單所有訂單明細
			out.println("單一課程訂單的所有明細");
			out.println("<br>");
		List<MemberLessonDetailVO> lmdlist = dao.getOneAll("LO009");
		for (MemberLessonDetailVO memberLessonDetailVO5 : lmdlist) {
			out.print(memberLessonDetailVO5.getMemberLessonDetailNo() + ",");
			out.print(memberLessonDetailVO5.getLessonOrderNo() + ",");
			out.print(memberLessonDetailVO5.getStudentStatus() + ",");
			out.println(memberLessonDetailVO5.getLessonDate() );
			out.println("<br>");

		}
			out.println("---------------------");	
			out.println("<br>");

		
	}

}
