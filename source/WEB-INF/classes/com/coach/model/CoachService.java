package com.coach.model;

import java.util.List;
import java.util.Map;

public class CoachService {


	private CoachDAO_interface dao;
	
	public CoachService () {
		dao = new CoachJNDIDAO(); 
	}
	
	
		//新增單一教練
public CoachVO coachInsert(String coach_name ,String coach_sex , String coach_cellphone ,String coach_account,
	String	coach_password	, String coach_address, byte[] coach_photo ,Integer coach_total_evaluation ,Integer coach_total_people_evaluation ,
	Integer coach_review ,Integer coach_auth  ,Double coach_average_evaluation ,byte[] coach_license ,Integer coach_income ,
	String coach_introduction ,String  coach_bank_account  ) {
				CoachVO CoachVO= new CoachVO();
				CoachVO.setCoach_name(coach_name);
				CoachVO.setCoach_sex(coach_sex);
				CoachVO.setCoach_cellphone(coach_cellphone);
				CoachVO.setCoach_account(coach_account);
				CoachVO.setCoach_password(coach_password);
				CoachVO.setCoach_address(coach_address);
				CoachVO.setCoach_photo(coach_photo);
				CoachVO.setCoach_total_evaluation(coach_total_evaluation);
				CoachVO.setCoach_total_people_evaluation(coach_total_people_evaluation);
				CoachVO.setCoach_review(coach_review);
				CoachVO.setCoach_auth(coach_auth);
				CoachVO.setCoach_average_evaluation(coach_average_evaluation);
				CoachVO.setCoach_license(coach_license);
				CoachVO.setCoach_income(coach_income);
				CoachVO.setCoach_introduction(coach_introduction);
				CoachVO.setCoach_bank_account(coach_bank_account);
				
				dao.insert(CoachVO);
				return CoachVO; 
						
			}
			
		//修改單一教練
public  CoachVO coachUpdate(String coach_sex ,String coach_cellphone,String coach_passward,String coach_address ,byte[] coach_photo ,Integer coach_total_evaluation
	,Integer coach_total_people_evaluation,Integer coach_review,Integer coach_auth,Double coach_average_evaluation ,byte[] coach_license ,Integer coach_income
	,String coach_introduction ,String coach_bank_account ,String coach_no) {
				CoachVO CoachVO= new CoachVO();
				CoachVO.setCoach_sex(coach_sex);
				CoachVO.setCoach_cellphone(coach_cellphone);
				CoachVO.setCoach_password(coach_passward);
				CoachVO.setCoach_address(coach_address);
				CoachVO.setCoach_photo(coach_photo);
				CoachVO.setCoach_total_evaluation(coach_total_evaluation);
				CoachVO.setCoach_total_people_evaluation(coach_total_people_evaluation);
				CoachVO.setCoach_review(coach_review);
				CoachVO.setCoach_auth(coach_auth);
				CoachVO.setCoach_average_evaluation(coach_average_evaluation);
				CoachVO.setCoach_license(coach_license);
				CoachVO.setCoach_income(coach_income);
				CoachVO.setCoach_introduction(coach_introduction);
				CoachVO.setCoach_bank_account(coach_bank_account);
				CoachVO.setCoach_no(coach_no);		
				
				dao.update(CoachVO);
				return CoachVO;
			}

		////萬用查詢
			public List<CoachVO> findQueryCoach(Map<String , String[]> map){
				return dao.getAny(map);
			}
			
		//刪除單一教練
			public void coachDelete(String coach_no) {
				dao.delete(coach_no);
			}
		
		//查單一教練
			public CoachVO findOneCoach(String coach_no) {
				return dao.findByPrimaryKey(coach_no);
			}
	
		//查all教練
			public List<CoachVO> findAllCoach() {
				return dao.getAll();
			}
		//查all教練Desc
			public List<CoachVO> findAllCoachDesc() {
				return dao.getAllDesc();
			}
			
//**修改單一教練狀態  caoch_review coach_auth
			public CoachVO CoachUpdateStatus(Integer coach_review,Integer coach_auth,String coach_no) {
				CoachVO CoachVO = new CoachVO();
				CoachVO.setCoach_review(coach_review);
				CoachVO.setCoach_auth(coach_auth);
				CoachVO.setCoach_no(coach_no);
				dao.updateOneStatus(CoachVO);
				return CoachVO;
			}
//**查詢單一教練收益
			public CoachVO findOneCoachIncome(String coach_no){
				return dao.findOneIncome(coach_no);
			}

//查詢教練帳號
			public CoachVO findOneCoachAccount(String coach_account) {
				return dao.findOneCoachAccount(coach_account);
			}
			
//不必要//advance
//**查詢教練單筆收入(課程) JOIN 課程訂單 LESSON_ORDER ->  LESSON_PRICE	 LESSON_STATUS=0 課程狀態 : 0:已完成1:進行中 2:退課(報名截止日前可退)
//**查詢教練單筆收入 (預約) JOIN 預約訂單  APPOINTMENT_ORDER -> APPOINTMENT_STATUS  預約狀態 : 0:已完成(已上課) 1:進行中(已付款,未上課) 2:已取消(教練在會員付款前砍單) 3.申請調課

}
