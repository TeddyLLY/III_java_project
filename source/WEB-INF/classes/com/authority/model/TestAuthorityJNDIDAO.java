package com.authority.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AuthorityTest")
public class TestAuthorityJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		AuthorityJNDIDAO dao = new AuthorityJNDIDAO();

		// 新增
//		AuthorityVO authorityO1 = new AuthorityVO();
//		authorityO1.setEmployee_no("E010");
//		authorityO1.setFunction_no("F013");
//		dao.insert(authorityO1);
//		out.println("新增成功");

		// 修改 XXXXX
//		AuthorityVO authorityVO1 = new AuthorityVO();
//		
//		authorityVO1.setFunction_no("F002");
//		authorityVO1.setEmployee_no("E001");
//		
//		authorityVO1.setFunction_no("F001");
//		authorityVO1.setEmployee_no("E001");
//		
//		dao.update(authorityVO1);

//		// 刪除
//		AuthorityVO authorityVO2 = new AuthorityVO();
//		authorityVO2.setFunction_no("F002");
//		authorityVO2.setEmployee_no("E001");
//		dao.delete(authorityVO2);
//		out.println("刪除成功");

//		// 單筆查詢
//		List<AuthorityVO> authorityVO3 = dao.findByPrimaryKey("E009");
//		out.println(((AuthorityVO) authorityVO3).getEmployee_no());
//		out.println(((AuthorityVO) authorityVO3).getFunction_no());

		// 多筆查詢
//		List<AuthorityVO> list = dao.getAll();
//		for (AuthorityVO Authority : list) {
//			out.print(Authority.getEmployee_no() + ",");
//			out.print(Authority.getFunction_no());
//			out.println();
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
