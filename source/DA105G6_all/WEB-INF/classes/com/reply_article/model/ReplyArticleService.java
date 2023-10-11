package com.reply_article.model;

import java.sql.Timestamp;
import java.util.List;

import com.article.model.ArticleDAO_interface;
import com.article.model.ArticleJDBCDAO;
import com.article.model.ArticleVO;



public class ReplyArticleService {

	private ReplyArticleDAO_interface dao;

	public ReplyArticleService() {
		dao = new ReplyArticleJNDIDAO();
	}

	public ReplyArticleVO addReplyArticle(String reply_content,Timestamp reply_time,Timestamp last_updated,String article_no,String member_no,String coach_no) {

		ReplyArticleVO replyArticleVO = new ReplyArticleVO();

		replyArticleVO.setReply_content(reply_content);
		replyArticleVO.setReply_time(reply_time);
		replyArticleVO.setLast_updated(last_updated);
		replyArticleVO.setArticle_no(article_no);
		replyArticleVO.setMember_no(member_no);
		replyArticleVO.setCoach_no(coach_no);
		
		
		dao.insert(replyArticleVO);

		return replyArticleVO;
	}

	public ReplyArticleVO updateReplyArticle(String reply_no,String reply_content,Timestamp reply_time,Timestamp last_updated,String article_no,String member_no,String coach_no) {

		ReplyArticleVO replyArticleVO = new ReplyArticleVO();
		replyArticleVO.setReply_no(reply_no);
		replyArticleVO.setReply_content(reply_content);
		replyArticleVO.setReply_time(reply_time);
		replyArticleVO.setLast_updated(last_updated);
		replyArticleVO.setArticle_no(article_no);
		replyArticleVO.setMember_no(member_no);
		replyArticleVO.setCoach_no(coach_no);
		dao.update(replyArticleVO);
		
		return replyArticleVO;
	}
	

	public void deleteReplyArticle(String reply_no) {
		dao.delete(reply_no);
	}

	public ReplyArticleVO getOneReplyArticle(String reply_no) {
		return dao.findByPrimaryKey(reply_no);
	}

	public List<ReplyArticleVO> getAll() {
		return dao.getAll();
	}
	public List<ReplyArticleVO> getAllReplyByArticleNo(String article_no) {
		return dao.getAllReplyByArticleNo(article_no);
	}
}
