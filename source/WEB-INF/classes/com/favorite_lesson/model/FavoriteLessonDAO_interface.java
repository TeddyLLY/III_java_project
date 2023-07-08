package com.favorite_lesson.model;
import java.util.*;


public interface FavoriteLessonDAO_interface {
	
	public void insert(FavoriteLessonVO favoriteLessonVO);
	//收藏課程tb無修改資料需求
	public void delete(FavoriteLessonVO favoriteLessonVO);
	public List<FavoriteLessonVO> findByPrimaryKey(String memberNo);
	public List<FavoriteLessonVO> getAll();

}
