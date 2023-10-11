package com.favorite_product.model;

public class FavoriteProductVO implements java.io.Serializable{
	private String product_no;
	private String member_no;
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product_no == null) ? 0 : product_no.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoriteProductVO other = (FavoriteProductVO) obj;
		if (product_no == null) {
			if (other.product_no != null)
				return false;
		} else if (!product_no.equals(other.product_no))
			return false;
		return true;
	}
	
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	
	
}
