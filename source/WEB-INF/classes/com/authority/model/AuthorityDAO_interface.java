package com.authority.model;

import java.util.*;

public interface AuthorityDAO_interface {
	public void insert(AuthorityVO authorityVO);
	public void insert1(String employe_no, List<AuthorityVO> insertAuthoritylist);
//  public void update(AuthorityVO authorityVO); 複合主鍵不適用
    public void delete(AuthorityVO authorityVO);
    public List<AuthorityVO> findByPrimaryKey(String employee_no);
    public List<AuthorityVO> getAll();
    
    //自增主鍵
    public void insert2(AuthorityVO authorityVO, java.sql.Connection con);
}


