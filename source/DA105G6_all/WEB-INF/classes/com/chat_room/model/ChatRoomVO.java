package com.chat_room.model;


public class ChatRoomVO {
	
	private String chat_room_no;

	private String member_no;
	private String coach_no;
	
	public ChatRoomVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public String getChat_room_no() {
		return chat_room_no;
	}

	public void setChat_room_no(String chat_room_no) {
		this.chat_room_no = chat_room_no;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getCoach_no() {
		return coach_no;
	}

	public void setCoach_no(String coach_no) {
		this.coach_no = coach_no;
	}
	
}
