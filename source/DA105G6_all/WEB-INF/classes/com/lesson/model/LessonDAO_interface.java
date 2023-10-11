package com.lesson.model;

import java.util.List;
import java.util.Map;

import com.lesson_detail.model.LessonDetailVO;

public interface LessonDAO_interface {
	public void insert(LessonVO lessonVO);

	public void update(LessonVO lessonVO);

	public void delete(String lessonNo);

	public void delete2(String lessonNo);// 刪除課程+課程明細

	public LessonVO findByPrimaryKey(String lessonNo);

	public List<LessonVO> getmAll(); // 所有課程詳情

	public List<LessonVO> getAll(); // 課程總覽(概覽&&不顯示過期課程)

	public List<LessonVO> gethistoryAll();

	public List<LessonVO> search(String string);

	public void insertWithDetails(LessonVO lessonVO, List<LessonDetailVO> list);

	public void update2(LessonVO lessonVO, List<LessonDetailVO> list);

	public List<LessonVO> getAll(Map<String, String[]> map); // 複合查詢

	public List<LessonVO> getLessonByCoach(String coachNo);

	public List<LessonVO> getLessonDateByCoach(String coachNo);


	public void updateLessonStatus(LessonVO lessonVO);


	// Android
	LessonVO findByNoForAndroid(String lessonNo);

	List<LessonVO> getAllForAndroid();

	byte[] getImage(String lessonNo);

}
