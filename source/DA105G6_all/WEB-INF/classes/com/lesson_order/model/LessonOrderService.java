package com.lesson_order.model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lesson_detail.model.LessonDetailVO;
import com.member_lesson_detail.model.*;


public class LessonOrderService {
	private LessonOrderDAO_interface dao;
	private MemberLessonDetailDAO_interface dao3;
	
	public LessonOrderService() {
		dao = new LessonOrderJNDIDAO();
		dao3 = new MemberLessonDetailJNDIDAO();
	}
	
	public LessonOrderVO addLessonOrder(String memberNo,String lessonNo, Integer lessonPrice, Timestamp dateAcquisition, Integer lessonStatus) {
		LessonOrderVO lessonOrderVO = new LessonOrderVO();
		lessonOrderVO.setMemberNo(memberNo);
		lessonOrderVO.setLessonNo(lessonNo);
		lessonOrderVO.setLessonPrice(lessonPrice);
		lessonOrderVO.setDateAcquisition(dateAcquisition);
		lessonOrderVO.setLessonStatus(lessonStatus);//lesson-order-status
		dao.insert(lessonOrderVO);
		return lessonOrderVO;		
	}
	
	public LessonOrderVO addLessonOrder2(String memberNo,String lessonNo, Integer lessonPrice, Timestamp dateAcquisition, Integer lessonStatus, List<LessonDetailVO> ldVO) {
		LessonOrderVO lessonOrderVO = new LessonOrderVO();
		lessonOrderVO.setMemberNo(memberNo);
		lessonOrderVO.setLessonNo(lessonNo);
		lessonOrderVO.setLessonPrice(lessonPrice);
		lessonOrderVO.setDateAcquisition(dateAcquisition);
		lessonOrderVO.setLessonStatus(lessonStatus);//lesson-order-status
		
		List<LessonDetailVO> list = ldVO;
		
		dao.insert2(lessonOrderVO,list);
		return lessonOrderVO;		
	}
	
	public LessonOrderVO updateLessonOrder(String lessonOrderNo ,String memberNo,String lessonNo, Integer lessonPrice, Timestamp dateAcquisition, Integer lessonStatus) {
		LessonOrderVO lessonOrderVO = new LessonOrderVO();		
		lessonOrderVO.setLessonOrderNo(lessonOrderNo);
		lessonOrderVO.setMemberNo(memberNo);
		lessonOrderVO.setLessonNo(lessonNo);
		lessonOrderVO.setLessonPrice(lessonPrice);
		lessonOrderVO.setDateAcquisition(dateAcquisition);
		lessonOrderVO.setLessonStatus(lessonStatus);
		dao.update(lessonOrderVO);
		return lessonOrderVO;		
	}
	
	public LessonOrderVO updateLessonOrderStatus(String lessonOrderNo ,Integer lessonStatus) {
		LessonOrderVO lessonOrderVO1 = dao.findByPrimaryKey(lessonOrderNo);	
		LessonOrderVO lessonOrderVO = new LessonOrderVO();	
		lessonOrderVO1.setLessonOrderNo(lessonOrderNo);
		lessonOrderVO.setMemberNo(lessonOrderVO1.getMemberNo());
		lessonOrderVO.setLessonNo(lessonOrderVO1.getLessonNo());
		lessonOrderVO.setLessonPrice(lessonOrderVO1.getLessonPrice());
		lessonOrderVO.setDateAcquisition(lessonOrderVO1.getDateAcquisition());
		lessonOrderVO.setLessonStatus(lessonStatus);
		dao.update(lessonOrderVO);
		return lessonOrderVO;		
	}
	
	public void deleteLessonOrder(String lessonOrderNo) {
		dao.delete2(lessonOrderNo);
	}
	
	public void deleteLessonOrder2(String lessonOrderNo ,String memberNo,String lessonNo, Integer lessonPrice) {
		LessonOrderVO lessonOrderVO = new LessonOrderVO();		
		lessonOrderVO.setLessonOrderNo(lessonOrderNo);
		lessonOrderVO.setMemberNo(memberNo);
		lessonOrderVO.setLessonNo(lessonNo);
		lessonOrderVO.setLessonPrice(lessonPrice);
		dao.delete2(lessonOrderVO);
	}
	
	public LessonOrderVO getOneLessonOrder(String lessonOrderNo) {
		return dao.findByPrimaryKey(lessonOrderNo);
	}

	public String getOneLessonOrder_ajax(String lessonOrderNo) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String jsonStr = "";
		jsonStr = gson.toJson(dao.findByPrimaryKey(lessonOrderNo)).toString();
		return jsonStr;
	}
	
	public List<LessonOrderVO> getMemberAllLessonOrder(String memberNo) {
		return dao.getOneAll(memberNo);
	}
	public String getMemberAllLessonOrder_ajax(String memberNo) {
		String jsonStr = "";
		jsonStr = new JSONArray(dao.getOneAll(memberNo)).toString();
		return jsonStr;
	}
	public List<LessonOrderVO> getLessonJoinMember(String lessonNo) {
		return dao.getLessonJoinMember(lessonNo);
	}
	
	public void changeMemberLessonStatus(String lessonNo,Integer studentStatus,Integer orgstatus) {
		List<LessonOrderVO>leList = dao.getLessonJoinMember(lessonNo);

		for(LessonOrderVO lessonOrderVO:leList) {
			dao3.updateSTATUSTODAY(lessonOrderVO.getLessonOrderNo(),studentStatus,orgstatus);//原為0改為2		
		}
		
	}	
	
	public List<LessonOrderVO> getAll(){
		return dao.getAll();
	}
	public List<String> getLessonDateByMember(String memberNo) {
		MemberLessonDetailService mdSvc = new MemberLessonDetailService();
		List<LessonOrderVO>list =dao.getOneAll(memberNo);
		List<String> memLeDate = new LinkedList<String>();
		for(LessonOrderVO loVO:list) {
			List<MemberLessonDetailVO>ldlist=mdSvc.getDetailByLessonOrder(loVO.getLessonOrderNo());
			for(MemberLessonDetailVO ldVO:ldlist) {
				memLeDate.add("'"+ldVO.getLessonDate()+"'");
			}	
		}
		return memLeDate;
	}		
	
}
