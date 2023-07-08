package com.product.model;

import java.sql.Date;

public class ProductVO implements java.io.Serializable{
	private String product_no;
	private String product_name;
	private byte[] product_photo;
	private Integer product_quantity;
	private Integer product_price;
	private Integer product_sales;
	private String product_content;
	private Integer product_total_evaluation;
	private Integer product_people_of_evaluation;
	private Integer product_status;
	private Integer product_average_evaluation;
	public ProductVO(){

    }
 
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public byte[] getProduct_photo() {
		return product_photo;
	}
	public void setProduct_photo(byte[] product_photo) {
		this.product_photo = product_photo;
	}
	public Integer getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(Integer product_quantity) {
		this.product_quantity = product_quantity;
	}
	public Integer getProduct_price() {
		return product_price;
	}
	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}
	public Integer getProduct_sales() {
		return product_sales;
	}
	public void setProduct_sales(Integer product_sales) {
		this.product_sales = product_sales;
	}
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	public Integer getProduct_total_evaluation() {
		return product_total_evaluation;
	}
	public void setProduct_total_evaluation(Integer product_total_evaluation) {
		this.product_total_evaluation = product_total_evaluation;
	}
	public Integer getProduct_people_of_evaluation() {
		return product_people_of_evaluation;
	}
	public void setProduct_people_of_evaluation(Integer product_people_of_evaluation) {
		this.product_people_of_evaluation = product_people_of_evaluation;
	}
	public Integer getProduct_status() {
		return product_status;
	}
	public void setProduct_status(Integer product_status) {
		this.product_status = product_status;
	}
	public Integer getProduct_average_evaluation() {
		return product_average_evaluation;
	}
	public void setProduct_average_evaluation(Integer product_average_evaluation) {
		this.product_average_evaluation = product_average_evaluation;
	}
	
	public ProductVO(String product_no, String product_name, byte[] product_photo, Integer product_quantity, Integer product_price, Integer product_sales, String product_content, Integer product_total_evaluation, Integer product_people_of_evaluation, Integer product_status, Integer product_average_evaluation) {
        this.product_no = product_no;
        this.product_name = product_name;
        this.product_photo = product_photo;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
        this.product_sales = product_sales;
        this.product_content = product_content;
        this.product_total_evaluation = product_total_evaluation;
        this.product_people_of_evaluation = product_people_of_evaluation;
        this.product_status = product_status;
        this.product_average_evaluation = product_average_evaluation;
    }
	
}
