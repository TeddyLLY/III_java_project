package com.lesson.model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.lesson_detail.model.LessonDetailService;
import com.lesson_detail.model.LessonDetailVO;

public class LessonService {
	
	private LessonDAO_interface dao;
	
	public LessonService() {
		dao=new LessonJNDIDAO();
	}
	
	
	public LessonVO addLesson(String coachNo,String lessonName,
			String lessonPoint ,Integer lessonPrice, String lessonContent,
			Date lessonStartDate ,Date lessonEndDate, Integer lessonRegistration ,
			Integer lessonMaximumPeople, byte[] lessonPicture, String lessonLocation,
			Integer lessonClass,Integer lessonStatus) {
		LessonVO lessonVO =new LessonVO();
		lessonVO.setCoachNo(coachNo);
		lessonVO.setLessonName(lessonName);
		lessonVO.setLessonPoint(lessonPoint);
		lessonVO.setLessonPrice(lessonPrice);
		lessonVO.setLessonContent(lessonContent);
		lessonVO.setLessonStartDate(lessonStartDate);
		lessonVO.setLessonEndDate(lessonEndDate);
		lessonVO.setLessonRegistration(lessonRegistration);
		lessonVO.setLessonMaximumPeople(lessonMaximumPeople);
		lessonVO.setLessonPicture(lessonPicture);
		lessonVO.setLessonLocation(lessonLocation);
		lessonVO.setLessonClass(lessonClass);
		lessonVO.setLessonStatus(lessonStatus);
		dao.insert(lessonVO);
		return lessonVO;
	}
	public LessonVO updateLesson(String lessonNo,String lessonName,
			String lessonPoint ,Integer lessonPrice, String lessonContent,Date lessonStartDate,
			Date lessonEndDate,
			Integer lessonMaximumPeople, byte[] lessonPicture, String lessonLocation,
			Integer lessonClass,Integer lessonStatus) {
		LessonVO lessonVO =new LessonVO();
		lessonVO.setLessonNo(lessonNo);
//		lessonVO.setCoachNo(coachNo);//不予操作
		lessonVO.setLessonName(lessonName);
		lessonVO.setLessonPoint(lessonPoint);
		lessonVO.setLessonPrice(lessonPrice);
		lessonVO.setLessonContent(lessonContent);
		lessonVO.setLessonStartDate(lessonStartDate);
		lessonVO.setLessonEndDate(lessonEndDate);
//		lessonVO.setLessonRegistration(lessonRegistration);//不予操作
		lessonVO.setLessonMaximumPeople(lessonMaximumPeople);
		lessonVO.setLessonPicture(lessonPicture);
		lessonVO.setLessonLocation(lessonLocation);
		lessonVO.setLessonClass(lessonClass);
		lessonVO.setLessonStatus(lessonStatus);
		dao.update(lessonVO);
		return lessonVO;
	}
	public LessonVO updateLesson2(String lessonNo,String lessonName,
			String lessonPoint ,Integer lessonPrice, String lessonContent,
			Date lessonEndDate,
			Integer lessonMaximumPeople, byte[] lessonPicture, String lessonLocation,
			Integer lessonClass,Integer lessonStatus,List<LessonDetailVO> lcdVO) {
		LessonVO lessonVO =new LessonVO();
		lessonVO.setLessonNo(lessonNo);
//		lessonVO.setCoachNo(coachNo);//不予操作
		lessonVO.setLessonName(lessonName);
		lessonVO.setLessonPoint(lessonPoint);
		lessonVO.setLessonPrice(lessonPrice);
		lessonVO.setLessonContent(lessonContent);
//		lessonVO.setLessonStartDate(lessonStartDate); //不予操作
		lessonVO.setLessonEndDate(lessonEndDate);
//		lessonVO.setLessonRegistration(lessonRegistration);//不予操作
		lessonVO.setLessonMaximumPeople(lessonMaximumPeople);
		lessonVO.setLessonPicture(lessonPicture);
		lessonVO.setLessonLocation(lessonLocation);
		lessonVO.setLessonClass(lessonClass);
		lessonVO.setLessonStatus(lessonStatus);
		List<LessonDetailVO> list = lcdVO;
		dao.update2(lessonVO,list);
		return lessonVO;
	}	
	public void updateLessonStatus(String lessonNo,Integer lessonStatus) {
		LessonVO lessonVO =new LessonVO();
		lessonVO.setLessonNo(lessonNo);		
		lessonVO.setLessonStatus(lessonStatus);	
		dao.updateLessonStatus(lessonVO);
	}	
	public void deleteLesson(String lessonNo) {
		dao.delete2(lessonNo);
	}
	public LessonVO getOneLesson(String lessonNo) {
		return dao.findByPrimaryKey(lessonNo);
	}
	
	public List<LessonVO> getLessonByCoach(String coachNo) {
		return dao.getLessonByCoach(coachNo);
	}
	
	public List<String> getLessonDateByCoach(String coachNo) {
		LessonDetailService ldSvc = new LessonDetailService();
		List<LessonVO>list =dao.getLessonDateByCoach(coachNo);
		List<String> coLeDate = new LinkedList<String>();
		for(LessonVO lVO:list) {
			List<LessonDetailVO>ldlist=ldSvc.getDetailByLesson(lVO.getLessonNo());
			for(LessonDetailVO ldVO:ldlist) {
				coLeDate.add("'"+ldVO.getLessonDate()+"'");
			}	
		}
		return coLeDate;
	}	
	public String getLessonDateByCoach2(String coachNo,String lessonDate) {
		LessonDetailService ldSvc = new LessonDetailService();
		String lessonName = null;
		List<LessonVO>list =dao.getLessonDateByCoach(coachNo);
		for(LessonVO lVO:list) {
			List<LessonDetailVO>ldlist=ldSvc.getDetailByLesson(lVO.getLessonNo());
			for(LessonDetailVO ldVO:ldlist) {
				if(ldVO.getLessonDate().toString().equals(lessonDate)) {
					lessonName= lVO.getLessonName();
				}
			}	
		}
		return lessonName;
	}	
	public List<LessonVO> getAll() {
		return dao.getAll();
	}
	
	public List<LessonVO> gethistoryAll() {
		return dao.gethistoryAll();
	}
	
	public List<LessonVO> getmAll(){
		return dao.getmAll();
	}
	
	public List<LessonVO> searchLesson(String string){
		return dao.search(string);
	}	
	
	public List<LessonVO> compositeQuery(Map<String,String[]> map){
		return dao.getAll(map);
	}	
	
	public LessonVO addLessonXDetail(String coachNo,String lessonName,
			String lessonPoint ,Integer lessonPrice, String lessonContent,
			Date lessonStartDate ,Date lessonEndDate, Integer lessonRegistration ,
			Integer lessonMaximumPeople, byte[] lessonPicture, String lessonLocation,
			Integer lessonClass,Integer lessonStatus,List<LessonDetailVO> lcdVO) {
		LessonVO lessonVO =new LessonVO();
		lessonVO.setCoachNo(coachNo);
		lessonVO.setLessonName(lessonName);
		lessonVO.setLessonPoint(lessonPoint);
		lessonVO.setLessonPrice(lessonPrice);
		lessonVO.setLessonContent(lessonContent);
		lessonVO.setLessonStartDate(lessonStartDate);
		lessonVO.setLessonEndDate(lessonEndDate);
		lessonVO.setLessonRegistration(lessonRegistration);
		lessonVO.setLessonMaximumPeople(lessonMaximumPeople);
		lessonVO.setLessonPicture(lessonPicture);
		lessonVO.setLessonLocation(lessonLocation);
		lessonVO.setLessonClass(lessonClass);
		lessonVO.setLessonStatus(lessonStatus);
		
		List<LessonDetailVO> list = lcdVO;  //not sure
		
		dao.insertWithDetails(lessonVO, list);
		return lessonVO;
	}

	
}
