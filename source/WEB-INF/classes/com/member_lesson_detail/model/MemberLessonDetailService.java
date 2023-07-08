package com.member_lesson_detail.model;

import java.sql.Date;
import java.util.List;

import org.json.JSONArray;

public class MemberLessonDetailService {

	private MemberLessonDetailDAO_interface dao;
	
	public MemberLessonDetailService() {
		dao = new MemberLessonDetailJNDIDAO();
	}
	
	public MemberLessonDetailVO addMemberDetail(String lessonOrderNo,Integer studentStatus,Date lessonDate) {
		MemberLessonDetailVO memberLessonDetailVO = new MemberLessonDetailVO();
		memberLessonDetailVO.setLessonOrderNo(lessonOrderNo);
		memberLessonDetailVO.setStudentStatus(studentStatus);
		memberLessonDetailVO.setLessonDate(lessonDate);
		memberLessonDetailVO.setStudentStatus(studentStatus);
		
		dao.insert(memberLessonDetailVO);
		return memberLessonDetailVO;
	}
	
	public MemberLessonDetailVO updateMemberDetail(String memberLessonDetailNo, String lessonOrderNo,Integer studentStatus,Date lessonDate) {
		MemberLessonDetailVO memberLessonDetailVO = new MemberLessonDetailVO();
		memberLessonDetailVO.setMemberLessonDetailNo(memberLessonDetailNo);
		memberLessonDetailVO.setLessonOrderNo(lessonOrderNo);
		memberLessonDetailVO.setStudentStatus(studentStatus);
		memberLessonDetailVO.setLessonDate(lessonDate);
		memberLessonDetailVO.setStudentStatus(studentStatus);
		
		dao.update(memberLessonDetailVO);
		return memberLessonDetailVO;
	}
	
	public void deleteMemberLessonDetail(String memberLessonDetailNo) {
		dao.delete(memberLessonDetailNo);
	}
	
	public MemberLessonDetailVO getOneMemberLessonDetail(String memberLessonDetailNo) {
		return dao.findByPrimaryKey(memberLessonDetailNo);
	}
	
	public List<MemberLessonDetailVO> getDetailByLessonOrder(String lessonOrderNo){
		return dao.getOneAll(lessonOrderNo);
	}
	public String getDetailByLessonOrder_json(String lessonOrderNo){
		String jsonStr = "";
		jsonStr = new JSONArray(dao.getOneAll(lessonOrderNo)).toString();
		return jsonStr;
	}
	
	public void updateStatusToday(String lessonOrderNo,Integer studentstatus,Integer orgstatus) {
		dao.updateSTATUSTODAY(lessonOrderNo, studentstatus,orgstatus);		
	}
	
	public void updateMYStatus(String memberLessonDetailNo,Integer studentStatus) {
		dao.updateMYSTATUS(memberLessonDetailNo, studentStatus);		
	}	
	
	List<MemberLessonDetailVO> getAll(){
		return dao.getAll();
	}

}
