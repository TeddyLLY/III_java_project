package com.favorite_product.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestFavoriteProductJNDIDAO
 */
@WebServlet("/TestFavoriteProductJNDIDAO")
public class TestFavoriteProductJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestFavoriteProductJNDIDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FavoriteProductJNDIDAO favoriteproductjndidao = new FavoriteProductJNDIDAO();
		response.setContentType("text/olain; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// insert
		FavoriteProductVO favoriteproductvoinsert = new FavoriteProductVO();
		favoriteproductvoinsert.setMember_no("M001");
		favoriteproductvoinsert.setProduct_no("P001");
		favoriteproductjndidao.insert(favoriteproductvoinsert);

		// update
		FavoriteProductVO favoriteproductvoupdate = new FavoriteProductVO();
		favoriteproductvoupdate.setProduct_no("P002");
		favoriteproductvoupdate.setMember_no("M010");
//		
		favoriteproductjndidao.update(favoriteproductvoupdate);

		// delete
		favoriteproductjndidao.delete("M002","P001");

		// getOne
//		FavoriteProductVO favoriteproductvogetone = favoriteproductjndidao.findByPrimaryKey("M001");
//		out.print(favoriteproductvogetone.getMember_no()+ ",");
//		out.print(favoriteproductvogetone.getProduct_no());
//		out.println("---------------------");
////
////		// getAll
		List<FavoriteProductVO> list = favoriteproductjndidao.getAll();
		for (FavoriteProductVO afavoriteproductvo : list) {
			out.print(afavoriteproductvo.getMember_no() + ",");
			out.print(afavoriteproductvo.getProduct_no());
			out.println();

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
