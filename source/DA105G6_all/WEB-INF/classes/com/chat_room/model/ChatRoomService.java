package com.chat_room.model;

import java.util.List;

public class ChatRoomService {
	 
	private ChatRoomDAO_interface dao;
	
	public ChatRoomService() {
		dao = new ChatRoomJNDIDAO();
	}
	
	public ChatRoomVO insertChatRoom(String chat_room_no , String member_no , String coach_no) {
		ChatRoomVO chatRoomVO = new ChatRoomVO();
		chatRoomVO.setChat_room_no(chat_room_no);
		chatRoomVO.setMember_no(member_no);
		chatRoomVO.setCoach_no(coach_no);
		
		dao.insert(chatRoomVO);
		return chatRoomVO;
	}
	
	public void deleteChatRoom(String chat_room_no) {
		dao.delete(chat_room_no);
	}
	
	public ChatRoomVO findByPk(String chat_room_no) {
		return dao.findByPrimaryKey(chat_room_no);
	}
	
	public List<ChatRoomVO> findOneCoachAllRoom(String coach_no){
		return dao.getOneCoachAllRoom(coach_no);
	}
	
	public List<ChatRoomVO> findOneMemberAllRoom(String member_no){
		return dao.getOneMemberAllRoom(member_no);
	}
	
}
