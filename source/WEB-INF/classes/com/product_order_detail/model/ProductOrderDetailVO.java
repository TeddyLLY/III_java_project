package com.product_order_detail.model;

public class ProductOrderDetailVO implements java.io.Serializable{
	private String order_no;
	private String product_no;
	private Integer detail_unit_price;
	private Integer detail_qty;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result + ((detail_qty == null) ? 0 : detail_qty.hashCode());
//		result = prime * result + ((detail_unit_price == null) ? 0 : detail_unit_price.hashCode());
//		result = prime * result + ((order_no == null) ? 0 : order_no.hashCode());
		result = prime * result + ((product_no == null) ? 0 : product_no.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductOrderDetailVO other = (ProductOrderDetailVO) obj;
//		if (detail_qty == null) {
//			if (other.detail_qty != null)
//				return false;
//		} else if (!detail_qty.equals(other.detail_qty))
//			return false;
//		if (detail_unit_price == null) {
//			if (other.detail_unit_price != null)
//				return false;
//		} else if (!detail_unit_price.equals(other.detail_unit_price))
//			return false;
//		if (order_no == null) {
//			if (other.order_no != null)
//				return false;
//		} else if (!order_no.equals(other.order_no))
//			return false;
		if (product_no == null) {
			if (other.product_no != null)
				return false;
		} else if (!product_no.equals(other.product_no))
			return false;
		return true;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public Integer getDetail_unit_price() {
		return detail_unit_price;
	}
	public void setDetail_unit_price(Integer detail_unit_price) {
		this.detail_unit_price = detail_unit_price;
	}
	public Integer getDetail_qty() {
		return detail_qty;
	}
	public void setDetail_qty(Integer detail_qty) {
		this.detail_qty = detail_qty;
	}
	
	public ProductOrderDetailVO(){
		
	}
	
//	public ProductOrderDetailVO(String product_no,Integer detail_unit_price,Integer detail_qty){
//		
//		this.product_no=product_no;
//		this.detail_unit_price=detail_unit_price;
//		this.detail_qty=detail_qty;
//	}
}
