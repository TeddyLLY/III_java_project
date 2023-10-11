package com.product.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.product.model.ProductDAO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductJDBCDAO;
import com.product.model.ProductVO;
import com.toolclass.ImageUtil;



public class ProductServletAndroid extends HttpServlet {
private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		ProductDAO_interface dao = new ProductDAO();
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
			List<ProductVO> productList = dao.getAllFromAndroid();
			writeText(res, gson.toJson(productList));
//		} else if ("findByCategory".equals(action)) {
//			int cid = Integer.parseInt(jsonObject.get("cid").getAsString());
//			List<ProductVO> productList = dao.findByCategory(cid);
//			writeText(res, gson.toJson(productList));
		} else if ("getFive".equals(action)){
			List<ProductVO> productList = dao.getfiveFromAndroid();
			writeText(res, gson.toJson(productList));
			// 圖片請求
		} else if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String product_no = jsonObject.get("product_no").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = dao.getImage(product_no);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			} else {
				
			};
			os.write(image);

		} else {
			writeText(res, "");
		}

		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		ProductDAO_interface dao = new ProductDAO();
		List<ProductVO> productList = dao.getAllFromAndroid();
		writeText(res, new Gson().toJson(productList));
	}
	
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

}
