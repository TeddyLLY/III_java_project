package com.product_order_detail.model;

import java.sql.Connection;
import java.util.List;





public interface ProductOrderDetailDAO_interface {
	public void insert(ProductOrderDetailVO productorderdetailVO);

	public void update(ProductOrderDetailVO productorderdetailVO);

	public void delete(String order_no);

	public List<ProductOrderDetailVO> findByPrimaryKey(String order_no);
	
	public List<ProductOrderDetailVO> findByProductNo(String product_no);

	public List<ProductOrderDetailVO> getAll();
	
	public void insertOrderDetailByOrder (ProductOrderDetailVO productorderdetailVO , java.sql.Connection con);
	
	
	//Android
	public void addWithProductOrder(ProductOrderDetailVO productOrderDetail, Connection con);
	 
	public List<ProductOrderDetailVO> findByOrderId(String order_no);
}
