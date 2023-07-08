package com.coach.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestCoachJNDI")
public class TestCoachJNDI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public TestCoachJNDI() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/plain; charset=UTF-8");
		  PrintWriter out = response.getWriter();
		  
		  CoachJNDIDAO dao = new CoachJNDIDAO();
			
			
//		//	 新增
//				CoachVO vo1 =new CoachVO();
//				vo1.setCoach_name("linux");
//				vo1.setCoach_sex("男");
//				vo1.setCoach_cellphone("0978312546");
//				vo1.setCoach_account("linux468@gmail.com");
//				vo1.setCoach_password("ckok101");
//				vo1.setCoach_address("彰化花壇");
//				vo1.setCoach_photo(null);
//				vo1.setCoach_total_evaluation(250);//漢堡刷起來
//				vo1.setCoach_total_people_evaluation(8888);
//				vo1.setCoach_review(4);
//				vo1.setCoach_auth(1);
//				vo1.setCoach_average_evaluation(4.1);
//				vo1.setCoach_license(null);
//				vo1.setCoach_income(1000);
//				vo1.setCoach_introduction("i am linux hello~");
//				vo1.setCoach_bank_account("999-999-999999");
//				dao.insert(vo1);
//				
//				out.println("新增完成");
			 //修改
//				CoachVO vo2 =new CoachVO();
//				vo2.setCoach_sex("d");
//				vo2.setCoach_cellphone("0227400920");
//				vo2.setCoach_password("ichangethepsw");
//				vo2.setCoach_address("u.s.");
//				vo2.setCoach_photo(null);
//				vo2.setCoach_total_evaluation(250);//漢堡刷起來
//				vo2.setCoach_total_people_evaluation(9999);
//				vo2.setCoach_review(4);
//				vo2.setCoach_auth(1);
//				vo2.setCoach_average_evaluation(4.1);
//				vo2.setCoach_license(null);
//				vo2.setCoach_income(1500);
//				vo2.setCoach_introduction(" hello~ WOeld~");
//				vo2.setCoach_bank_account("666-666-666666");
//				vo2.setCoach_no("C011");
//				
//				dao.update(vo2);
//				
//				out.println("修改完成");
//				
//			
//			// 查詢
//				CoachVO vo3 = new CoachVO();
//				vo3 =dao.findByPrimaryKey("C011");
//				out.println(vo3.getCoach_no());
//				out.println(vo3.getCoach_name());
//				out.println(vo3.getCoach_sex());
//				out.println(vo3.getCoach_cellphone());
//				out.println(vo3.getCoach_account());
//				out.println(vo3.getCoach_password());
//				out.println(vo3.getCoach_address());
//				out.println(vo3.getCoach_photo());
//				out.println(vo3.getCoach_total_evaluation());
//				out.println(vo3.getCoach_total_people_evaluation());
//				out.println(vo3.getCoach_review());
//				out.println(vo3.getCoach_auth());
//				out.println(vo3.getCoach_average_evaluation());
//				out.println(vo3.getCoach_license());
//				out.println(vo3.getCoach_income());
//				out.println(vo3.getCoach_introduction());
//				out.println(vo3.getCoach_bank_account());
//				out.println("-------------------------------------單一查詢");
//				
//			// 查詢
//				List<CoachVO> list = dao.getAll();
//				for(CoachVO vo4 : list) {
//					out.println(vo4.getCoach_no());
//					out.println(vo4.getCoach_name());
//					out.println(vo4.getCoach_sex());
//					out.println(vo4.getCoach_cellphone());
//					out.println(vo4.getCoach_account());
//					out.println(vo4.getCoach_password());
//					out.println(vo4.getCoach_address());
//					out.println(vo4.getCoach_photo());
//					out.println(vo4.getCoach_total_evaluation());
//					out.println(vo4.getCoach_total_people_evaluation());
//					out.println(vo4.getCoach_review());
//					out.println(vo4.getCoach_auth());
//					out.println(vo4.getCoach_average_evaluation());
//					out.println(vo4.getCoach_license());
//					out.println(vo4.getCoach_income());
//					out.println(vo4.getCoach_introduction());
//					out.println(vo4.getCoach_bank_account());
//					out.println("--");
//				}
//					// 刪除
//					dao.delete("C011");
//					out.println("刪除完成");
				
				//**修改單一教練狀態  caoch_review coach_auth
//				CoachVO vo5 =new CoachVO();
//				vo5.setCoach_review(1);
//				vo5.setCoach_auth(2);
//				vo5.setCoach_no("C008");
//				dao.updateOneStatus(vo5);
				
				//**查詢單一教練收益
//				CoachVO vo6 =new CoachVO();
//				vo6 =dao.findOneIncome("C009");
//				out.println(vo6.getCoach_income());
//				
//				List<CoachVO> list1 = dao.getAllDesc();
//				for(CoachVO vo7 : list1) {
//					out.println(vo7.getCoach_no());
//					out.println(vo7.getCoach_name());
//					out.println(vo7.getCoach_sex());
//					out.println(vo7.getCoach_cellphone());
//					out.println(vo7.getCoach_account());
//					out.println(vo7.getCoach_password());
//					out.println(vo7.getCoach_address());
//					out.println(vo7.getCoach_photo());
//					out.println(vo7.getCoach_total_evaluation());
//					out.println(vo7.getCoach_total_people_evaluation());
//					out.println(vo7.getCoach_review());
//					out.println(vo7.getCoach_auth());
//					out.println(vo7.getCoach_average_evaluation());
//					out.println(vo7.getCoach_license());
//					out.println(vo7.getCoach_income());
//					out.println(vo7.getCoach_introduction());
//					out.println(vo7.getCoach_bank_account());
//					out.println("--");
//				}
		  
		  	CoachVO vo8 = new CoachVO();
		  	vo8 = dao.findOneCoachAccount("123@gmail.com");
			out.println(vo8.getCoach_no());
			out.println(vo8.getCoach_name());
			out.println(vo8.getCoach_sex());
			out.println(vo8.getCoach_cellphone());
			out.println(vo8.getCoach_account());
			out.println(vo8.getCoach_password());
			out.println(vo8.getCoach_address());
			out.println(vo8.getCoach_photo());
			out.println(vo8.getCoach_total_evaluation());
			out.println(vo8.getCoach_total_people_evaluation());
			out.println(vo8.getCoach_review());
			out.println(vo8.getCoach_auth());
			out.println(vo8.getCoach_average_evaluation());
			out.println(vo8.getCoach_license());
			out.println(vo8.getCoach_income());
			out.println(vo8.getCoach_introduction());
			out.println(vo8.getCoach_bank_account());
			out.println("--");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
