package com.report_product.model;

import java.util.List;



public interface ReportProductDAO_interface {
	public void insert(ReportProductVO reportproductVO);

	public void update(ReportProductVO reportproductVO);

	public void delete(String report_product_no);

	public ReportProductVO findByPrimaryKey(String report_product_no);

	public List<ReportProductVO> getAll();
}
