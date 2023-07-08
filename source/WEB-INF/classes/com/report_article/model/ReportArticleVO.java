package com.report_article.model;


import java.sql.Timestamp;



public class ReportArticleVO {
private String report_article_no;
private String report_article_content;
private Timestamp report_article_time;
private Integer report_article_status;
private String article_no;
private String member_no;
private String report_article_reason;

public String getReport_article_no() {
	return report_article_no;
}
public void setReport_article_no(String report_article_no) {
	this.report_article_no = report_article_no;
}
public String getReport_article_content() {
	return report_article_content;
}
public void setReport_article_content(String report_article_content) {
	this.report_article_content = report_article_content;
}
public Timestamp getReport_article_time() {
	return report_article_time;
}
public void setReport_article_time(Timestamp report_article_time) {
	this.report_article_time = report_article_time;
}
public Integer getReport_article_status() {
	return report_article_status;
}
public void setReport_article_status(Integer report_article_status) {
	this.report_article_status = report_article_status;
}
public String getArticle_no() {
	return article_no;
}
public void setArticle_no(String article_no) {
	this.article_no = article_no;
}
public String getMember_no() {
	return member_no;
}
public void setMember_no(String member_no) {
	this.member_no = member_no;
}
public String getReport_article_reason() {
	return report_article_reason;
}
public void setReport_article_reason(String report_article_reason) {
	this.report_article_reason = report_article_reason;
}

}
