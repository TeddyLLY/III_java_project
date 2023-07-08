package com.member.model;

import java.util.List;
import java.util.Map;

public interface MemberDAO_interface {

    public void insert(MemberVO MemberVO);
    public void update(MemberVO MemberVO);
    public void delete(String member_no);
    public MemberVO  findByPrimaryKey(String member_no);
    public List<MemberVO> getAll();
    public List<MemberVO> getAllDesc();
    
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<MemberVO> getAny(Map<String, String[]> map); 
    
  //* 查詢會員帳密
    public MemberVO findOneMemberAccount(String member_account ) ;
    
  //*修改單一會員 REVIEW  & auth
    public void updateOneStatus(MemberVO MemberVO);
  //查詢會員點數
    public MemberVO findOnePoints(String member_no) ;
    

    public void updatePoint(Integer memberPoint,String memberNo) ;
    
    
    
    
  //安卓特規方法-1
    boolean isMember(String member_account, String member_password);
    boolean isUserIdExist(String member_no);
   //安卓特規方法-2
    boolean insertToAndroid(MemberVO memberVO);
    boolean updateFromAndroid(MemberVO memberVO);
    boolean deleteFromAndroin(String member_no);
    MemberVO findById(String member_no);
    List<MemberVO> getAllFromAndroid();
    
    
    
    
}
