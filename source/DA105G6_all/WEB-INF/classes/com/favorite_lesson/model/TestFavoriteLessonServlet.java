package com.favorite_lesson.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestFavoriteLessonServlet")
public class TestFavoriteLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		FavoriteLessonJNDIDAO dao= new FavoriteLessonJNDIDAO();
		PrintWriter out =res.getWriter();


//		// 新增
//		FavoriteLessonVO favoriteLessonVO1 = new FavoriteLessonVO();
//		favoriteLessonVO1.setLessonNo("LE006");
//		favoriteLessonVO1.setMemberNo("M010");
//		dao.insert(favoriteLessonVO1);

//		// 刪除
//		FavoriteLessonVO favoriteLessonVO2 = new FavoriteLessonVO();
//		favoriteLessonVO2.setLessonNo("LE006");
//		favoriteLessonVO2.setMemberNo("M010");
//		dao.delete(favoriteLessonVO2);

//		// 查詢
		
//		List<FavoriteLessonVO> alist = dao.findByPrimaryKey("M001");
//		for (FavoriteLessonVO favoriteLessonVO3 : alist) {
//		out.print(favoriteLessonVO3.getMemberNo() + ",");
//		out.println(favoriteLessonVO3.getLessonNo());
//		}
//	
//		out.println("---------------------");

		// 查詢
		List<FavoriteLessonVO> list = dao.getAll();
		for (FavoriteLessonVO aEmp : list) {
			out.print(aEmp.getMemberNo() + ",");
			out.print(aEmp.getLessonNo());
			out.println();
		}
	
	}
}
