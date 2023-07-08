package com.employee.model;

public class EmployeeVO implements java.io.Serializable{
	private String employee_no;
	private String employee_name;
	private byte[] employee_photo;
	private String employee_title;
	private String employee_account;
	private String employee_password;
	private String employee_mail;
	private String employee_cellphone;
	
	public byte[] getEmployee_photo() {
		return employee_photo;
	}
	public void setEmployee_photo(byte[] employee_photo) {
		this.employee_photo = employee_photo;
	}
	public String getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmployee_title() {
		return employee_title;
	}
	public void setEmployee_title(String employee_title) {
		this.employee_title = employee_title;
	}
	public String getEmployee_account() {
		return employee_account;
	}
	public void setEmployee_account(String employee_account) {
		this.employee_account = employee_account;
	}
	public String getEmployee_password() {
		return employee_password;
	}
	public void setEmployee_password(String employee_password) {
		this.employee_password = employee_password;
	}
	public String getEmployee_mail() {
		return employee_mail;
	}
	public void setEmployee_mail(String employee_mail) {
		this.employee_mail = employee_mail;
	}
	public String getEmployee_cellphone() {
		return employee_cellphone;
	}
	public void setEmployee_cellphone(String employee_cellphone) {
		this.employee_cellphone = employee_cellphone;
	}
	public String getRandomPassword() {
	    int z;
	    StringBuilder sb = new StringBuilder();
	    int i;
	    for (i = 0; i < 5; i++) {
	      z = (int) ((Math.random() * 7) % 3);
	 
	      if (z == 1) { // 放數字
	        sb.append((int) ((Math.random() * 10) + 48));
	      } else if (z == 2) { // 放大寫英文
	        sb.append((char) (((Math.random() * 26) + 65)));
	      } else {// 放小寫英文
	        sb.append(((char) ((Math.random() * 26) + 97)));
	      }
	    }
	    return sb.toString();
	  }
	
}
