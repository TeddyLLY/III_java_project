package com.lesson_detail.model;
import java.util.List;



public interface LessonDetailDAO_interface {

    public void insert(LessonDetailVO lessonDetailVO);
    public void update(LessonDetailVO lessonDetailVO);
    public void deleteByLessonDetail(String lessonDetailNo); //隨lesson一起刪除(x)
    public void delete(String lessonNo);
    public LessonDetailVO findByPrimaryKey(String lessonDetailNo);
    public List<LessonDetailVO> findByLesson(String lessonNo);//查看單一課程
    public List<LessonDetailVO> getAll();  //查看所有課程的詳情
    
    public void insert2 (LessonDetailVO lessonDetailVO , java.sql.Connection con);//自增主鍵 with lesson
}
