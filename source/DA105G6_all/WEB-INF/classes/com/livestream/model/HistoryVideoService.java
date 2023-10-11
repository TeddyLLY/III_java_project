package com.livestream.model;

import java.sql.Timestamp;
import java.util.List;

public class HistoryVideoService {
	
	private HistoryVideoDAO_interface dao;
	
	public HistoryVideoService() {
		dao = new HistoryVideoDAO();
	}

	public HistoryVideoVO insert(Timestamp history_video_time, String title, byte[] history_video_content,String live_no,String coach_no) {
		
		HistoryVideoVO historyVideoVO=new HistoryVideoVO();
		historyVideoVO.setHistory_video_time(history_video_time);
		historyVideoVO.setHistory_video_title(title);
		historyVideoVO.setHistory_video_content(history_video_content);
		historyVideoVO.setCoach_no(coach_no);
		historyVideoVO.setLive_no(live_no);
		dao.insert(historyVideoVO);
		return historyVideoVO;
	}
	public HistoryVideoVO update(String history_video_no,Timestamp history_video_time, String title, byte[] history_video_content,String live_no,String coach_no) {
		
		HistoryVideoVO historyVideoVO=new HistoryVideoVO();
		historyVideoVO.setHistory_video_no(coach_no);
		historyVideoVO.setHistory_video_time(history_video_time);
		historyVideoVO.setHistory_video_title(title);
		historyVideoVO.setHistory_video_content(history_video_content);
		historyVideoVO.setCoach_no(coach_no);
		historyVideoVO.setLive_no(live_no);
		dao.update(historyVideoVO);
		return historyVideoVO;
		
	}
	public void delete(String history_video_no) {
		dao.delete(history_video_no);
		
	}
	public HistoryVideoVO findByPrimaryKey(String history_video_no) {
		return dao.findByPrimaryKey(history_video_no);
		
	}
	public List<HistoryVideoVO> getAll(){
		return dao.getAll();
		
	}
	public HistoryVideoVO update(HistoryVideoVO historyVideoVO) {
		dao.update(historyVideoVO);
		return historyVideoVO;
		
	}

}
