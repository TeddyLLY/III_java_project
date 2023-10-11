package com.top_up_record.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestTopUpRecordJNDIDAO")
public class TestTopUpRecordJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestTopUpRecordJNDIDAO() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=utf8"); 
		
		PrintWriter out = response.getWriter();
		TopUpRecordJNDIDAO dao = new TopUpRecordJNDIDAO();
		Timestamp time= new Timestamp(System.currentTimeMillis());
		
		//新增
//		TopUpRecordVO vo1 = new TopUpRecordVO();
//		vo1.setMember_no("M001");
//		vo1.setRecord_time(time);
//		vo1.setMoney_record(6600);
//		dao.insert(vo1);
		//刪除
//		dao.delete("T002");
		//修改
//		TopUpRecordVO vo2 = new TopUpRecordVO();
//		vo2.setMember_no("M001");
//		vo2.setRecord_time(time);
//		vo2.setMoney_record(548700);
//		vo2.setTop_up_record_no("T001");
//		dao.update(vo2);
//		//查詢
//		TopUpRecordVO vo3 = new TopUpRecordVO();
//		vo3 = dao.findByPrimaryKey("T008");
//		out.println(vo3.getTop_up_record_no());
//		out.println(vo3.getMember_no());
//		out.println(vo3.getRecord_time());
//		out.println(vo3.getMoney_record());
//		out.println("----------------------------------------");
////		//查詢
//		List<TopUpRecordVO> list = dao.getAll();
//		for(TopUpRecordVO vo4 :list) {
//			out.println(vo4.getTop_up_record_no());
//			out.println(vo4.getMember_no());
//			out.println(vo4.getRecord_time());
//			out.println(vo4.getMoney_record());
//			out.println("-->");
//		}
		
		List<TopUpRecordVO> list1 = dao.findOneMemberRecord("M001");
		
		for(TopUpRecordVO vo6 :list1) {
		out.println(vo6.getTop_up_record_no());
		out.println(vo6.getMember_no());
		out.println(vo6.getRecord_time());
		out.println(vo6.getMoney_record());
		out.println("----------------------------------------");
		}
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
