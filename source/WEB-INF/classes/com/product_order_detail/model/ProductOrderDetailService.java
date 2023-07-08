package com.product_order_detail.model;

import java.sql.Connection;
import java.util.List;

public class ProductOrderDetailService {

	private ProductOrderDetailDAO_interface productorderdetailservicedao;

	public ProductOrderDetailService() {
		productorderdetailservicedao = new ProductOrderDetailDAO();
	}

	public ProductOrderDetailVO addProductorderDetail(String order_no, String product_no, Integer detail_unit_price,
			Integer detail_qty) {

		ProductOrderDetailVO productorderdetailvo = new ProductOrderDetailVO();
		productorderdetailvo.setOrder_no(order_no);
		productorderdetailvo.setProduct_no(product_no);
		productorderdetailvo.setDetail_unit_price(detail_unit_price);
		productorderdetailvo.setDetail_qty(detail_qty);
		return productorderdetailvo;
	}

	public ProductOrderDetailVO updateProductDetail(Integer detail_unit_price, Integer detail_qty, String order_no,
			String product_no) {
		ProductOrderDetailVO productorderdetailvo = new ProductOrderDetailVO();
		productorderdetailvo.setDetail_unit_price(detail_unit_price);
		productorderdetailvo.setDetail_qty(detail_qty);
		productorderdetailvo.setOrder_no(order_no);
		productorderdetailvo.setProduct_no(product_no);
		return productorderdetailvo;
	}

	public void deleteProductOrderDetail(String order_no) {
		productorderdetailservicedao.delete(order_no);
	}

	public List<ProductOrderDetailVO> getAll() {
		return productorderdetailservicedao.getAll();
	}

	public List<ProductOrderDetailVO> getOneProductOrderDetail(String order_no) {
		return productorderdetailservicedao.findByPrimaryKey(order_no);
	}
	
	public List<ProductOrderDetailVO> findByProductNo(String product_no){
		return productorderdetailservicedao.findByProductNo(product_no);
	}
	
	public void insertOrderDetailByOrder(ProductOrderDetailVO productorderdetailVO, Connection con) {
		productorderdetailservicedao.insertOrderDetailByOrder(productorderdetailVO, con);
	}
	
}



