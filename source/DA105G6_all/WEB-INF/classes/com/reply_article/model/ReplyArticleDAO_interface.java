package com.reply_article.model;

import java.util.*;

public interface ReplyArticleDAO_interface {
	public void insert(ReplyArticleVO replyArticleVO);
    public void update(ReplyArticleVO replyArticleVO);
    public void delete(String reply_no);
    public ReplyArticleVO findByPrimaryKey(String reply_no);
    public List<ReplyArticleVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ReplyArticleVO> getAll(Map<String, String[]> map); 
    public List<ReplyArticleVO> getAllReplyByArticleNo(String article_no);
}
