package com.top_up_record.model;

import java.util.List;


public interface TopUpRecordDAO_interface {

	
        public void insert(TopUpRecordVO TopUpRecordVO);
        public void update(TopUpRecordVO TopUpRecordVO);
        public void delete(String  top_up_record_no);
        public TopUpRecordVO  findByPrimaryKey(String top_up_record_no);
        public List<TopUpRecordVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//      public List<EmpVO> getAll(Map<String, String[]> map); 

      //**查詢單一會員儲值紀錄
        public List<TopUpRecordVO>  findOneMemberRecord(String member_no);
	
}
