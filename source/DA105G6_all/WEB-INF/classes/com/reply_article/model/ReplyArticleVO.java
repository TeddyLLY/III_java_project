package com.reply_article.model;

import java.sql.Timestamp;

public class ReplyArticleVO {
private String reply_no;
private String reply_content;
private Timestamp reply_time;
private Timestamp last_updated;
private String article_no;
private String member_no;
private String coach_no;

public String getReply_no() {
	return reply_no;
}
public void setReply_no(String reply_no) {
	this.reply_no = reply_no;
}
public String getReply_content() {
	return reply_content;
}
public void setReply_content(String reply_content) {
	this.reply_content = reply_content;
}
public Timestamp getReply_time() {
	return reply_time;
}
public void setReply_time(Timestamp reply_time) {
	this.reply_time = reply_time;
}
public Timestamp getLast_updated() {
	return last_updated;
}
public void setLast_updated(Timestamp last_updated) {
	this.last_updated = last_updated;
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
public String getCoach_no() {
	return coach_no;
}
public void setCoach_no(String coach_no) {
	this.coach_no = coach_no;
}

}
