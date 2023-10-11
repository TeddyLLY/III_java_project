package com.employee_function.model;

import java.util.*;

public interface Employee_functionDAO_interface {
	public void insert(Employee_functionVO employee_functionVO);
    public void update(Employee_functionVO employee_functionVO);
    public void delete(String function_no);
    public Employee_functionVO findByPrimaryKey(String function_no);
    public List<Employee_functionVO> getAll();
}
