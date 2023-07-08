package com.employee_function.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FunctionTest")
public class TestEmployee_functionJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		Employee_functionJNDIDAO dao = new Employee_functionJNDIDAO();

		// 新增
		Employee_functionVO employee_functionVO1 = new Employee_functionVO();
		
		employee_functionVO1.setFunction_name("討論區檢舉管理");
		dao.insert(employee_functionVO1);	
		out.println("新增成功");
		
		// 修改
		Employee_functionVO employee_functionVO2 = new Employee_functionVO();
		employee_functionVO2.setFunction_no("F016");
		employee_functionVO2.setFunction_name("管理討論區檢舉");
		dao.update(employee_functionVO2);
		out.println("修改成功");

		// 刪除
//		dao.delete("F016");
//		out.println("刪除成功");

		// 單筆查詢
		Employee_functionVO employee_functionVO3 = dao.findByPrimaryKey("F015");
		out.print(employee_functionVO3.getFunction_no() + ",");
		out.print(employee_functionVO3.getFunction_name());
		out.println("---------------------");

		// 多筆查詢
		List<Employee_functionVO> list = dao.getAll();
		
		for (Employee_functionVO employee_function : list) {
			out.print(employee_function.getFunction_no() + ",");
			out.print(employee_function.getFunction_name());
			out.println();
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
