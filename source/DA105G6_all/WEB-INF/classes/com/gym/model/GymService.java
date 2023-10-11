package com.gym.model;

import java.util.List;

public class GymService {
	
	private GymDAO_interface dao;
	
	public GymService() {
		dao = new GymJNDIDAO();
	}
	
	public GymVO addGym(String gym_name, String gym_latitude, String gym_longitude, String gym_address,String gym_content) {
		GymVO gymVO = new GymVO();
		gymVO.setGym_name(gym_name);
		gymVO.setGym_latitude(gym_latitude);
		gymVO.setGym_longitude(gym_longitude);
		gymVO.setGym_address(gym_address);
		gymVO.setGym_content(gym_content);
		dao.insert(gymVO);
		return gymVO;
	}
	
	public GymVO updateGym(String gym_no, String gym_name, String gym_latitude, String gym_longitude, String gym_address, String gym_content) {
		GymVO gymVO = new GymVO();
		gymVO.setGym_no(gym_no);
		gymVO.setGym_name(gym_name);
		gymVO.setGym_latitude(gym_latitude);
		gymVO.setGym_longitude(gym_longitude);
		gymVO.setGym_address(gym_address);
		gymVO.setGym_content(gym_content);
		dao.update(gymVO);
		return gymVO;
	}
	
	public void deleteGym(String gym_no) {
		dao.delete(gym_no);
	}
	
	public GymVO getOneGym(String gym_no) {
		return dao.findByPrimaryKey(gym_no);
	}
	
	public List<GymVO> getAll(){
		return dao.getAll();
	}
}
