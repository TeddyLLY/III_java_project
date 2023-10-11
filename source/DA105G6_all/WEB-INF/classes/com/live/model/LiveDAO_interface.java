package com.live.model;

import java.util.*;

public interface LiveDAO_interface {
	public void insert(LiveVO liveVO);
    public void update(LiveVO liveVO);
    public void delete(String live_no);
    public LiveVO findByPrimaryKey(String live_no);
    public List<LiveVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<LiveVO> getAll(Map<String, String[]> map); 
}
