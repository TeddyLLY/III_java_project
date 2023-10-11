package com.coach_schedule.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.coach_schedule.model.CoachScheduleService;
import com.coach_schedule.model.CoachScheduleVO;


public class CoachScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {  //ajax版
			
			PrintWriter out = res.getWriter();
			
			try {

				String coachNo = req.getParameter("coachNo");
				
				java.sql.Date coachDate = null;
				try {
					coachDate = java.sql.Date.valueOf(req.getParameter("coachDate").trim());
				} catch (IllegalArgumentException e) {
					out.print(e);
					return;
				}				
				


				/***************************2.開始新增資料***************************************/
				CoachScheduleService csSvc = new CoachScheduleService();
				csSvc.addCoachScheduleVO(coachNo, coachDate);
				
				out.print("schedule insert success");
				

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				out.print(e);

			}			
		}
		
		if("delete2".equals(action)) {  //ajax版
			
			PrintWriter out = res.getWriter();
			
			try {

				String coachNo = req.getParameter("coachNo");
				
				java.sql.Date coachDate = null;
				try {
					coachDate = java.sql.Date.valueOf(req.getParameter("coachDate").trim());
				} catch (IllegalArgumentException e) {
					out.print(e);
					return;
				}				
				


				/***************************2.開始新增資料***************************************/
				CoachScheduleService csSvc = new CoachScheduleService();
				csSvc.deleteOneCoachSchedule(coachNo, coachDate);
				out.print('0');
				

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				out.print(e);

			}			
		}		
		
		if("get_coach_schedule_ajax".equals(action)) {  //ajax版
			
			PrintWriter out = res.getWriter();
			
			try {

				String coachNo = req.getParameter("coachNo");
				

				/***************************2.開始新增資料***************************************/
				CoachScheduleService csSvc = new CoachScheduleService();
				List<CoachScheduleVO> cslist = csSvc.getCoachSchedule(coachNo);
				List<String> schedule = new LinkedList<String>();
				for(CoachScheduleVO csVO:cslist) {
					schedule.add("'"+csVO.getCoachDate()+"'");
				}
				
				Object[] array = schedule.toArray();
		        String s = Arrays.toString(array);
				out.print(s);
				

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				out.print(e);

			}			
		}		

	}

}
