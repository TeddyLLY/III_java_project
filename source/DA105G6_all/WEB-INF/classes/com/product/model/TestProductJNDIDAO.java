package com.product.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProductJNDIDAO
 */
@WebServlet("/TestProductJNDIDAO")
public class TestProductJNDIDAO extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/olain; charset=UTF-8");
		PrintWriter out =response.getWriter();
		ProductJNDIDAO productjndidao = new ProductJNDIDAO();
		
		// insert
		ProductVO productvoinsert = new ProductVO();
		productvoinsert.setProduct_name("補碳健身餐");
		byte[] pic = getPictureByteArray("pic/p11.jpg");
		productvoinsert.setProduct_photo(pic);
		productvoinsert.setProduct_quantity(100);
		productvoinsert.setProduct_price(170);
		productvoinsert.setProduct_sales(100);
		productvoinsert.setProduct_content("世界");
		productvoinsert.setProduct_total_evaluation(800);
		productvoinsert.setProduct_people_of_evaluation(20);
		productvoinsert.setProduct_status(1);
		productvoinsert.setProduct_average_evaluation(4);

		productjndidao.insert(productvoinsert);
//
//		// update
		ProductVO productvoupdate = new ProductVO();
		productvoupdate.setProduct_name("吃飯");
		productvoupdate.setProduct_quantity(1);
		productvoupdate.setProduct_no("P008");

		productjndidao.update(productvoupdate);
//
//		// delete
//		productjndidao.delete("P004");

		// getOne
		ProductVO productVOgetone = productjndidao.findByPrimaryKey("P001");
		out.print(productVOgetone.getProduct_name() + ",");
		out.print(productVOgetone.getProduct_photo() + ",");
		out.print(productVOgetone.getProduct_quantity() + ",");
		out.print(productVOgetone.getProduct_price() + ",");
		out.print(productVOgetone.getProduct_sales() + ",");
		out.print(productVOgetone.getProduct_content() + ",");
		out.print(productVOgetone.getProduct_total_evaluation() + ",");
		out.print(productVOgetone.getProduct_people_of_evaluation() + ",");
		out.print(productVOgetone.getProduct_status() + ",");
		out.print(productVOgetone.getProduct_average_evaluation());
		out.println("---------------------");

		// getAll
		List<ProductVO> list = productjndidao.getAll();
		for (ProductVO aProduct : list) {
			out.print(aProduct.getProduct_no() + ",");
			out.print(aProduct.getProduct_name() + ",");
			out.print(aProduct.getProduct_photo() + ",");
			out.print(aProduct.getProduct_quantity() + ",");
			out.print(aProduct.getProduct_price() + ",");
			out.print(aProduct.getProduct_sales() + ",");
			out.print(aProduct.getProduct_content() + ",");
			out.print(aProduct.getProduct_total_evaluation() + ",");
			out.print(aProduct.getProduct_people_of_evaluation() + ",");
			out.print(aProduct.getProduct_status() + ",");
			out.print(aProduct.getProduct_average_evaluation());

			out.println();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
}
