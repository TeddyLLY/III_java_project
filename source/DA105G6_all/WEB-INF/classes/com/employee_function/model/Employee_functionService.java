package com.employee_function.model;

import java.util.List;

public class Employee_functionService {
	
	public Employee_functionDAO_interface dao;
	
	public Employee_functionService() {
		dao = new Employee_functionJNDIDAO();
	}
	
	public Employee_functionVO addEmployee_function(String function_name) {
		Employee_functionVO employee_functionVO = new Employee_functionVO();
		employee_functionVO.setFunction_name(function_name);
		dao.insert(employee_functionVO);
		return employee_functionVO;
	}
	
	public Employee_functionVO updateEmployee_function(String function_no, String function_name) {
		Employee_functionVO employee_functionVO = new Employee_functionVO();
		employee_functionVO.setFunction_no(function_no);
		employee_functionVO.setFunction_name(function_name);
		dao.update(employee_functionVO);
		return employee_functionVO;
	}
	
	public void deleteEmployee_function(String function_no) {
		dao.delete(function_no);
	}
	
	public Employee_functionVO getOneEmployee_function(String function_no) {
		return dao.findByPrimaryKey(function_no);
	}
	
	public List<Employee_functionVO> getAll() {
		return dao.getAll();
	}
}
