package com.product.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import java.sql.Date;

/*
 * 註1: classpath必須有javax.persistence-api-2.2.jar 
 * 註2: Annotation可以添加在屬性上，也可以添加在getXxx()方法之上
 */


@Entity  //要加上@Entity才能成為JPA的一個Entity類別
@Table(name = "PRODUCT") //代表這個class是對應到資料庫的實體table，目前對應的table是PRODUCT
public class ProductVOH implements java.io.Serializable{
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
	
	public ProductVOH() { //必需有一個不傳參數建構子(JavaBean基本知識)
	}
	
	@Id //@Id代表這個屬性是這個Entity的唯一識別屬性，並且對映到Table的主鍵 
	@Column(name = "product_no")  //@Column指這個屬性是對應到資料庫Table的哪一個欄位   //【非必要，但當欄位名稱與屬性名稱不同時則一定要用】
	@SequenceGenerator(name="name1", sequenceName="product_no_seq", allocationSize=1) //1.先用@SequenceGenerator建立一個generator
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="name1")      //2.再用@GeneratedValue的generator屬性指定要用哪個generator //【strategy的GenerationType, 有四種值: AUTO, IDENTITY, SEQUENCE, TABLE】 
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
	
	
	
}
