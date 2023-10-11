package com.coach.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coach.model.CoachDAO;
import com.coach.model.CoachDAO_interface;
import com.coach.model.CoachVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lesson.model.LessonVO;
import com.member.model.MemberDAO;
import com.member.model.MemberDAO_interface;
import com.member.model.MemberVO;

public class CoachServletAndroid extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		CoachDAO_interface coachDao = new CoachDAO();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if (action.equals("isCoach")) {
			String coachId = jsonObject.get("coachId").getAsString();
			String coachPsw = jsonObject.get("coachPsw").getAsString();
			writeText(res,	String.valueOf(coachDao.isCoach(coachId, coachPsw)));
		} else if(action.equals("getName")) {
			String coachNo = jsonObject.get("coach_no").getAsString();
			CoachVO coach = coachDao.findOneName(coachNo);
			writeText(res, gson.toJson(coach));
		} else if (action.equals("isUserIdExist")) {
			String userId = jsonObject.get("coachId").getAsString();
//			writeText(res, String.valueOf(coachDao.isCoachIdExist(userId)));
		} else if (action.equals("insert")) {
			MemberVO member = gson.fromJson(jsonObject.get("coach").getAsString(), MemberVO.class);
//			writeText(res, String.valueOf(coachDao.insertToAndroid(member)));
		} else if (action.equals("findById")) {
			String userId = jsonObject.get("coachId").getAsString();
			CoachVO coach = coachDao.findById(userId);
			writeText(res, coach == null ? "" : gson.toJson(coach));
		} else if (action.equals("update")) {
			MemberVO member = gson.fromJson(jsonObject.get("coach").getAsString(), MemberVO.class);
//			writeText(res, String.valueOf(coachDao.updateFromAndroid(member)));
		} 
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);

	}
}
