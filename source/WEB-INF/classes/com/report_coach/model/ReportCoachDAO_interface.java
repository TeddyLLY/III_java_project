package com.report_coach.model;

import java.util.List;

public interface ReportCoachDAO_interface {
	
	
    public void insert(ReportCoachVO ReportCoachVo);
    public void update(ReportCoachVO ReportCoachVO);
    public void delete(String report_coach_no);
    public ReportCoachVO  findByPrimaryKey(String report_coach_no);
    public List<ReportCoachVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    
  //**查詢未處理(處理完成)檢舉教練事件status desc
    public List<ReportCoachVO> getAllSameStatus(Integer report_coach_status );
    
  //select coach_name from coach where coach_no= 'C001';
    
}
