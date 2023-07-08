package com.dbgifreader;


import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;

public class DBGifReader extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();	
	
		try {
			
			String employee_no = req.getParameter("employee_no");
			EmployeeService employeeSvc = new EmployeeService();
			EmployeeVO employeeVO = employeeSvc.getOneEmployee(employee_no);
			out.write(employeeVO.getEmployee_photo());
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/back-end/employee/images/none2.jpg");
			byte[] employee_photo = new byte[in.available()];
			in.read(employee_photo);
			out.write(employee_photo);
			in.close();
		}
		
	}

}
