package com.product_order.model;
import java.util.List;

import com.product_order_detail.model.ProductOrderDetailVO;



public interface ProductOrderDAO_interface {
	public void insert(ProductOrderVO productorderVO);

	public void update(ProductOrderVO productorderVO);

	public void delete(String order_no);

	public ProductOrderVO findByPrimaryKey(String order_no);
	
	public List<ProductOrderVO> findByMemberKey(String member_no);

	public ProductOrderVO findByMemberOrderComplete(String member_no);

	public List<ProductOrderVO> getAll();
	
	public void insertWithProductOrderDetail(ProductOrderVO productorderVO , List<ProductOrderDetailVO> list);
	
	
	
	//Android
	public ProductOrderVO findById(String order_no);
	 
	public List<ProductOrderVO> getAll(String member_no, String start, String end);
	 
	public String addWithProductOrderDetail(ProductOrderVO productOrder, List<ProductOrderDetailVO> productOrderDetailList);
}
