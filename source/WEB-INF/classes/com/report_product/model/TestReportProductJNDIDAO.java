package com.report_product.model;

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
 * Servlet implementation class TestReportProductJNDIDAO
 */
@WebServlet("/TestReportProductJNDIDAO")
public class TestReportProductJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestReportProductJNDIDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ReportProductJNDIDAO reportproductjndidao = new ReportProductJNDIDAO();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		// insert
//		ReportProductVO reportproductvoinsert = new ReportProductVO();
//		reportproductvoinsert.setMember_no("M001");
//		reportproductvoinsert.setProduct_no("P001");
//		reportproductvoinsert.setReport_product_content("貴鬆鬆");
//		reportproductvoinsert.setReport_product_status(0);
//		reportproductvoinsert.setReport_product_time(time);
//		
//		reportproductjndidao.insert(reportproductvoinsert);

		// update

		ReportProductVO reportproductvoupdate = new ReportProductVO();
		reportproductvoupdate.setReport_product_content("打錯了");
		reportproductvoupdate.setReport_product_status(1);
		reportproductvoupdate.setReport_product_no("RP011");
		reportproductjndidao.update(reportproductvoupdate);

		// delete
		reportproductjndidao.delete("RP002");

		// getOne
//		ReportProductVO reportproductvogetone = reportproductjndidao.findByPrimaryKey("RP010");
//		out.print(reportproductvogetone.getMember_no() + ",");
//		out.print(reportproductvogetone.getProduct_no() + ",");
//		out.print(reportproductvogetone.getReport_product_content() + ",");
//		out.print(reportproductvogetone.getReport_product_status() + ",");
//		out.print(reportproductvogetone.getReport_product_time() );
//		out.println("---------------------");

		// getAll
//		List<ReportProductVO> list = reportproductjndidao.getAll();
//		for (ReportProductVO areportproductvo : list) {
//			out.print(areportproductvo.getReport_product_no() + ",");
//			out.print(areportproductvo.getMember_no() + ",");
//			out.print(areportproductvo.getProduct_no() + ",");
//			out.print(areportproductvo.getReport_product_content() + ",");
//			out.print(areportproductvo.getReport_product_status() + ",");
//			out.print(areportproductvo.getReport_product_time());
//			out.println();
//
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
