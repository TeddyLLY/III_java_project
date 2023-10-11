package com.live.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.article.model.ArticleDAO_interface;
import com.article.model.ArticleJDBCDAO;
import com.article.model.ArticleVO;



public class LiveService {

	private LiveDAO_interface dao;

	public LiveService() {
		dao = new LiveJNDIDAO();
	}

	public LiveVO addLive(Date live_notice,Date live_time,String live_title,String live_imformation,String coach_no) {

		LiveVO liveVO = new LiveVO();

		liveVO.setLive_notice(live_notice);
		liveVO.setLive_time(live_time);
		liveVO.setLive_title(live_title);
		liveVO.setLive_imformation(live_imformation);
		liveVO.setCoach_no(coach_no);
		
		
		dao.insert(liveVO);

		return liveVO;
	}

	public LiveVO updateLive(String live_no,Date live_notice,Date live_time,String live_title,String live_imformation,String coach_no) {

		LiveVO liveVO = new LiveVO();
		liveVO.setLive_no(live_no);
		liveVO.setLive_notice(live_notice);
		liveVO.setLive_time(live_time);
		liveVO.setLive_title(live_title);
		liveVO.setLive_imformation(live_imformation);
		liveVO.setCoach_no(coach_no);
		dao.update(liveVO);

		return liveVO;
	}

	public void deleteLive(String live_no) {
		dao.delete(live_no);
	}

	public LiveVO getOneLive(String live_no) {
		return dao.findByPrimaryKey(live_no);
	}

	public List<LiveVO> getAll() {
		return dao.getAll();
	}
}
