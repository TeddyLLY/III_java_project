package com.favorite_product.model;

import java.util.List;



public interface FavoriteProductDAO_interface {
	public void insert(FavoriteProductVO favoriteproductVO);

	public void update(FavoriteProductVO favoriteproductVO);

	public void delete(String member_no,String product_no);

	public List<FavoriteProductVO> findByPrimaryKey(String member_no);

	public List<FavoriteProductVO> getAll();
}
