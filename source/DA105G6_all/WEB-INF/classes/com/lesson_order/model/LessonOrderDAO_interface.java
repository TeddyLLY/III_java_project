package com.lesson_order.model;

import java.util.List;

import com.lesson_detail.model.LessonDetailVO;

public interface LessonOrderDAO_interface {

    public void insert(LessonOrderVO lessonOrderVO);
    public void update(LessonOrderVO lessonOrderVO);
    public void delete(String lessonOrderNo);
    public void delete2(LessonOrderVO lessonOrderVO); //刪除課程與課程下所有明細
    public LessonOrderVO findByPrimaryKey(String lessonOrderNo);//查看單一訂單
    public List<LessonOrderVO> getAll();  //查看所有會員訂單
    public List<LessonOrderVO> getOneAll(String memberNo);  //查看單一會員所有訂單
	public void insert2(LessonOrderVO lessonOrderVO, List<LessonDetailVO> list);
	public List<LessonOrderVO> getLessonJoinMember(String lessonNo);
	void delete2(String lessonOrderNo);
}
