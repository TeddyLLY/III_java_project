package com.coach.model;

import java.util.List;
import java.util.Map;

public interface CoachDAO_interface {
	
    public void insert(CoachVO CoachVO);
    public void update(CoachVO CoachVO);
    public void delete(String coach_no);
    public CoachVO  findByPrimaryKey(String coach_no);
    public List<CoachVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
  public List<CoachVO> getAny(Map<String, String[]> map);
    
    public List<CoachVO> getAllDesc();
    
    //查詢教練帳號
    public CoachVO findOneCoachAccount(String coach_account );
    
    public void updateOneStatus(CoachVO CoachVO);
    public CoachVO findOneIncome(String coach_no);
    
    
    //Android
    public CoachVO findOneName(String coach_no);
    boolean isCoach(String coach_account, String coach_password);
    boolean isCoachIdExist(String coach_no);
    boolean insertToAndroid(CoachVO coachVO);
    boolean updateFromAndroid(CoachVO coachVO);
    boolean deleteFromAndroid(String coach_no);
    CoachVO findById(String coach_no);
    List<CoachVO> getAllFromAndroid();
}
