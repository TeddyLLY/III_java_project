package com.lesson.controller;



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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lesson.model.LessonDAO;
import com.lesson.model.LessonDAO_interface;
import com.lesson.model.LessonVO;
import com.toolclass.ImageUtil;

public class LessonServletAndroid extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		LessonDAO_interface dao = new LessonDAO();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		

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
			List<LessonVO> lessonList = dao.getAllForAndroid();
			writeText(res, gson.toJson(lessonList));
		} 
//		else if ("findByCategory".equals(action)) {
//			int cid = Integer.parseInt(jsonObject.get("cid").getAsString());
//			List<LessonVO> bookList = dao.findByCategory(cid);
//			writeText(res, gson.toJson(bookList));
//			// 圖片請求
//		}
		  else if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String lessonNo = jsonObject.get("lesson_no").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = dao.getImage(lessonNo);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			os.write(image);

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
		LessonDAO_interface dao = new LessonDAO();
		List<LessonVO> lessonList = dao.getAll();
		writeText(res, new Gson().toJson(lessonList));
	}

}
