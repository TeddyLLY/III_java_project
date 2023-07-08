package com.coach_schedule.model;

import java.sql.Date;
import java.util.List;

public class CoachScheduleService {
	
	private CoachScheduleDAO_interface dao;
	
	public CoachScheduleService() {
		dao = new CoachScheduleJNDIDAO();
	}
	
	
	public CoachScheduleVO addCoachScheduleVO(String coachNo, Date coachDate) {
		CoachScheduleVO coachScheduleVO = new CoachScheduleVO();
		
		coachScheduleVO.setCoachNo(coachNo);
		coachScheduleVO.setCoachDate(coachDate);
		dao.insert(coachScheduleVO);
		
		return coachScheduleVO;
	}
	
	public CoachScheduleVO updateCoachScheduleVO(String coachScheduleNo, String coachNo, Date coachDate) {
		CoachScheduleVO coachScheduleVO = new CoachScheduleVO();
		coachScheduleVO.setCoachScheduleNo(coachScheduleNo);
		coachScheduleVO.setCoachNo(coachNo);
		coachScheduleVO.setCoachDate(coachDate);
		dao.update(coachScheduleVO);
		
		return coachScheduleVO;
	}
	
	public void deleteCoach_schedule_no(String coachScheduleNo) {
		dao.delete(coachScheduleNo);
	}
	
	public void deleteOneCoachSchedule(String coachNo,Date coachDate) {
		CoachScheduleVO coachScheduleVO = new CoachScheduleVO();
		coachScheduleVO.setCoachNo(coachNo);
		coachScheduleVO.setCoachDate(coachDate);		
		dao.delete2(coachScheduleVO);
	}
	
	public CoachScheduleVO getOneCoachSchedule(String coachScheduleNo) {
		return dao.findByPrimaryKey(coachScheduleNo);
	}
	
	public List<CoachScheduleVO> getAll(){
		return dao.getAll();
	}
	
	public List<CoachScheduleVO> getCoachSchedule(String coachNo){
		return dao.getCoachSchedule(coachNo);
	}
	
}
