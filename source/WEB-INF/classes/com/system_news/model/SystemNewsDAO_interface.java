package com.system_news.model;

import java.util.List;

public interface SystemNewsDAO_interface {
	
    public void insert(SystemNewsVO SystemNewsVO);
    public void update(SystemNewsVO SystemNewsVO);
    public void delete(String system_news_no);
    public SystemNewsVO  findByPrimaryKey(String system_news_no);
    public List<SystemNewsVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

    //**查詢單一會員所有系統訊息
    public  List<SystemNewsVO>  findByOneMember(String member_no);
    //**查詢單一教練所有系統訊息
    public  List<SystemNewsVO>  findByOneCoach(String coach_no);
}
