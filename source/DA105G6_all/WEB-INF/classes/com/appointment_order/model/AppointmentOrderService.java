package com.appointment_order.model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import com.appointment_order_detail.model.AppointmentOrderDetailService;
import com.appointment_order_detail.model.AppointmentOrderDetailVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.model.*;

public class AppointmentOrderService {

	private AppointmentOrderDAO_interface dao;
	private MemberDAO_interface dao2;
	
	
	public AppointmentOrderService() {
		dao = new AppointmentOrderJNDIDAO();
	}
	
	  
	public AppointmentOrderVO addAppointmentOrderVO(String memberNo, String coachNo, Integer appointmentPrice, Timestamp orderTime, Integer appointmentStatus, String appointmentLocation, String appointmentDemand) {
		
		AppointmentOrderVO appointmentOrderVO = new AppointmentOrderVO();
		appointmentOrderVO.setMemberNo(memberNo);
		appointmentOrderVO.setCoachNo(coachNo);
		appointmentOrderVO.setAppointmentPrice(appointmentPrice);
		appointmentOrderVO.setOrderTime(orderTime);
		appointmentOrderVO.setAppointmentStatus(appointmentStatus);
		appointmentOrderVO.setAppointmentLocation(appointmentLocation);
		appointmentOrderVO.setAppointmentDemand(appointmentDemand);
		dao.insert(appointmentOrderVO);
		
		return appointmentOrderVO;
		
	};
	
	public AppointmentOrderVO updateAppointmentOrder (String appointmentOrderNo, String memberNo, String coachNo, Integer appointmentPrice, Timestamp orderTime, Integer appointmentStatus, String appointmentLocation, String appointmentDemand) {
		AppointmentOrderVO appointmentOrderVO = new AppointmentOrderVO();
		appointmentOrderVO.setAppointmentOrderNo(appointmentOrderNo);
		appointmentOrderVO.setMemberNo(memberNo);
		appointmentOrderVO.setCoachNo(coachNo);
		appointmentOrderVO.setAppointmentPrice(appointmentPrice);
		appointmentOrderVO.setOrderTime(orderTime);
		appointmentOrderVO.setAppointmentStatus(appointmentStatus);
		appointmentOrderVO.setAppointmentLocation(appointmentLocation);
		appointmentOrderVO.setAppointmentDemand(appointmentDemand);
		dao.update(appointmentOrderVO);
		
		return appointmentOrderVO;
	}
	
	public void deleteAppointmentOrder(String appointmentOrderNo) {
		dao.delete(appointmentOrderNo);
	}
	
	public String getOneAppointmentOrder(String appointmentOrderNo) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String jsonStr = "";
		jsonStr = gson.toJson(dao.findByPrimaryKey(appointmentOrderNo)).toString();
		return jsonStr;
	}
	public AppointmentOrderVO getOneAppointmentOrder2(String appointmentOrderNo) {
		return dao.findByPrimaryKey(appointmentOrderNo);
	}
	
	public List<AppointmentOrderVO> getAll(){
		return dao.getAll();
	}

	public List<AppointmentOrderVO> getMemAll(String memberNo){
		return dao.getMemAll(memberNo);
	}
	
	public List<AppointmentOrderVO> getCoachAll(String coachNo){
		return dao.getCoachAll(coachNo);
	}

	public List<String> getCoachCertainDate(String coachNo){
		AppointmentOrderDetailService apdSvc = new AppointmentOrderDetailService();
		List<AppointmentOrderVO> aplist=dao.getCoachCertainDate(coachNo);
		List<String> coapDate = new LinkedList<String>();
		for(AppointmentOrderVO apVO:aplist){
			List<AppointmentOrderDetailVO>apdlist=apdSvc.getDetailByAppointmentOrder(apVO.getAppointmentOrderNo());
			for(AppointmentOrderDetailVO apdVO:apdlist) {
				coapDate.add("'"+apdVO.getAppointmentDate()+"'");
			}						
		}
		return coapDate;
	}	

	public String getCoachCertainDate2(String coachNo,String apdate){
		MemberService memSvc = new MemberService();
		AppointmentOrderDetailService apdSvc = new AppointmentOrderDetailService();
		List<AppointmentOrderVO> aplist=dao.getCoachCertainDate(coachNo);
		String memberNo;
		for(AppointmentOrderVO apVO:aplist){
			List<AppointmentOrderDetailVO>apdlist=apdSvc.getDetailByAppointmentOrder(apVO.getAppointmentOrderNo());
			for(AppointmentOrderDetailVO apdVO:apdlist) {
				if(apdate.equals(apdVO.getAppointmentDate().toString())) {
					memberNo=apVO.getMemberNo();
					MemberVO memVO =memSvc.findOneMember(memberNo);
					return  memVO.getMember_name().toUpperCase();
				}
				
			}						
		}
		return "no_data";
	}	
	
	public List<String> getMemCertainDate(String memberNo){
		AppointmentOrderDetailService apdSvc = new AppointmentOrderDetailService();
		List<AppointmentOrderVO> aplist=dao.getMemberCertainDate(memberNo);
		List<String> memapDate = new LinkedList<String>();
		for(AppointmentOrderVO apVO:aplist){
			List<AppointmentOrderDetailVO>apdlist=apdSvc.getDetailByAppointmentOrder(apVO.getAppointmentOrderNo());
			for(AppointmentOrderDetailVO apdVO:apdlist) {
				memapDate.add("'"+apdVO.getAppointmentDate()+"'");
			}						
		}
		return memapDate;
	}	
	public List<AppointmentOrderDetailVO> getMemCertainDate2(String memberNo){
		AppointmentOrderDetailService apdSvc = new AppointmentOrderDetailService();
		List<AppointmentOrderVO> aplist=dao.getMemberCertainDate(memberNo);
		
		
		List<AppointmentOrderDetailVO> memapDate = new LinkedList<AppointmentOrderDetailVO>();
		for(AppointmentOrderVO apVO:aplist){
			for(AppointmentOrderDetailVO apdVO:apdSvc.getDetailByAppointmentOrder(apVO.getAppointmentOrderNo())) {
				memapDate.add(apdVO);
			}				
		}
		return memapDate;
	}		
	
	
	public AppointmentOrderVO updatePrice(String appointmentOrderNo,Integer appointmentPrice,Integer appointmentStatus){
		AppointmentOrderVO appointmentOrderVO = new AppointmentOrderVO();
		appointmentOrderVO.setAppointmentOrderNo(appointmentOrderNo);
		appointmentOrderVO.setAppointmentPrice(appointmentPrice);
		appointmentOrderVO.setAppointmentStatus(appointmentStatus);
		dao.updatePrice(appointmentOrderVO);
		 return appointmentOrderVO;
	}

	public void updatePrice2(String appointmentOrderNo,Integer appointmentPrice,Integer appointmentStatus,String memberNo, Integer memPoint){//預約扣款用
		AppointmentOrderVO appointmentOrderVO = new AppointmentOrderVO();
		appointmentOrderVO.setAppointmentOrderNo(appointmentOrderNo);
		appointmentOrderVO.setAppointmentPrice(appointmentPrice);
		appointmentOrderVO.setAppointmentStatus(appointmentStatus);
		appointmentOrderVO.setAppointmentStatus(appointmentStatus);
		dao.updatePrice(appointmentOrderVO);
		dao2 =new MemberDAO();
		dao2.updatePoint(memPoint, memberNo);
	}

	public AppointmentOrderVO addAppointmentOrderXDetail(String memberNo, String coachNo, Integer appointmentPrice,
			Timestamp orderTime, Integer appointmentStatus, String appointmentLocation, String appointmentDemand,
			List<AppointmentOrderDetailVO> aodVO) {

		AppointmentOrderVO appointmentOrderVO = new AppointmentOrderVO();
		appointmentOrderVO.setMemberNo(memberNo);
		appointmentOrderVO.setCoachNo(coachNo);
		appointmentOrderVO.setAppointmentDemand(appointmentDemand);
		appointmentOrderVO.setAppointmentLocation(appointmentLocation);
		appointmentOrderVO.setAppointmentPrice(appointmentPrice);
		appointmentOrderVO.setAppointmentStatus(appointmentStatus);
		appointmentOrderVO.setOrderTime(orderTime);
		appointmentOrderVO.setAppointmentDemand(appointmentDemand);
		List<AppointmentOrderDetailVO> list = aodVO;
		
		dao.insert2(appointmentOrderVO,list);
		return null;
	}	
	
}
