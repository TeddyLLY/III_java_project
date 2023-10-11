package com.lesson_detail.model;

import java.sql.Date;
import java.util.List;

import org.json.JSONArray;



public class LessonDetailService {
	private LessonDetailDAO_interface dao;
	
	public LessonDetailService() {
		dao = new LessonDetailJNDIDAO();
	}
	
	public LessonDetailVO addLessonDetail(String lessonNo,Date lessonDate) {
		LessonDetailVO lessonDetailVO = new LessonDetailVO();
		lessonDetailVO.setLessonNo(lessonNo);
		lessonDetailVO.setLessonDate(lessonDate);
		dao.insert(lessonDetailVO);
		return lessonDetailVO;
	}
	public LessonDetailVO updateLessonDetail(String lessonDetailNo,String lessonNo,Date lessonDate) {
		LessonDetailVO lessonDetailVO = new LessonDetailVO();
		lessonDetailVO.setLessonDetailNo(lessonDetailNo);
		lessonDetailVO.setLessonNo(lessonNo);
		lessonDetailVO.setLessonDate(lessonDate);
		dao.update(lessonDetailVO);
		return lessonDetailVO;
	}	
	public  String getDetailByLesson_ajax(String lessonNo){

		String jsonStr = "";
		jsonStr = new JSONArray(dao.findByLesson(lessonNo)).toString();
		return jsonStr;
	}		
	
	public void deleteLessonDetail(String lessonNo) {
		dao.delete(lessonNo);
	}
	
	public void deleteLessonDetailOnly(String lessonDetailNo) {
		dao.deleteByLessonDetail(lessonDetailNo);
	}	
	
	public LessonDetailVO getOneLessonDetail(String lessonDetailNo) {
		return dao.findByPrimaryKey(lessonDetailNo);
	}
	
	public List<LessonDetailVO> getDetailByLesson(String lessonNo){
		return dao.findByLesson(lessonNo);
	}
	
	public List<LessonDetailVO> getAll(){
		return dao.getAll();
	}

}
