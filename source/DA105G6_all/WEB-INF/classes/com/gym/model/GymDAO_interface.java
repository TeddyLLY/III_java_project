package com.gym.model;

import java.util.*;

public interface GymDAO_interface {
	 public void insert(GymVO gymVO);
     public void update(GymVO gymVO);
     public void delete(String gym_no);
     public GymVO findByPrimaryKey(String gym_no);
     public List<GymVO> getAll();

}
