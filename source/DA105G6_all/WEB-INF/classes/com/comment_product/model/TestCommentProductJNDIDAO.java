package com.comment_product.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestCommentProductJNDIDAO
 */
@WebServlet("/TestCommentProductJNDIDAO")
public class TestCommentProductJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestCommentProductJNDIDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		CommentProductJNDIDAO commentproductjndidao = new CommentProductJNDIDAO();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		// insert
		CommentProductVO commentproductvoinsert = new CommentProductVO();
		commentproductvoinsert.setMember_no("M001");
		commentproductvoinsert.setProduct_no("P001");
		commentproductvoinsert.setProduct_star(5);
		commentproductvoinsert.setComment_product_content("太好用了吧");
		commentproductvoinsert.setComment_product_time(time);
		
		
		commentproductjndidao.insert(commentproductvoinsert);

		// update

		CommentProductVO commentproductvoupdate = new CommentProductVO();
		commentproductvoupdate.setProduct_star(5);
		commentproductvoupdate.setComment_product_content("我要推薦我朋友太好用了");
		commentproductvoupdate.setComment_product_no("CP001");
		commentproductjndidao.update(commentproductvoupdate);

		// delete
//		commentproductjndidao.delete("CP003");

		// getOne
		CommentProductVO commentproductvogetone = commentproductjndidao.findByPrimaryKey("CP008");
		out.print(commentproductvogetone.getMember_no() + ",");	
		out.print(commentproductvogetone.getProduct_no() + ",");			
		out.print(commentproductvogetone.getProduct_star()+ ",");
		out.print(commentproductvogetone.getComment_product_content() + ",");
		out.print(commentproductvogetone.getComment_product_time());
		out.println("---------------------");
//
//		// getAll
		List<CommentProductVO> list = commentproductjndidao.getAll();
		for (CommentProductVO acommentproductvo : list) {
			out.print(acommentproductvo.getComment_product_no() + ",");
			out.print(acommentproductvo.getMember_no() + ",");
			out.print(acommentproductvo.getProduct_no() + ",");
			out.print(acommentproductvo.getProduct_star()+ ",");
			out.print(acommentproductvo.getComment_product_content() + ",");
			out.print(acommentproductvo.getComment_product_time());
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

}
