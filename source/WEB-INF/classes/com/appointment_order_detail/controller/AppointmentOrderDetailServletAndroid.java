package com.appointment_order_detail.controller;

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
import com.appointment_order_detail.model.AppointmentOrderDetailDAO;
import com.appointment_order_detail.model.AppointmentOrderDetailDAO_interface;
import com.appointment_order_detail.model.AppointmentOrderDetailVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.member.model.MemberVO;

public class AppointmentOrderDetailServletAndroid extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		AppointmentOrderDetailDAO_interface dao = new AppointmentOrderDetailDAO();
		Gson gson = new Gson();

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
			List<AppointmentOrderDetailVO> appointOrderDetailList = dao.getAll();
			writeText(res, gson.toJson(appointOrderDetailList));
		} else if (action.equals("findById")) {
			String userId = jsonObject.get("userId").getAsString();
			AppointmentOrderDetailVO appointOrderDetail = dao.findById(userId);
			writeText(res, appointOrderDetail == null ? "" : gson.toJson(appointOrderDetail));
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
