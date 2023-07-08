package com.appointment_order.controller;

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

import com.appointment_order.model.AppointmentOrderDAO;
import com.appointment_order.model.AppointmentOrderDAO_interface;
import com.appointment_order.model.AppointmentOrderVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class AppointmentOrderServletAndroid extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		AppointmentOrderDAO_interface dao = new AppointmentOrderDAO();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if ("getAll".equals(action)) {
			List<AppointmentOrderVO> appointOrderList = dao.getAll();
			writeText(res, gson.toJson(appointOrderList));
		} 
//		else if ("findByCategory".equals(action)) {
//			int cid = Integer.parseInt(jsonObject.get("cid").getAsString());
//			List<AppointmentOrderVO> bookList = dao.findByCategory(cid);
//			writeText(res, gson.toJson(bookList));
//			
//		}
		else if ("getCoachAppointment".equals(action)) {
			String coachNo = jsonObject.get("coachNo").getAsString();
			List<AppointmentOrderVO> appointOrderList = dao.getCoaAllAndroid(coachNo);
			writeText(res, gson.toJson(appointOrderList));
		}
		else if ("getMyList".equals(action)) {
			String memberNo = jsonObject.get("memberNo").getAsString();
			List<AppointmentOrderVO> appointOrderList = dao.getMemAllAndroid(memberNo);
			writeText(res, gson.toJson(appointOrderList));	
		} else {
			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AppointmentOrderDAO_interface dao = new AppointmentOrderDAO();
		List<AppointmentOrderVO> appointOrderList = dao.getAll();
		writeText(res, new Gson().toJson(appointOrderList));
	}

}
