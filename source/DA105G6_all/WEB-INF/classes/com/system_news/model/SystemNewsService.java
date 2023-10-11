package com.system_news.model;

import java.util.List;

public class SystemNewsService {

	private SystemNewsDAO_interface dao;
	public SystemNewsService() {
		super();
		dao = new SystemNewsJNDIDAO();
	}
	
	//新增一件系統訊息  
	public SystemNewsVO insertOneSystemNews
		(String member_no,String coach_no,String system_content,java.sql.Timestamp system_time) {
			SystemNewsVO SystemNewsVO= new SystemNewsVO();
			SystemNewsVO.setMember_no(member_no);
			SystemNewsVO.setCoach_no(coach_no);
			SystemNewsVO.setSystem_content(system_content);
			SystemNewsVO.setSystem_time(system_time);
			
			dao.insert(SystemNewsVO);
			return SystemNewsVO;
		}
		
	//修改一件系統訊息  
	public SystemNewsVO updateOneSystemNews
	(String member_no,String coach_no,String system_content,java.sql.Timestamp system_time ,String system_news_no) {
		SystemNewsVO SystemNewsVO= new SystemNewsVO();
		SystemNewsVO.setMember_no(member_no);
		SystemNewsVO.setCoach_no(coach_no);
		SystemNewsVO.setSystem_content(system_content);
		SystemNewsVO.setSystem_time(system_time);
		SystemNewsVO.setSystem_news_no(system_news_no);
		
		dao.update(SystemNewsVO);
		return SystemNewsVO;
	}
	
	//刪除一件系統訊息 
	public void deleteOneSystemNews(String system_news_no) {
		dao.delete(system_news_no);
	}
	//查詢一件系統訊息  desc
	public SystemNewsVO findOneSystemNews(String system_news_no) {
		return dao.findByPrimaryKey(system_news_no);
	}
	//查詢所有系統訊息 desc
	public List<SystemNewsVO> findAllSystemNews(){
		return dao.getAll();
	}
	
///
	
	//**查詢單一會員所有系統訊息 desc
	public List<SystemNewsVO> findOneMemberAllSystemNews(String member_no){
		return dao.findByOneMember(member_no);
	}
	//**查詢單一教練所有系統訊息 desc
	public List<SystemNewsVO> findOneCoachAllSystemNews(String coach_no){
		return dao.findByOneCoach(coach_no);
	}	
	
	
	//advance
	//刪除單一教練大於一個月的所有系統訊息 目前沒必要刪
	//刪除單一會員大於一個月的所有系統訊息 目前沒必要刪
	//刪除所有大於一個月的系統訊息 目前沒必要刪
	//*新增一筆訊息給所有教練 用findAllDesc 就好,最好不要用join 的表格會很亂
	//*新增一筆訊息給所有會員 用findAllDesc 就好,最好不要用join 的表格會很亂

}
