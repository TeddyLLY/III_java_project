package com.authority.model;

import java.util.List;

public class AuthorityService {
	
	public AuthorityDAO_interface dao;
	
	public AuthorityService() {
		dao = new AuthorityJNDIDAO();
	}
	
	public AuthorityVO addAuthority(String employee_no, String function_no) {
		AuthorityVO authorityVO = new AuthorityVO();
		authorityVO.setEmployee_no(employee_no);
		authorityVO.setFunction_no(function_no);	
		dao.insert(authorityVO);
		return authorityVO;
	}
	
	public AuthorityVO addAuthority1(String employee_no, List<AuthorityVO> insertAuthoritylist) {
		AuthorityVO authorityVO = new AuthorityVO();
		authorityVO.setEmployee_no(employee_no);
		dao.insert1(employee_no, insertAuthoritylist);
		return authorityVO;
	}
	
	
	public void deleteAuthority(String employee_no) {
		AuthorityVO authorityVO = new AuthorityVO();
		authorityVO.setEmployee_no(employee_no);

		dao.delete(authorityVO);
	}
	
	public List<AuthorityVO> getOneAuthority(String employee_no) {
		return dao.findByPrimaryKey(employee_no);
	}
	
	public List<AuthorityVO> getAll() {
		return dao.getAll();
	}
}
