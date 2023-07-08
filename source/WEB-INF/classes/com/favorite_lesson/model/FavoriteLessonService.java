package com.favorite_lesson.model;

import java.util.List;

public class FavoriteLessonService {
	
	private FavoriteLessonDAO_interface dao;
	
	public FavoriteLessonService() {
		dao = new FavoriteLessonJNDIDAO();
	}
	public FavoriteLessonVO addFavoriteLesson(String memberNo,String lessonNo) {
		FavoriteLessonVO favoriteLessonVO = new FavoriteLessonVO();
		favoriteLessonVO.setMemberNo(memberNo);
		favoriteLessonVO.setLessonNo(lessonNo);
		dao.insert(favoriteLessonVO);
		return favoriteLessonVO;
	}
	public void deleteFavoriteLesson(String lessonNo,String memberNo) {
		FavoriteLessonVO favoriteLessonVO = new FavoriteLessonVO();
		favoriteLessonVO.setLessonNo(lessonNo);
		favoriteLessonVO.setMemberNo(memberNo);
		dao.delete(favoriteLessonVO);
	}
	public List<FavoriteLessonVO> getMemberFavoriteLesson(String memberNo) {
		return dao.findByPrimaryKey(memberNo);
	}
	public List<FavoriteLessonVO> getAllFavoriteLesson() {
		return dao.getAll();
	}
}
