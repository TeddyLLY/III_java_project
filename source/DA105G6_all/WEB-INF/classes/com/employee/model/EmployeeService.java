package com.employee.model;

import java.io.File;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Part;

import com.authority.model.AuthorityVO;
import com.employee_function.model.Employee_functionVO;

public class EmployeeService {
	
	private EmployeeDAO_interface dao;
	
	public EmployeeService() {
		dao = new EmployeeJNDIDAO();
	}
	
	public EmployeeVO addEmployee(String employee_name, String employee_title, String employee_account, String employee_mail, String employee_cellphone, byte[] employee_photo, String employee_password) {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmployee_name(employee_name);
		employeeVO.setEmployee_title(employee_title);
		employeeVO.setEmployee_account(employee_account);
		employeeVO.setEmployee_password(employee_password);
		employeeVO.setEmployee_mail(employee_mail);	
		employeeVO.setEmployee_cellphone(employee_cellphone);	
		employeeVO.setEmployee_photo(employee_photo);
		
		dao.insert(employeeVO);
		return employeeVO;
	}
	
	public EmployeeVO addEmployeeWithAuthority(String employee_name, String employee_title, String employee_account, String employee_mail, String employee_cellphone, byte[] employee_photo, String employee_password, List<AuthorityVO> insertAuthoritylist) {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmployee_name(employee_name);
		employeeVO.setEmployee_title(employee_title);
		employeeVO.setEmployee_account(employee_account);
		employeeVO.setEmployee_password(employee_password);
		employeeVO.setEmployee_mail(employee_mail);	
		employeeVO.setEmployee_cellphone(employee_cellphone);	
		employeeVO.setEmployee_photo(employee_photo);
		
		dao.insertWithAutjority(employeeVO, insertAuthoritylist);
		return employeeVO;
	}
	
	public EmployeeVO updateEmployee(String employee_no, String employee_name, String employee_title, String employee_account, String employee_mail, String employee_cellphone, byte[] employee_photo) {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmployee_no(employee_no);
		employeeVO.setEmployee_name(employee_name);
		employeeVO.setEmployee_title(employee_title);
		employeeVO.setEmployee_account(employee_account);
//		employeeVO.setEmployee_password(employee_password);
		employeeVO.setEmployee_mail(employee_mail);
		employeeVO.setEmployee_cellphone(employee_cellphone);
		employeeVO.setEmployee_photo(employee_photo);
		dao.update(employeeVO);
		return employeeVO;
	}
	
	public void deleteEmployee(String employee_no) {
		dao.delete(employee_no);
	}
	
	public EmployeeVO getOneEmployee(String employee_no) {
		return dao.findByPrimaryKey(employee_no);
	}
	
	public List<String> getEmployeeByAuthority(String employee_no){
		return dao.getEmployeeByAuthority(employee_no);
	}
	
	public List<EmployeeVO> getAll(){
		return dao.getAll();
	}
	
	public String findAuthority (String employee_no, String function_no) {
		return dao.findAuthority(employee_no, function_no);
	}
	
}
