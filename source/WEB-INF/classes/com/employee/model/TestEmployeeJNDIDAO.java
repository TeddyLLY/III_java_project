package com.employee.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EmployeeTest")
public class TestEmployeeJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		EmployeeJNDIDAO dao = new EmployeeJNDIDAO();
		
		//新增
		EmployeeVO employeeVO1 = new EmployeeVO();
		employeeVO1.setEmployee_name("chika");
		employeeVO1.setEmployee_title("客服人員");
		employeeVO1.setEmployee_account("chika5548");
		employeeVO1.setEmployee_password("wertgb");
		employeeVO1.setEmployee_mail("chika19921006@gmail.com");
		employeeVO1.setEmployee_cellphone("0965045859");
		dao.insert(employeeVO1);
		out.println("新增成功");
	
		// 修改
		EmployeeVO employeeVO2 = new EmployeeVO();
		employeeVO2.setEmployee_no("E010");
		employeeVO2.setEmployee_name("CHIKA");
		employeeVO2.setEmployee_title("直播管理員");
		employeeVO2.setEmployee_account("CHIKA20008154");
		employeeVO2.setEmployee_password("ASDLKU");
		employeeVO2.setEmployee_mail("CHIKA20000509@gmail.com");
		employeeVO2.setEmployee_cellphone("098304692");
		dao.update(employeeVO2);
		out.println("修改成功");
	
		// 刪除
//		dao.delete("E011");
//		out.println("刪除成功");
		
		// 單筆查詢
		EmployeeVO employeeVO3 = dao.findByPrimaryKey("E010");
		out.print(employeeVO3.getEmployee_no() + ",");
		out.print(employeeVO3.getEmployee_name() + ",");
		out.print(employeeVO3.getEmployee_title() + ",");
		out.print(employeeVO3.getEmployee_account() + ",");
		out.print(employeeVO3.getEmployee_password() + ",");
		out.print(employeeVO3.getEmployee_mail() + ",");
		out.println(employeeVO3.getEmployee_cellphone());
		out.println("---------------------");
	
		// 多筆查詢
		List<EmployeeVO> list = dao.getAll();
		
		for (EmployeeVO Employee : list) {
			out.print(Employee.getEmployee_no() + ",");
			out.print(Employee.getEmployee_name() + ",");
			out.print(Employee.getEmployee_title() + ",");
			out.print(Employee.getEmployee_account() + ",");
			out.print(Employee.getEmployee_password() + ",");
			out.print(Employee.getEmployee_mail() + ",");
			out.print(Employee.getEmployee_cellphone());
			out.println();
		}
		
//		EmployeeVO employeeVO3 = dao.findEmployeePwd("E010");
//		out.print(employeeVO3.getEmployee_no() + ",");
//		out.print(employeeVO3.getEmployee_account() + ",");
//		out.print(employeeVO3.getEmployee_password() + ",");
//
//		out.println("---------------------");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
