package com.system_news.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestSystemNewsJNDIDAO")
public class TestSystemNewsJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestSystemNewsJNDIDAO() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=utf-8 "); 
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		
		SystemNewsJNDIDAO dao = new SystemNewsJNDIDAO();
		
		Timestamp time= new Timestamp(System.currentTimeMillis());
		//新增
//		SystemNewsVO vo1 = new SystemNewsVO();
//		vo1.setMember_no("M001");
//		vo1.setCoach_no("C001");
//		vo1.setSystem_content("春節快樂");
//		vo1.setSystem_time(time);
//		dao.insert(vo1);
		//刪除
//		dao.delete("S001");
		//修改
//		SystemNewsVO vo2 = new SystemNewsVO();
//		vo2.setMember_no("M001");
//		vo2.setCoach_no("C001");
//		vo2.setSystem_content("春節期間小心駕駛");
//		vo2.setSystem_time(time);
//		vo2.setSystem_news_no("S001");
//		dao.update(vo2);
//		//查詢 DESC 
//		SystemNewsVO vo3 = new SystemNewsVO();
//		vo3=dao.findByPrimaryKey("S007");
//		out.println(vo3.getSystem_news_no());
//		out.println(vo3.getMember_no());
//		out.println(vo3.getCoach_no());
//		out.println(vo3.getSystem_content());
//		out.println(vo3.getSystem_time());
//		out.println("-------------------------------------");
//		//查詢 DESC 
//		List<SystemNewsVO> list =dao.getAll();
//		for(SystemNewsVO vo4 :list){
//			out.println(vo4.getSystem_news_no());
//			out.println(vo4.getMember_no());
//			out.println(vo4.getCoach_no());
//			out.println(vo4.getSystem_content());
//			out.println(vo4.getSystem_time());
//			out.println("-->");
//		}
//		
//		//查詢COACH DESC 
//				List<SystemNewsVO> list1 =dao.findByOneCoach("C010");
//				for(SystemNewsVO vo5 :list1){
//					out.println(vo5.getSystem_news_no());
//					out.println(vo5.getMember_no());
//					out.println(vo5.getCoach_no());
//					out.println(vo5.getSystem_content());
//					out.println(vo5.getSystem_time());
//					out.println("---------------------");
//				}
				//查詢MEMBER DESC
					List<SystemNewsVO> list2 =dao.findByOneMember("M001");
					for(SystemNewsVO vo6 :list2){
						out.println(vo6.getSystem_news_no());
						out.println(vo6.getMember_no());
						out.println(vo6.getCoach_no());
						out.println(vo6.getSystem_content());
						out.println(vo6.getSystem_time());
						out.println("-->");
					}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
