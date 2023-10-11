package com.report_article.model;

import java.util.*;

public interface ReportArticleDAO_interface {
	public void insert(ReportArticleVO reportArticleVO);
    public void update(ReportArticleVO reportArticleVO);
    public void delete(String report_article_no);
    public ReportArticleVO findByPrimaryKey(String report_article_no);
    public List<ReportArticleVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ReportArticleVO> getAll(Map<String, String[]> map); 
}
