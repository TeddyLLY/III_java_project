package com.chat_room.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ChatRoomJNDIDAO  implements ChatRoomDAO_interface{
       
    public ChatRoomJNDIDAO() {
        super();
    }

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT =
  "INSERT INTO CHAT_ROOM (CHAT_ROOM_NO, MEMBER_NO , COACH_NO )"
+ "VALUES(               ?   	      , ?         ,?  )";
	
	private static final String DELETE= 
"DELETE FROM CHAT_ROOM WHERE CHAT_ROOM_NO =?";
	
	private static final String FIND_BY_PK= 
"SELECT * FROM CHAT_ROOM WHERE CHAT_ROOM_NO =? ";	
	
	private static final String FIND_ONE_COACH_ALL_ROOM=
"SELECT * FROM CHAT_ROOM WHERE COACH_NO =? ";
	
	private static final String FIND_ONE_MEMBER_ALL_ROOM= 
"SELECT * FROM CHAT_ROOM WHERE MEMBER_NO =?";
	
	@Override
	public void insert(ChatRoomVO chatRoomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			 con.setAutoCommit(false);  
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, chatRoomVO.getChat_room_no());
			pstmt.setString(2, chatRoomVO.getMember_no());
			pstmt.setString(3, chatRoomVO.getCoach_no());
			
			pstmt.executeUpdate();
			con.commit();
		}catch (SQLException e) {
			throw new RuntimeException("CoachDao insert error . " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}
		} // finally end
	}

	@Override
	public void delete(String chat_room_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			 con.setAutoCommit(false);  
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chat_room_no);

			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException("CoachDao delete error . " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}
		} // finally end
	}

	@Override
	public ChatRoomVO findByPrimaryKey(String chat_room_no) {
		ChatRoomVO chatRoomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, chat_room_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				 chatRoomVO = new ChatRoomVO();
				 chatRoomVO.setChat_room_no(rs.getString("CHAT_ROOM_NO"));
				 chatRoomVO.setMember_no(rs.getString("MEMBER_NO"));
				 chatRoomVO.setCoach_no(rs.getString("COACH_NO"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("A findByPrimaryKey error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		} // finally END
		return chatRoomVO;

	}

	@Override
	public List<ChatRoomVO> getOneCoachAllRoom(String coach_no) {
		List<ChatRoomVO> list = new ArrayList<>();
		ChatRoomVO chatRoomVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ONE_COACH_ALL_ROOM);
			pstmt.setString(1, coach_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				chatRoomVO = new ChatRoomVO();
				chatRoomVO.setChat_room_no(rs.getString("CHAT_ROOM_NO"));
				chatRoomVO.setMember_no(rs.getString("MEMBER_NO"));
				chatRoomVO.setCoach_no(rs.getString("COACH_NO"));
				list.add(chatRoomVO);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
			
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<ChatRoomVO> getOneMemberAllRoom(String member_no) {
		List<ChatRoomVO> list = new ArrayList<>();
		ChatRoomVO chatRoomVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ONE_MEMBER_ALL_ROOM);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				chatRoomVO = new ChatRoomVO();
				chatRoomVO.setChat_room_no(rs.getString("CHAT_ROOM_NO"));
				chatRoomVO.setMember_no(rs.getString("MEMBER_NO"));
				chatRoomVO.setCoach_no(rs.getString("COACH_NO"));
				list.add(chatRoomVO);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
			
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
