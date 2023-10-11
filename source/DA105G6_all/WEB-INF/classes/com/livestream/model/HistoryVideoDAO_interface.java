package com.livestream.model;

import java.util.List;

public interface HistoryVideoDAO_interface {
	public void insert(HistoryVideoVO history_videoVO);
	public void update(HistoryVideoVO history_videoVO);
	public void delete(String history_video_no);
	public HistoryVideoVO findByPrimaryKey(String history_video_no);
	public List<HistoryVideoVO> getAll();
}
