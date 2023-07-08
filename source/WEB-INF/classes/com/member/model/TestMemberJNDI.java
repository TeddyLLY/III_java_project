package com.member.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestMemberJNDI")
public class TestMemberJNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestMemberJNDI() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8;");
		PrintWriter out = response.getWriter();
		  
		  MemberJNDIDAO dao = new MemberJNDIDAO();
		  
			
			//新增
//			MemberVO vo1 = new MemberVO();
//			vo1.setMember_name("葉問");
//			vo1.setMember_sex("男");
//			vo1.setMember_cellphone("0943871254");
//			vo1.setMember_account("fighttoten@gmail.com");
//			vo1.setMember_password("8525301");
//			vo1.setMember_address("廣東");
//			vo1.setMember_photo(null);
//			vo1.setMember_points(1700);
//			vo1.setMember_height(183);
//			vo1.setMember_weight(80);
//			vo1.setMember_review(0);
//			vo1.setMember_auth(0);
//			vo1.setMember_bodyfat(5.6);
//			vo1.setMember_card("1111-2222-3333-4444");
//			dao.insert(vo1);
			
			//修改
//			MemberVO vo2 = new MemberVO();
//			vo2.setMember_sex("6");
//			vo2.setMember_cellphone("0943521068");
//			vo2.setMember_password("123456");
//			vo2.setMember_address("新竹竹北");
//			vo2.setMember_photo(null);
//			vo2.setMember_points(3000);
//			vo2.setMember_height(190);
//			vo2.setMember_weight(91);
//			vo2.setMember_review(0);
//			vo2.setMember_auth(2);
//			vo2.setMember_bodyfat(4.4);
//			vo2.setMember_card("2222-3333-4444-5555");
//			vo2.setMember_no("M001");
//			dao.update(vo2);
//			
			
			//查詢
//			MemberVO vo3 =new MemberVO();
//			vo3 = dao.findByPrimaryKey("M011");
//			out.println(vo3.getMember_no());
//			out.println(vo3.getMember_name());
//			out.println(vo3.getMember_sex());
//			out.println(vo3.getMember_cellphone());
//			out.println(vo3.getMember_account());
//			out.println(vo3.getMember_password());
//			out.println(vo3.getMember_address());
//			out.println(vo3.getMember_photo());
//			out.println(vo3.getMember_points());
//			out.println(vo3.getMember_height());
//			out.println(vo3.getMember_weight());
//			out.println(vo3.getMember_review());
//			out.println(vo3.getMember_auth());
//			out.println(vo3.getMember_bodyfat());
//			out.println(vo3.getMember_card());
//			out.println("--------------------------------------------------------------------------");
//			
//			//刪除
//			
//			dao.delete("M011");
		  
		  
		  	//查詢
//			List<MemberVO> list = dao.getAll();
//			for(MemberVO vo4 : list) {
//				out.println(vo4.getMember_name());
//				out.println(vo4.getMember_sex());
//				out.println(vo4.getMember_cellphone());
//				out.println(vo4.getMember_account());
//				out.println(vo4.getMember_password());
//				out.println(vo4.getMember_address());
//				out.println(vo4.getMember_photo());
//				out.println(vo4.getMember_points());
//				out.println(vo4.getMember_height());
//				out.println(vo4.getMember_weight());
//				out.println(vo4.getMember_review());
//				out.println(vo4.getMember_auth());
//				out.println(vo4.getMember_bodyfat());
//				out.println(vo4.getMember_card());
//				out.println("-->");
//			}
			//查詢會員狀態
			MemberVO vo5 = new MemberVO();
			
			vo5.setMember_review(0);
			vo5.setMember_auth(2);

			vo5.setMember_no("M001");
			dao.updateOneStatus(vo5);
		
//			MemberVO vo6 =new MemberVO();
//			vo6 = dao.findOnePoints("M007");
//			out.println(vo6.getMember_points());
//			out.println("--------------------------------------------------------------------------");
//	
//			 List<MemberVO> list1 = dao.getAllDesc();
//				for(MemberVO vo7 : list1) {
//					out.println(vo7.getMember_no());
//					out.println(vo7.getMember_name());
//					out.println(vo7.getMember_sex());
//					out.println(vo7.getMember_cellphone());
//					out.println(vo7.getMember_account());
//					out.println(vo7.getMember_password());
//					out.println(vo7.getMember_address());
//					out.println(vo7.getMember_photo());
//					out.println(vo7.getMember_points());
//					out.println(vo7.getMember_height());
//					out.println(vo7.getMember_weight());
//					out.println(vo7.getMember_review());
//					out.println(vo7.getMember_auth());
//					out.println(vo7.getMember_bodyfat());
//					out.println(vo7.getMember_card());
//					out.println("-->");
//				}
			
			//查會員帳號
			MemberVO vo8 = new MemberVO();
			vo8 = dao.findOneMemberAccount("123@gmail.com");
			
			out.println(vo8.getMember_no());
			out.println(vo8.getMember_name());
			out.println(vo8.getMember_sex());
			out.println(vo8.getMember_cellphone());
			out.println(vo8.getMember_account());
			out.println(vo8.getMember_password());
			out.println(vo8.getMember_address());
			out.println(vo8.getMember_photo());
			out.println(vo8.getMember_points());
			out.println(vo8.getMember_height());
			out.println(vo8.getMember_weight());
			out.println(vo8.getMember_review());
			out.println(vo8.getMember_auth());
			out.println(vo8.getMember_bodyfat());
			out.println(vo8.getMember_card());
			out.println("-->");
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
