package com.product_order.model;

import java.sql.Date;
import java.util.List;

public class ProductOrderVO implements java.io.Serializable {
	private String order_no;
	private String member_no;
	private String order_recipient;
	private String order_address;
	private Date order_date;
	private Integer order_status;

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getOrder_recipient() {
		return order_recipient;
	}

	public void setOrder_recipient(String order_recipient) {
		this.order_recipient = order_recipient;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	private List<OrderProduct> orderProductList;

	public ProductOrderVO() {
		super();
	}

	public ProductOrderVO(String order_no, String member_no, String order_recipient, String order_address,
			Date order_date, Integer order_status, List<OrderProduct> orderProductList) {
		this.order_no = order_no;
		this.member_no = member_no;
		this.order_recipient = order_recipient;
		this.order_address = order_address;
		this.order_date = order_date;
		this.order_status = order_status;
		this.orderProductList = orderProductList;
	}

	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
}
