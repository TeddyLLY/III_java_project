package com.member.model;

import java.util.List;
import java.util.Map;

public class MemberService {

	private MemberDAO_interface dao;	
	
	public MemberService() {
		super();
		dao = new MemberJNDIDAO();
	}
	
	
//新增會員
	public MemberVO memberInsert(String member_name ,String member_sex,String member_cellphone,String member_account
,String member_password,String member_address,byte[] member_photo,Integer member_points,Integer member_height
,Integer member_weight,Integer member_review , Integer member_auth,	Double member_bodyfat,String member_card , Integer member_wing_span) {
		MemberVO MemberVO = new MemberVO();
		MemberVO.setMember_name(member_name);
		MemberVO.setMember_sex(member_sex);
		MemberVO.setMember_cellphone(member_cellphone);
		MemberVO.setMember_account(member_account);
		MemberVO.setMember_password(member_password);
		MemberVO.setMember_address(member_address);
		MemberVO.setMember_photo(member_photo);
		MemberVO.setMember_points(member_points);
		MemberVO.setMember_height(member_height);
		MemberVO.setMember_weight(member_weight);
		MemberVO.setMember_review(member_review);
		MemberVO.setMember_auth(member_auth);
		MemberVO.setMember_bodyfat(member_bodyfat);
		MemberVO.setMember_card(member_card);
		MemberVO.setMember_wing_span(member_wing_span);
		dao.insert(MemberVO);
		return MemberVO;
	}
	
//修改單一會員	
	public MemberVO MemberUpdate(String member_sex ,String member_cellphone,String member_passward,String member_address,byte[] member_photo,Integer member_points,Integer member_height , Integer member_weight
,Integer member_review,Integer member_auth,Double member_bodyfat,String member_card,Integer member_wing_span,String member_no ) {
		MemberVO MemberVO = new MemberVO();
		MemberVO.setMember_sex(member_sex);
		MemberVO.setMember_cellphone(member_cellphone);
		MemberVO.setMember_password(member_passward);
		MemberVO.setMember_address(member_address);
		MemberVO.setMember_photo(member_photo);
		MemberVO.setMember_points(member_points);
		MemberVO.setMember_height(member_height);
		MemberVO.setMember_weight(member_weight);
		MemberVO.setMember_review(member_review);
		MemberVO.setMember_auth(member_auth);
		MemberVO.setMember_bodyfat(member_bodyfat);
		MemberVO.setMember_card(member_card);
		MemberVO.setMember_wing_span(member_wing_span);
		MemberVO.setMember_no(member_no);
		dao.update(MemberVO);
		return MemberVO;
	}
	
//刪除單一會員
	public void deleteMember(String member_no) {
		dao.delete(member_no);
	}
	
	
//查單一會員
	public MemberVO findOneMember(String member_no) {
		return dao.findByPrimaryKey(member_no);
	}
	
//查所有會員
	public List<MemberVO> findAllMember() {
		return	dao.getAll();
	}
//查所有會員Desc
	public List<MemberVO> findAllMemberDesc() {
		return	dao.getAllDesc();
	}

//萬用查詢
	public List<MemberVO> findQueryMember(Map<String, String[]> map){
		return dao.getAny(map);
	}
//*修改單一會員 REVIEW  & auth
	public MemberVO updateMemberStatus(Integer member_review ,Integer member_auth , String member_no) {
		MemberVO MemberVO = new MemberVO();
		MemberVO.setMember_review(member_review);
		MemberVO.setMember_auth(member_auth);
		MemberVO.setMember_no(member_no);
		dao.updateOneStatus(MemberVO);
		return MemberVO;
	}
	
//查詢會員點數	
	public MemberVO findOnePoints(String member_no) {
		return dao.findOnePoints(member_no);
	}
	
//查詢會員帳號
	public MemberVO findOneMemberAccount(String member_account) {
		return dao.findOneMemberAccount(member_account);
	}
	
	
}
