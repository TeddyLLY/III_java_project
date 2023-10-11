package com.report_coach.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/TestReportCoachJNDIDAO")
public class TestReportCoachJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestReportCoachJNDIDAO() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ReportCoachJNDIDAO dao = new ReportCoachJNDIDAO();
	
		
		Timestamp time= new Timestamp(System.currentTimeMillis());
		//新增
//		ReportCoachVO vo1= new ReportCoachVO();
//		vo1.setCoach_no("C003");
//		vo1.setMember_no("M001");
//		vo1.setReport_coach_content("JDBCDAO");
//		vo1.setReport_coach_time(time);
//		vo1.setReport_coach_status(0);
//		dao.insert(vo1);
//		//刪除
//		dao.delete("RC011");
		//修改
//		ReportCoachVO vo2= new ReportCoachVO();
//		vo2.setCoach_no("C006");
//		vo2.setMember_no("M009");
//		vo2.setReport_coach_content("教練摸魚");
//		vo2.setReport_coach_time(time);
//		vo2.setReport_coach_status(2);
//		vo2.setReport_coach_no("RC001");
//		dao.update(vo2);
//		
//		//查詢
		ReportCoachVO vo3 = new ReportCoachVO();
		vo3 = dao.findByPrimaryKey("RC010");
		out.println(vo3.getReport_coach_no());
		out.println(vo3.getCoach_no());
		out.println(vo3.getMember_no());
		out.println(vo3.getReport_coach_content());
		out.println(vo3.getReport_coach_time());
		out.println(vo3.getReport_coach_status());
		out.println("---------------------------------");
		//查詢
		List<ReportCoachVO> list = dao.getAll();
		for(ReportCoachVO vo4 : list) {
			out.println(vo4.getReport_coach_no());
			out.println(vo4.getCoach_no());
			out.println(vo4.getMember_no());
			out.println(vo4.getReport_coach_content());
			out.println(vo4.getReport_coach_time());
			out.println(vo4.getReport_coach_status());
		out.println("-->");
		}
		
		
		List<ReportCoachVO> list1 = dao.getAllSameStatus(1);
		
		for(ReportCoachVO vo5 : list1) {
		out.println(vo5.getReport_coach_no());
		out.println(vo5.getCoach_no());
		out.println(vo5.getMember_no());
		out.println(vo5.getReport_coach_content());
		out.println(vo5.getReport_coach_time());
		out.println(vo5.getReport_coach_status());
		out.println("---------------------------------");
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
