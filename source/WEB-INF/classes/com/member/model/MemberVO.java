package com.member.model;

public class MemberVO implements java.io.Serializable {
	private String member_no;
	private String member_name;
	private String member_sex;
	private String member_cellphone; 
	private String member_account;
	private String member_password;
	private String member_address;
	private byte[] member_photo;
	private Integer member_points;
	private Integer member_height;
	private Integer member_weight;
	private Integer member_review;
	private Integer member_auth;
	private Double member_bodyfat;
	private String member_card;
	private Integer member_wing_span;
	
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
//---------------------------------------------
	public String getMember_card() {
		return member_card;
	}
	public void setMember_card(String member_card) {
		this.member_card = member_card;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_sex() {
		return member_sex;
	}
	public void setMember_sex(String member_sex) {
		this.member_sex = member_sex;
	}
	public String getMember_cellphone() {
		return member_cellphone;
	}
	public void setMember_cellphone(String member_cellphone) {
		this.member_cellphone = member_cellphone;
	}
	public String getMember_account() {
		return member_account;
	}
	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public byte[] getMember_photo() {
		return member_photo;
	}
	public void setMember_photo(byte[] member_photo) {
		this.member_photo = member_photo;
	}
	public Integer getMember_points() {
		return member_points;
	}
	public void setMember_points(Integer member_points) {
		this.member_points = member_points;
	}
	public Integer getMember_height() {
		return member_height;
	}
	public void setMember_height(Integer member_height) {
		this.member_height = member_height;
	}
	public Integer getMember_weight() {
		return member_weight;
	}
	public void setMember_weight(Integer member_weight) {
		this.member_weight = member_weight;
	}
	public Integer getMember_review() {
		return member_review;
	}
	public void setMember_review(Integer member_review) {
		this.member_review = member_review;
	}
	public Integer getMember_auth() {
		return member_auth;
	}
	public void setMember_auth(Integer member_auth) {
		this.member_auth = member_auth;
	}
	public Double getMember_bodyfat() {
		return member_bodyfat;
	}
	public void setMember_bodyfat(Double member_bodyfat) {
		this.member_bodyfat = member_bodyfat;
	}
	public Integer getMember_wing_span() {
		return member_wing_span;
	}
	public void setMember_wing_span(Integer member_wing_span) {
		this.member_wing_span = member_wing_span;
	}
	
}
