package com.chat_room.model;

import java.util.List;

public interface ChatRoomDAO_interface {
	
	public void  insert(ChatRoomVO chatRoomVO);
	public void delete(String chat_room_no);
	
	 public ChatRoomVO findByPrimaryKey(String chat_room_no);
	 
	 public List<ChatRoomVO> getOneCoachAllRoom( String coach_no);
	 public List<ChatRoomVO> getOneMemberAllRoom( String member_no);
	 
}
