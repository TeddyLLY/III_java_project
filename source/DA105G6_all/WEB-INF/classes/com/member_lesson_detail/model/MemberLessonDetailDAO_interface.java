package com.member_lesson_detail.model;

import java.util.List;


public interface MemberLessonDetailDAO_interface {

    public void insert(MemberLessonDetailVO memberLessonDetailVO);
    public void update(MemberLessonDetailVO memberLessonDetailVO);
    public void updateSTATUSTODAY(String lessonOrderNo,Integer studentStatus,Integer orgstatus);
    public void updateMYSTATUS(String memberLessonDetailNo,Integer studentStatus);
    public void delete(String memberLessonDetailNo);
    public void delete2(String lessonOrderNo); //刪除明細(依訂單編號)
    public MemberLessonDetailVO findByPrimaryKey(String memberLessonDetailNo);//查看單一訂單明細
    public List<MemberLessonDetailVO> getAll();  //查看所有訂單明細
    public List<MemberLessonDetailVO> getOneAll(String lessonOrderNo);  //查看單一訂單所有明細

}
