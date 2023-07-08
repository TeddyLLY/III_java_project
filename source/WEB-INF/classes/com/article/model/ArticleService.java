package com.article.model;

import java.sql.Timestamp;
import java.util.List;


import com.article.model.ArticleJDBCDAO;
import com.article.model.ArticleDAO_interface;
import com.article.model.ArticleVO;
import com.reply_article.model.ReplyArticleVO;


public class ArticleService {

	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleJNDIDAO();
	}

	public ArticleVO addArticle(String article_title, String article_content, String article_glass,
			Integer article_visitors, Integer article_replies, Integer article_status,Timestamp article_post_time,Timestamp article_editart_lasttime,String member_no,String coach_no,byte[] article_picture) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_glass(article_glass);
		articleVO.setArticle_visitors(article_visitors);
		articleVO.setArticle_replies(article_replies);
		articleVO.setArticle_status(article_status);
		articleVO.setArticle_post_time(article_post_time);
		articleVO.setArticle_editart_lasttime(article_editart_lasttime);
		articleVO.setMember_no(member_no);
		articleVO.setCoach_no(coach_no);
		articleVO.setArticle_picture(article_picture);
		dao.insert(articleVO);

		return articleVO;
	}

	public ArticleVO updateArticle(String article_no,String article_title, String article_content, String article_glass,
			Integer article_visitors, Integer article_replies, Integer article_status,Timestamp article_post_time,Timestamp article_editart_lasttime,String member_no,String coach_no,byte[] article_picture) {

		ArticleVO articleVO = new ArticleVO();
		articleVO.setArticle_no(article_no);
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_glass(article_glass);
		articleVO.setArticle_visitors(article_visitors);
		articleVO.setArticle_replies(article_replies);
		articleVO.setArticle_status(article_status);
		articleVO.setArticle_post_time(article_post_time);
		articleVO.setArticle_editart_lasttime(article_editart_lasttime);
		articleVO.setMember_no(member_no);
		articleVO.setCoach_no(coach_no);
		articleVO.setArticle_picture(article_picture);
		dao.update(articleVO);

		return articleVO;
	}

	public void deleteArticle(String article_no) {
		dao.delete(article_no);
	}

	public ArticleVO getOneArticle(String article_no) {
		return dao.findByPrimaryKey(article_no);
	}

	public List<ArticleVO> getAll() {
		return dao.getAll();
	}
	public List<ArticleVO> query(String article_no, String article_title, String article_content,
			String article_glass) {
		return dao.query(article_no, article_title, article_content, article_glass);
	}
	public List<ReplyArticleVO> getAllReplyByArticleNo(String article_no) {
		return dao.getAllReplyByArticleNo(article_no);
	}
}


















