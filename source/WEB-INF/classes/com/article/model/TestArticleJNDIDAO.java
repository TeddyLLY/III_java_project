package com.article.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.history_video.model.HistoryVideoJDBCDAO;

/**
 * Servlet implementation class TestArticleJNDIDAO
 */
@WebServlet("/TestArticleJNDIDAO")
public class TestArticleJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestArticleJNDIDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ArticleJNDIDAO dao = new ArticleJNDIDAO();
		
		
//		// 新增
//		ArticleVO articleVO1 = new ArticleVO();
//		articleVO1.setArticle_title("測試");
//		articleVO1.setArticle_content("測試");
//		articleVO1.setArticle_glass("閒聊");
//		articleVO1.setArticle_visitors(50);
//		articleVO1.setArticle_replies(50);
//		articleVO1.setArticle_status(0);
//		articleVO1.setArticle_picture(null);
//		articleVO1.setArticle_post_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		articleVO1.setArticle_editart_lasttime(new java.sql.Date(new GregorianCalendar(2020,01,26).getTime().getTime()));
//		dao.insert(articleVO1);

		// 修改
//		ArticleVO articleVO2 = new ArticleVO();
//		articleVO2.setArticle_no("A001");
//		articleVO2.setArticle_title("測試");
//		articleVO2.setArticle_content("測試");
//		articleVO2.setArticle_glass("閒聊");
//		articleVO2.setArticle_visitors(50);
//		articleVO2.setArticle_replies(50);
//		articleVO2.setArticle_status(0);
//		articleVO2.setArticle_picture(null);
//		articleVO2.setArticle_post_time(new java.sql.Date(new GregorianCalendar(2020,01,26).getTime().getTime()));
//		articleVO2.setArticle_editart_lasttime(new java.sql.Date(new GregorianCalendar(2020,01,26).getTime().getTime()));
//		dao.update(articleVO2);
////
//		// 刪除
//		dao.delete("A006");
//
//		// 查詢
//		ArticleVO articleVO3 = dao.findByPrimaryKey("A005");
//		out.print(articleVO3.getArticle_no() + ",");
//		out.print(articleVO3.getArticle_title() + ",");
//		out.print(articleVO3.getArticle_content() + ",");
//		out.print(articleVO3.getArticle_glass() + ",");
//		out.print(articleVO3.getArticle_visitors() + ",");
//		out.print(articleVO3.getArticle_replies() + ",");
//		out.print(articleVO3.getArticle_status()+ ",");
//		out.print(articleVO3.getArticle_picture()+ ",");
//		out.print(articleVO3.getArticle_post_time()+ ",");
//		out.print(articleVO3.getArticle_editart_lasttime());
//		out.println("---------------------");
////
//		// 查詢
		
		List<ArticleVO> list = dao.query("1",null,null,null);
		for (ArticleVO aArticle : list) {
			out.print(aArticle.getArticle_no() + ",");
			out.print(aArticle.getArticle_title() + ",");
			out.print(aArticle.getArticle_content() + ",");
			out.print(aArticle.getArticle_glass() + ",");
			out.print(aArticle.getArticle_visitors() + ",");
			out.print(aArticle.getArticle_replies() + ",");
			out.print(aArticle.getArticle_status()+ ",");
			out.print(aArticle.getArticle_post_time()+ ",");
			out.print(aArticle.getArticle_editart_lasttime());
			out.println("123");
		
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
