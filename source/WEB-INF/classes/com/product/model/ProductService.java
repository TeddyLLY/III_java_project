package com.product.model;

import java.util.List;

public class ProductService {
	private ProductDAO_interface productservicedao;

	public ProductService() {
		productservicedao = new ProductDAO();
	}

	public ProductVO addProduct(String product_name, byte[] product_photo, Integer product_quantity,
			Integer product_price, Integer product_sales, String product_content, Integer product_total_evaluation,
			Integer product_people_of_evaluation, Integer product_status, Integer product_average_evaluation) {

		ProductVO productvo = new ProductVO();
		productvo.setProduct_name(product_name);
		productvo.setProduct_photo(product_photo);
		productvo.setProduct_quantity(product_quantity);
		productvo.setProduct_price(product_price);
		productvo.setProduct_sales(product_sales);
		productvo.setProduct_content(product_content);
		productvo.setProduct_total_evaluation(product_total_evaluation);
		productvo.setProduct_people_of_evaluation(product_people_of_evaluation);
		productvo.setProduct_status(product_status);
		productvo.setProduct_average_evaluation(product_average_evaluation);
		productservicedao.insert(productvo);
		return productvo;
	}

	public ProductVO updateProduct(String product_name, byte[] product_photo, Integer product_quantity,
			Integer product_price, Integer product_sales, String product_content, Integer product_total_evaluation,
			Integer product_people_of_evaluation, Integer product_status, Integer product_average_evaluation, String product_no) {
		ProductVO productvo = new ProductVO();
		productvo.setProduct_name(product_name);
		productvo.setProduct_photo(product_photo);
		productvo.setProduct_quantity(product_quantity);
		productvo.setProduct_price(product_price);
		productvo.setProduct_sales(product_sales);
		productvo.setProduct_content(product_content);
		productvo.setProduct_total_evaluation(product_total_evaluation);
		productvo.setProduct_people_of_evaluation(product_people_of_evaluation);
		productvo.setProduct_status(product_status);
		productvo.setProduct_average_evaluation(product_average_evaluation);
		productvo.setProduct_no(product_no);
		productservicedao.update(productvo);
		return productvo;
	}
	
	public ProductVO updateRate(Integer product_total_evaluation, String product_no) {
		ProductVO productvo = new ProductVO();		
		productvo.setProduct_total_evaluation(product_total_evaluation);
		productvo.setProduct_no(product_no);
		System.out.println(88);
		productservicedao.updateRate(productvo);
		System.out.println(99);
		return productvo;
		
	}

	public void deleteProduct(String product_no) {
		productservicedao.delete(product_no);
	}

	public List<ProductVO> getAll() {
		return productservicedao.getAll();
	}
	
	public List<ProductVO> getAllDown() {
		return productservicedao.getAllDown();
	}
	
	public List<ProductVO> getAllStatus(){
		return productservicedao.getAllStatus();
	}

	public ProductVO getOneProduct(String product_no) {
		return productservicedao.findByPrimaryKey(product_no);
	}
	
	public String getOneProductName(String product_no) {
		return productservicedao.findByProductNo(product_no);
	}
	
	public Integer getPeopleTotal(String product_no) {
		return productservicedao.findPeopleTotal(product_no);
	}
	
	
}
