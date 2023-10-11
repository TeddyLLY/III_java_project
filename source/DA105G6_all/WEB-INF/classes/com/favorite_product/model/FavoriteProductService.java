package com.favorite_product.model;

import java.util.List;

import com.report_product.model.ReportProductVO;

public class FavoriteProductService {

	private FavoriteProductDAO_interface favoriteproductdao;

	public FavoriteProductService() {
		favoriteproductdao = new FavoriteProductDAO();
	}

	public FavoriteProductVO addFavoriteProduct(String member_no, String product_no) {
		FavoriteProductVO favoriteProductVO = new FavoriteProductVO();
		favoriteProductVO.setMember_no(member_no);
		favoriteProductVO.setProduct_no(product_no);
		favoriteproductdao.insert(favoriteProductVO);
		return favoriteProductVO;
	}

	public void deleteFavoriteProduct(String member_no, String product_no) {
		favoriteproductdao.delete(member_no, product_no);
	}

	public List<FavoriteProductVO> getAll() {
		return favoriteproductdao.getAll();
	}

	public List<FavoriteProductVO> findByPrimaryKey(String member_no) {
		return favoriteproductdao.findByPrimaryKey(member_no);
	}

}
