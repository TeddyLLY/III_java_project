package com.report_product.model;
import java.sql.Timestamp;
public class ReportProductVO implements java.io.Serializable{
	private String report_product_no;
	private String member_no;
	private String product_no;
	private String report_product_content;
	private Integer report_product_status;
	private Timestamp report_product_time;
	
	public String getReport_product_no() {
		return report_product_no;
	}
	public void setReport_product_no(String report_product_no) {
		this.report_product_no = report_product_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getReport_product_content() {
		return report_product_content;
	}
	public void setReport_product_content(String report_product_content) {
		this.report_product_content = report_product_content;
	}
	public Integer getReport_product_status() {
		return report_product_status;
	}
	public void setReport_product_status(Integer report_product_status) {
		this.report_product_status = report_product_status;
	}
	public Timestamp getReport_product_time() {
		return report_product_time;
	}
	public void setReport_product_time(Timestamp report_product_time) {
		this.report_product_time = report_product_time;
	}
	
	
}
