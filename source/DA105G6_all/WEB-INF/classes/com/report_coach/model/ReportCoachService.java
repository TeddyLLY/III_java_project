package com.report_coach.model;

import java.util.List;

public class ReportCoachService {
	ReportCoachDAO_interface dao;
	
	public ReportCoachService() {
		super();
		dao = new ReportCoachJNDIDAO() ;
	
	}

	//新增一件檢舉教練事件
	public ReportCoachVO insertOneReportCoach(String coach_no,String member_no,String report_coach_content,java.sql.Timestamp report_coach_time,Integer report_coach_status) {
		ReportCoachVO reportCoachVO = new ReportCoachVO();
		reportCoachVO.setCoach_no(coach_no);
		reportCoachVO.setMember_no(member_no);
		reportCoachVO.setReport_coach_content(report_coach_content);
		reportCoachVO.setReport_coach_time(report_coach_time);
		reportCoachVO.setReport_coach_status(report_coach_status);
		
		dao.insert(reportCoachVO);
		return reportCoachVO;
	}
	
	//修改一件檢舉教練事件
		public ReportCoachVO updateOneReportCoach
(String coach_no,String member_no,String report_coach_content,java.sql.Timestamp report_coach_time,Integer report_coach_status ,String report_coach_no ) {
			ReportCoachVO reportCoachVO = new ReportCoachVO();
			
			reportCoachVO.setCoach_no(coach_no);
			reportCoachVO.setMember_no(member_no);
			reportCoachVO.setReport_coach_content(report_coach_content);
			reportCoachVO.setReport_coach_time(report_coach_time);
			reportCoachVO.setReport_coach_status(report_coach_status);
			reportCoachVO.setReport_coach_no(report_coach_no);
			dao.update(reportCoachVO);
			return reportCoachVO;
		}
	//查詢一件單一檢舉教練事件
		public ReportCoachVO findOneReportCoach(String report_coach_no) {
			return dao.findByPrimaryKey(report_coach_no);
		}
		
	
	//查詢所有檢舉教練事件
		public List<ReportCoachVO> findAllReportCoach(){
			return dao.getAll();
		}
		
	//刪除單一檢舉教練事件
	public void deleteOneReportCoach (String report_coach_no) {
		dao.delete(report_coach_no);
	}
	
	//advance
	//**查詢未處理(處理完成)檢舉教練事件status desc
	public List<ReportCoachVO> findAllSameStatus(Integer report_coach_status){
		return dao.getAllSameStatus(report_coach_status);
		
	}
}
