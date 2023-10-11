package com.product_order.model;

import com.product.model.ProductVO;

public class OrderProduct extends ProductVO {
	private Integer quantity;
	private boolean isChecked;
	
		
	public OrderProduct(String product_no, String product_name, byte[] product_photo, Integer product_quantity, Integer product_price, Integer product_sales, String product_content, Integer product_total_evaluation, Integer product_people_of_evaluation, Integer product_status, Integer product_average_evaluation) {
		super(product_no, product_name, product_photo, product_quantity, product_price, product_sales, product_content, product_total_evaluation, product_people_of_evaluation, product_status, product_average_evaluation);
        this.quantity = quantity;
	}
	
	public OrderProduct (ProductVO product, Integer quantity) {
        this(product.getProduct_no(),product.getProduct_name(),product.getProduct_photo(),product.getProduct_quantity(),product.getProduct_price(),product.getProduct_sales(),product.getProduct_content(),product.getProduct_total_evaluation(),product.getProduct_people_of_evaluation(),product.getProduct_status(),product.getProduct_average_evaluation());
        this.quantity = quantity;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
