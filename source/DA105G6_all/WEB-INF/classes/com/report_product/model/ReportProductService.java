package com.report_product.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public class ReportProductService {

	private ReportProductDAO_interface reportproductdao;

	public ReportProductService() {
		reportproductdao = new ReportProductDAO();
	}

	public ReportProductVO addReportProduct(String member_no, String product_no, String report_product_content) {

		ReportProductVO reportproductvo = new ReportProductVO();
		reportproductvo.setMember_no(member_no);
		reportproductvo.setProduct_no(product_no);
		reportproductvo.setReport_product_content(report_product_content);
		reportproductdao.insert(reportproductvo);
		return reportproductvo;
	}

	public ReportProductVO updateReportProduct(Integer report_product_status, String report_product_no) {
		ReportProductVO reportproductvo = new ReportProductVO();
		reportproductvo.setReport_product_status(report_product_status);
		reportproductvo.setReport_product_no(report_product_no);
		reportproductdao.update(reportproductvo);
		return reportproductvo;
	}

	public void deleteReportProduct(String report_product_no) {
		reportproductdao.delete(report_product_no);
	}

	public List<ReportProductVO> getAll() {
		return reportproductdao.getAll();
	}

	public ReportProductVO getOneReportProduct(String report_product_no) {
		return reportproductdao.findByPrimaryKey(report_product_no);
	}
}
