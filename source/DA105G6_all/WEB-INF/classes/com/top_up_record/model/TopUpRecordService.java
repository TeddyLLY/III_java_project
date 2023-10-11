package com.top_up_record.model;

import java.util.List;

public class TopUpRecordService {
	private TopUpRecordDAO_interface dao;
	public TopUpRecordService() {
		super();
		dao = new TopUpRecordJNDIDAO();
	}

	
	//新增單一user儲值紀錄
	public TopUpRecordVO insertOneTopUpRecord(String member_no,java.sql.Timestamp record_time,Integer money_record) {
		TopUpRecordVO TopUpRecordVO = new TopUpRecordVO();
		
		TopUpRecordVO.setMember_no(member_no);
		TopUpRecordVO.setRecord_time(record_time);
		TopUpRecordVO.setMoney_record(money_record);
		
		dao.insert(TopUpRecordVO);
		return TopUpRecordVO;
	}
	//修改單一user儲值紀錄
	public TopUpRecordVO updateTopUpRecord(String member_no,java.sql.Timestamp record_time,Integer money_record,String top_up_record_no) {
		TopUpRecordVO TopUpRecordVO = new TopUpRecordVO();
		
		TopUpRecordVO.setMember_no(member_no);
		TopUpRecordVO.setRecord_time(record_time);
		TopUpRecordVO.setMoney_record(money_record);
		TopUpRecordVO.setTop_up_record_no(top_up_record_no);
		
		dao.update(TopUpRecordVO);
		return TopUpRecordVO;
	}
	//查詢單一user儲值紀錄
	public TopUpRecordVO findOneTopUpRecord(String top_up_record_no) {
		return dao.findByPrimaryKey(top_up_record_no);
	}
	//查詢所有使用者儲值紀錄
	public List<TopUpRecordVO> findAllTopUpRecord(){
		return dao.getAll();
	}
	//刪除單一user儲值紀錄
	public void deleteOneUserTopUpRecord(String top_up_record_no) {
		dao.delete(top_up_record_no);
	}
	
	
	//**查詢單一會員儲值紀錄
	public List <TopUpRecordVO> findOneMemberRecord(String member_no){
		return dao.findOneMemberRecord(member_no);
		
	}
	
	
	//advance 暫時不用這些都要join
	//刪除時間大於1個月的單一會員儲值紀錄 
	//刪除時間大於1個月的所有儲值紀錄
	//*新增每一個會員xx點
	
}
