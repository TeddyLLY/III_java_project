package com.news.model;

import java.util.List;

public class NewsService {
	
	private NewsDAO_interface dao;
	
	public NewsService() {
		dao = new NewsJNDIDAO();
	}
	
	public NewsVO addNews(String news_title, String news_content, java.sql.Date news_time) {
		
		NewsVO newsVO = new NewsVO();
		newsVO.setNews_title(news_title);
		newsVO.setNews_content(news_content);
		newsVO.setNews_time(news_time);
		dao.insert(newsVO);
		return newsVO;
	}
	
	public NewsVO updateNews(String news_no, String news_title, String news_content, java.sql.Date news_time) {
		NewsVO newsVO = new NewsVO();
		newsVO.setNews_no(news_no);
		newsVO.setNews_title(news_title);
		newsVO.setNews_content(news_content);
		newsVO.setNews_time(news_time);
		dao.update(newsVO);
		return newsVO;
	}
	
	public void deleteNews(String news_no) {
		dao.delete(news_no);
	}
	
	public NewsVO getOneNews(String news_no) {
		return dao.findByPrimaryKey(news_no);
	}
	
	public List<NewsVO> getAll() {
		return dao.getAll();
	}
}
