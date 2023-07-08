package com.article.model;

import java.util.*;

import com.reply_article.model.ReplyArticleVO;

public interface ArticleDAO_interface {
	public void insert(ArticleVO articleVO);
    public void update(ArticleVO articleVO);
    public void delete(String article_no);
    public ArticleVO findByPrimaryKey(String article_no);
    public List<ArticleVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ArticleVO> getAll(Map<String, String[]> map); 
	public List<ArticleVO> query(String article_no, String article_title, String article_content,
			String article_glass);
	public List<ReplyArticleVO> getAllReplyByArticleNo(String article_no);
}
