package com.product.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.product.model.ProductService;
import com.product.model.ProductVO;

public class DBGifReader4 extends HttpServlet {

	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String product_no= req.getParameter("product_no");
			ProductService productSvc = new ProductService();
			ProductVO ProductVO = productSvc.getOneProduct(product_no);
			out.write(ProductVO.getProduct_photo());
				
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	

}