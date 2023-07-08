package com.comment_product.model;

import java.sql.Timestamp;
import java.util.List;


public class CommentProductService {

	private CommentProductDAO_interface commentproductdao;

	public CommentProductService() {
		commentproductdao = new CommentProductDAO();
	}

	public CommentProductVO addCommentProduct(String member_no, String product_no, Integer product_star, String comment_product_content) {

		CommentProductVO commentproductvo = new CommentProductVO();
		commentproductvo.setMember_no(member_no);
		commentproductvo.setProduct_no(product_no);
		commentproductvo.setProduct_star(product_star);
		commentproductvo.setComment_product_content(comment_product_content);
		commentproductdao.insert(commentproductvo);
		return commentproductvo;
	}

	public CommentProductVO updateCommentProduct(Integer product_star, String comment_product_content, String comment_product_no) {
		CommentProductVO commentproductvo = new CommentProductVO();
		commentproductvo.setProduct_star(product_star);
		commentproductvo.setComment_product_content(comment_product_content);
		commentproductvo.setComment_product_no(comment_product_no);
		return commentproductvo;
	}

	public void deleteProductOrderDetail(String comment_product_no) {
		commentproductdao.delete(comment_product_no);
	}

	public List<CommentProductVO> getAll() {
		return commentproductdao.getAll();
	}

	public CommentProductVO getOneProductOrderDetail(String comment_product_no) {
		return commentproductdao.findByPrimaryKey(comment_product_no);
	}
}
