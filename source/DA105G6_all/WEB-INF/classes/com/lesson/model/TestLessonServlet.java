package com.lesson.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesson_detail.model.LessonDetailJNDIDAO;

@WebServlet("/TestLessonServlet")
public class TestLessonServlet extends HttpServlet {
	
	private static byte[] getPictureByteArray(String url) { //傳圖片用
		File file = new File(url);
		byte[] img = null;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			img= new byte[in.available()];
			in.read(img);
			in.close();
		} catch (IOException e ) {
			e.printStackTrace();
		}
		return img;
	}	
	
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
		LessonJNDIDAO dao = new LessonJNDIDAO();
		PrintWriter out =res.getWriter();

		// 新增
//		LessonVO lessonVO1 = new LessonVO();
//		lessonVO1.setCoachNo("C010");
//		lessonVO1.setLessonName("高階魔鬼訓練");
//		lessonVO1.setLessonPoint("簡介:高階的魔鬼訓練");
//		lessonVO1.setLessonPrice(9999);
//		lessonVO1.setLessonContent("***我是課程內容****高階魔鬼訓練");
//		lessonVO1.setLessonStartDate(java.sql.Date.valueOf("2020-01-02"));
//		lessonVO1.setLessonEndDate(java.sql.Date.valueOf("2020-01-30"));
//		lessonVO1.setLessonRegistration(10); //報名人數之後不可讓教練輸入
//		lessonVO1.setLessonMaximumPeople(15);
//		lessonVO1.setLessonPicture(new byte[15]);
//		lessonVO1.setLessonLocation("大安森林公園");
//		dao.insert(lessonVO1);

		// 修改
//		LessonVO lessonVO2 = new LessonVO();
//		lessonVO2.setLessonName("肺活量訓練");
//		lessonVO2.setLessonPoint("***我是課程簡介2***");
//		lessonVO2.setLessonPrice(500);
//		lessonVO2.setLessonContent("***我是課程內容2***");
//		lessonVO2.setLessonEndDate(java.sql.Date.valueOf("2020-02-01"));
//		lessonVO2.setLessonRegistration(15); //報名人數之後不可讓教練輸入
//		lessonVO2.setLessonMaximumPeople(15);
//		lessonVO2.setLessonPicture(new byte[15]);
//		lessonVO2.setLessonLocation("河濱公園");
//		lessonVO2.setLessonNo("LE034");
//		dao.update(lessonVO2);

		// 只刪除課程
//		dao.delete("LE010");

		// 刪除課程+詳情+收藏(注意:已有會員購買並產生課程訂單，則課程無法刪除)
		
//		dao.delete2("LE009");		
		
		// 查詢課程詳情
		LessonVO lessonVO3 = dao.findByPrimaryKey("LE001"); //
		out.print(lessonVO3.getLessonNo() + ",");
		out.print(lessonVO3.getCoachNo() + ",");
		out.print(lessonVO3.getLessonName() + ",");
		out.print(lessonVO3.getLessonPoint() + ",");
		out.print(lessonVO3.getLessonContent() + ",");
		out.print(lessonVO3.getLessonPrice() + ",");
		out.print(lessonVO3.getLessonStartDate() + ",");
		out.print(lessonVO3.getLessonEndDate() + ",");
		out.print(lessonVO3.getLessonRegistration() + ",");
		out.print(lessonVO3.getLessonMaximumPeople() + ",");
		out.print(lessonVO3.getLessonPicture() + ",");
		out.println(lessonVO3.getLessonLocation());
		out.println("<br>");
		out.println("---------------------");
		out.println("<br>");

		// 所有課程詳情
		List<LessonVO> mlist = dao.getmAll();
		for (LessonVO LE : mlist) {
			out.print(LE.getLessonNo() + ",");
			out.print(LE.getCoachNo() + ",");
			out.print(LE.getLessonName() + ",");
			out.print(LE.getLessonPoint() + ",");
			out.print(LE.getLessonContent() + ",");
			out.print(LE.getLessonPrice() + ",");
			out.print(LE.getLessonStartDate() + ",");
			out.print(LE.getLessonEndDate() + ",");
			out.print(LE.getLessonRegistration() + ",");
			out.print(LE.getLessonMaximumPeople() + ",");
			out.print(LE.getLessonPicture() + ",");
			out.println(LE.getLessonLocation());
			out.println("<br>");

		}
			out.println("---------------------");
			out.println("<br>");
			
			//課程總覽
		List<LessonVO> list = dao.getAll();
		for (LessonVO LE : list) {
			out.print(LE.getCoachNo() + ",");
			out.print(LE.getLessonName() + ",");
			out.print(LE.getLessonPrice() + ",");
			out.print(LE.getLessonEndDate() + ",");
			out.print(LE.getLessonRegistration() + ",");
			out.print(LE.getLessonMaximumPeople() + ",");
			out.print(LE.getLessonPicture() + ",");
			out.println(LE.getLessonLocation());
			out.println("<br>");
		}
			out.println("---------------------");	
			out.println("<br>");
			

			ArrayList<LessonVO> picArray = new ArrayList<LessonVO>();
			LessonVO lessonVO = new LessonVO();
			lessonVO.setLessonNo("LE001");
			lessonVO.setLessonPicture(getPictureByteArray(req.getContextPath()+"/lesson_pkg/lesosn_pic/0.jpg"));			
			picArray.add(lessonVO);	
			lessonVO.setLessonNo("LE002");
			lessonVO.setLessonPicture(getPictureByteArray(req.getContextPath()+"/lesson_pkg/lesosn_pic/1.jpg"));			
			picArray.add(lessonVO);	
			lessonVO.setLessonNo("LE003");
			lessonVO.setLessonPicture(getPictureByteArray(req.getContextPath()+"/lesson_pkg/lesosn_pic/2.jpg"));			
			picArray.add(lessonVO);	
			lessonVO.setLessonNo("LE004");
			lessonVO.setLessonPicture(getPictureByteArray(req.getContextPath()+"/lesson_pkg/lesosn_pic/3.jpg"));			
			picArray.add(lessonVO);	
			lessonVO.setLessonNo("LE005");
			lessonVO.setLessonPicture(getPictureByteArray(req.getContextPath()+"/lesson_pkg/lesosn_pic/4.jfif"));			
			picArray.add(lessonVO);	
			lessonVO.setLessonNo("LE006");
			lessonVO.setLessonPicture(getPictureByteArray(req.getContextPath()+"/lesson_pkg/lesosn_pic/5.jfif"));			
			picArray.add(lessonVO);	
			lessonVO.setLessonNo("LE007");
			lessonVO.setLessonPicture(getPictureByteArray(req.getContextPath()+"/lesson_pkg/lesosn_pic/6.jpg"));			
			picArray.add(lessonVO);	
			lessonVO.setLessonNo("LE008");
			lessonVO.setLessonPicture(getPictureByteArray(req.getContextPath()+"/lesson_pkg/lesosn_pic/7.jfif"));			
			picArray.add(lessonVO);	
			lessonVO.setLessonNo("LE009");
			lessonVO.setLessonPicture(getPictureByteArray(req.getContextPath()+"/lesson_pkg/lesosn_pic/8.jpg"));			
			picArray.add(lessonVO);	
			lessonVO.setLessonNo("LE010");
			lessonVO.setLessonPicture(getPictureByteArray(req.getContextPath()+"/lesson_pkg/lesosn_pic/9.jpg"));			
			picArray.add(lessonVO);				
			

			for(LessonVO uplessonPIC :picArray)
				dao.updateLessonPic(picArray);
			

			
			
			
	}

}
