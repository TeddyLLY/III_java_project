package com.article.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ArticleVO implements java.io.Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String article_no;
private String article_title;
private String article_content;
private String article_glass;
private Integer article_visitors;
private Integer article_replies;
private Integer article_status;
private Timestamp article_post_time;
private Timestamp article_editart_lasttime;
private String coach_no;
private String member_no;
private byte[] article_picture;

public String getArticle_no() {
	return article_no;
}
public void setArticle_no(String article_no) {
	this.article_no = article_no;
}
public String getArticle_title() {
	return article_title;
}
public void setArticle_title(String article_title) {
	this.article_title = article_title;
}
public String getArticle_content() {
	return article_content;
}
public void setArticle_content(String article_content) {
	this.article_content = article_content;
}
public String getArticle_glass() {
	return article_glass;
}
public void setArticle_glass(String article_glass) {
	this.article_glass = article_glass;
}
public Integer getArticle_visitors() {
	return article_visitors;
}
public void setArticle_visitors(Integer article_visitors) {
	this.article_visitors = article_visitors;
}
public Integer getArticle_replies() {
	return article_replies;
}
public void setArticle_replies(Integer article_replies) {
	this.article_replies = article_replies;
}
public Integer getArticle_status() {
	return article_status;
}
public void setArticle_status(Integer article_status) {
	this.article_status = article_status;
}
public Timestamp getArticle_post_time() {
	return article_post_time;
}
public void setArticle_post_time(Timestamp article_post_time) {
	this.article_post_time = article_post_time;
}
public Timestamp getArticle_editart_lasttime() {
	return article_editart_lasttime;
}
public void setArticle_editart_lasttime(Timestamp article_editart_lasttime) {
	this.article_editart_lasttime = article_editart_lasttime;
}
public String getCoach_no() {
	return coach_no;
}
public void setCoach_no(String coach_no) {
	this.coach_no = coach_no;
}
public String getMember_no() {
	return member_no;
}
public void setMember_no(String member_no) {
	this.member_no = member_no;
}
public byte[] getArticle_picture() {
	return article_picture;
}
public void setArticle_picture(byte[] article_picture) {
	this.article_picture = article_picture;
}



}
