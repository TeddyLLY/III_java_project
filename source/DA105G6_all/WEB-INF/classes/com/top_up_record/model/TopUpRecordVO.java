package com.top_up_record.model;

import java.sql.Timestamp;

public class TopUpRecordVO implements java.io.Serializable{
	private String top_up_record_no;
	private String member_no;
	private Timestamp record_time;
	private Integer money_record;
	
	
	public TopUpRecordVO() {
		super();
		// TODO Auto-generated constructor stub
	}

//------------------------------------------------------------

	public String getTop_up_record_no() {
		return top_up_record_no;
	}


	public void setTop_up_record_no(String top_up_record_no) {
		this.top_up_record_no = top_up_record_no;
	}


	public String getMember_no() {
		return member_no;
	}


	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}


	public Timestamp getRecord_time() {
		return record_time;
	}


	public void setRecord_time(Timestamp record_time) {
		this.record_time = record_time;
	}


	public Integer getMoney_record() {
		return money_record;
	}


	public void setMoney_record(Integer money_record) {
		this.money_record = money_record;
	}
	
//----------------------------------------------------
	
	
	
}
