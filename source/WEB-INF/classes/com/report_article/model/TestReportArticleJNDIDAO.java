package com.report_article.model;

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
 * Servlet implementation class TestReportArticleJNDIDAO
 */
@WebServlet("/TestReportArticleJNDIDAO")
public class TestReportArticleJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestReportArticleJNDIDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ReportArticleJNDIDAO dao = new ReportArticleJNDIDAO();
		
//		// 新增
//		ReportArticleVO reportArticleVO = new ReportArticleVO();
//		reportArticleVO.setReport_article_content("111");
//		reportArticleVO.setReport_article_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		reportArticleVO.setReport_article_status(0);
//		reportArticleVO.setArticle_no("A002");
//		reportArticleVO.setMember_no("M003");
//		
//		dao.insert(reportArticleVO);
//
//		// 修改
//		ReportArticleVO reportArticleVO = new ReportArticleVO();
//		reportArticleVO.setReport_article_no("RA002");
//		reportArticleVO.setReport_article_content("吳永志2");
//		reportArticleVO.setReport_article_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		reportArticleVO.setReport_article_status(0);
//		reportArticleVO.setArticle_no("A001");
//		reportArticleVO.setMember_no("M005");
//		
//		dao.update(reportArticleVO);
//
//		// 刪除
//		dao.delete("RA002");
//
//		// 查詢
//		ReportArticleVO reportArticleVO = dao.findByPrimaryKey("RA001");
//		System.out.print(reportArticleVO.getReport_article_no() + ",");
//		System.out.print(reportArticleVO.getReport_article_content() + ",");
//		System.out.print(reportArticleVO.getReport_article_time() + ",");
//		System.out.print(reportArticleVO.getReport_article_status() + ",");
//		System.out.print(reportArticleVO.getArticle_no() + ",");
//		System.out.print(reportArticleVO.getMember_no() + ",");
//		
//		System.out.println("---------------------");
////
//		// 查詢
//		List<ReportArticleVO> list = dao.getAll();
//		for (ReportArticleVO aEmp : list) {
//			System.out.print(aEmp.getReport_article_no() + ",");
//			System.out.print(aEmp.getReport_article_content() + ",");
//			System.out.print(aEmp.getReport_article_time() + ",");
//			System.out.print(aEmp.getReport_article_status() + ",");
//			System.out.print(aEmp.getArticle_no() + ",");
//			System.out.print(aEmp.getMember_no() + ",");
//			
//			System.out.println();
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
