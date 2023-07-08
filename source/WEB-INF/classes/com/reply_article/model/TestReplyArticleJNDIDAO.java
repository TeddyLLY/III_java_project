package com.reply_article.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.live.model.LiveJDBCDAO;

/**
 * Servlet implementation class TestReplyArticleJNDIDAO
 */
@WebServlet("/TestReplyArticleJNDIDAO")
public class TestReplyArticleJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestReplyArticleJNDIDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ReplyArticleJNDIDAO dao = new ReplyArticleJNDIDAO();
		
//		// 新增
//		ReplyArticleVO replyArticleVO = new ReplyArticleVO();
//		replyArticleVO.setReply_content("222");
//		replyArticleVO.setReply_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		replyArticleVO.setLast_updated(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		replyArticleVO.setArticle_no("A003");
//		replyArticleVO.setMember_no(null);
//		replyArticleVO.setCoach_no("C001");
//		dao.insert(replyArticleVO);
//
//		// 修改
//		ReplyArticleVO replyArticleVO = new ReplyArticleVO();
//		replyArticleVO.setReply_no("R003");
//		replyArticleVO.setReply_content("123");
//		replyArticleVO.setReply_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		replyArticleVO.setLast_updated(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		replyArticleVO.setArticle_no("A002");
//		replyArticleVO.setMember_no("M002");
//		replyArticleVO.setCoach_no(null);
//		dao.update(replyArticleVO);
//
//		// 刪除
//		dao.delete("R007");
//
//		// 查詢
//		ReplyArticleVO replyArticleVO = dao.findByPrimaryKey("R003");
//		out.print(replyArticleVO.getReply_no() + ",");
//		out.print(replyArticleVO.getReply_content() + ",");
//		out.print(replyArticleVO.getReply_time() + ",");
//		out.print(replyArticleVO.getLast_updated() + ",");
//		out.print(replyArticleVO.getArticle_no() + ",");
//		out.print(replyArticleVO.getMember_no() + ",");
//		out.println(replyArticleVO.getCoach_no());
//		out.println("---------------------");
//
//		// 查詢
//		List<ReplyArticleVO> list = dao.getAll();
//		for (ReplyArticleVO aEmp : list) {
//			out.print(aEmp.getReply_no() + ",");
//			out.print(aEmp.getReply_content() + ",");
//			out.print(aEmp.getReply_time() + ",");
//			out.print(aEmp.getLast_updated() + ",");
//			out.print(aEmp.getArticle_no() + ",");
//			out.print(aEmp.getMember_no() + ",");
//			out.print(aEmp.getCoach_no());
//			out.println();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
