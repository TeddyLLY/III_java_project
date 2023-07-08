package com.report_article.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.article.model.ArticleDAO_interface;
import com.article.model.ArticleJDBCDAO;
import com.article.model.ArticleVO;



public class ReportArticleService {

	private ReportArticleDAO_interface dao;

	public ReportArticleService() {
		dao = new ReportArticleJNDIDAO();
	}

	public ReportArticleVO addReportArticle(String report_article_content,Timestamp report_article_time,Integer report_article_status,String article_no,String member_no,String report_article_reason) {

		ReportArticleVO reportArticleVO = new ReportArticleVO();

		reportArticleVO.setReport_article_content(report_article_content);
		reportArticleVO.setReport_article_time(report_article_time);
		reportArticleVO.setReport_article_status(report_article_status);
		reportArticleVO.setArticle_no(article_no);
		reportArticleVO.setMember_no(member_no);
		reportArticleVO.setReport_article_reason(report_article_reason);
		
		dao.insert(reportArticleVO);

		return reportArticleVO;
	}

	public ReportArticleVO updateReportArticle(String report_article_no,String report_article_content,Timestamp report_article_time,Integer report_article_status,String article_no,String member_no,String report_article_reason) {

		ReportArticleVO reportArticleVO = new ReportArticleVO();
		reportArticleVO.setReport_article_no(report_article_no);
		reportArticleVO.setReport_article_content(report_article_content);
		reportArticleVO.setReport_article_time(report_article_time);
		reportArticleVO.setReport_article_status(report_article_status);
		reportArticleVO.setArticle_no(article_no);
		reportArticleVO.setMember_no(member_no);
		reportArticleVO.setReport_article_reason(report_article_reason);
		dao.update(reportArticleVO);

		return reportArticleVO;
	}

	public void deleteReportArticle(String article_no) {
		dao.delete(article_no);
	}

	public ReportArticleVO getOneReportArticle(String article_no) {
		return dao.findByPrimaryKey(article_no);
	}

	public List<ReportArticleVO> getAll() {
		return dao.getAll();
	}
}
