package com.top_up_record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.report_coach.model.ReportCoachVO;


public class TopUpRecordJDBCDAO implements TopUpRecordDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
"INSERT INTO TOP_UP_RECORD(TOP_UP_RECORD_NO                      ,MEMBER_NO ,RECORD_TIME ,MONEY_RECORD)"
+"VALUES( 'T'||LPAD(TO_CHAR(TOP_UP_RECORD_NO_SEQ.NEXTVAL),3,'0') ,?         ,?           , ? )";

	private static final String GET_ALL_STMT =
"SELECT TOP_UP_RECORD_NO , MEMBER_NO ,RECORD_TIME ,MONEY_RECORD FROM TOP_UP_RECORD ORDER BY RECORD_TIME DESC";
	private static final String GET_ONE_STMT =
"SELECT TOP_UP_RECORD_NO , MEMBER_NO ,RECORD_TIME ,MONEY_RECORD FROM TOP_UP_RECORD WHERE TOP_UP_RECORD_NO=? ORDER BY RECORD_TIME DESC";	
	private static final String DELETE =
"DELETE FROM TOP_UP_RECORD WHERE TOP_UP_RECORD_NO=?";
	private static final String UPDATE =
"UPDATE TOP_UP_RECORD SET MEMBER_NO=? ,RECORD_TIME=? ,MONEY_RECORD=? WHERE TOP_UP_RECORD_NO=?";
	private static final String FIND_ONE_MEMBER_NO =
"SELECT TOP_UP_RECORD_NO, MEMBER_NO ,RECORD_TIME, MONEY_RECORD FROM TOP_UP_RECORD WHERE MEMBER_NO=? ORDER BY RECORD_TIME DESC";
	

	
	public static void main(String[] args) {
		TopUpRecordJDBCDAO dao = new TopUpRecordJDBCDAO();
		Timestamp time= new Timestamp(System.currentTimeMillis());
		
		//新增
		TopUpRecordVO vo1 = new TopUpRecordVO();
		vo1.setMember_no("M001");
		vo1.setRecord_time(time);
		vo1.setMoney_record(6600);
		dao.insert(vo1);
//		//刪除
//		dao.delete("T002");
//		//修改
//		TopUpRecordVO vo2 = new TopUpRecordVO();
//		vo2.setMember_no("M001");
//		vo2.setRecord_time(time);
//		vo2.setMoney_record(548700);
//		vo2.setTop_up_record_no("T001");
//		dao.update(vo2);
//		//查詢pk
		TopUpRecordVO vo3 = new TopUpRecordVO();
		vo3 = dao.findByPrimaryKey("T008");
		System.out.println(vo3.getTop_up_record_no());
		System.out.println(vo3.getMember_no());
		System.out.println(vo3.getRecord_time());
		System.out.println(vo3.getMoney_record());
		System.out.println("----------------------------------------");
		
		//查詢one member
		List<TopUpRecordVO> list1 = dao.findOneMemberRecord("M001");
			
				for(TopUpRecordVO vo6 :list1) {
				System.out.println(vo6.getTop_up_record_no());
				System.out.println(vo6.getMember_no());
				System.out.println(vo6.getRecord_time());
				System.out.println(vo6.getMoney_record());
				System.out.println("----------------------------------------");
				}
			//查詢all
		List<TopUpRecordVO> list = dao.getAll();
		for(TopUpRecordVO vo4 :list) {
			System.out.println(vo4.getTop_up_record_no());
			System.out.println(vo4.getMember_no());
			System.out.println(vo4.getRecord_time());
			System.out.println(vo4.getMoney_record());
			System.out.println("-->");
		}
//		
	}
	
	@Override
	public List<TopUpRecordVO> findOneMemberRecord(String member_no) {
		List<TopUpRecordVO> list = new ArrayList<TopUpRecordVO>();
		TopUpRecordVO TopUpRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_ONE_MEMBER_NO);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TopUpRecordVO = new TopUpRecordVO();
				TopUpRecordVO.setTop_up_record_no(rs.getString("TOP_UP_RECORD_NO"));
				TopUpRecordVO.setMember_no(rs.getString("MEMBER_NO"));
				TopUpRecordVO.setRecord_time(rs.getTimestamp("RECORD_TIME"));
				TopUpRecordVO.setMoney_record(rs.getInt("MONEY_RECORD"));
				list.add(TopUpRecordVO);
}
			
		} catch (SQLException e) {
			throw new RuntimeException("A findByPrimaryKey error occured. " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return list;
	}
	
	@Override
	public void insert(TopUpRecordVO TopUpRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, userid, passwd);
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, TopUpRecordVO.getMember_no());
			pstmt.setTimestamp(2, TopUpRecordVO.getRecord_time());
			pstmt.setInt(3, TopUpRecordVO.getMoney_record());
			pstmt.executeQuery();
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException(" insert error . " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}//finally end
	}
	
	@Override
	public void update(TopUpRecordVO TopUpRecordVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, TopUpRecordVO.getMember_no());
			pstmt.setTimestamp(2, TopUpRecordVO.getRecord_time());
			pstmt.setInt(3, TopUpRecordVO.getMoney_record());
			pstmt.setString(4, TopUpRecordVO.getTop_up_record_no());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException("CoachDao insert error . " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String  top_up_record_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url ,userid, passwd );
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, top_up_record_no);
			pstmt.executeQuery();
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException("CoachDao delete error . " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public TopUpRecordVO findByPrimaryKey(String top_up_record_no) {
		TopUpRecordVO TopUpRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, top_up_record_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TopUpRecordVO = new TopUpRecordVO();
				TopUpRecordVO.setTop_up_record_no(rs.getString("TOP_UP_RECORD_NO"));
				TopUpRecordVO.setMember_no(rs.getString("MEMBER_NO"));
				TopUpRecordVO.setRecord_time(rs.getTimestamp("RECORD_TIME"));
				TopUpRecordVO.setMoney_record(rs.getInt("MONEY_RECORD"));
}
			
		} catch (SQLException e) {
			throw new RuntimeException("A findByPrimaryKey error occured. " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return TopUpRecordVO;
	}
	
	@Override
	public List<TopUpRecordVO> getAll() {
		List<TopUpRecordVO> list = new ArrayList<TopUpRecordVO>();
		TopUpRecordVO TopUpRecordVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TopUpRecordVO = new TopUpRecordVO();
				TopUpRecordVO.setTop_up_record_no(rs.getString("TOP_UP_RECORD_NO"));
				TopUpRecordVO.setMember_no(rs.getString("MEMBER_NO"));
				TopUpRecordVO.setRecord_time(rs.getTimestamp("RECORD_TIME"));
				TopUpRecordVO.setMoney_record(rs.getInt("MONEY_RECORD"));
				list.add(TopUpRecordVO);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
