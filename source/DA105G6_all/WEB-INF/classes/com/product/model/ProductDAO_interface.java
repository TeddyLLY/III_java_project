package com.product.model;

import java.util.List;

import com.product_order.model.ProductOrderVO;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);

	public void update(ProductVO productVO);

	public void updateRate(ProductVO productVO);

	public void delete(String product_no);

	public List<ProductVO> getAll();

	public List<ProductVO> getAllDown();

	public List<ProductVO> getAllStatus();

	public ProductVO findByPrimaryKey(String product_no);

	public String findByProductNo(String product_no);

	public Integer findPeopleTotal(String product_no);

	public void updatepic(ProductVO productVO);

	// Android
	public ProductVO findByProNo(String product_no);

	public List<ProductVO> getAllFromAndroid();

	byte[] getImage(String product_no);

	public List<ProductVO> getfiveFromAndroid();
}
