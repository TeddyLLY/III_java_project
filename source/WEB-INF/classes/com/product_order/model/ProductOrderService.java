package com.product_order.model;

import java.sql.Date;
import java.util.List;

import com.product_order_detail.model.ProductOrderDetailVO;

public class ProductOrderService {
	private ProductOrderDAO_interface productorderservicedao;

	public ProductOrderService() {
		productorderservicedao = new ProductOrderDAO();
	}

	public ProductOrderVO addProductOrder(String order_no, String member_no, String order_recipient, String order_address,
			Date order_date, Integer order_status) {

		ProductOrderVO productordervo = new ProductOrderVO();
		productordervo.setOrder_recipient(order_recipient);
		productordervo.setOrder_address(order_address);
		productordervo.setOrder_date(order_date);
		productordervo.setOrder_status(order_status);
		productorderservicedao.insert(productordervo);
		return productordervo;
	}

	public ProductOrderVO updateProductOrder(Integer order_status, String order_no) {
		ProductOrderVO productordervo = new ProductOrderVO();
		productordervo.setOrder_status(order_status);
		productordervo.setOrder_no(order_no);
		productorderservicedao.update(productordervo);
		return productordervo;
	}

	public void deleteProductOrder(String order_no) {
		productorderservicedao.delete(order_no);
	}

	public List<ProductOrderVO> getAll() {
		return productorderservicedao.getAll();
	}

	public ProductOrderVO getOneProductOrder(String order_no) {
		return productorderservicedao.findByPrimaryKey(order_no);
	}
	
	public List<ProductOrderVO> findByMemberKey(String member_no) {
		return productorderservicedao.findByMemberKey(member_no);
	}

	public ProductOrderVO findByMemberOrderComplete(String member_no) {
		return productorderservicedao.findByMemberOrderComplete(member_no);
	}
	
	public void insertWithProductOrderDetail(ProductOrderVO productorderVO, List<ProductOrderDetailVO> list) {
		productorderservicedao.insertWithProductOrderDetail(productorderVO, list);
	}
}
