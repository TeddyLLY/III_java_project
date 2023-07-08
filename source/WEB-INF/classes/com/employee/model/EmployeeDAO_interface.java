package com.employee.model;

import java.util.*;

import com.authority.model.AuthorityVO;


public interface EmployeeDAO_interface {
	public void insert(EmployeeVO employeeVO);
    public void update(EmployeeVO employeeVO);
    public void delete(String employee_no);
    public EmployeeVO findByPrimaryKey(String employee_no);
    public List<EmployeeVO> getAll();

    //拿帳密
    public EmployeeVO employeeLogin(String employee_account, String employee_password);
    //找單一員工權限
    public List<String> getEmployeeByAuthority(String employee_no);
    //同時新增員工與權限
    public void insertWithAutjority(EmployeeVO employeeVO, List<AuthorityVO> insertAuthoritylist);

    public String findAuthority(String employee_no,String function_no);
    
    }
