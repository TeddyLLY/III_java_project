package com.coach_schedule.model;

import java.util.*;

public interface CoachScheduleDAO_interface {
	
	public void insert(CoachScheduleVO coachscheduleVO);
	public void update(CoachScheduleVO coachscheduleVO);
	public void delete(String coachScheduleNo);
	public CoachScheduleVO findByPrimaryKey(String coachScheduleNo);
	public List<CoachScheduleVO> getAll();
	public List<CoachScheduleVO> getCoachSchedule(String coachNo);
	void delete2(CoachScheduleVO coachscheduleVO);
	
}
