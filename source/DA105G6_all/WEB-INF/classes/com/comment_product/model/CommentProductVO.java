package com.comment_product.model;

import java.sql.Timestamp;

public class CommentProductVO implements java.io.Serializable{
	private String comment_product_no;
	private String member_no;
	private String product_no;
	private Integer product_star;
	private String comment_product_content;
	private Timestamp comment_product_time;
	public String getComment_product_no() {
		return comment_product_no;
	}
	public void setComment_product_no(String comment_product_no) {
		this.comment_product_no = comment_product_no;
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
	public Integer getProduct_star() {
		return product_star;
	}
	public void setProduct_star(Integer product_star) {
		this.product_star = product_star;
	}
	public String getComment_product_content() {
		return comment_product_content;
	}
	public void setComment_product_content(String comment_product_content) {
		this.comment_product_content = comment_product_content;
	}
	public Timestamp getComment_product_time() {
		return comment_product_time;
	}
	public void setComment_product_time(Timestamp comment_product_time) {
		this.comment_product_time = comment_product_time;
	}
	
	
}
