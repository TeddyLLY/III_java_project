package com.coach.model;

public class CoachVO implements java.io.Serializable{
	
	private String coach_no;
	private String coach_name;
	private String coach_sex;
	private String coach_cellphone;
	private String coach_account;
	private String coach_password;
	private String coach_address;
	private byte[] coach_photo;
	private Integer coach_total_evaluation;
	private Integer coach_total_people_evaluation;
	private Integer coach_review;
	private Integer coach_auth;
	private Double coach_average_evaluation;
	private byte[] coach_license;
	private int coach_income;
	private String coach_introduction;
	private String coach_bank_account;
	

	public CoachVO() {
		super();
		// TODO Auto-generated constructor stub
	}

//-------------------------------------------------------
	
	public String getCoach_bank_account() {
		return coach_bank_account;
	}

	public void setCoach_bank_account(String coach_bank_account) {
		this.coach_bank_account = coach_bank_account;
	}

	public  String getCoach_no() {
		return coach_no;
	}


	public void setCoach_no(String coach_no) {
		this.coach_no = coach_no;
	}


	public String getCoach_name() {
		return coach_name;
	}


	public void setCoach_name(String coach_name) {
		this.coach_name = coach_name;
	}


	public String getCoach_sex() {
		return coach_sex;
	}


	public void setCoach_sex(String coach_sex) {
		this.coach_sex = coach_sex;
	}


	public String getCoach_cellphone() {
		return coach_cellphone;
	}


	public void setCoach_cellphone(String coach_cellphone) {
		this.coach_cellphone = coach_cellphone;
	}


	public String getCoach_account() {
		return coach_account;
	}


	public void setCoach_account(String coach_account) {
		this.coach_account = coach_account;
	}


	public String getCoach_password() {
		return coach_password;
	}


	public void setCoach_password(String coach_password) {
		this.coach_password = coach_password;
	}


	public String getCoach_address() {
		return coach_address;
	}


	public void setCoach_address(String coach_address) {
		this.coach_address = coach_address;
	}


	public byte[] getCoach_photo() {
		return coach_photo;
	}


	public void setCoach_photo(byte[] coach_photo) {
		this.coach_photo = coach_photo;
	}


	public Integer getCoach_total_evaluation() {
		return coach_total_evaluation;
	}


	public void setCoach_total_evaluation(Integer coach_total_evaluation) {
		this.coach_total_evaluation = coach_total_evaluation;
	}


	public Integer getCoach_total_people_evaluation() {
		return coach_total_people_evaluation;
	}


	public void setCoach_total_people_evaluation(Integer coach_total_people_evaluation) {
		this.coach_total_people_evaluation = coach_total_people_evaluation;
	}


	public Integer getCoach_review() {
		return coach_review;
	}


	public void setCoach_review(Integer coach_review) {
		this.coach_review = coach_review;
	}


	public Integer getCoach_auth() {
		return coach_auth;
	}


	public void setCoach_auth(Integer coach_auth) {
		this.coach_auth = coach_auth;
	}


	public Double getCoach_average_evaluation() {
		return coach_average_evaluation;
	}


	public void setCoach_average_evaluation(Double coach_average_evaluation) {
		this.coach_average_evaluation = coach_average_evaluation;
	}


	public byte[] getCoach_license() {
		return coach_license;
	}


	public void setCoach_license(byte[] coach_license) {
		this.coach_license = coach_license;
	}


	public int getCoach_income() {
		return coach_income;
	}


	public void setCoach_income(int coach_income) {
		this.coach_income = coach_income;
	}


	public String getCoach_introduction() {
		return coach_introduction;
	}


	public void setCoach_introduction(String coach_introduction) {
		this.coach_introduction = coach_introduction;
	}
	
//-----------------------------------------------
	
	
	
	
}
