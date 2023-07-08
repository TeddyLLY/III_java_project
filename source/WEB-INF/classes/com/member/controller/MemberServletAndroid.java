package com.member.controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.member.model.MemberDAO;
import com.member.model.MemberDAO_interface;
import com.member.model.MemberJDBCDAO;
import com.member.model.MemberVO;

public class MemberServletAndroid extends HttpServlet {
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
		MemberDAO_interface memberDao = new MemberDAO();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if (action.equals("isMember")) {
			String userId = jsonObject.get("userId").getAsString();
			String password = jsonObject.get("password").getAsString();
			writeText(res,	String.valueOf(memberDao.isMember(userId, password)));
		} else if (action.equals("isUserIdExist")) {
			String userId = jsonObject.get("userId").getAsString();
			writeText(res, String.valueOf(memberDao.isUserIdExist(userId)));
		} else if (action.equals("insert")) {
			MemberVO member = gson.fromJson(jsonObject.get("member").getAsString(), MemberVO.class);
			writeText(res, String.valueOf(memberDao.insertToAndroid(member)));
		} else if (action.equals("findById")) {
			String userId = jsonObject.get("userId").getAsString();
			MemberVO member = memberDao.findById(userId);
			writeText(res, member == null ? "" : gson.toJson(member));
		} else if (action.equals("update")) {
			MemberVO member = gson.fromJson(jsonObject.get("member").getAsString(), MemberVO.class);
			writeText(res, String.valueOf(memberDao.updateFromAndroid(member)));
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
