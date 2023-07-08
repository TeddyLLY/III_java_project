package com.comment_product.model;

import java.util.List;


public interface CommentProductDAO_interface {
	public void insert(CommentProductVO commentproductVO);

	public void update(CommentProductVO commentproductVO);

	public void delete(String comment_product_no);

	public CommentProductVO findByPrimaryKey(String comment_product_no);

	public List<CommentProductVO> getAll();
}
