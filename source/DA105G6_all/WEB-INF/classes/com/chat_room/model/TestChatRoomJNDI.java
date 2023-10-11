package com.chat_room.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestChatRoomJNDI")
public class TestChatRoomJNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestChatRoomJNDI() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain; charset=UTF-8");
		  PrintWriter out = res.getWriter();
		  
		  ChatRoomJNDIDAO dao = new ChatRoomJNDIDAO();
		  
		  //新增
		  	ChatRoomVO vo1 = new ChatRoomVO();
		  	vo1.setChat_room_no("M002C001");
		  	vo1.setMember_no("M001");
		  	vo1.setCoach_no("C001");
		  	dao.insert(vo1);
		  	out.println("新增完成");
		 //delete
		  	dao.delete("M002C001");
		  	out.println("刪除完成");
		 //find by pk
//		  	out.println("--------find by pk start---------------");
//		  	ChatRoomVO vo2 = new ChatRoomVO();
//		  	vo2 = dao.findByPrimaryKey("M001C001");
//		  	out.println(vo2.getChat_room_no());
//			out.println(vo2.getMember_no());
//			out.println(vo2.getMember_no());
//			System.out.println("///////");
		 //find by coach 
		  	out.println("--------find by coach start---------------");
		  	List<ChatRoomVO> vo3= new ArrayList<ChatRoomVO>();
		  	vo3 = dao.getOneCoachAllRoom("C001");
		  	for(ChatRoomVO vo : vo3) {
		  		out.println(vo.getChat_room_no());
				out.println(vo.getMember_no());
				out.println(vo.getCoach_no());
				System.out.println("///////");
		  	}
			
			
		 //find by member
		  	out.println("--------find by member start---------------");
		  	List<ChatRoomVO> vo4= new ArrayList<ChatRoomVO>();
		  	vo4 = dao.getOneMemberAllRoom("M001");
		 	for(ChatRoomVO vo : vo4) {
		  		out.println(vo.getChat_room_no());
				out.println(vo.getMember_no());
				out.println(vo.getCoach_no());
				System.out.println("///////");
		  	}
	}

}
